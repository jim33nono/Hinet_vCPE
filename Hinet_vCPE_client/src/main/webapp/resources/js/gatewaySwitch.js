$(document).ready(function() {
	bindEvents();
	showGatewaySwitchList();
	
})
	
function bindEvents() {
	$('.btnCreateGatewaySwitch').bind({
		click : createGatewaySwitchDialog
	})
	
}

function isBlank(str) {
    return (!str || /^\s*$/.test(str));
}

function showGatewaySwitchList(){
	$('#tableGatewaySwitchList').bootstrapTable({
		url : 'showGatewaySwitchList',
		columns : [
		           
		           	{
		           		field : 'destinationIp',
		           		title : '目的地IP:',
		           		halign : 'left',
		           		events: operateEditGatewaySwitch,
		           		formatter: operateEditGatewaySwitchFormatter
		           	},
		           	{
		           		field : 'subnet',
		           		title : 'Subnet:',
		           		halign :'left'
		           	},
		           	{
		           		field : 'primaryCircuit',
		           		title : '主要線路:',
		           		halign : 'left'
		           	},
		           	{
		           		field : 'secondaryCircuit',
		           		title : '次要線路:',
		           		halign : 'left'
		           	},
		           	{
		           		field : 'msDelay',
		           		title : '切換條件(毫秒):',
		           		halign : 'left'
		           	},
		           	{
                        field: 'operate',
                        title: '刪除',
                        align: 'center',
                        events: operateDeleteGatewaySwitch,
                        formatter: operateDeleteGatewaySwitchFormatter
                        
                    } 
	           	  ]
	
	});
	
	
}

function operateDeleteGatewaySwitchFormatter(value, row, index) {
    return [
        '<a class="removeGatewaySwitch" href="javascript:void(0)" title="Remove">',
        	'<i class="glyphicon glyphicon-trash"></i>',
        '</a>'
    ].join('');
    
}

window.operateDeleteGatewaySwitch = {
    'click .removeGatewaySwitch': function (e, value, row, index) {
    	

    	bootbox.confirm("確定要刪除嗎?", function(result){
    	    if (result == true){
		    	$.post('deleteGatewaySwitch',{
		    		id : row.id
		    	}).success(function(data){
		    		if(data!=true){
		    			alet('Error')
		    			return false
		    		}
		    		
				})
				
		    	$('#tableGatewaySwitchList').bootstrapTable('remove', {
		            field: 'id',
		            values: [row.id]
		        });
		    	
    	    }
    		
    	});	
    }

};

function createGatewaySwitchDialog(){
	
	$.get('getPrimaryAndSecondaryCircuitName').success(function(data){
		var availableWanAndIpsec = [];
		if(data.length){
			$(data).each(function(){
				availableWanAndIpsec.push({value:this, text: this});
    		});
 		}else{
 			availableWanAndIpsec.push({value:"Wan and IPsec exhausted",text:'Wan and IPsec exhausted'});
 		}
		
		bootbox.form({
    	    title: 'Gateway Switch 新增',
    	    fields: {
    	        destinationIp: {
    	            label: '目的地IP:',
    	            type:  'text',
    	        },
    	        subnet: {
    	        	label: 'Subnet:',
    	        	type: 'text'
    	        },
    	        primaryCircuitName: {
    	            label: '主要線路:',
    	            type:  'select',
    	            options: availableWanAndIpsec
    	        },
    	        secondaryCircuitName: {
    	            label: '次要線路:',
    	            type:  'select',
    	            options: availableWanAndIpsec
    	        },
    	        msDelay: {
    	            label: '切換條件(毫秒):',
    	            type:  'text',
    	        }
    	        
    	    },
    	    callback: function (values) {
    	    	if(values!=null){
    	    		if (isBlank(values.destinationIp) || values.primaryCircuitName == null || values.secondaryCircuitName == null || isBlank(values.msDelay) ){
    	    			bootbox.alert("資料必須填齊全");
	    	    		return false;
	    	    	}
    	    		if (values.primaryCircuitName == values.secondaryCircuitName){
    	    			bootbox.alert("主要、次要線路不能相同");
    	    			return false;
    	    		}
    	    		if (!$.isNumeric(values.msDelay)) {
    	    			bootbox.alert("切換時間必須是數字");
    	    			return false;
    	    		}
    	    		
	    	    	$.post('createGatewaySwitch',{
	    	    		destinationIp : values.destinationIp,
	    	    		subnet : values.subnet,
	    	    		msDelay : values.msDelay,
	    	    		primaryCircuitName : values.primaryCircuitName,
	    	    		secondaryCircuitName : values.secondaryCircuitName,
	    	    		
	    	    	}).success(function(data){
	    	    		if(data){
	    	    			$('#tableGatewaySwitchList').bootstrapTable('refresh');
	    	    		}else{
	    	    			alert('Error!')
	    	    			return false
	    	    		}
	    	    	})
    	    	}
    	    }
    	    
    	})
    	
	})
	
}

