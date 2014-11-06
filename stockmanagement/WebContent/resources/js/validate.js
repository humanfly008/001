 
// validate.js

/* ==================================================================
 *	validate date format
 * ==================================================================
 */

// Check browser version
var isNav4 = false, isNav5 = false, isIE4 = false
var strSeperator = "-"; 
// If you are using any Java validation on the back side you will want to use the / because 
// Java date validations do not recognize the dash as a valid date separator.
var vDateType = 3; // Global value for type of date format
//                1 = mm/dd/yyyy
//                2 = yyyy/dd/mm  (Unable to do date check at this time)
//                3 = dd/mm/yyyy
var vYearType = 4; //Set to 2 or 4 for number of digits in the year for Netscape
var vYearLength = 2; // Set to 4 if you want to force the user to enter 4 digits for the year before validating.
var err = 0; // Set the error code to a default of zero

if(navigator.appName == "Netscape") {
	if (navigator.appVersion < "5") {
		isNav4 = true;
		isNav5 = false;
	}else

		if (navigator.appVersion > "4") {

			isNav4 = false;

			isNav5 = true;
		}
}else {
	isIE4 = true;
}

function DateFormat(vDateName, vDateValue, e, dateCheck, dateType) {
	vDateType = dateType;
	// vDateName = object name
	// vDateValue = value in the field being checked
	// e = event
	// dateCheck 
	// True  = Verify that the vDateValue is a valid date
	// False = Format values being entered into vDateValue only
	// vDateType
	// 1 = mm/dd/yyyy
	// 2 = yyyy/mm/dd
	// 3 = dd/mm/yyyy
	//Enter a tilde sign for the first number and you can check the variable information.
	if (vDateValue == "~") {
		alert("AppVersion = "+navigator.appVersion+" \nNav. 4 Version = "+isNav4+" \nNav. 5 Version = "+isNav5+" \nIE Version = "+isIE4+" \nYear Type = "+vYearType+" \nDate Type = "+vDateType+" \nSeparator = "+strSeperator);
		vDateName.value = "";
		vDateName.focus();
	
		return true;
	}
	

	var whichCode = (window.Event) ? e.which : e.keyCode;
	// Check to see if a seperator is already present.
	// bypass the date if a seperator is present and the length greater than 8
	if (vDateValue.length > 8 && isNav4) {
		if ((vDateValue.indexOf("-") >= 1) || (vDateValue.indexOf("/") >= 1))
			return true;
	}

	//Eliminate all the ASCII codes that are not valid

	var alphaCheck = " abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ/-";

	if (alphaCheck.indexOf(vDateValue) >= 1) {
		if (isNav4) {
			vDateName.value = "";
			vDateName.focus();
			vDateName.select();

			return false;
		}else {
			vDateName.value = vDateName.value.substr(0, (vDateValue.length-1));
		
			return false;   
		}
	}
	
if (whichCode == 8) //Ignore the Netscape value for backspace. IE has no value
return false;
else {
//Create numeric string values for 0123456789/
//The codes provided include both keyboard and keypad values
var strCheck = '47,48,49,50,51,52,53,54,55,56,57,58,59,95,96,97,98,99,100,101,102,103,104,105';
if (strCheck.indexOf(whichCode) != -1) {
if (isNav4) {
if (((vDateValue.length < 6 && dateCheck) || (vDateValue.length == 7 && dateCheck)) && (vDateValue.length >=1)) {
alert("Invalid Date\nPlease Re-Enter");
vDateName.value = "";
vDateName.focus();
vDateName.select();
return false;
}
if (vDateValue.length == 6 && dateCheck) {
var mDay = vDateName.value.substr(2,2);
var mMonth = vDateName.value.substr(0,2);
var mYear = vDateName.value.substr(4,4)
//Turn a two digit year into a 4 digit year
if (mYear.length == 2 && vYearType == 4) {
var mToday = new Date();
//If the year is greater than 30 years from now use 19, otherwise use 20
var checkYear = mToday.getFullYear() + 30; 
var mCheckYear = '20' + mYear;
if (mCheckYear >= checkYear)
mYear = '19' + mYear;
else
mYear = '20' + mYear;
}
var vDateValueCheck = mMonth+strSeperator+mDay+strSeperator+mYear;
if (!dateValid(vDateValueCheck)) {
alert("Invalid Date\nPlease Re-Enter");
vDateName.value = "";
vDateName.focus();
vDateName.select();
return false;
}
return true;
}
else {
// Reformat the date for validation and set date type to a 1
if (vDateValue.length >= 8  && dateCheck) {
if (vDateType == 1) // mmddyyyy
{
var mDay = vDateName.value.substr(2,2);
var mMonth = vDateName.value.substr(0,2);
var mYear = vDateName.value.substr(4,4)
vDateName.value = mMonth+strSeperator+mDay+strSeperator+mYear;
}
if (vDateType == 2) // yyyymmdd
{
var mYear = vDateName.value.substr(0,4)
var mMonth = vDateName.value.substr(4,2);
var mDay = vDateName.value.substr(6,2);
vDateName.value = mYear+strSeperator+mMonth+strSeperator+mDay;
}
if (vDateType == 3) // ddmmyyyy
{
var mMonth = vDateName.value.substr(2,2);
var mDay = vDateName.value.substr(0,2);
var mYear = vDateName.value.substr(4,4)
vDateName.value = mDay+strSeperator+mMonth+strSeperator+mYear;
}
//Create a temporary variable for storing the DateType and change
//the DateType to a 1 for validation.
var vDateTypeTemp = vDateType;
vDateType = 1;
var vDateValueCheck = mMonth+strSeperator+mDay+strSeperator+mYear;
if (!dateValid(vDateValueCheck)) {
alert("Invalid Date\nPlease Re-Enter");
vDateType = vDateTypeTemp;
vDateName.value = "";
vDateName.focus();
vDateName.select();
return false;
}
vDateType = vDateTypeTemp;
return true;
}
else {
if (((vDateValue.length < 8 && dateCheck) || (vDateValue.length == 9 && dateCheck)) && (vDateValue.length >=1)) {
alert("Invalid Date\nPlease Re-Enter");
vDateName.value = "";
vDateName.focus();
vDateName.select();
return false;
         }
      }
   }
}
else {
// Non isNav Check
if (((vDateValue.length < 8 && dateCheck) || (vDateValue.length == 9 && dateCheck)) && (vDateValue.length >=1)) {
alert("Invalid Date\nPlease Re-Enter");
vDateName.value = "";
vDateName.focus();
return true;
}
// Reformat date to format that can be validated. mm/dd/yyyy
if (vDateValue.length >= 8 && dateCheck) {
// Additional date formats can be entered here and parsed out to
// a valid date format that the validation routine will recognize.
if (vDateType == 1) // mm/dd/yyyy
{
var mMonth = vDateName.value.substr(0,2);
var mDay = vDateName.value.substr(3,2);
var mYear = vDateName.value.substr(6,4)
}
if (vDateType == 2) // yyyy/mm/dd
{
var mYear = vDateName.value.substr(0,4)
var mMonth = vDateName.value.substr(5,2);
var mDay = vDateName.value.substr(8,2);
}
if (vDateType == 3) // dd/mm/yyyy
{
var mDay = vDateName.value.substr(0,2);
var mMonth = vDateName.value.substr(3,2);
var mYear = vDateName.value.substr(6,4)
}
if (vYearLength == 4) {
if (mYear.length < 4) {
alert("Invalid Date\nPlease Re-Enter");
vDateName.value = "";
vDateName.focus();
return true;
   }
}
// Create temp. variable for storing the current vDateType
var vDateTypeTemp = vDateType;
// Change vDateType to a 1 for standard date format for validation
// Type will be changed back when validation is completed.
vDateType = 1;
// Store reformatted date to new variable for validation.
var vDateValueCheck = mMonth+strSeperator+mDay+strSeperator+mYear;
if (mYear.length == 2 && vYearType == 4 && dateCheck) {
//Turn a two digit year into a 4 digit year
var mToday = new Date();
//If the year is greater than 30 years from now use 19, otherwise use 20
var checkYear = mToday.getFullYear() + 30; 
var mCheckYear = '20' + mYear;
if (mCheckYear >= checkYear)
mYear = '19' + mYear;
else
mYear = '20' + mYear;
vDateValueCheck = mMonth+strSeperator+mDay+strSeperator+mYear;
// Store the new value back to the field.  This function will
// not work with date type of 2 since the year is entered first.
if (vDateTypeTemp == 1) // mm/dd/yyyy
vDateName.value = mMonth+strSeperator+mDay+strSeperator+mYear;
if (vDateTypeTemp == 3) // dd/mm/yyyy
vDateName.value = mDay+strSeperator+mMonth+strSeperator+mYear;
} 
if (!dateValid(vDateValueCheck)) {
alert("Invalid Date\nPlease Re-Enter");
vDateType = vDateTypeTemp;
vDateName.value = "";
vDateName.focus();
return true;
}
vDateType = vDateTypeTemp;
return true;
}
else {
if (vDateType == 1) {
if (vDateValue.length == 2) {
vDateName.value = vDateValue+strSeperator;
}
if (vDateValue.length == 5) {
vDateName.value = vDateValue+strSeperator;
   }
}
if (vDateType == 2) {
if (vDateValue.length == 4) {
vDateName.value = vDateValue+strSeperator;
}
if (vDateValue.length == 7) {
vDateName.value = vDateValue+strSeperator;
   }
} 
if (vDateType == 3) {
if (vDateValue.length == 2) {
vDateName.value = vDateValue+strSeperator;
}
if (vDateValue.length == 5) {
vDateName.value = vDateValue+strSeperator;
   }
}
return true;
   }
}
if (vDateValue.length == 10&& dateCheck) {
if (!dateValid(vDateName)) {
// Un-comment the next line of code for debugging the dateValid() function error messages
//alert(err);  
alert("Invalid Date\nPlease Re-Enter");
vDateName.focus();
vDateName.select();
   }
}
return false;
}
else {
// If the value is not in the string return the string minus the last
// key entered.
if (isNav4) {
vDateName.value = "";
vDateName.focus();
vDateName.select();
return false;
}
else
{
vDateName.value = vDateName.value.substr(0, (vDateValue.length-1));
return false;
         }
      }
   }
}
function dateValid(objName) {
var strDate;
var strDateArray;
var strDay;
var strMonth;
var strYear;
var intday;
var intMonth;
var intYear;
var booFound = false;
var datefield = objName;
var strSeparatorArray = new Array("-"," ","/",".");
var intElementNr;
// var err = 0;
var strMonthArray = new Array(12);
strMonthArray[0] = "Jan";
strMonthArray[1] = "Feb";
strMonthArray[2] = "Mar";
strMonthArray[3] = "Apr";
strMonthArray[4] = "May";
strMonthArray[5] = "Jun";
strMonthArray[6] = "Jul";
strMonthArray[7] = "Aug";
strMonthArray[8] = "Sep";
strMonthArray[9] = "Oct";
strMonthArray[10] = "Nov";
strMonthArray[11] = "Dec";
//strDate = datefield.value;
strDate = objName;
if (strDate.length < 1) {
return true;
}
for (intElementNr = 0; intElementNr < strSeparatorArray.length; intElementNr++) {
if (strDate.indexOf(strSeparatorArray[intElementNr]) != -1) {
strDateArray = strDate.split(strSeparatorArray[intElementNr]);
if (strDateArray.length != 3) {
err = 1;
return false;
}
else {
strDay = strDateArray[0];
strMonth = strDateArray[1];
strYear = strDateArray[2];
}
booFound = true;
   }
}
if (booFound == false) {
if (strDate.length>5) {
strDay = strDate.substr(0, 2);
strMonth = strDate.substr(2, 2);
strYear = strDate.substr(4);
   }
}
//Adjustment for short years entered
if (strYear.length == 2) {
strYear = '20' + strYear;
}
strTemp = strDay;
strDay = strMonth;
strMonth = strTemp;
intday = parseInt(strDay, 10);
if (isNaN(intday)) {
err = 2;
return false;
}
intMonth = parseInt(strMonth, 10);
if (isNaN(intMonth)) {
for (i = 0;i<12;i++) {
if (strMonth.toUpperCase() == strMonthArray[i].toUpperCase()) {
intMonth = i+1;
strMonth = strMonthArray[i];
i = 12;
   }
}
if (isNaN(intMonth)) {
err = 3;
return false;
   }
}
intYear = parseInt(strYear, 10);
if (isNaN(intYear)) {
err = 4;
return false;
}
if (intMonth>12 || intMonth<1) {
err = 5;
return false;
}
if ((intMonth == 1 || intMonth == 3 || intMonth == 5 || intMonth == 7 || intMonth == 8 || intMonth == 10 || intMonth == 12) && (intday > 31 || intday < 1)) {
err = 6;
return false;
}
if ((intMonth == 4 || intMonth == 6 || intMonth == 9 || intMonth == 11) && (intday > 30 || intday < 1)) {
err = 7;
return false;
}
if (intMonth == 2) {
if (intday < 1) {
err = 8;
return false;
}
if (LeapYear(intYear) == true) {
if (intday > 29) {
err = 9;
return false;
   }
}
else {
if (intday > 28) {
err = 10;
return false;
      }
   }
}
return true;
}
function LeapYear(intYear) {
if (intYear % 100 == 0) {
if (intYear % 400 == 0) { return true; }
}
else {
if ((intYear % 4) == 0) { return true; }
}
return false;
}


