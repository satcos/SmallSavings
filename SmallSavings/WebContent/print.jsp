<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>PORD ${sessionScope.report.getDate()} - ${sessionScope.report.getPayno()}</title>
		<script type ="text/javascript" src = "js/Template.js" language = "javascript" ></script>
		<script type ="text/javascript" src = "js/srch.js" language = "javascript" ></script>
		<link rel="stylesheet" type="text/css" href="css/style.css" />
	</head>
	<body>
		<div id="wrapper">
			<!-- Java Script to generate Menu and Header -->
			<script type="text/javascript">
					generateMenu("print");
			</script>
<input type="image" src="images/print.png" name="print" id="non-printable" class=normaltext onclick="JavaScript:window.print();" value="print" width="60" height="60">
	<div id="onscreendisp">
		<c:if test="${sessionScope.msg!=null}">
			<h6><c:out value='${msg}'/></h6>
			<c:remove var="msg" scope="session"/>
		</c:if>
		<table width="950">
			<tr><td width="950" colspan="6"><center><h1>SCHEDULE FOR DEPOSIT IN PORD ACCOUNT</h1></center></td></tr>
			<tr><td colspan="3">Name and Certificate of Authority : ${sessionScope.report.getUserName()}</td><td colspan="3" align="right">No. of the MPKBY Agent : ${sessionScope.report.getAgentNo()} </td> </tr>
			<tr><td colspan=2>Date : ${sessionScope.report.getDate()}</td><td colspan=2>No.of Accounts:${sessionScope.report.getNoOfAccount()}</td><td colspan=2 align="right">Commission: Rs ${sessionScope.report.getComission()}</td></tr>
		</table>
		<div style="clear: both;">&nbsp;</div>
		<table border="1" cellspacing="0"	cellpadding="0" width="950">
			<tr height="35">
				<th width="50">Slno</th>
				<th width="200">Name/Address</th>
				<th width="75">Amount</th>
				<th width="75">Fine</th>
				<th width="100">Account No</th>
				<th width="140">Aslaas</th>
				<th width="75">Balance</th>
				<th width="200">Remark</th>
				<th width="25">Delete</th>
			</tr>
			<c:set var="color" value='true'/>
			<c:forEach var="result" items="${sessionScope.report.table}">
				<tr height="25" <c:if test="${color == 'true'}">${"bgcolor='#FFFCAC'"}</c:if>>
				 	<td>&nbsp;${result.getMonth()}</td>
					<td>&nbsp;${result.getAdd()}&nbsp;</td>
					<td align="right">${result.getAmount()}&nbsp;</td>
					<td align="right">${result.getFine()}&nbsp;</td>
					<td align="center">${result.getAccno()}</td>
					<td>&nbsp;${result.getAslaas()}</td>
					<td align="right">${result.getBalance()}&nbsp;</td>
					<td>&nbsp;${result.getRemarks()}</td>
					<td><a href="Payment?slno=${result.getSlno()}&dop=${sessionScope.report.getDate()}&payno=${sessionScope.report.getPayno()}&action=deleteSingle" onclick="return confirmDelete();"> <img src="images/delete.gif"></a></td>
				</tr>
				<c:set var="color" value="${not color}"/>
			</c:forEach>
				<tr><td colspan="2"></td><td align="right">Rs: ${sessionScope.report.getTotalAmount()}</td><td align="right">Rs: ${sessionScope.report.getFine()}</td><td colspan="4"></td></tr>
		</table>
		<div style="clear: both;">&nbsp;</div>
		<div style="clear: both;">&nbsp;</div>
		<div style="clear: both;">&nbsp;</div>
		<div style="clear: both;">&nbsp;</div>
		<div style="clear: both;">&nbsp;</div>
		<!-- Java Script to generate footer -->
		<script type="text/javascript">
				generateFooter();
		</script>
	</div>

	<div id="printable">
		<table width="950">
			<tr><td width="950" colspan="6"><center><h1>SCHEDULE FOR DEPOSIT IN PORD ACCOUNT</h1></center></td></tr>
			<tr><td colspan="3">Name and Certificate of Authority : S.Kala</td><td colspan="3" align="right">No. of the MPKBY Agent : 339/2000 </td> </tr>
			<tr><td colspan=2>Date:${sessionScope.report.getDate()}</td><td colspan=2>No.of Accounts:${sessionScope.report.getNoOfAccount()}</td><td colspan=2 align="right">Commission: Rs ${sessionScope.report.getComission()}</td></tr>
		</table>
		<div style="clear: both;">&nbsp;</div>
		<table border="1" cellspacing="0"	cellpadding="0" width="950">
			<tr height="35">
				<th width="50">Slno</th>
				<th width="200">Name/Address</th>
				<th width="75">Amount</th>
				<th width="75">Fine</th>
				<th width="100">Account No</th>
				<th width="140">Aslaas</th>
				<th width="75">Balance</th>
				<th width="200">Remark</th>
			</tr>
			<c:set var="rowCount" value='6'/>			
			<c:forEach var="result" items="${sessionScope.report.table}">
				<c:if test="${rowCount == 36}">
					</table>
					<div id="breakhere"></div>
					<table border="1" cellspacing="0"	cellpadding="0" width="950">
						<tr id="tablehead">
							<th width="50">Slno</th>
							<th width="200">Name/Address</th>
							<th width="75">Amount</th>
							<th width="75">Fine</th>
							<th width="100">Account No</th>
							<th width="140">Aslaas</th>
							<th width="75">Balance</th>
							<th width="200">Remark</th>
						</tr>
					</div>
					<c:set var="rowCount" value="${0}"/>
				</c:if>
				<tr height="40">
				 	<td>&nbsp;${result.getMonth()}</td>
					<td>&nbsp;${result.getAdd()}&nbsp;</td>
					<td align="right">${result.getAmount()}&nbsp;</td>
					<td align="right">${result.getFine()}&nbsp;</td>
					<td align="center">${result.getAccno()}</td>
					<td>&nbsp;${result.getAslaas()}</td>
					<td align="right">${result.getBalance()}&nbsp;</td>
					<td>&nbsp;${result.getRemarks()}</td>
				</tr>
				<c:set var="rowCount" value="${1 + rowCount}"/>
			</c:forEach>
				<tr><td colspan="2"></td><td align="right">Rs: ${sessionScope.report.getTotalAmount()}</td><td align="right">Rs: ${sessionScope.report.getFine()}</td><td colspan="4"></td></tr>
		</table>
	</div>
</div>
	</body>
</html>