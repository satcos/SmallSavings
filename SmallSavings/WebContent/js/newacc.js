function valaccountno()
{
	var taccountno=document.getElementById("accountnoin");
	if(taccountno.value.length!=6||taccountno.value.match(/^[0-9]+$/)==null)
	{
		document.getElementById("accountnoout").innerHTML="Please enter a valid Account No.";
		return false;
	}
	else
	{
		document.getElementById("accountnoout").innerHTML="";
		return true;
	}
}
function valaslaas()
{
	var taslaas=document.getElementById("aslaasin");
	if(taslaas.value==null||taslaas.value=="")
	{
		document.getElementById("aslaasout").innerHTML="Please enter ASLAAS No";
		return false;
	}
	else
	{
		document.getElementById("aslaasout").innerHTML="";
		return true;
	}
}
function valaddress()
{
	var taddress=document.getElementById("addressin");
	if(taddress.value==null||taddress.value=="")
	{
		document.getElementById("addressout").innerHTML="Please enter Name/Address";
		return false;
	}
	else
	{
		document.getElementById("addressout").innerHTML="";
		return true;
	}
}
function valamount()
{
	var tamount=document.getElementById("amountin");
	if(tamount.value.match(/^[0-9]+$/)==null)
	{
		document.getElementById("amountout").innerHTML="Please enter valid Amount.";
		return false;
	}
	else
	{
		document.getElementById("amountout").innerHTML="";
		return true;
	}
}
function valdoj()
{
	var tdoj=document.getElementById("DateOfJoining");
	if(tdoj.value==null||tdoj.value=="")
	{
		document.getElementById("dojout").innerHTML="Please enter Date of Joining";
		return false;
	}
	else
	{
		document.getElementById("dojout").innerHTML="";
		return true;
	}
}
function valpayno()
{
	var tpayno=document.getElementById("paynoin");
	if(tpayno.value=="payno_select")
	{
		document.getElementById("paynoout").innerHTML="Please select payment no.";
		return false;
	}
	else
	{
		document.getElementById("paynoout").innerHTML="";
		return true;
	}
}


function valall()
{
	var ans=true;
	ans=ans && valaddress();
	ans=ans && valamount();
	ans=ans && valdoj();
	ans=ans && valpayno();
	return ans;
}

function valpayment()
{
	var ans=true;
	ans=ans && valdoj();
	ans=ans && valpayno();
	return ans;
}

function informAddition(slno)	{
	alert("Account Successfully Added." + slno);
}

function informUpdation()	{
	alert("Account Successfully updated.");
}

function showMessage(msg)	{
	alert(msg);
}