/* ==================================================================
 *	validate number format
 * ==================================================================
 */
 function outcomma(theNumberValue){
 	numberArray = theNumberValue.split(",");
 	outStringComma = "";
 	
 	if(numberArray.length > 1){
	 	for(var i=0; i<numberArray.length; i++){
	 		outStringComma += numberArray[i];
	 	}
	 } else {
	 	outStringComma = theNumberValue;
	 }
	 
	 if(outStringComma.indexOf("(") != -1 && outStringComma.indexOf(")") != -1){
	 	outStringComma = outStringComma.replace("(","");
	 	outStringComma = outStringComma.replace(")","");
	 	outStringComma = "-" + outStringComma;
	 }
 	
 	return outStringComma;
 }
 

function formatCurrency(theNumber) {
	theNumber = theNumber.toString().replace(/\$|\,/g,'');
	if(isNaN(theNumber))
	theNumber = "0";
	sign = (theNumber == (theNumber = Math.abs(theNumber)));
	theNumber = Math.floor(theNumber*100+0.50000000001);
	cents = theNumber%100;
	theNumber = Math.floor(theNumber/100).toString();
	if(cents<10)
	cents = "0" + cents;
	for (var i = 0; i < Math.floor((theNumber.length-(1+i))/3); i++)
	theNumber = theNumber.substring(0,theNumber.length-(4*i+3))+','+
	theNumber.substring(theNumber.length-(4*i+3));
	return (((sign)?'':'(') + theNumber + '.' + cents + ((sign)?'':')'));
}

