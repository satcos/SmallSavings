<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
			};
		</script>
		<link rel="stylesheet" type="text/css" media="all" href="css/DatePick.css" />
		<link rel="stylesheet" type="text/css" href="css/style.css" />
	</head>
	<body>
		<div id="wrapper">
			<!-- Java Script to generate Menu and Header -->
			<script type="text/javascript">
					generateMenu("newacc");
					generateHeader();
			</script>
	<div id="page">
	<div id="page-bgtop">
	<div id="page-bgbtm">
	<div id="content">
		<h2 class="title"><a href="#">Add New Account</a></h2>
		<form action="SearchAccount" method="post" onsubmit= "return valall()">
		<input type="hidden" value="newacc" name="action" />
		<table border="0" padding="0" width="540">
			<tr><td width="150" height="40">Name/Address:</td><td width="150"><input type="text" name="address" maxlength="30" id="addressin" onBlur="valaddress()"/></td><td width="240"><p id="addressout" class="errmsg"></p></td></tr>
			<tr><td width="150" height="40">Amount:</td><td width="150"><input type="text" name="amount" maxlength="20" id="amountin" onBlur="valamount()"/></td><td width="240"><p id="amountout" class="errmsg"></p></td></tr>
			<tr><td width="150" height="40">Date of Joining:</td><td width="150"><input type="text" name="doj" maxlength="20" id="DateOfJoining" readonly /></td><td width="240"><p id="dojout" class="errmsg"></p></td></tr>
			<tr><td width="150" height="40">Pay No:</td><td width="150">
				<select name="payno" id="paynoin" onBlur="valpayno()" onclick="valpayno()">
				<option value="payno_select">Select</option>
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="3">3</option>
				<option value="4">4</option>
				<option value="5">5</option>
				</select></td><td width="240"><p id="paynoout" class="errmsg"></p></td></tr>
			<tr><td colspan="2"><center><input type="submit" value="Submit">   <input type="reset" value="Reset"></center></td><td></td></tr>
		</table>
		</form>		
	</div>
	<div id="sidebar">
		<img src="images/save.gif">
	</div>
	<div style="clear: both;">&nbsp;</div>
	<div style="clear: both;">&nbsp;</div>
	<div style="clear: both;">&nbsp;</div>
	<div style="clear: both;">&nbsp;</div>
	<div style="clear: both;">&nbsp;</div>
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
		<c:if test="${sessionScope.slno!=null}">
			<script type="text/javascript">
				informAddition(${sessionScope.slno});
			</script>
			<c:remove var="slno" scope="session"/>
		</c:if>
	</body>
</html>