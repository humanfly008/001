var objOnebox = "";
var objAllbox = "";
var result = "";
var count = "0";
var flagLoop = true;
var checkType = "";

function checkList(allId,oneId){
	 
	checkType = oneId.indexOf(':');

	if(checkType <= 0){
		objAllbox = document.getElementById(allId);
		if(objAllbox.checked)
			setcheckBoxAll(oneId,'true');
		else
			setcheckBoxAll(oneId,'false');
	}else{
		objAllbox = document.getElementById(allId);
		if(objAllbox.checked)
			setbooleanCheckAll(oneId,'true');
		else
			setbooleanCheckAll(oneId,'false');
	}
}

function setcheckBoxAll(oneId,mode){
	objOnebox = document.getElementsByName(oneId);
	for(i=0; i< objOnebox.length ;i++){
		if(objOnebox[i].id == oneId){
			if(mode == 'true')
				objOnebox[i].checked = true ;
			else
				objOnebox[i].checked = false ;
		}
	}
}

function oneCheck(allId,oneId){
	objOnebox =	document.getElementsByName(oneId);
	objOneAll = document.getElementById(allId);
	count = 0;
	
	for(i=0; i < objOnebox.length ;i++){
		if(objOnebox[i].id == oneId && objOnebox[i].checked){
			count ++;
			if(count == objOnebox.length)
				objOneAll.checked = true;
			else
				objOneAll.checked = false;
		}
	}
}

function setbooleanCheckAll(oneId,mode){
	var split = oneId.split(':');
	count = 0;
	
	do{	
		result = "";
		for(j=0; j < split.length ;j++ ){
			if((split.length-1) == j){
				result += count;
				result += ':';
			}
			
			result += split[j];

			if((j+1) != split.length )
				result += ':';
		}
		
		objOnebox = document.getElementById(result);
		
		if(objOnebox != null){
			flagLoop = true;
			if(mode == 'true')
				objOnebox.checked = true;
			if(mode == 'false')
				objOnebox.checked = false;

		}else{
			flagLoop = false;
		}
		
		count++;
	}while (flagLoop);
}

function booleanOneCheck(allId,oneId){
	var split = oneId.split(':');
	count = 0;
	var checked = true;
	objOneAll = document.getElementById(allId);
	
	if(objOneAll.checked){
		do{	
			result = "";
			for(j=0;j < split.length ;j++ ){
				if((split.length-1) == j){
					result += count;
					result += ":";
				}
				result += split[j];

				if((j+1) != split.length )
					result += ":";
			}
			
			objOnebox = document.getElementById(result);
			
			if(objOnebox != null){
				if(!objOnebox.checked){
					objOneAll.checked = false;
					flagLoop = false;
				}else
					flagLoop = true;
			}else
				flagLoop = false;
			
			count++;
		}while (flagLoop);
	}else{
		do{	
			result = "";
			for(j=0;j < split.length ;j++ ){
				if((split.length-1) == j){
					result += count;
					result += ':';
				}
				result += split[j];

				if((j+1) != split.length )
					result += ':';
			}
			objOnebox = document.getElementById(result);
			
			if(objOnebox == null){
				flagLoop = false;
			}else{
				flagLoop =true;
				if(objOnebox.checked)
					checked = true;
				else{
					objOneAll.checked = false;
					return null;
				}
			}			
			value = '';
			count++;
		}while (flagLoop);
		objOneAll.checked = checked;
	}
}

function doValidateDelete(oneId){
	var field;
	var check = false;
	var split;
	count = 0;
	checkType = oneId.indexOf(':');
	if(checkType > 0){
		do{	
			split = oneId.split(':');
			result = "";
			for(j=0;j < split.length ;j++ ){
				if((split.length-1) == j){
					result += count;
					result += ':';
				}
				result += split[j];

				if((j+1) != split.length )
					result += ':';
			}
			objOnebox = document.getElementById(result);
			objOne = 	document.getElementById(result);
			if(objOnebox == null){
				flagLoop = faobjOneboxlse;
			}else{
				flagLoop = true;
				if(objOnebox.checked){
					check = true;
					break;	
				}
			}			
			value = '';
			count++;
		}while (flagLoop);
	}else{
		field = document.getElementsByName(oneId);
		for (var i= 0;i< field.length;i++){
			if(field[i].checked == true){
				check = true;
				break;
			}else{
				lv_check = false;
			}
		}
	}
	//alert msg
	if(!check){
		checkDeleteDialog.show();
	}else{
		confirmDialog.show();
	}
	
	return check;
}

