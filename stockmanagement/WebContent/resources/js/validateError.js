var error_list = new Array();
var error_message = "";
var result_message = "";

var loadingDialog;

function setErrorMessage(obj_desc, error_message){   
	var error_index = error_list.length;
	error_list[error_index] = messageFormat(obj_desc, error_message);
}

function messageFormat(obj_desc, error_message){
	result_message  =  "["+obj_desc+"]"+ "  :  "+error_message;
	
	return result_message;
}

//**** validate custom ***..
function validateNotNull(obj_name, obj_desc){ 				// validate_notnull 	
	var obj = document.getElementById(obj_name);
	if(obj!=null){
		var value = trim(obj.value);	
		if (value == ""){  
			error_message = document.getElementById("validate_notnull").value;				
			setErrorMessage(obj_desc, error_message);
		}
	}
}		

function validateEmail(obj_name, obj_desc){
	var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	var obj = document.getElementById(obj_name);
	if(obj!=null){
		var value = trim(obj.value);	
		if (value == ""){
			error_message = document.getElementById("validate_notnull").value;				
		}else if(!filter.test(value)){
			error_message = document.getElementById("validate_email").value;	
		}
		
		setErrorMessage(obj_desc, error_message);
		
	}
}

/***** validate time format DD/MM/YYYY example "29/06/2014" ****/
function validateDateFormat_DDMMYYYY(obj_name, obj_desc){
	var filter = /^(0[1-9]|[12][0-9]|3[01])\/(0[1-9]|1[012])\/[0-9]{4}/;
	var dateValue = document.getElementById(obj_name).value;
	
	if(dateValue!="" && !dateValue.match(filter)){
		error_message = document.getElementById("validate_dateformat_ddMMyyyy").value;
		setErrorMessage(obj_desc, error_message);
	}
	
}

/***** validate time format hh:mm example "14:09" ****/
function validateTimeFormat_HHmm(obj_name, obj_desc){ 			
	var filter = /^\s*([01]?\d|2[0-3]):?([0-5]\d)\s*$/;
	var result = false, checkTime;
	var timeValue = document.getElementById(obj_name);
	
	if ((checkTime = timeValue.value.match(filter))) {
		result = (checkTime[1].length == 2 ? "" : "0") + checkTime[1] + ":" + checkTime[2];
	}
	
	if(!result && timeValue.value!=""){
		error_message = document.getElementById("validate_timeformat_HHmm").value;
		setErrorMessage(obj_desc, error_message);
	}
}


//**** display message ****//
function displayErrorMessage(){
	var divErrorMessage = document.getElementById('errorMessage');
	var domErrorMessage = "";
	divErrorMessage.innerHTML = "";
	
	if(error_list.length>0){
		domErrorMessage += '<div class="ui-messages-error ui-corner-all">';
		domErrorMessage += '<a id="showError" href="#" class="ui-messages-close" onclick="$(this).parent().slideUp();return false;"><span class="ui-icon ui-icon-close"></span></a><span class="ui-messages-error-icon"></span>';
		domErrorMessage += '<ul>';
	
		for(var i=0; i<error_list.length; i++){
			domErrorMessage += '<li style="text-align: left;">';
			domErrorMessage += '<span class="ui-messages-error-summary">';
			domErrorMessage += error_list[i];
			domErrorMessage += '</span>';
			domErrorMessage += '</li>';
		}
			
		domErrorMessage += '</ul>';
		domErrorMessage += '</div>';
		divErrorMessage.innerHTML = domErrorMessage;
		error_list = new Array();
		
		return false;
	}
	
	return true;
}


function displayErrorMessage(errorMessage){
	var divErrorMessage = document.getElementById(errorMessage);
	var domErrorMessage = "";
	divErrorMessage.innerHTML = "";
	
	if(error_list.length>0){
		domErrorMessage += '<div class="ui-messages-error ui-corner-all">';
		domErrorMessage += '<a id="showError" href="#" class="ui-messages-close" onclick="$(this).parent().slideUp();return false;"><span class="ui-icon ui-icon-close"></span></a><span class="ui-messages-error-icon"></span>';
		domErrorMessage += '<ul>';
	
		for(var i=0; i<error_list.length; i++){
			domErrorMessage += '<li style="text-align: left;">';
			domErrorMessage += '<span class="ui-messages-error-summary">';
			domErrorMessage += error_list[i];
			domErrorMessage += '</span>';
			domErrorMessage += '</li>';
		}
			
		domErrorMessage += '</ul>';
		domErrorMessage += '</div>';
		divErrorMessage.innerHTML = domErrorMessage;
		error_list = new Array();
		
		return false;
	}
	
	return true;
}






