function isCharAndNumber(event) {
	var keyCode = event.keyCode ? event.keyCode : event.which ? event.which : event.charCode;
	if(BrowserDetect.browser != "Firefox") {
		if (!((keyCode >= 48 && keyCode <= 57) || keyCode == 8 || keyCode == 9
				|| (keyCode >= 65 && keyCode <= 90) || (keyCode >= 97 && keyCode <= 122))) {
			return false;
		}
	}
	else { 
		if (!((keyCode >= 48 && keyCode <= 57) || keyCode == 8 || keyCode == 9 || keyCode == 37 || keyCode == 39 || keyCode == 46
				|| (keyCode >= 65 && keyCode <= 90) || (keyCode >= 97 && keyCode <= 122))) {
			return false;
		}
	}
}
 
function isCharAndNumberAndUnderscore(event) {
	// underscore = 95
	var keyCode = event.keyCode ? event.keyCode : event.which ? event.which : event.charCode;
	if(BrowserDetect.browser != "Firefox") {
		if (!((keyCode >= 48 && keyCode <= 57) || keyCode == 8 || keyCode == 9
				|| (keyCode >= 65 && keyCode <= 90) || (keyCode >= 97 && keyCode <= 122) || keyCode==95 )) {
			return false;
		}
	}
	else {
		if (!((keyCode >= 48 && keyCode <= 57) || keyCode == 8 || keyCode == 9 || keyCode == 37 || keyCode == 39 || keyCode == 46
				|| (keyCode >= 65 && keyCode <= 90) || (keyCode >= 97 && keyCode <= 122) || keyCode==95 )) {
			return false;
		}
	}
}

function isCharAndNumberAndHyphen(event) {
	// hyphen = 45
	var keyCode = event.keyCode ? event.keyCode : event.which ? event.which : event.charCode;
	if(BrowserDetect.browser != "Firefox") {
		if (!((keyCode >= 48 && keyCode <= 57) || keyCode == 8 || keyCode == 9
				|| (keyCode >= 65 && keyCode <= 90) || (keyCode >= 97 && keyCode <= 122) || keyCode==45 )) {
			return false;
		}
	}
	else {
		if (!((keyCode >= 48 && keyCode <= 57) || keyCode == 8 || keyCode == 9 || keyCode == 37 || keyCode == 39 || keyCode == 46
				|| (keyCode >= 65 && keyCode <= 90) || (keyCode >= 97 && keyCode <= 122) || keyCode==45 )) {
			return false;
		}
	}
}

/*
function name : checkBlank
Description  : Check element is blank
*/
function checkBlank(form,errNull) { 
	if(form.type == "text" || form.type == "password" || form.type == "textarea" || form.type == "file" || 
        	form.type == "hidden") {
		while('' + form.value.charAt(0) == ' ') {
			form.value = form.value.substring(1,form.value.length);
		}
		while('' + form.value.charAt(form.value.length-1) == ' ') {
			form.value = form.value.substring(0,form.value.length-1);
		}
		// Replace wording of Enter press(\r\n) to check blank string
		var tempWord = form.value;
		var index = form.value.indexOf('\r\n');
		while(index != -1) {
			form.value = form.value.replace('\r\n','');
			index = form.value.indexOf('\r\n');
		}
		if (form.value == null || form.value == "" || form.value == ' ' || form.value.length == 0) {
			if ((form.type=="text")||(form.type=="textarea")) alert(errNull);
			if (form.type=="password") alert(errNull);
			if (form.type=="file") alert(errNull); 
			if (form.type=="hidden") alert(errNull);
				form.value = tempWord;
				return false;
		}
		else {
			form.value = tempWord;
			return true;
		}
	}
	if (form.type == "select-one" || form.type == "select-multiple") {
		if (form.selectedIndex == 0 || form.selectedIndex == "none")   {
			alert(errNull);
			return false;
		} else {
			return true;
		}
	}
	if (form.type == "radio" || form.type == "checkbox") {
		var status = true;
		if (!form.checked) {
			alert(errNull);
			return false;
		} else {
			return true;
		}
	}
}