function formatForexcRate(theNumber,faction) {
	theNumber = theNumber.toString().replace(/\$|\,/g,'');
	if(isNaN(theNumber))
	theNumber = "0";
	sign = (theNumber == (theNumber = Math.abs(theNumber)));
	theNumber = Math.floor(theNumber*Math.pow(10,faction)+0.50000000001);
	cents = theNumber%Math.pow(10,faction);
	theNumber = Math.floor(theNumber/Math.pow(10,faction)).toString();
	while((cents+"").length<faction){
	   cents = "0" + cents;
                }
	for (var i = 0; i < Math.floor((theNumber.length-(1+i))/3); i++)
	theNumber = theNumber.substring(0,theNumber.length-(4*i+3))+','+
	theNumber.substring(theNumber.length-(4*i+3));
	return (((sign)?'':'(') + theNumber + '.' + cents + ((sign)?'':')'));
}
function convertNum(obj){
	if(isNumber(outcomma(obj.value))){
		number = obj.value;
		obj.value = formatCurrency(number);
	}else{
		obj.value = "";
		obj.focus();
	}
}
function isNumber(elem){
	var srt = elem;
	var re = /^[-]?\d*\.?\d*$/;
	srt = srt.toString();
	if(!srt.match(re)){
		alert("Enter only number to the field.");
		return false;
	}else{
		return true;
	}
}
function LTrim(String)
{
	var i = 0;
	var j = String.length - 1;

	if (String == null)
		return (false);

	for (i = 0; i < String.length; i++)
	{
		if (String.substr(i, 1) != ' ' &&
		    String.substr(i, 1) != '\t')
			break;
	}

	if (i <= j)
		return (String.substr(i, (j+1)-i));
	else
		return ('');
}
function RTrim(String)
{
	var i = 0;
	var j = String.length - 1;

	if (String == null)
		return (false);

	for(j = String.length - 1; j >= 0; j--)
	{
		if (String.substr(j, 1) != ' ' &&
			String.substr(j, 1) != '\t')
		break;
	}

	if (i <= j)
		return (String.substr(i, (j+1)-i));
	else
		return ('');
}
function Trim(String){
	if (String == null)
		return (false);

	return RTrim(LTrim(String));
}
function tonumber(str) {
	str = Trim(str);
	if(str!="") {
		str = Replace(str,',','');
		return  parseFloat(str);
	} else {
		return 0;
	}
}
function Replace(Expression, Find, Replace)
{
	var temp = Expression;
	var a = 0;

	for (var i = 0; i < Expression.length; i++) 
	{
		a = temp.indexOf(Find);
		if (a == -1)
			break;
		else
			temp = temp.substring(0, a) + Replace + temp.substring((a + Find.length));
	}

	return temp;
}
function NumberFormat(thisObj,decimal) {
	thisObj.value = Replace(thisObj.value,',','');
	thisObj.value = Replace(thisObj.value,'.00','');
	thisObj.value = FormatCurrency(thisObj.value);
	if(isNumber(outcomma(thisObj.value))){
		if (decimal==false)
		{
			thisObj.value = Replace(thisObj.value,'.00','');
		} else {
			if(thisObj.value !="") {
				if (thisObj.value==".00")
				{
					thisObj.value = '';
				}
			}
		}
	}else{
		thisObj.value = "";
		thisObj.focus();
	}
	
}
function FormatCurrency(Expression)
{
	var iNumDecimals = 2;
	var dbInVal = Expression;
	var bNegative = false;
	var iInVal = 0;
	var strInVal
	var strWhole = "", strDec = "";
	var strTemp = "", strOut = "";
	var iLen = 0;

	if (dbInVal < 0)
	{
		bNegative = true;
		dbInVal *= -1;
	}

	dbInVal = dbInVal * Math.pow(10, iNumDecimals)
	iInVal = parseInt(dbInVal);
	if ((dbInVal - iInVal) >= .5)
	{
		iInVal++;
	}
	strInVal = iInVal + "";
	strWhole = strInVal.substring(0, (strInVal.length - iNumDecimals));
	strDec = strInVal.substring((strInVal.length - iNumDecimals), strInVal.length);
	while (strDec.length < iNumDecimals)
	{
		strDec = "0" + strDec;
	}
	iLen = strWhole.length;
	if (iLen >= 3)
	{
		while (iLen > 0)
		{
			strTemp = strWhole.substring(iLen - 3, iLen);
			if (strTemp.length == 3)
			{
				strOut = "," + strTemp + strOut;
				iLen -= 3;
			}
			else
			{
				strOut = strTemp + strOut;
				iLen = 0;
			}
		}
		if (strOut.substring(0, 1) == ",")
		{
			strWhole = strOut.substring(1, strOut.length);
		}
		else
		{
			strWhole = strOut;
		}
		if (strWhole=='' || strWhole==0)
		{
			 strWhole = '0';
		}
	}
	if (bNegative)
	{
		return "-" + strWhole + "." + strDec;
	}
	else
	{
		return "" + strWhole + "." + strDec;
	}
}

