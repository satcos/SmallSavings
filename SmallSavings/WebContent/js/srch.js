function valslno()	{
	var tslno=document.getElementById("slnoin");
	if(tslno.value==null||tslno.value=="")
	{
		document.getElementById("srcherr").innerHTML="";
		return true;
	}
	else if(tslno.value.match(/^[0-9]+$/)==null)
	{
		document.getElementById("srcherr").innerHTML="Please enter a valid Sl No or Leave it Blank.";
		return false;
	}
	else
	{
		document.getElementById("srcherr").innerHTML="";
		return true;
	}
}
function valaccountno()	{
	var taccountno=document.getElementById("accountnoin");
	if(taccountno.value==null ||taccountno.value=="")
	{
		document.getElementById("srcherr").innerHTML="";
		return true;
	}
	else if(taccountno.value.match(/^[0-9]+$/)==null)
	{
		document.getElementById("srcherr").innerHTML="Please enter a valid Account No or Leave it Blank.";
		return false;
	}
	else
	{
		document.getElementById("srcherr").innerHTML="";
		return true;
	}
}
function valblank()	{
	if((document.getElementById("slnoin").value==null||document.getElementById("slnoin").value=="")&&(document.getElementById("accountnoin").value==null||document.getElementById("accountnoin").value=="")&&(document.getElementById("addressin").value==null||document.getElementById("addressin").value=="")&&(document.getElementById("DateOfJoining").value==null||document.getElementById("DateOfJoining").value==""))
	{
		document.getElementById("srcherr").innerHTML="Atleast Fill One Field";
		return false;
	}
	else
	{
		document.getElementById("srcherr").innerHTML="";
		return true;
	}
}
function valall()	{
	var ans=true;
	ans=ans && valslno();
	ans=ans && valaccountno();
	ans=ans && valblank();
	return ans;
}
function confirmDelete()	{
	var response=confirm("Are you sure, you want to delete?");
	if(response==true)
		return true;
	else
		return false;
}
function informDeletion()	{
	alert("Account Successfully Deleted.");
}
function showMessage(msg)	{
	alert(msg);
}