/*
function name : checkBlankNoTrim
Description  : Check element is blank and not trim
*/
function checkBlankNoTrim(form,errNull) { 
	if(form.type == "text" || form.type == "password" || form.type == "textarea" || form.type == "file" || 
        	form.type == "hidden") {
        var value = trim(form.value);
		// Replace wording of Enter press(\r\n) to check blank string
		var index = form.value.indexOf('\r\n');
		while(index != -1) {
			form.value = form.value.replace('\r\n','');
			index = form.value.indexOf('\r\n');
		}
		if (value == null || value == "" || value == ' ' || value.length == 0) {
			if ((form.type=="text")||(form.type=="textarea")) alert(errNull);
			if (form.type=="password") alert(errNull);
			if (form.type=="file") alert(errNull); 
			if (form.type=="hidden") alert(errNull);
				return false;
		}
		else {
			return true;
		}
	}
	if (form.type == "select-one" || form.type == "select-multiple") {
		if (form.selectedIndex == 0 || form.selectedIndex == "none")   {
			alert(errNull);
			return false;
		} else {
			return true;
		}
	}
	if (form.type == "radio" || form.type == "checkbox") {
		var status = true;
		if (!form.checked) {
			alert(errNull);
			return false;
		} else {
			return true;
		}
	}
}

/*
function name : isInteger
Description : Type only Number, Backspace, Tab and comma
*/
function isInteger(event) { 
	var keyCode = event.keyCode ? event.keyCode : event.which ? event.which : event.charCode;
	if (!((keyCode >= 48 && keyCode <= 57) || keyCode == 8 || keyCode == 9 || keyCode == 37 || keyCode == 39 || keyCode == 46 || keyCode == 44 )) {
		return false;
	}
	
}

/*
function name : isIntegerAndPlus
Description : Type only Number, Backspace, and Tab
*/
function isIntegerAndPlus(event) { 
	var keyCode = event.keyCode ? event.keyCode : event.which ? event.which : event.charCode; 
	if(BrowserDetect.browser != "Firefox") {
		if (!((keyCode >= 48 && keyCode <= 57) || keyCode == 8 || keyCode == 9|| keyCode == 43 ) ) {
			return false;
		}
	}
	else {
		if (!((keyCode >= 48 && keyCode <= 57) || keyCode == 8 || keyCode == 9 || keyCode == 37 || keyCode == 39 || keyCode == 46 || keyCode == 43) ) {
			return false;
		}
	}
}


/*
function name : isIntegerDate
Description : Type only Number, Backspace, Slash and Tab
*/
function isIntegerDate(event) { 
	var keyCode = event.keyCode ? event.keyCode : event.which ? event.which : event.charCode;
	if(BrowserDetect.browser != "Firefox") {
		if (!((keyCode >= 47 && keyCode <= 57) || keyCode == 8 || keyCode == 9)) {
			return false;
		}
	}
	else {
		if (!((keyCode >= 47 && keyCode <= 57) || keyCode == 8 || keyCode == 9 || keyCode == 37 || keyCode == 39 || keyCode == 46)) {
			return false;
		}
	}
}

/*
function name : isString
Description : Type only letter, Slash and full stop
*/
function isString(event) { 
	var keyCode = event.keyCode ? event.keyCode : event.which ? event.which : event.charCode;
	if (!((keyCode >= 65 && keyCode <= 90) || (keyCode >= 97 && keyCode <= 122) || keyCode == 46 || keyCode == 47 )) {
		error_message = document.getElementById("validate_string").value;		
 		alert(error_message);
 		
 		return false;
	}
	return true; 
}

/*
function name : isIntegerTime
Description : Type only Number and Colon
*/
function isIntegerTime(event) { 
	var keyCode = event.keyCode ? event.keyCode : event.which ? event.which : event.charCode;
	if (!((keyCode >= 48 && keyCode <= 57) || keyCode == 8 || keyCode == 9 || keyCode == 58 )) {
		error_message = document.getElementById("validate_time").value;		
 		alert(error_message);
 		
 		return false;
	}
	return true; 
}

