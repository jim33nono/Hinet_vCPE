$(document).ready(function() {
	bindEvents();
	showAccountManagementList();
	
})
	
function bindEvents() {
	$('.btnCreateAccountManagement').bind({
		click : createAccountManagementDialog
	})
	
}

function showAccountManagementList(){
	$('#tableAccountManagementList').bootstrapTable({
		url : 'showAccountManagementList',
		columns : [
		           
		           	{
		           		field : 'account',
		           		title : '帳號:',
		           		halign : 'left',
		           		events: operateEditAccount,
		           		formatter: operateEditAccountFormatter
		           	},
		           	{
		           		field : 'password',
		           		title : '密碼:',
		           		halign : 'left'
		           	},
		           	{
		           		field : 'macAddress',
		           		title : 'Mac address:',
		           		halign : 'left'
		           	},
		           	{
                        field: 'delete',
                        title: '刪除',
                        align: 'center',
                        events: operateDeleteAccount,
                        formatter: operateDeleteAccountFormatter
                        
                    },
                    {
                    	field: 'internetSetting',
                        title: '網路設定',
                        align: 'center',
                        events: guideToInternetSetting,
                        formatter: guideToInternetSettingFormatter
                    },
                    {
                    	field: 'gatewaySwitch',
                        title: 'Gateway切換',
                        align: 'center',
                        events: guideToGatewaySwitch,
                        formatter: guideToGatewaySwitchFormatter
                    }
	           	  ]
	
	});
	
	
}

function guideToInternetSettingFormatter(value, row, index) {
    return [
        '<a class="guideToInternetSetting" href="javascript:void(0)" title="Internet Setting">',
        	'<i class="glyphicon glyphicon-cog"></i>',
        '</a>'
    ].join('');
    
}

window.guideToInternetSetting = {
    'click .guideToInternetSetting': function (e, value, row, index) {
    	
    	$.post('guideProcess',{
    		account : row.account,
    		macAddress : row.macAddress
    	}).success(function(data){
    		if(data){
    			location.href = 'guideToInternetSettingIndex';
    		}else{
    			alet('Error')
    			return false
    		}
    		
		})
		
    }

};

function guideToGatewaySwitchFormatter(value, row, index) {
    return [
        '<a class="guideToGatewaySwitch" href="javascript:void(0)" title="Gateway Switch">',
        	'<i class="glyphicon glyphicon-sort"></i>',
        '</a>'
    ].join('');
    
}

window.guideToGatewaySwitch = {
    'click .guideToGatewaySwitch': function (e, value, row, index) {
    	
    	$.post('guideProcess',{
    		account : row.account,
    		macAddress : row.macAddress
    	}).success(function(data){
    		if(data){
    			location.href = 'guideToGatewaySwitchIndex';
//    			window.location.href = 'gatewaySwitch/index';
    		}else{
    			alet('Error')
    			return false
    		}
    		
		})
		
    }

};

function operateDeleteAccountFormatter(value, row, index) {
    return [
        '<a class="removeAccount" href="javascript:void(0)" title="Remove">',
        	'<i class="glyphicon glyphicon-trash"></i>',
        '</a>'
    ].join('');
    
}

window.operateDeleteAccount = {
    'click .removeAccount': function (e, value, row, index) {
    	
    	bootbox.confirm("確定要刪除嗎?", function(result){
    		if (result == true){
	    		$.post('deleteAccount',{
	        		id : row.id
	        	}).success(function(data){
	        		if(data!=true){
	        			alet('Error')
	        			return false
	        		}
	        		
	    		})
	    		
	    		$('#tableAccountManagementList').bootstrapTable('remove', {
	                field: 'id',
	                values: [row.id]
	            });
    		}
    		
    	});
    	
    	
    }

};

function createAccountManagementDialog(){
	
		
		bootbox.form({
    	    title: '帳號新增',
    	    fields: {
    	        account: {
    	            label: '帳號:',
    	            type:  'text'
    	        },
    	        password: {
    	            label: '密碼:',
    	            type:  'text'
    	        },
    	        macAddress: {
    	            label: 'Mac address:',
    	            type:  'text'
    	        }
    	        
    	    },
    	    callback: function (values) {
    	    	if(values!=null){
    	    		if (values.account == '' || values.password == '' || values.macAddress == ''){
    	    			bootbox.alert("資料必須填齊全");
	    	    		return false;
	    	    	}
	    	    	$.post('createAccount',{
	    	    		account : values.account,
	    	    		password : values.password,
	    	    		macAddress : values.macAddress
	    	    		
	    	    	}).success(function(data){
	    	    		if(data){
	    	    			$('#tableAccountManagementList').bootstrapTable('refresh');
	    	    		}else{
	    	    			bootbox.alert("帳號不能重覆!");
	    	    			return false
	    	    		}
	    	    	})
    	    	}
    	    }
    	    
    	})
    	
	
}

function operateEditAccountFormatter(value, row, index) {
    return [
        '<a class="editAccount" href="javascript:void(0)" title="Edit">',
        	value,
        '</a>'
    ].join('');
    
}

window.operateEditAccount = {
        'click .editAccount': function (e, value, row, index) {
        	
        		bootbox.form({
            	    title: '帳號管理編輯',
            	    fields: {
            	    	account: {
            	            label: '帳號:',
            	            type:  'text',
            	            value: row.account
            	        },
            	        password: {
            	            label: '密碼:',
            	            type:  'text',
            	            value: row.password
            	        },
            	        macAddress: {
            	            label: 'Mac Address:',
            	            type:  'text',
            	            value: row.macAddress
            	        }
            	    },
            	    callback: function (values) {
            	    	if(values!=null){
            	    		if (values.account == '' || values.password == '' || values.macAddress == ''){
            	    			bootbox.alert("資料必須填齊全");
        	    	    		return false;
        	    	    	}
            	    		
        	    	    	$.post('editAccount',{
        	    	    		id : row.id,
        	    	    		account : values.account,
        	    	    		password : values.password,
        	    	    		macAddress : values.macAddress
        	    	    		
        	    	    	}).success(function(data){
        	    	    		if(data){
        	    	    			$('#tableAccountManagementList').bootstrapTable('refresh');
        	    	    		}else{
        	    	    			bootbox.alert("帳號不能重覆!");
        	    	    			return false
        	    	    		}
        	    	    	})
            	    	}
            	    }
            	    
            	})
            	
        
        }
    
};
