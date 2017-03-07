	$(document).ready(function() {
		bindEvents();
		var $table = $('#tableIpsecList');
		showWanList();
		showIpsecList();
		
	});
	
	function bindEvents() {
		$('.btnSubmit').bind({
			click : submitInternetSetting
		})
		
		$('.btnCreateWan').bind({
			click : createWanDialog
		})
		
		$('.btnCreateIpsec').bind({
			click : createIpsecDialog
		})
	
	}
	
	function isBlank(str) {
	    return (!str || /^\s*$/.test(str));
	}
	
	function showPortList(){
		$('#tablePortList').bootstrapTable({
			url : 'showPortList',
			columns : [
			           	{
			           		field : 'portName',
			           		title : 'Port名稱',
			           		halign : 'left',
			           		events: operateEditPort,
			           		formatter: operateEditPortFormatter
			           	},
			           	{
			           		field : 'portNo',
			           		title : 'Port接口',
			           		halign : 'left'
			           	}
		           	  ]
		
		});
	}
	
	window.operateEditPort = {
	        'click .editPort': function (e, value, row, index) {
	        	
	        	bootbox.confirm("<form  class='form-horizontal formEditPort'>\
									<div class='form-group'>\
										<label for='portNameEdit' class='col-sm-4 control-label'>Port 名稱</label>\
										<div class='col-sm-7'>\
											<input type='text' class='form-control' id='portNameEdit'\
											name='portNameEdit' value='"+ row.portName + "'>\
										</div>\
									</div>\
									<div class='form-group'>\
										<label for='portIpEdit' class='col-sm-4 control-label'>Port 接口</label>\
										<div class='col-sm-7'>\
											<input type='text' class='form-control' id='portIpEdit'\
											name='portIpEdit' value='"+ row.portNo + "' readOnly>\
										</div>\
									</div>\
								</form>", function(result) {
						        if(result){
						        	if (!isBlank($('#portNameEdit').val())){
							        	$.post('editPortName',{
							        		id : row.id,
							        		portName : $('#portNameEdit').val()
							        	}).success(function(data){
							        		if(data){
							        			$('#tablePortList').bootstrapTable('refresh');
							        		}else{
							        			bootbox.alert('Port接口名稱不能重覆!');
							        			return false
							        		}
							        		
							        	});
						        	}else {
						        		bootbox.alert('請輸入Port接口名稱!');
						        		return false;
						        	}
						        }
						        
					});
			        	
			 }
			    
	};
	
	function operateEditPortFormatter(value, row, index) {
        return [
            '<a class="editPort" href="javascript:void(0)" title="Edit">',
            	value,
            '</a>'
        ].join('');
        
    }

	function showWanList(){
		$('#tableWanList').bootstrapTable({
			url : 'showWanList',
			columns : [
			           	{
			           		field : 'defaultSetting',
			           		title : 'Default',
			           		align : 'center',
			           		formatter: defaulSettingFormatter
			           	},
			           	{
			           		field : 'wanName',
			           		title : 'WAN名稱',
			           		halign : 'left',
			           		events: operateEditWan,
			           		formatter: operateEditWanFormatter
			           	},
			           	{
			           		field : 'portName',
			           		title : 'Port名稱',
			           		halign : 'left'
			           	},
			           	{
			           		field : 'wanIp',
			           		title : 'WAN IP',
			           		halign : 'left'
			           	},
			         	{
			           		field : 'subnet',
			           		title : 'Subnet',
			           		halign : 'left'
			           	},
			           	{
	                        field: 'operate',
	                        title: '刪除WAN',
	                        align: 'center',
	                        events: operateDeleteWan,
	                        formatter: operateDeleteWanFormatter
	                    } 
			           	
			]
			
		});
	}
	
	function defaulSettingFormatter(value, row, index) {
		if(row.defaultSetting == 'Yes'){
			return [
		            '<i class="glyphicon glyphicon-ok"></i>',
		        ].join('');
		}
		if(row.defaultSetting == 'No'){
			return [
		        ].join('');
		}
		
	}
	
	function operateDeleteWanFormatter(value, row, index) {
        return [
            '<a class="removeWan" href="javascript:void(0)" title="Remove">',
            	'<i class="glyphicon glyphicon-trash"></i>',
            '</a>'
        ].join('');
        
    }

    window.operateDeleteWan = {
        'click .removeWan': function (e, value, row, index) {
        	

        	bootbox.confirm("確定要刪除嗎?", function(result){
        	    if (result == true){
        	    	
		        	$.post('deleteWan',{
		        		id : row.id
		        	}).success(function(data){
		        		if(data!=true){
		        			alet('Error')
		        			return false
		        		}
		    		})
		    		
		        	$('#tableWanList').bootstrapTable('remove', {
		                field: 'id',
		                values: [row.id]
		            });
        	    }
        	    		
        	});   	
		        	
        }
    
    };
    
    function createWanDialog(){
    	
    	$.get('getAllPortName').success(function(data){
    		var availablePorts = [];
    		if(data.length){
    			$(data).each(function(){
        			availablePorts.push({value:this.id,text:this.portName});
        		});
     		} else{
     			availablePorts.push({value:0,text:'Port exhausted'});
     		}
    		    	
	    	bootbox.form({
	    	    title: 'Wan 新增',
	    	    fields: {
	    	        wanName: {
	    	            label: 'WAN 名稱',
	    	            type:  'text',
	    	        },
	    	        portId: {
	    	            label: 'Port接口',
	    	            type:  'select',
	    	            options: availablePorts
	    	        },
	    	        wanIp: {
	    	            label: 'WAN IP 位置',
	    	            type:  'text',
	    	        },
	    	        subnet:{
        	        	label: 'Subnet',
        	        	type: 'text',
        	        }
	    	        
	    	    },
	    	    callback: function (values) {
	    	    	
	    	    	if(values!=null){
	    	    		if (isBlank(values.wanName) || isBlank(values.wanIp) || isBlank(values.subnet)) {
        	    			bootbox.alert("資料必須齊全");
		    	    		return false;
        	    		}
        	    		if (values.portId == null){
	    	    			bootbox.alert("必須選取Port接口");
		    	    		return false;
		    	    	}
		    	    	$.post('createNewWan',{
		    	    		wanName : values.wanName,
		    	    		portId : values.portId,
		    	    		wanIp : values.wanIp,
		    	    		subnet : values.subnet,
//		    	    		defaultGateway : values.defaultGateway
		    	    		
		    	    	}).success(function(data){
		    	    		if(data){
		    	    			$('#tableWanList').bootstrapTable('refresh');
		    	    		}else{
		    	    			bootbox.alert("新增WAN名稱不能重覆");
		    	    			return false
		    	    		}
		    	    	})
	    	    	}
	    	    }
	    	    
	    	})
	    	
    	})
    	
    }
    
    function operateEditWanFormatter(value, row, index) {
        return [
            '<a class="editWan" href="javascript:void(0)" title="Edit">',
            	value,
            '</a>'
        ].join('');
        
    }
    
    //
    window.operateEditWan = {
            'click .editWan': function (e, value, row, index) {
            	
            	$.post('getAllPortNameByWanEdit',{
            		portName : row.portName
            	}).success(function(data){
            		var availablePorts = [];
//            		var defaultSettingBoolean;
            		if(data.length){
            			$(data).each(function(){
                			availablePorts.push({value:this.id,text:this.portName});
                			
                		});
             		} else{
             			availablePorts.push({value:0,text:'Port exhausted'});
             		}
            		
            		
            	
            	bootbox.form({
            	    title: 'WAN 編輯',
            	    fields: {
            	        wanName: {
            	            label: 'WAN 名稱',
            	            type:  'text',
            	            value: row.wanName
            	        },
            	        portId: {
            	            label: 'Port接口',
            	            type:  'select',
            	            options: availablePorts
            	        },
            	        wanIp: {
            	            label: 'WAN IP 位置',
            	            type:  'text',
            	            value: row.wanIp
            	        },
            	        subnet:{
            	        	label: 'Subnet',
            	        	type: 'text',
            	        	value: row.subnet
            	        },
            	        defaultSetting: {
            	            label: 'Default setting',
            	            type: 'checkbox',
            	            value: row.defaultSetting == 'Yes' ? true : false
            	        }
            	        
            	    },
            	    callback: function (values) {
            	    	if(values!=null){
            	    		if (isBlank(values.wanName) || isBlank(values.wanIp) || isBlank(values.subnet)) {
            	    			bootbox.alert("資料必須齊全");
    		    	    		return false;
            	    		}
            	    		if (values.portId == null){
    	    	    			bootbox.alert("必須選取Port接口");
    		    	    		return false;
    		    	    	}
        	    	    	$.post('editWan',{
        	    	    		id : row.id,
        	    	    		wanName : values.wanName,
        	    	    		portId : values.portId,
        	    	    		wanIp : values.wanIp,
        	    	    		subnet : values.subnet,
        	    	    		defaultSetting : values.defaultSetting == true ? 'Yes' : 'No' 
        	    	    		
        	    	    	}).success(function(data){
        	    	    		if(data){
        	    	    			$('#tableWanList').bootstrapTable('refresh');
        	    	    		}else{
        	    	    			bootbox.alert("編輯WAN名稱不能重覆");
        	    	    			return false
        	    	    		}
        	    	    	})
            	    	}
            	    }
            	})
              })	
            }
        
    };
	
	function showIpsecList(){
		$('#tableIpsecList').bootstrapTable({
			url : 'showIpsecList',
			columns : [
			           	{
			           		field : 'ipsecName',
			           		title : 'IPsec名稱',
			           		halign : 'left',
			           		events: operateEditIpsec,
			           		formatter: operateEditIpsecFormatter
			           	},
			           	{
			           		field : 'remoteLanIp',
			           		title : 'Remote LAN IP',
			           		halign : 'left'
			           	},
			        	{
			           		field : 'remoteLanSubnet',
			           		title : 'Remote LAN Subnet',
			           		halign : 'left'
			           	},
			           	{
			           		field : 'remoteWanIp',
			           		title : 'Remote WAN IP',
			           		halign : 'left'
			           	},
			         	{
			           		field : 'preSharedKey',
			           		title : 'Pre-shared Key',
			           		halign : 'left'
			           	},
			        	{
			           		field : 'wanName',
			           		title : 'WAN Name',
			           		halign : 'left'
			           	},
			           	{
	                        field: 'operate',
	                        title: '刪除IPsec',
	                        align: 'center',
	                        events: operateDeleteIpsec,
	                        formatter: operateDeleteIpsecFormatter
	                    } 
			           	
			
			]
			
		});
		
	}
	
	function operateEditIpsecFormatter(value, row, index) {
        return [
            '<a class="editIpsec" href="javascript:void(0)" title="Edit">',
            	value,
            '</a>'
        ].join('');
        
    }
	
	window.operateEditIpsec = {
	        'click .editIpsec': function (e, value, row, index) {
	        	
	        	$.get('getAllWanNames').success(function(data){
	        		var availableWanNames = [];
	    			if(data.length){
	    				$(data).each(function(){
	    					if (this.defaultSetting == 'Yes'){
	    						availableWanNames.push({value:this.id, text:'預設: '+this.wanName});
	    					}else{
	    						availableWanNames.push({value:this.id, text:this.wanName});
	    					}
	    				})
	    			} else{
	    				availableWanNames.push({value:null, text:'No more WAN(Do not select this!)'});
	    			}
	        	
	    			bootbox.form({
	    	    	    title: 'IPsec 新增',
	    	    	    fields: {
	    	    	        ipsecName: {
	    	    	            label: 'IPsec Name',
	    	    	            type:  'text',
	    	    	            value: row.ipsecName
	    	    	        },
	    	    	        remoteLanIp: {
	    	    	            label: 'Remote LAN IP',
	    	    	            type:  'text',
	    	    	            value: row.remoteLanIp
	    	    	        },
	    	    	        remoteLanSubnet:{
	    	    	        	label: 'Remote LAN Subnet',
	    	    	        	type: 'text',
	    	    	        	value: row.remoteLanSubnet
	    	    	        },
	    	    	        remoteWanIp: {
	    	    	            label: 'Remote WAN IP',
	    	    	            type:  'text',
	    	    	            value: row.remoteWanIp
	    	    	        },
	    	    	        preSharedKey: {
	    	    	            label: 'Pre-shared Key',
	    	    	            type:  'text',
	    	    	            value: row.preSharedKey
	    	    	        },
	    	    	        wanId: {
	    	    	            label: 'WAN name',
	    	    	            type:  'select',
	    	    	            options: availableWanNames
	    	    	        }
	    	    	        
	    	    	    },
	    				callback: function(values) {
					    	
					        if(values != null){
					        	if (isBlank(values.ipsecName) || isBlank(values.remoteLanIp) || isBlank(values.remoteLanSubnet) || isBlank(values.remoteWanIp) || 
					        			isBlank(values.preSharedKey) || values.wanId == null){
									bootbox.alert("資料必須輸入完整");
									return false;
								}
					        	
					        	$.post('editIpsec',{
					        		id : row.id,
					        		ipsecName : values.ipsecName,
					        		remoteLanIp : values.remoteLanIp,
					        		remoteLanSubnet : values.remoteLanSubnet,
					        		remoteWanIp : values.remoteWanIp,
					        		preSharedKey : values.preSharedKey,
					        		wanId : values.wanId,
					        	}).success(function(data){
					        		
					        		if(data){
					        			$('#tableIpsecList').bootstrapTable('refresh');
					        		}else{
					        			bootbox.alert("編輯IPsec名稱不能重覆");
					        			return false
					        		}
					        		
					        	});
					        }
	    				}
					        
				  });
	        });
	        	
	        }
	    
	    };
	
	
	function operateDeleteIpsecFormatter(value, row, index) {
        return [
            '<a class="removeIpsec" href="javascript:void(0)" title="Remove">',
            	'<i class="glyphicon glyphicon-trash"></i>',
            '</a>'
        ].join('');
        
    }

    window.operateDeleteIpsec = {
        'click .removeIpsec': function (e, value, row, index) {
        	
        	bootbox.confirm("確定要刪除嗎?", function(result){
        		if (result == true){
		        	$.post('deleteIpsec',{
		        		id : row.id
		        	}).success(function(data){
		        		if(data!=true){
		        			alet('Error')
		        			return false
		        		}
		    		})
		    		
		        	$('#tableIpsecList').bootstrapTable('remove', {
		                field: 'id',
		                values: [row.id]
		            });
		        	
        		}
        		
        	});	
		        	
        }
    
    };
	
	function createIpsecDialog(){
		
		$.get('getAllWanNames').success(function(data){
			var availableWanNames = [];
			if(data.length){
				$(data).each(function(){
					if (this.defaultSetting == 'Yes'){
						availableWanNames.push({value:this.id, text:'預設: '+this.wanName});
					}else{
						availableWanNames.push({value:this.id, text:this.wanName});
					}
				})
			} else{
				availableWanNames.push({value:null, text:'No more WAN(Do not select this!)'});
			}
		
			bootbox.form({
	    	    title: 'IPsec 新增',
	    	    fields: {
	    	        ipsecName: {
	    	            label: 'IPsec Name',
	    	            type:  'text'
	    	        },
	    	        remoteLanIp: {
	    	            label: 'Remote LAN IP',
	    	            type:  'text'
	    	        },
	    	        remoteLanSubnet:{
	    	        	label: 'Remote LAN Subnet',
	    	        	type: 'text'
	    	        },
	    	        remoteWanIp: {
	    	            label: 'Remote WAN IP',
	    	            type:  'text'
	    	        },
	    	        preSharedKey: {
	    	            label: 'Pre-shared Key',
	    	            type:  'text'
	    	        },
	    	        wanId: {
	    	            label: 'WAN name',
	    	            type:  'select',
	    	            options: availableWanNames
	    	        }
	    	        
	    	    },
				callback: function(values) {
					
				        if(values!=null){
				        	
				        	if (isBlank(values.ipsecName) || isBlank(values.remoteLanIp) || isBlank(values.remoteLanSubnet) || isBlank(values.remoteWanIp) || 
				        			isBlank(values.preSharedKey) || values.wanId == null){
								bootbox.alert("資料必須輸入完整");
								return false;
							}
				        	
				        	$.post('createNewIpsec',{
				        		ipsecName : values.ipsecName,
				        		remoteLanIp : values.remoteLanIp,
				        		remoteLanSubnet : values.remoteLanSubnet,
				        		remoteWanIp : values.remoteWanIp,
				        		preSharedKey : values.preSharedKey,
				        		wanId : values.wanId,
				        	}).success(function(data){
				        		if(data){
				        			$('#tableIpsecList').bootstrapTable('refresh');
				        		}else{
				        			bootbox.alert("新增IPsec名稱不能重覆");
				        			return false
				        		}
				        		
				        	});
				        }
				}
				        
			})  
		})
		
		
	}
	
	function submitInternetSetting() {
		
		$('.btnSubmit').validator();
		
		var checkboxPortIdArray = new Array();
		var chechboxLanPortId1 = $('#lanPortMutiIdCheckbox1').prop('checked')
		var chechboxLanPortId2 = $('#lanPortMutiIdCheckbox2').prop('checked')
		var chechboxLanPortId3 = $('#lanPortMutiIdCheckbox3').prop('checked')
		var chechboxLanPortId4 = $('#lanPortMutiIdCheckbox4').prop('checked')
		var chechboxLanPortId5 = $('#lanPortMutiIdCheckbox5').prop('checked')
		var chechboxLanPortId6 = $('#lanPortMutiIdCheckbox6').prop('checked')
		
		if (chechboxLanPortId1){
			checkboxPortIdArray.push(parseInt($('#lanPortMutiIdCheckbox1').val()))
		}
		if (chechboxLanPortId2){
			checkboxPortIdArray.push(parseInt($('#lanPortMutiIdCheckbox2').val()))
		}
		if (chechboxLanPortId3){
			checkboxPortIdArray.push(parseInt($('#lanPortMutiIdCheckbox3').val()))
		}
		if (chechboxLanPortId4){
			checkboxPortIdArray.push(parseInt($('#lanPortMutiIdCheckbox4').val()))
		}
		if (chechboxLanPortId5){
			checkboxPortIdArray.push(parseInt($('#lanPortMutiIdCheckbox5').val()))
		}
		if (chechboxLanPortId6){
			checkboxPortIdArray.push(parseInt($('#lanPortMutiIdCheckbox6').val()))
		}
		if(checkboxPortIdArray.length == 0 ){
			bootbox.alert('你必須設定LAN的Port接口')
			setInterval(redirect, 1500);
			function redirect() {
				location.href = 'index';
			}
			return false
		}
		
		$.post('submitInfo', {
			
			//DHCP
			defaultGatewayForDhcp : $('input[name="defaultGatewayForDhcp"]').val(),
			dhcpDns1 : $('input[name="dhcpDns1"]').val(),
			startIP : $('input[name="startIP"]').val(),
			endIP : $('input[name="endIP"]').val(),
			
			//LAN
			lanIp : $('input[name="lanIp"]').val(),
			lanSubnetMask : $('input[name="lanSubnetMask"]').val(),
			lanPortMutiId : $('input[name="lanPortMutiId"]').val(),
			checkboxPortIdArray : checkboxPortIdArray

		}).success(function(data){
			if(data){
	    			bootbox.alert("資料成功更新!");
			}
			else{
				alert('Error')
				return false
			}
			
		})

	}