/*
function name : isDecimal
Description : Type only Number, Backspace, Tab and Dot(.)
*/
function isDecimal(event) { 
	var keyCode = event.keyCode ? event.keyCode : event.which ? event.which : event.charCode;
	if(BrowserDetect.browser != "Firefox") {
		if (!((keyCode >= 48 && keyCode <= 57) || keyCode == 8 || keyCode == 9 || keyCode == 46)) {
			return false;
		}
	}
	else {
		if (!((keyCode >= 48 && keyCode <= 57) || keyCode == 8 || keyCode == 9 || keyCode == 37 || keyCode == 39 || keyCode == 46)) {
			return false;
		}
	}
}

/*
function name : checkNumeric
Description : Check value is number only
*/
function checkNumeric(numForm, errMessage) {
	if(isNaN(numForm.value)) {
		alert(errMessage);
		return false;
	}
	else {
		return true;
	}
}

/*
function name : checkDateFromTo
Description : If user input dateFrom must input dateTo too & If user input dateTo must input dateFrom too
*/
function checkDateFromTo(dateFrom, dateTo, errMessage) {
	deleteSpace(dateFrom);
	deleteSpace(dateTo);
	
	var fromVal = dateFrom.value;
	var toVal = dateTo.value;
	
	if(fromVal == "" && toVal != "") {
		alert(errMessage);
		return false;
	}
	else if(fromVal != "" && toVal == "") {
		alert(errMessage);
		return false;
	}
	else if(fromVal == "" && toVal == "") {
		alert(errMessage);
		return false;
	}
	return true;
}

/*
function name : checkEmail
Description : check email format
*/
function checkEmail(emailForm, errMessage) {
	var email = emailForm.value;
	/*
	if (email.search(/^\w+([0-9a-zA-Z]([\-\.\w]*[0-9a-zA-Z])*\@([0-9a-zA-Z][\-\w]*[0-9a-zA-Z]\.)+[a-zA-Z]{2,9})+$/) == -1) {
		alert(errMessage);
		return false;
	}
	*/
	
	var whiteSpaceExp=/\s/g;
	var filter = /^([a-zA-Z0-9_.-])+@(([a-zA-Z0-9-])+.)+([a-zA-Z0-9]{2,4})+$/;
	
	if(whiteSpaceExp.test(email)) {
		alert(errMessage);
		//emailForm.focus;
		return false;
		
	} else if (!filter.test(email)) {
		alert(errMessage);
		//emailForm.focus;
		return false;
	}
	else {
		var value = email.substring(email.indexOf("@") + 1, email.length);
		var result = hasSymbol(value);
		if(result == true) {
			alert(errMessage);
			return false;
		}
	}
	
	return true;
}

/*
function name : checkLength
Description : Check size of element
*/
function checkLength(form,length,errMessage){
	if(form.value.length > length){
		alert(errMessage);
		return false;
	}
	return true;
}

/*
function name : checkDateFormat
Description : Check date format is dd/mm/yyyy
*/
function checkDateFormat(form,errNull,errMessage){ 
	if (checkBlank(form,errNull)==false){
		return false;
	}
	deleteSpace(form);
	var Char;
	var ValidNum = "0123456789";
	var ValidSlash = "/";       
	var len = form.value.length;
	if (len!=10){
		alert(errMessage);
		return false;
	}
	for (var i = 0; i<len;i++) {
		Char = form.value.charAt(i);
		if ((i==2)||(i==5)){
			if (ValidSlash.indexOf(Char) == -1){
				alert(errMessage);
				return false;
			}
		}
		else {
			if (ValidNum.indexOf(Char) == -1){
				alert(errMessage);
				return false;
			}
		}
	}
	if (!DateFormat(form,"","",true,"")) {
		alert(errMessage);
		return false;
	}
	return true;       
}

/*
function name : checkLessThanDate
Description : compare dateTo,dateFrom
Ex. checkLessThanDate("10/10/2004","10/12/2003","msg");
*/
function checkLessThanDate(formStart, formFinish, msgTh, msgEn){		

 	isplit = formStart.value.indexOf('/');

	sDay = formStart.value.substring(0, isplit);
	monthSplit = isplit + 1;
	isplit = formStart.value.indexOf('/', monthSplit);
    sMonth = formStart.value.substring((sDay.length + 1), isplit);
	sYear	= formStart.value.substring(isplit + 1);
	isplit = formFinish.value.indexOf('/');
	fDay = formFinish.value.substring(0, isplit);
	monthSplit = isplit + 1;
	isplit = formFinish.value.indexOf('/', monthSplit);
    fMonth = formFinish.value.substring((sDay.length + 1), isplit);
	fYear	= formFinish.value.substring(isplit + 1);
	if (fYear == sYear){
		if(sMonth > fMonth){
			alert(msgTh);
			return false;
		}
		if (fMonth == sMonth){
			if(sDay > fDay){
				alert(msgTh);
				return false;
			}
		}
	} else{
		if(fYear < sYear){
			alert(msgTh);
			return false;
		}
	}
	return true;
}