function checkNumKey(){
//alert(event.keyCode);
	if(event.keyCode != 8 && event.keyCode != 9 ){
		if (event.keyCode < 48 || event.keyCode > 57){ 
			event.returnValue = false;
		}
	}
}
function isNumberic(value) {
    var number = '0123456789';
		if(value == undefined)return false;
		for (var i=0; i < value.length; i++) {
        var c = value.charAt(i);
        if (number.indexOf(c) == -1) return false;
	}
    
    return true;
}

function chkNumber(value){
    if(isNumberic(value)){
    	return value;
    }
    return 0;
}

function getNumberValue(txt){
	return chkNumber(eval(outcomma(document.getElementById(txt).value)));
}

function round2Scale(num){
	var ans = Math.round(num*100)/100; 
	return ans;	
}

function round(num){
	var ans = Math.round(num); 
	return ans;	
}


// return the value of the radio button that is checked
// return an empty string if none are checked, or
// there are no radio buttons
function getCheckedValue(radioObj) {
	if(!radioObj)
		return "";
	var radioLength = radioObj.length;
	if(radioLength == undefined)
		if(radioObj.checked)
			return radioObj.value;
		else
			return "";
	for(var i = 0; i < radioLength; i++) {
		if(radioObj[i].checked) {
			return radioObj[i].value;
		}
	}
	return "";
}

