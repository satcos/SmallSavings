<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList,java.util.Iterator,javabean.SearchBean" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Post Office Recurring Deposit-New Account</title>
		<script type ="text/javascript" src = "js/Template.js" language = "javascript" ></script>
		<script type ="text/javascript" src = "js/newacc.js" language = "javascript" ></script>
		<script type="text/javascript" src="js/DatePick.js"></script>
		<script type="text/javascript">
			window.onload = function(){
				new JsDatePick({
					useMode:2,
					target:"DateOfJoining",
					dateFormat:"%Y-%m-%d"
					
				});
				new JsDatePick({
					useMode:2,
					target:"DateOfPayment",
					dateFormat:"%Y-%m-%d"
					
				});
			};
		</script>
		<link rel="stylesheet" type="text/css" media="all" href="css/DatePick.css" />
		<link rel="stylesheet" type="text/css" href="css/style.css" />
	</head>
	<body>
		<div id="wrapper">
			<!-- Java Script to generate Menu and Header -->
			<script type="text/javascript">
					generateMenu("search");
					generateHeader();
			</script>
	<div id="page">
	<div id="page-bgtop">
	<div id="page-bgbtm">
	<div id="content">
		<h2 class="title"><a href="#">Edit Account</a></h2>
		<form action="SearchAccount" method="post" onsubmit= "return valall()">
		<input type="hidden" value="update" name="action" />
		<input type="hidden" value="${sessionScope.editResult.getSlno()}" name="slnoh" />
		<table border="0" padding="0" width="540">
			<tr><td width="150" height="40">Sl No:</td><td width="150"><input type="text" name="slno" maxlength="6" id="slnoin" onBlur="valslno()" value="${sessionScope.editResult.getSlno()}" disabled/></td><td width="240"><p id="slnoout" class="errmsg"></p></td></tr>
			<tr><td width="150" height="40">Account No:</td><td width="150"><input type="text" name="accountno" maxlength="6" id="accountnoin" onBlur="valaccountno()" value="${sessionScope.editResult.getAccno()}"/></td><td width="240"><p id="accountnoout" class="errmsg"></p></td></tr>
			<tr><td width="150" height="40">Aslaas's No:</td><td width="150"><input type="text" name="aslaas" maxlength="20" id="aslaasin" onBlur="valaslaas()" value="${sessionScope.editResult.getAslaas()}"/></td><td width="240"><p id="aslaasout" class="errmsg"></p></td></tr>
			<tr><td width="150" height="40">Name/Address:</td><td width="150"><input type="text" name="address" maxlength="30" id="addressin" onBlur="valaddress()" value="${sessionScope.editResult.getAdd()}"/></td><td width="240"><p id="addressout" class="errmsg"></p></td></tr>
			<tr><td width="150" height="40">Date of Joining:</td><td width="150"><input type="text" name="doj" maxlength="20" id="DateOfJoining" readonly value="${sessionScope.editResult.getDoj()}"/></td><td width="240"></td></tr>
			<tr><td width="150" height="40">Amount:</td><td width="150"><input type="text" name="amount" maxlength="20" id="amountin" onBlur="valamount()" value="${sessionScope.editResult.getAmount()}"/></td><td width="240"><p id="amountout" class="errmsg"></p></td></tr>
			<tr><td width="150" height="40">Balance:</td><td width="150"><input type="text" name="balance" maxlength="20" id="balin" onBlur="valbal()" value="${sessionScope.editResult.getBalance()}"/></td><td width="240"><p id="balout" class="errmsg"></p></td></tr>
			<tr><td width="150" height="40">Last Date of Payment:</td><td width="150"><input type="text" name="lpd" maxlength="20" id="DateOfPayment" readonly value="${sessionScope.editResult.getLpd()}"/></td><td width="240"></td></tr>		
			<tr><td colspan="2"><center><a href="SearchAccount?slno=${sessionScope.editResult.getSlno()}&action=edit&position=Previous"><img src="images/1394305992_Previous.png" /></a>
				<input type="submit" value="Submit">   <input type="reset" value="Reset">
				<a href="SearchAccount?slno=${sessionScope.editResult.getSlno()}&action=edit&position=Next"><img src="images/1394305865_Next.png" /></a></center></td><td></td></tr>
		</table>
		</form>
	</div>
	<div id="sidebar">
		<img src="images/save.gif">
	</div>
	<div style="clear: both;">&nbsp;</div>
	</div>
	</div>
	</div>
	<!-- end #page -->
</div>
		<!-- Java Script to generate footer -->
		<script type="text/javascript">
				generateFooter();
		</script>
		<c:if test="${sessionScope.success=='true'}">
			<script type="text/javascript">
				informUpdation();
			</script>
			<c:remove var="success" scope="session"/>
		</c:if>
		<c:if test="${sessionScope.msg!=null}">
			<script type="text/javascript">
				showMessage("${sessionScope.msg}");
			</script>
			<c:remove var="msg" scope="session"/>
		</c:if>
	</body>
</html>