/*
function name : checkHistoryMonth
Description : Compare Date From and Date To not over history month from configuration
*/
function checkHistoryMonth(dateFrom, dateTo, historyMonths, errMessage) {
	var dateFromStr = dateFrom.value;
	var dateToStr = dateTo.value;
	
	dateFromStr = dateFromStr.split("/");
	dateToStr = dateToStr.split("/");
	
	var startDate = new Date(dateFromStr[2],dateFromStr[1]-1,dateFromStr[0]);
	var finDate = new Date(dateToStr[2],dateToStr[1]-1,dateToStr[0]);
	
	var years = (finDate.getFullYear() - startDate.getFullYear()) * 12;
	var months = (finDate.getMonth() - startDate.getMonth());
	
	var diffMonth = years + months;
	if(diffMonth < historyMonths) {
		return true;
	}
	else {
		if(diffMonth == historyMonths) {
			if(startDate.getDate() >= finDate.getDate()) {
				return true;
			}
			else {
				alert(errMessage);
				return false;
			}
		}
		else {
			alert(errMessage);
			return false;
		}
	}
}

/*
function name : checkMobileNo
Description : Check mobile no. must initial by 08 and have a 10 digit
*/
function checkMobileNo(mobileForm, errMessage) {
	deleteSpace(mobileForm);
	var mobileVal = mobileForm.value;
	if(mobileVal.length > 0){
		if(mobileVal.length != 10) {
			alert(errMessage);
			return false;
		}
		else {
			if("08" != mobileVal.substring(0,2)) {
				alert(errMessage);
				return false;
			}
			else {
				if(!mobileVal.match(/(.*[0-9].*[0-9].*[0-9].*[0-9].*[0-9].*[0-9].*[0-9].*[0-9].*[0-9].*[0-9])/)) {
					alert(errMessage);
					return false;
				}
			}
		}
	}
	return true;
}

/*
function name : checkDecimal
Description : Check decimal value have not dot > 1
*/
function checkDecimal(dotForm, errMessage) {
	var dot = 0;
	var val = dotForm.value;
	for(var i = 0; i<val.length ; i++) {
		if(val.charAt(i) == '.'){
			dot++;
		}
	}
	if(dot > 1) {
		alert(errMessage);
		return false;
	}
	return true;
}

/*
function name : checkUploadFile
Description : Check upload file type and file name must have 1 dot
*/
function checkUploadFile(uploadForm, allFileType, errNullMsg, errDotMsg, errExtMsg) {
	var dot = 0;
	var uploadVal = uploadForm.value;
	
	if("" == uploadVal) {
		alert(errNullMsg);
		return false;
	}
	
	if(uploadVal.indexOf("/") > 0) {
		var lastSlashPosition = uploadVal.lastIndexOf("/");
	}
	else if(uploadVal.indexOf("\\") > 0) {
		var lastSlashPosition = uploadVal.lastIndexOf("\\");
	}
	var fileName = uploadVal.substring(lastSlashPosition + 1, uploadVal.length);
	for(var i = 0; i<fileName.length ; i++) {
		if(fileName.charAt(i) == '.'){
			dot++;
		}
	}
	if(dot != 1) {
		alert(errDotMsg);
		return false;
	}
	
    var dotPosition = fileName.lastIndexOf(".");
    var fileExt = fileName.substring(dotPosition + 1, fileName.length);
    
	var fileType = allFileType.split(",");
	var result = false;

	for(var i = 0; i<fileType.length; i++) {
		if(fileExt.toUpperCase() == fileType[i]) {
			result = true;
			break;
		}
	}
	
	if(!result) {
		alert(errExtMsg);
		return result;
	}
	
	return result;
}

