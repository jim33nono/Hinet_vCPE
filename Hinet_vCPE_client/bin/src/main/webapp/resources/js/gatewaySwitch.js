$(document).ready(function() {
	bindEvents();
	showGatewaySwitchList();
	
})
	
function bindEvents() {
	$('.btnCreateGatewaySwitch').bind({
		click : createGatewaySwitchDialog
	})
	
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
        	'<i class="glyphicon glyphicon-remove"></i>',
        '</a>'
    ].join('');
    
}

window.operateDeleteGatewaySwitch = {
    'click .removeGatewaySwitch': function (e, value, row, index) {
    	
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
//    	            value: 'IP位置'
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
//    	            value: '5000'
    	        }
    	        
    	    },
    	    callback: function (values) {
    	    	if(values!=null){
    	    		if (values.destinationIp == '' || values.primaryCircuitName == null || values.secondaryCircuitName == null || values.msDelay == ''){
    	    			bootbox.alert("資料必須填齊全");
	    	    		return false;
	    	    	}
	    	    	$.post('createGatewaySwitch',{
	    	    		destinationIp : values.destinationIp,
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
            	    		if (values.destinationIp == '' || values.primaryCircuitName == null || values.secondaryCircuitName == null || values.msDelay == ''){
            	    			bootbox.alert("資料必須填齊全");
        	    	    		return false;
        	    	    	}
            	    		
        	    	    	$.post('editGatewaySwitch',{
        	    	    		id : row.id,
        	    	    		destinationIp : values.destinationIp,
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
