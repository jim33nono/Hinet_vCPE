	$(document).ready(function() {
		bindEvents();
		var $table = $('#tableIpsecList');
		showPortList();
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
			           		field : 'defaultGateway',
			           		title : 'Default Gateway',
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
            	'<i class="glyphicon glyphicon-remove"></i>',
            '</a>'
        ].join('');
        
    }

    window.operateDeleteWan = {
        'click .removeWan': function (e, value, row, index) {
        	
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
//	    	            value: 'WAN name'
	    	        },
	    	        portId: {
	    	            label: 'Port接口',
	    	            type:  'select',
	    	            options: availablePorts
	    	        },
	    	        wanIp: {
	    	            label: 'WAN IP 位置',
	    	            type:  'text',
//	    	            value: 'WAN IP'
	    	        },
	    	        defaultGateway: {
	    	            label: 'Default gateway 位置',
	    	            type:  'text',
//	    	            value: 'Default gateway1'
	    	        }
	    	        
	    	    },
	    	    callback: function (values) {
	    	    	
	    	    	if(values!=null){
	    	    		if (values.wanName == '' || values.portId == null || values.wanIp == '' || values.defaultGateway == ''){
	    	    			bootbox.alert("資料必須填齊全");
		    	    		return false;
		    	    	}
		    	    	$.post('createNewWan',{
		    	    		wanName : values.wanName,
		    	    		portId : values.portId,
		    	    		wanIp : values.wanIp,
		    	    		defaultGateway : values.defaultGateway
		    	    		
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
            	        defaultGateway: {
            	            label: 'Default gateway 位置',
            	            type:  'text',
            	            value: row.defaultGateway
            	        },
            	        defaultSetting: {
            	            label: 'Default setting',
            	            type: 'checkbox',
            	            value: row.defaultSetting == 'Yes' ? true : false
            	        }
            	        
            	    },
            	    callback: function (values) {
            	    	if(values!=null){
            	    		if (values.portId == null){
    	    	    			bootbox.alert("必須選取Port接口");
    		    	    		return false;
    		    	    	}
        	    	    	$.post('editWan',{
        	    	    		id : row.id,
        	    	    		wanName : values.wanName,
        	    	    		portId : values.portId,
        	    	    		wanIp : values.wanIp,
        	    	    		defaultGateway : values.defaultGateway,
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
			           		field : 'ip',
			           		title : 'IPsec IP',
			           		halign : 'left'
			           	},
			           	{
			           		field : 'gateway',
			           		title : 'Gateway',
			           		halign : 'left'
			           	},
			           	{
			           		field : 'allowSubnet',
			           		title : 'Allowed Subnet',
			           		halign : 'left'
			           	},
			           	{
			           		field : 'siteToSiteIp',
			           		title : 'Site-to-site IP',
			           		halign : 'left'
			           	},
			           	{
			           		field : 'preSharedKey',
			           		title : 'Pre-shared Key',
			           		halign : 'left'
			           	},
			           	{
			           		field : 'gatewayInterface',
			           		title : 'Gateway Interface',
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
	        	
	        	bootbox.confirm("<form  class='form-horizontal formEditIpsec'>\
						<div class='form-group'>\
							<label for='ipsecNameEdit' class='col-sm-4 control-label'>IPsec 名稱</label>\
							<div class='col-sm-7 '>\
								<input type='text' class='form-control' id='ipsecNameEdit'\
								name='ipsecNameEdit' value='"+ row.ipsecName + "'>\
							</div>\
						</div>\
						<div class='form-group'>\
							<label for='ipsecIpEdit' class='col-sm-4 control-label'>IPsec IP</label>\
							<div class='col-sm-7 '>\
								<input type='text' class='form-control' id='ipsecIpEdit'\
								name='ipsecIpEdit' value='"+ row.ip + "'>\
							</div>\
						</div>\
						<div class='form-group'>\
							<label for='ipsecGatewayEdit' class='col-sm-4 control-label'>Gateway</label>\
							<div class='col-sm-7 '>\
								<input type='text' class='form-control' id='ipsecGatewayEdit'\
								name='ipsecGatewayEdit' value='"+ row.gateway + "'>\
							</div>\
						</div>\
						<div class='form-group'>\
							<label for='ipsecAllowSubnetEdit' class='col-sm-4 control-label'>Allowed Subnet</label>\
							<div class='col-sm-7 '>\
								<input type='text' class='form-control' id='ipsecAllowSubnetEdit'\
								name='ipsecAllowSubnetEdit' value='"+ row.allowSubnet + "'>\
							</div>\
						</div>\
						<div class='form-group'>\
							<label for='ipsecSiteToSiteIpEdit' class='col-sm-4 control-label'>Site-to-site IP</label>\
							<div class='col-sm-7 '>\
								<input type='text' class='form-control' id='ipsecSiteToSiteIpEdit'\
								name='ipsecSiteToSiteIpEdit' value='"+ row.siteToSiteIp + "'>\
							</div>\
						</div>\
						<div class='form-group'>\
							<label for='ipsecPreSharedKeyEdit' class='col-sm-4 control-label'>Pre-shared Key</label>\
							<div class='col-sm-7 '>\
								<input type='text' class='form-control' id='ipsecPreSharedKeyEdit'\
								name='ipsecPreSharedKeyEdit' value='"+ row.preSharedKey + "'>\
							</div>\
						</div>\
						<div class='form-group'>\
							<label for='ipsecGatewayInterfaceEdit' class='col-sm-4 control-label'>Gateway Interface</label>\
							<div class='col-sm-7 '>\
								<input type='text' class='form-control' id='ipsecGatewayInterfaceEdit'\
								name='ipsecGatewayInterfaceEdit' value='"+ row.gatewayInterface + "'>\
							</div>\
						</div>\
				    </form>", function(result) {
				    	
				        if(result){
				        	$.post('editIpsec',{
				        		id : row.id,
				        		ipsecName : $('#ipsecNameEdit').val(),
				        		ip : $('#ipsecIpEdit').val(),
				        		gateway : $('#ipsecGatewayEdit').val(),
				        		allowSubnet : $('#ipsecAllowSubnetEdit').val(),
				        		siteToSiteIp : $('#ipsecSiteToSiteIpEdit').val(),
				        		preSharedKey : $('#ipsecPreSharedKeyEdit').val(),
				        		gatewayInterface : $('#ipsecGatewayInterfaceEdit').val()
				        	}).success(function(data){
				        		
				        		if(data){
				        			$('#tableIpsecList').bootstrapTable('refresh');
				        		}else{
				        			bootbox.alert("編輯IPsec名稱不能重覆");
				        			return false
				        		}
				        		
				        	});
				        }
				        
			});
	        	
	        }
	    
	    };
	
	
	function operateDeleteIpsecFormatter(value, row, index) {
        return [
            '<a class="removeIpsec" href="javascript:void(0)" title="Remove">',
            	'<i class="glyphicon glyphicon-remove"></i>',
            '</a>'
        ].join('');
        
    }

    window.operateDeleteIpsec = {
        'click .removeIpsec': function (e, value, row, index) {
        	
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
    
    };
	
	function createIpsecDialog(){
//		$('#ipsecGatewayInterface').val('test')
		bootbox.confirm("<form  class='form-horizontal formCreateIpsec' >\
					<div class='form-group'>\
						<label for='ipsecName' class='col-sm-4 control-label'>IPsec 名稱</label>\
						<div class='col-sm-7'>\
							<input type='text' class='form-control' id='ipsecName'\
							name='ipsecName' placeholder='請輸入IPsec名稱'>\
						</div>\
					</div>\
					<div class='form-group'>\
						<label for='ipsecIp' class='col-sm-4 control-label'>IPsec IP</label>\
						<div class='col-sm-7'>\
							<input type='text' class='form-control' id='ipsecIp'\
							name='ip' placeholder='請輸入IP'>\
						</div>\
					</div>\
					<div class='form-group'>\
						<label for='ipsecGateway' class='col-sm-4 control-label'>Gateway</label>\
						<div class='col-sm-7'>\
							<input type='text' class='form-control' id='ipsecGateway'\
							name='gateway' placeholder='請輸入Gateway IP'>\
						</div>\
					</div>\
					<div class='form-group'>\
						<label for='ipsecAllowSubnet' class='col-sm-4 control-label'>Allowed Subnet</label>\
						<div class='col-sm-7'>\
							<input type='text' class='form-control' id='ipsecAllowSubnet'\
							name='allowSubnet' placeholder='請輸入Allowed Subnet IP'>\
						</div>\
					</div>\
					<div class='form-group'>\
						<label for='ipsecSiteToSiteIp' class='col-sm-4 control-label'>Site-to-site IP</label>\
						<div class='col-sm-7'>\
							<input type='text' class='form-control' id='ipsecSiteToSiteIp'\
							name='siteToSiteIp' placeholder='請輸入Site-to-site IP'>\
						</div>\
					</div>\
					<div class='form-group'>\
						<label for='ipsecPreSharedKey' class='col-sm-4 control-label'>Pre-shared Key</label>\
						<div class='col-sm-7'>\
							<input type='text' class='form-control' id='ipsecPreSharedKey'\
							name='preSharedKey' placeholder='請輸入Pre-shared Key'>\
						</div>\
					</div>\
					<div class='form-group'>\
						<label for='ipsecGatewayInterface' class='col-sm-4 control-label'>Gateway Interface</label>\
						<div class='col-sm-7'>\
							<input type='text' class='form-control' id='ipsecGatewayInterface'\
							name='gatewayInterface' placeholder='請輸入Gateway Interface'>\
						</div>\
					</div>\
			    </form>", function(result) {
			    	
			    	
			        if(result){
			        	$.post('createNewIpsec',{
			        		ipsecName : $('#ipsecName').val(),
			        		ip : $('#ipsecIp').val(),
			        		gateway : $('#ipsecGateway').val(),
			        		allowSubnet : $('#ipsecAllowSubnet').val(),
			        		siteToSiteIp : $('#ipsecSiteToSiteIp').val(),
			        		preSharedKey : $('#ipsecPreSharedKey').val(),
			        		gatewayInterface : $('#ipsecGatewayInterface').val()
			        	}).success(function(data){
			        		if(data){
			        			$('#tableIpsecList').bootstrapTable('refresh');
			        		}else{
			        			bootbox.alert("新增IPsec名稱不能重覆");
			        			return false
			        		}
			        		
			        	});
			        }
			        
		});
		
		
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
			alert('你必須設定LAN的Port')
			return false
		}
		
		$.post('submitInfo', {
			
			//DHCP
			dhcpDns1 : $('input[name="dhcpDns1"]').val(),
			dhcpDns2 : $('input[name="dhcpDns2"]').val(),
			subnet : $('input[name="subnet"]').val(),
			netmask : $('input[name="netmask"]').val(),
			startIP : $('input[name="startIP"]').val(),
			endIP : $('input[name="endIP"]').val(),
			defaultGatewayForDhcp : $('input[name="defaultGatewayForDhcp"]').val(),
			
			//LAN
			lanIp : $('input[name="lanIp"]').val(),
			lanSubnetMask : $('input[name="lanSubnetMask"]').val(),
			lanPortMutiId : $('input[name="lanPortMutiId"]').val(),
			checkboxPortIdArray : checkboxPortIdArray
			

	}).success(function(data){
		if(data){
    			bootbox.alert("資料成功更新!");
	    		return false;
		}
		else{
			alet('Error')
			return false
		}
		
	})

}
