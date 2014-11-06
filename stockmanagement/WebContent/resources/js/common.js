var webapp_context = "RVP2011/";

function redirect(link,target)
{ 
  obj = document.forms[0];
  tmp = obj.target;
  obj.target = target;
  obj.action = webapp_context+link;
  obj.submit();
  obj.target = tmp;
}

function setValue(obj_name , value)
{document.getElementById(obj_name).value = value;
}

function getValue(obj_name)
{return document.getElementById(obj_name).value;
}

function getTitle(obj_name)
{var title ="";
	try {title = document.getElementById(obj_name).title;} catch (e) { }
	return title;
}

function setFocusObject(obj_name)
{
	try { 
		document.getElementById(obj_name).focus();
		document.getElementById(obj_name).select();
	} 
	catch (e) 
	{ }
}

function setDisable(obj_name , status)
{var obj = document.getElementById(obj_name)
	if (status)
	   {obj.disabled = status;
	     //obj.className = "textboxdis";
	   }
	   else
	   {obj.disabled = status;
	     //obj.className = "textbox";
	   }
}

function setReadOnly(obj_name , status)
{var obj = document.getElementById(obj_name)
	if (status)
	   {obj.readOnly = status;
	     //obj.className = "textboxdis";
	   }
	   else
	   {obj.readOnly = status;
	     //obj.className = "textbox";
	   }
}


function arrayToString(arr)
{var tmp = "";
	for (i=0; i<arr.length; i++)
	{if (i == 0) 
		{tmp += "'"+arr[i]+"'";}
      	else 
		{tmp += ",'"+arr[i]+"'";}
	}
  return tmp;
}

function format (data , decimal) {
	 //decimal  - the number of decimals after the digit from 0 to 3
//-- Returns the passed number as a string in the xxx,xxx.xx format.
	   anynum=eval(data);
	   divider =10;
	   switch(decimal){
			case 0:
				divider =1;
				break;
			case 1:
				divider =10;
				break;
			case 2:
				divider =100;
				break;
			default:  	 //for 3 decimal places
				divider =1000;
		}

	   workNum=Math.abs((Math.round(anynum*divider)/divider));

	   workStr=""+workNum

	   if (workStr.indexOf(".")==-1){workStr+="."}

	   dStr=workStr.substr(0,workStr.indexOf("."));dNum=dStr-0
	   pStr=workStr.substr(workStr.indexOf("."))

	   while (pStr.length-1< decimal){pStr+="0"}

	   if(pStr =='.') pStr ='';

	   //--- Adds a comma in the thousands place.    
	   if (dNum>=1000) {
		  dLen=dStr.length
		  dStr=parseInt(""+(dNum/1000))+","+dStr.substring(dLen-3,dLen)
	   }

	   //-- Adds a comma in the millions place.
	   if (dNum>=1000000) {
		  dLen=dStr.length
		  dStr=parseInt(""+(dNum/1000000))+","+dStr.substring(dLen-7,dLen)
	   }
	   retval = dStr + pStr
	   //-- Put numbers in parentheses if negative.
	   if (anynum<0) {retval="("+retval+")";}

	  
	//You could include a dollar sign in the return value.
	  //retval =  "$"+retval
	  
	 return retval;
 }
 
function ltrim(str) {
if (str==null){
   return "";
}
 if (str.length>0) {
       while (str.substring(0,1)==" ") {
	      str=str.substring(1,str.length);
	   }
	}
    return str;
}

function rtrim(str) {
   if (str==null){
	   return "";
   }
   if (str.length>0) {
       while (str.substring(str.length-1,str.length)==" ") {
	      str=str.substring(0,str.length-1);
	   }
	}
    return str;
  }

  function trim(str) {
    str=ltrim(str);
	str=rtrim(str);
	return str;
  }
  
  
function getDate(str)
{var tmp = str.split("/");
	var day = tmp[2] * 1;
	var month = tmp[1] * 1;
	var year = tmp[0] * 1;
	var date1 = new Date(year , month , day)
	return date1;
}

function showLoadingDialog() {
	loadingDialog.show();
}

function hideLoadingDialog() {
	loadingDialog.hide();
}