/*
function name : checkFileLength
Description : Check length of file name
*/
function checkFileLength(uploadForm, length, errMessage) {
	var uploadVal = uploadForm.value;
	if(uploadVal.indexOf("/") > 0) {
		var lastSlashPosition = uploadVal.lastIndexOf("/");
	}
	else if(uploadVal.indexOf("\\") > 0) {
		var lastSlashPosition = uploadVal.lastIndexOf("\\");
	}
	var fileName = uploadVal.substring(lastSlashPosition + 1, uploadVal.length);
	if(fileName.length > length){
		alert(errMessage);
		return false;
	}
	return true;
}

/*
function name : checkPassword
Description : Verify password for user role
*/
function checkPassword(passForm, errMessage) {
	var password = passForm.value;
	if(password.length < 8) {
		alert(errMessage);
		return false;
	}
	if(password.match(/.*[\d,\W,A-Z].*[\d,\W,A-Z]/) == null) {
		alert(errMessage);
		return false;
	}
}

/*
function name : limitText
Description : Limit text value not over limit number that given
*/
function limitText(limitField, limitNum) {
	if (limitField.value.length > limitNum) {
		limitField.value = limitField.value.substring(0, limitNum);
	}
}


/*
function name : validateCustomerId
Description : Verify Customer Id 

function validateCustomerId(custId, errMessage) {
	var custId = custId.value;
	if (custId.length > 15) {
		alert(errMessage);
		return false;
	}

	if(custId.search(/^[0-9a-zA-Zเธ�-เธฎ\t().#\x20\x2A\x2D\x2F]*$/) == -1) {
		alert(errMessage);
		return false;
	}
	return true;
}
*/
/*
function name : validCustomerId
Description : Verify on key press Character 0-9,a-z,A-Z,เธ�-เธฎ,()./-#*blank
*/
function validCustomerId(event) {
	var keyCode = event.keyCode ? event.keyCode : event.which ? event.which : event.charCode;
	if(BrowserDetect.browser != "Firefox") {
		if (!((keyCode >= 48 && keyCode <= 57) || (keyCode >= 65 && keyCode <= 90) || (keyCode >= 97 && keyCode <= 122) || (keyCode >= 3585 && keyCode <= 3630) || keyCode == 3 || keyCode == 8 || keyCode == 9 || keyCode == 13 || keyCode == 32 || keyCode == 35 || keyCode == 40 || keyCode == 41 || keyCode == 42 || (keyCode >= 45 && keyCode <= 47))) {
			return false;
		}
	}
	else {
		if (!((keyCode >= 48 && keyCode <= 57) || (keyCode >= 65 && keyCode <= 90) || (keyCode >= 97 && keyCode <= 122) || (keyCode >= 3585 && keyCode <= 3630) || keyCode == 3 || keyCode == 8 || keyCode == 9 || keyCode == 13 || keyCode == 32 || keyCode == 35 || (keyCode >= 37 && keyCode <= 42) || (keyCode >= 45 && keyCode <= 47))) {
			return false;
		}
	}
}