// set the radio button with the given value as being checked
// do nothing if there are no radio buttons
// if the given value does not exist, all the radio buttons
// are reset to unchecked
function setCheckedValue(radioObj, newValue) {
	if(!radioObj)
		return;
	var radioLength = radioObj.length;
	if(radioLength == undefined) {
		radioObj.checked = (radioObj.value == newValue.toString());
		return;
	}
	for(var i = 0; i < radioLength; i++) {
		radioObj[i].checked = false;
		if(radioObj[i].value == newValue.toString()) {
			radioObj[i].checked = true;
		}
	}
}

function floor2Scale(num){
		var ans = Math.floor(num*100)/100; 
		return ans;	
}
function floor(num){
		var ans =  Math.floor(num); 
		return ans;	
}


function ceil2Scale(num){	
		var ans = Math.ceil(num); 
		return ans;	
}	

function validate_required(field,alerttxt){
  		if(field.length == 0){
  			alert(alerttxt);
  			return true; 		
  		}else{
  			return false;
  		}
}
function validateItem(field,alerttxt)
{
	if(field.length>3&&field.length<3)
		{
			alert(alerttxt);
			return true;
		}
	else{ return false;}
}
function check_number(ch)
{
   var len, digit;
   if(ch == " ")
   {
     return false;
     len=0;
   }else
   {
    len = ch.length;
   }
    for(var i=0 ; i<len ; i++)
   {
        digit = ch.charAt(i)
        if(digit >="0" && digit <="9"){
    	alert('Enter only number to the field.');
     }else
     {
       return false;
     }
   }
    return true;
}