function operateEditGatewaySwitchFormatter(value, row, index) {
    return [
        '<a class="editGatewaySwitch" href="javascript:void(0)" title="Edit">',
        	value,
        '</a>'
    ].join('');
    
}

window.operateEditGatewaySwitch = {
        'click .editGatewaySwitch': function (e, value, row, index) {
        	
        	$.get('getPrimaryAndSecondaryCircuitName').success(function(data){
        		var availableWanAndIpsec = [];
        		if(data.length){
        			$(data).each(function(){
        				availableWanAndIpsec.push({value:this, text: this});
            		});
         		}else{
         			availableWanAndIpsec.push({value:"Wan and IPsec exhausted",text:'Wan and IPsec exhausted'});
         		}
        		
        		bootbox.form({
            	    title: 'Gateway Switch 編輯',
            	    fields: {
            	        destinationIp: {
            	            label: '目的地IP:',
            	            type:  'text',
            	            value: row.destinationIp
            	        },
            	        subnet: {
            	        	label: 'Subnet:',
            	        	type: 'text',
            	        	value: row.subnet
            	        },
            	        primaryCircuitName: {
            	            label: '主要線路:',
            	            type:  'select',
            	            options: availableWanAndIpsec
            	        },
            	        secondaryCircuitName: {
            	            label: '次要線路:',
            	            type:  'select',
            	            options: availableWanAndIpsec
            	        },
            	        msDelay: {
            	            label: '切換條件(毫秒):',
            	            type:  'text',
            	            value: row.msDelay
            	        }
            	        
            	    },
            	    callback: function (values) {
            	    	if(values!=null){
            	    		if (isBlank(values.destinationIp) || isBlank(values.subnet) || values.primaryCircuitName == null || values.secondaryCircuitName == null || isBlank(values.msDelay)){
            	    			bootbox.alert("資料必須填齊全");
        	    	    		return false;
        	    	    	}
            	    		if (values.primaryCircuitName == values.secondaryCircuitName){
            	    			bootbox.alert("主要、次要線路不能相同");
            	    			return false;
            	    		}
            	    		if (!$.isNumeric(values.msDelay)) {
            	    			bootbox.alert("切換時間必須是數字");
            	    			return false;
            	    		}
            	    		
        	    	    	$.post('editGatewaySwitch',{
        	    	    		id : row.id,
        	    	    		destinationIp : values.destinationIp,
        	    	    		subnet : values.subnet,
        	    	    		msDelay : values.msDelay,
        	    	    		primaryCircuitName : values.primaryCircuitName,
        	    	    		secondaryCircuitName : values.secondaryCircuitName,
        	    	    		
        	    	    	}).success(function(data){
        	    	    		if(data){
        	    	    			$('#tableGatewaySwitchList').bootstrapTable('refresh');
        	    	    		}else{
        	    	    			alert('Error!')
        	    	    			return false
        	    	    		}
        	    	    	})
            	    	}
            	    }
            	    
            	})
            	
        	})
        
        }
    
};