/*
function name : hasSymbol
Description : Find Symbol on String  (~+=!\\[\\]{}()<>#$%'@^&*?:;|)
*/
function hasSymbol(str){
	if(str.search(/[~+=!{}()<>#$%@^&*?:;|\x5B\x5D\x27]/) == -1) {
	    return false;
	}
	return true;
}

/*
function name : checkFileNameSymbol
Description : Find symbol of file name (~+=!\\[\\]{}()<>#$%'@^&*?:;|)
*/
function checkFileNameSymbol(uploadForm, errMessage) {
	var uploadVal = uploadForm.value;
	if(uploadVal.indexOf("/") > 0) {
		var lastSlashPosition = uploadVal.lastIndexOf("/");
	}
	else if(uploadVal.indexOf("\\") > 0) {
		var lastSlashPosition = uploadVal.lastIndexOf("\\");
	}
	var fileName = uploadVal.substring(lastSlashPosition + 1, uploadVal.length);
	
		//if(fileName.search(/[~+=!{}()<>#$%@^&*?:;|\x5B\x5D\x27]/) == -1) {
	 //   return false;
	//
	
	var filter =/^[A-Za-z0-9._-]*$/;
	
	if (filter.test(fileName)) {		
		return false;
	}
	
	alert(errMessage);
	return true;
}

/*
function name : disableFileInput
Description : Disable key anything and Ctrl+key on File Input
*/
function disableFileInput(objForm) {
	if(BrowserDetect.browser == "Explorer") {
		objForm.disabled = true;
		objForm.disabled = false;
	}
}

function validateEngOnly(obj) {
	var strValue = obj.value;
	for(i=0;i<strValue.length;i++) {
     	valueChar = strValue.charAt(i);
 	 	//if(valueChar > '~') {
     	if(!valueChar.match(/^[a-zA-Z0-9\_\ ]+$/g)) {
	   		alert('Enter only English words to the field');
	   		return false;
 	 	}
 	}
	return true;
}
  
function validateThaiOnly(obj) {
	var strValue = obj.value;
	for(i=0;i<strValue.length;i++) {
     	valueChar = strValue.charAt(i);
     	if(valueChar.match(/^[a-zA-Z]/)) {
	   		alert('Enter only Thai words to the field');
	   		return false;
 	 	}
 	}
	return true;
}

function validateNotWhiteSpace(obj) {
	var strValue = obj.value;
	
	for(i=0;i<strValue.length;i++) {
     	valueChar = strValue.charAt(i);
 	 	if(valueChar == ' ') {
	   		return false;
 	 	}
 	}
	return true;
}

/*
function name : checkMinMax
Description : Check length max&min of element
*/
function checkMinMax(form, min, max, message){
   	if(form.value.length < min || form.value.length > max) {
   		alert(message);
   		return false;
   	}
	return true;
}


function isThaiChar(event) {	
	var keyCode = event.keyCode ? event.keyCode : event.which ? event.which : event.charCode;
	
	if(BrowserDetect.browser != "Firefox") {
		if (keyCode >= 126) {
			return false;
		}
	} else {
		if (keyCode >= 126) {
			return false;
		}
	}
}

function numberFormat3(thisObj,decimal) {
	if(outcomma(thisObj.value) == "0"){
        //alert("0");
	}else if(outcomma(thisObj.value) == ""){    
		thisObj.value = "";        
		thisObj.focus();        
	}else{    
		//thisObj.value = FormatCurrency(thisObj.value);        
		if(isNumber(outcomma(thisObj.value))){        
			if (decimal==false){            
				thisObj.value = Replace(thisObj.value,'.00','');            	
			} else {            
				if(thisObj.value !="") {            	
					if (thisObj.value==".00"){            		
						thisObj.value = '';            			
					}            		
				}            	
			}            
		}else{        
			thisObj.value = "";            
			thisObj.focus();            
		}
	    
	}
}

function numberFormat4(thisObj,decimal) {
	if(outcomma(thisObj.value) == "0"){
            //alert("0");
	}else if(outcomma(thisObj.value) == ""){    
		thisObj.value = "0";        
		thisObj.focus();        
	}else{    
		//thisObj.value = FormatCurrency(thisObj.value);        
		if(isNumber(outcomma(thisObj.value))){        
			if (decimal==false){            
				thisObj.value = Replace(thisObj.value,'.00','');            	
			} else {            
				if(thisObj.value !="") {            	
					if (thisObj.value==".00"){            		
						thisObj.value = '';            			
					}            		
				}            	
			}            
		}else{        
			thisObj.value = "";            
			thisObj.focus();            
		}
        
	}

}

function numberFormat5(thisObj,decimal) {
	if(outcomma(thisObj.value) == "0"){ 
	}else if(outcomma(thisObj.value) == ""){    
		thisObj.value = "";        
		thisObj.focus();        
	}else{    
		//thisObj.value = FormatCurrency(thisObj.value);        
		if(isNumber(outcomma(thisObj.value))){        
			if (decimal==false){            
				thisObj.value = Replace(thisObj.value,'','');            	
			} else {            
				if(thisObj.value !="") {            	
					if (thisObj.value==""){            		
						thisObj.value = '';            			
					}            		
				}            	
			}            
		}else{        
			thisObj.value = "";            
			thisObj.focus();            
		}
        
	}

}