// ++++++++++++++++++++++++++++++++++++++++++++++++++ 
// -- ---- function for set field to readonly -------
// -- ----- By Nich -------- Date 4 June 2010 -------
// ++++++++++++++++++++++++++++++++++++++++++++++++++  
function lp_set_readonly( ao_obj, ab_readonly ) { 
	var lv_class = ao_obj . className; 

	ao_obj . readOnly = ab_readonly; 

	if ( ab_readonly ){ 
		lv_class = ( lv_class == "" ) ? "display" : lv_class + " display";  
		ao_obj . tabIndex = "-1"; 
	}else{
		lv_class = lv_class .replace( /display/g, "" );
	}
	ao_obj . className = lv_class;  
}
// ++++++++++++++++++++++++++++++++++++++++++++++++++ 
// -- - function for check when input key number ----
// -- ----- By Nich -------- Date 31 May 2010 -------
// ++++++++++++++++++++++++++++++++++++++++++++++++++  
   function isNumberKey(evt)  { 
        var charCode = (evt.which) ? evt.which : event.keyCode 
        if ( ( charCode > 31 && ( charCode < 48 || charCode > 57)  ) && (charCode != 44 && charCode != 46)  ) {  
           return false;
	}
        return true;
   }
   
// ++++++++++++++++++++++++++++++++++++++++++++++++++
// -------- function for set number to float --------
// -------- By Nich -------- Date 31 May 2010 -------
// ++++++++++++++++++++++++++++++++++++++++++++++++++     
	function lp_set_number( av_value ){   
	var lv_value   = av_value ;
	var lv_lan     = 0;
	var lv_float   = "";
	var lv_index   = 0; 
	var lv_count   = 0; 
 
	lv_index = lv_value.indexOf(".");   
	lv_lan = lv_value.length;   

	if( lv_index >= 1){  
		lv_float = lv_value.substring( lv_index , lv_lan  );
		lv_value = lv_value.replace(lv_float , "");
		lv_lan   = lv_value.length; 
	}  
	 
	lv_value = lv_check_comma(lv_lan , lv_value) ;  

	if( lv_index > 1){
		if( lv_float.length < 3){
			lv_float = lv_float + "0";
		}
		lv_value = lv_value + lv_float; 
	}else{
		lv_value = lv_value + ".00"; 
	}
	return lv_value;
 }