function doValidateReject(oneId){
	var field;
	var check = false;
	var split;
	count = 0;
	checkType = oneId.indexOf(':');
	if(checkType > 0){
		do{	
			split = oneId.split(':');
			result = "";
			for(j=0;j < split.length ;j++ ){
				if((split.length-1) == j){
					result += count;
					result += ':';
				}
				result += split[j];

				if((j+1) != split.length )
					result += ':';
			}
			objOnebox = document.getElementById(result);
			objOne = 	document.getElementById(result);
			if(objOnebox == null){
				flagLoop = faobjOneboxlse;
			}else{
				flagLoop = true;
				if(objOnebox.checked){
					check = true;
					break;	
				}
			}			
			value = '';
			count++;
		}while (flagLoop);
	}else{
		field = document.getElementsByName(oneId);
		for (var i= 0;i< field.length;i++){
			if(field[i].checked == true){
				check = true;
				break;
			}else{
				lv_check = false;
			}
		}
	}
	//alert msg
	if(!check){
		checkRejectDialog.show();
	}else{
		confirmRejectDialog.show();
	}
	
	return check;
}

function doValidateApprove(oneId){
	var field;
	var check = false;
	var split;
	count = 0;
	checkType = oneId.indexOf(':');
	if(checkType > 0){
		do{	
			split = oneId.split(':');
			result = "";
			for(j=0;j < split.length ;j++ ){
				if((split.length-1) == j){
					result += count;
					result += ':';
				}
				result += split[j];

				if((j+1) != split.length )
					result += ':';
			}
			objOnebox = document.getElementById(result);
			objOne = 	document.getElementById(result);
			if(objOnebox == null){
				flagLoop = faobjOneboxlse;
			}else{
				flagLoop = true;
				if(objOnebox.checked){
					check = true;
					break;	
				}
			}			
			value = '';
			count++;
		}while (flagLoop);
	}else{
		field = document.getElementsByName(oneId);
		for (var i= 0;i< field.length;i++){
			if(field[i].checked == true){
				check = true;
				break;
			}else{
				lv_check = false;
			}
		}
	}
	//alert msg
	if(!check){
		checkApproveDialog.show();
	}else{
		confirmApproveDialog.show();
	}
	
	return check;
}

function doValidateCancel(oneId){
	var field;
	var check = false;
	var split;
	count = 0;
	checkType = oneId.indexOf(':');
	if(checkType > 0){
		do{	
			split = oneId.split(':');
			result = "";
			for(j=0;j < split.length ;j++ ){
				if((split.length-1) == j){
					result += count;
					result += ':';
				}
				result += split[j];

				if((j+1) != split.length )
					result += ':';
			}
			objOnebox = document.getElementById(result);
			objOne = 	document.getElementById(result);
			if(objOnebox == null){
				flagLoop = faobjOneboxlse;
			}else{
				flagLoop = true;
				if(objOnebox.checked){
					check = true;
					break;	
				}
			}			
			value = '';
			count++;
		}while (flagLoop);
	}else{
		field = document.getElementsByName(oneId);
		for (var i= 0;i< field.length;i++){
			if(field[i].checked == true){
				check = true;
				break;
			}else{
				lv_check = false;
			}
		}
	}
	//alert msg
	if(!check){
		checkCancelDialog.show();
	}else{
		confirmCancelDialog.show();
	}
	
	return false;
}

function doValidateOverride(oneId){
	var field;
	var check = false;
	var split;
	count = 0;
	checkType = oneId.indexOf(':');
	if(checkType > 0){
		do{	
			split = oneId.split(':');
			result = "";
			for(j=0;j < split.length ;j++ ){
				if((split.length-1) == j){
					result += count;
					result += ':';
				}
				result += split[j];

				if((j+1) != split.length )
					result += ':';
			}
			objOnebox = document.getElementById(result);
			objOne = 	document.getElementById(result);
			if(objOnebox == null){
				flagLoop = faobjOneboxlse;
			}else{
				flagLoop = true;
				if(objOnebox.checked){
					check = true;
					break;	
				}
			}			
			value = '';
			count++;
		}while (flagLoop);
	}else{
		field = document.getElementsByName(oneId);
		for (var i= 0;i< field.length;i++){
			if(field[i].checked == true){
				check = true;
				break;
			}else{
				lv_check = false;
			}
		}
	}
	//alert msg
	if(!check){
		checkOverrideDialog.show();
	}else{
		confirmOverrideDialog.show();
	}
	
	return false;
}
