$.ajaxSetup({

	beforeSend : function(xhr) {
		xhr.setRequestHeader('RequestType', "AJAX");
	}

});