// ++++++++++++++++++++++++++++++++++++++++++++++++++
// ------ Sub function for colon(,) in String -------
// -------- By Nich -------- Date 25 June 2010 -------
// ++++++++++++++++++++++++++++++++++++++++++++++++++ 
 function isNumberKeyWithColon(evt)  {  
        var charCode = (evt.which) ? evt.which : event.keyCode 
        if ( ( charCode > 31 && ( charCode < 48 || charCode > 57)  ) && (charCode != 58)  ) {  
           return false;
	}
        return true;
   }
// ++++++++++++++++++++++++++++++++++++++++++++++++++
// ------ Sub function for comma(,) in String -------
// -------- By Nich -------- Date 31 May 2010 -------
// ++++++++++++++++++++++++++++++++++++++++++++++++++ 
 function lv_check_comma( av_len , av_value ){
	var lv_result = "";
	var lv_len    = av_len;
	var lv_value  = av_value;  
	var lv_loop   = 0;  
	for( var i = 0 ; i < lv_len; i++){
		lv_loop = parseInt(lv_len-i)%3; 
		if(  lv_loop == 0 ){  
			if( parseInt(lv_len-i)%3 ==0 ){ 
				lv_result+=  "," +   lv_value.charAt(i);     
			}else{
				lv_result+=   lv_value.charAt(i);   
			}
		}else {      
				lv_result += lv_value.charAt(i);  
		}
		 
	}  
	lv_len = lv_result . length ;
	 
	if(parseInt(lv_len)%4 == 0){
		lv_result = lv_result.replace("," , "");
	}
	 
	lv_result = lv_result.replace( /,,/g, "," );  
	 
	return lv_result; 
}	 

