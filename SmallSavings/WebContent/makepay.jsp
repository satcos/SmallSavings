<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Post Office Recurring Deposit-Make Payment</title>
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
					generateMenu("makepay");
					generateHeader();
			</script>
	<div id="page">
	<div id="page-bgtop">
	<div id="page-bgbtm">
	<div id="content">
		<form action="Payment" method="post" onsubmit= "return valpayment();">
			<input type="hidden" value="paymentstep1" name="action" />
			<table border="0" padding="0" width="540">
				<tr><td width="150" height="40">Date of Payment:</td><td width="150"><input type="text" name="dop" maxlength="20" id="DateOfJoining" readonly /></td><td width="240"><p id="dojout" class="errmsg"></p></td></tr>
				<tr><td width="150" height="40">No:</td><td width="150">
					<select name="payno" id="paynoin" onBlur="valpayno()" onclick="valpayno()">
					<option value="payno_select">Select</option>
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
					<option value="4">4</option>
					<option value="5">5</option>
					<option value="6">6</option>
					</select></td><td width="240"><p id="paynoout" class="errmsg"></p></td></tr>
				<tr><td colspan="2"><center><input type="submit" value="Submit"></center></td><td></td></tr>
			</table>
		</form>
	</div>
	
	<div id="sidebar">
		<img src="images/member.gif">
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
	</body>
</html>