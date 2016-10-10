<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Post Office Recurring Deposit-Payment</title>
		<script type ="text/javascript" src = "js/Template.js" language = "javascript" ></script>
		<script type ="text/javascript" src = "js/srch.js" language = "javascript" ></script>
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
	<h2><c:out value="Date:${sessionScope.dop} Payment No:${sessionScope.payno}"/></h2>
	<table border="0" cellspacing="2"	cellpadding="0">
			<tr><th height="5" width="50">Slno</th>
			<th height="5" width="200">Name/Address</th>
			<th height="5" width="100">Account No</th>
			<th height="5" width="75">Amount</th>
			<th height="5" width="75">Balance</th>
			<th height="5" width="100">Last Payment Date</th>
			<th height="5" width="50">Month Paying</th>
			<th height="5" width="100">Amount Paying</th>
			<th height="5" width="50">Fine</th>
			<th height="5" width="100" align="center">Balance After Payment</th>
			<th height="5" width="50" align="center">Slno</th>
			<th height="5" width="50">Pay</th>
			</tr>
	</table>
	<form action="Payment" method="post">
	<div id="payment">
	<input type="hidden" value="paymentstep2" name="action" />
		<table border="0" cellspacing="2"	cellpadding="0">
			<c:set var="color" value='true'/>
			<c:forEach var="result" items="${sessionScope.payDisp}">
				<tr <c:if test="${color == 'true'}">${"bgcolor='#FFFCAC'"}</c:if>>
				 	<td height="5" width="50">${result.getSlno()}</td>
					<td height="5" width="200">${result.getAdd()}</td>
					<td height="5" width="100">${result.getAccno()}</td>
					<td height="5" width="75">${result.getAmount()}</td>
					<td height="5" width="75">${result.getBalance()}</td>
					<td height="5" width="100">${result.getLpd()}</td>
					<td height="5" width="50" align="center">${result.getMonthPaying()}</td>
					<td height="5" width="100">${result.getAmountPaying()}</td>
					<td height="5" width="50" align="right">${result.getFinePaying()}</td>
					<td height="5" width="100" align="right">${result.getPostBalance()}</td>
					<td height="5" width="50" align="center">${result.getSlno()}</td>
					<td height="5" width="50" align="center"><input type="checkbox" name="pay" value="${result.getSlno()}" /></td>
				</tr>
				<c:set var="color" value="${not color}"/>	
			</c:forEach>
		</table>
		</div>
		<center><input type="submit" value="Submit"></center>
		</form>
	</div>
	<div style="clear: both;">&nbsp;</div>
	<div style="clear: both;">&nbsp;</div>
	<div style="clear: both;">&nbsp;</div>
	</div>
		<!-- Java Script to generate footer -->
		<script type="text/javascript">
				generateFooter();
		</script>
	</body>
</html>