function IsNumeric(sText,obj)
{
	
	var ValidChars = "0123456789.,";
	
	var IsNumber=true; 
	
	var Char;
	
	for (i = 0; i < sText.length && IsNumber == true; i++){
	
	Char = sText.charAt(i);
	
	if (ValidChars.indexOf(Char) == -1){
	
	IsNumber = false;
	
	}
	
	}
	
	if(IsNumber==false){
	
	obj.value="0";
	
	}
}
// +++++++++++++++++++++++++++++++++++++++++++++++++++
// - function for validate data before sent to AS400 -
// -------- By Nich -------- Date 16 Feb 2011 --------
// +++++++++++++++++++++++++++++++++++++++++++++++++++
	function LTrim(str){
	   if (str==null){return null;}
	   for(var i=0;str.charAt(i)==" ";i++);
	   return str.substring(i,str.length);
	}
	function RTrim(str){
	   if (str==null){return null;}
	   for(var i=str.length-1;str.charAt(i)==" ";i--);
	   return str.substring(0,i+1);
	}
	function Trim(str){
	   return LTrim(RTrim(str));
	}
	
	function lp_validate_dataAS400( av_ent ){
		var lo_scr = av_ent.srcElement;  

		var	lv_scr = lo_scr.value;
	
		lv_scr = Trim(lv_scr);

		if (window.event) keycode = window.event.keyCode; //   IE 

		else if (e) keycode = e.which; //   Firefox 
		//lv_address =	lv_address.replace(/\s+/g," ");
		
		if(lv_scr != "" && lv_scr != null){
			if(lv_scr.length == 1){
				alert("Format wrong data");
				lo_scr.value = ""; 
				return false;
			}
			
			if(lv_scr.indexOf("-") == 0){
				alert("Format wrong data");
				lo_scr.value = ""; 
				return false;
			}
	
			if(lv_scr.lastIndexOf("-") == lv_scr.length - 1 ){
				alert("Format wrong data");
				lo_scr.value = ""; 
				return false;
			}
	
	
			lv_scr = lv_scr.replace(/ - /g , "-");
			lv_scr = lv_scr.replace(/ -/g , "-");
			lv_scr = lv_scr.replace(/- /g , "-");
			lv_scr = lv_scr.replace(/--/g , "-");
	
			lo_scr.value = lv_scr;
		}

		
	}

	function lp_check_2space_AS400( av_ent ) {
		var lo_scr = av_ent.srcElement; 
		var	lv_scr = lo_scr.value;

		var keycode;
		var lv_len = 0;
		 
		var lv_str = "";

		if (window.event) keycode = window.event.keyCode; // IE  

		else if (e) keycode = e.which; //  Firefox 

			//alert("keycode: " + keycode); // 

			lv_len = lv_scr.length;
	 
			
			if(keycode == 32 ){
			 
				lv_str = lv_scr.substr(lv_len - 1  , lv_len);
			 
				if( lv_str == " "){
				  alert("can't type space more than 2 ");
				  lo_scr. value = lv_scr.substr(0  , lv_len -1);
				}
			}

	}

	function lp_key_comma_AS400( av_ent ){ 
		var keycode;
		var lv_len = 0;

		var lo_scr = av_ent.srcElement; 
		var lv_str = "";
		var	lv_scr = lo_scr.value;
		var lv_str = "";
		
		lv_len = lv_str.length;

		if (window.event) keycode = window.event.keyCode; //  IE 

		else if (e) keycode = e.which; //  Firefox 

			if(keycode == 188){   
				lo_scr. value = lv_scr + " ";
				 
			}

			if( keycode == 221  ){
				lo_scr. value = lv_scr + " ";
			}

		
	}
	 