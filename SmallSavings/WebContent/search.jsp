<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Post Office Recurring Deposit-Search</title>
		<script type ="text/javascript" src = "js/Template.js" language = "javascript" ></script>
		<script type ="text/javascript" src = "js/srch.js" language = "javascript" ></script>
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
					generateMenu("search");
					generateHeader();
			</script>
	<div id="page">
		<h2 class="title"><a href="#">Search Account</a></h2>
		<form action="SearchAccount" method="post" onsubmit= "return valall()">
		<input type="hidden" value="search" name="action" />
			<table border="0" padding="0">
				<tr align="right"><td width="50">Sl No:</td><td><input type="text" name="slno" maxlength="20" size="3" id="slnoin"/></td>
					<td width="100">Account No:</td><td><input type="text" name="accountno" maxlength="6" size="6" id="accountnoin"/></td>
					<td width="110">Name/Address:</td><td><input type="text" name="address" maxlength="20" id="addressin"/></td>
					<td width="120">Date Of Joining:</td><td><input type="text" name="doj" maxlength="20" id="DateOfJoining" readonly size="8"/></td><td><input type="submit" value="Search"/> <input type="reset" value="Reset"/></td></tr>
				<tr><td colspan="9"><center><p id="srcherr" class="errmsg"> </p></center></td></tr>
			</table>
			<center><p id="srcherr" class="errmsg"></p></center>
		</form>
		<c:if test="${sessionScope.srchResult!=null}">
			<table border="0" cellspacing="2"	cellpadding="0">
				<tr><th height="5" width="50">Slno</th>
				<th height="5" width="200">Name/Address</th>
				<th height="5" width="100">Account No</th>
				<th height="5" width="140">Aslaas</th>
				<th height="5" width="100">DOJ</th>
				<th height="5" width="75">Amount</th>
				<th height="5" width="75">Balance</th>
				<th height="5" width="100">Last Payment Date</th>
				<th height="5" width="100">Action</th>
				</tr>
				<c:set var="color" value='true'/>
				<c:forEach var="result" items="${sessionScope.srchResult}">
					<tr <c:if test="${color == 'true'}">${"bgcolor='#FFFCAC'"}</c:if>>
					 	<td>${result.getSlno()}</td>
						<td>${result.getAdd()}</td>
						<td>${result.getAccno()}</td>
						<td>${result.getAslaas()}</td>
						<td>${result.getDoj()}</td>
						<td>${result.getAmount()}</td>
						<td>${result.getBalance()}</td>
						<td>${result.getLpd()}</td>
						<td><a href="SearchAccount?slno=${result.getSlno()}&action=edit"> Edit</a> / <a href="SearchAccount?slno=${result.getSlno()}&action=delete" onclick="return confirmDelete();">Delete</a></td>
					</tr>
					<c:set var="color" value="${not color}"/>	
				</c:forEach>
			</table>
			
			<c:if test="${sessionScope.numberOfResult==0}">
				<center><h3><c:out value='No Match Found'/></h3></center>
			</c:if>
		</c:if>
	</div>
	<div style="clear: both;">&nbsp;</div>
	<div style="clear: both;">&nbsp;</div>
	<div style="clear: both;">&nbsp;</div>
	<div style="clear: both;">&nbsp;</div>
	<div style="clear: both;">&nbsp;</div>
	<div style="clear: both;">&nbsp;</div>
	
	<!-- end #page -->
</div>
		<!-- Java Script to generate footer -->
		<script type="text/javascript">
				generateFooter();
		</script>
		<c:if test="${sessionScope.deleteAccount=='deleted'}">
			<script type="text/javascript">
				informDeletion();
			</script>
			<c:remove var="deleteAccount" scope="session"/>
		</c:if>
	</body>
</html>