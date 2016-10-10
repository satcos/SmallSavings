<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Post Office Recurring Deposit-Print</title>
		<script type ="text/javascript" src = "js/Template.js" language = "javascript" ></script>
		<link rel="stylesheet" type="text/css" href="css/style.css" />
	</head>
	<body>
		<div id="wrapper">
			<!-- Java Script to generate Menu and Header -->
			<script type="text/javascript">
					generateMenu("print");
					generateHeader();
			</script>
	<div id="page">
		<div id="printyear">
			<c:choose>
				<c:when test="${sessionScope.level=='Year'}">
					<div id="printyearTop"></div>
					<ul>
						<c:forEach var="year" items="${sessionScope.payYear}">
							<li><a href="Payment?action=printMonth&year=${year}"><c:out value='${year}'/></a></li>
						</c:forEach>
					</ul>
					<c:remove var="level" scope="session"/>
				</c:when>
				
				<c:when test="${sessionScope.level=='Month'}">
					<div id="printyearTop">
						<h3>Year ${year}</h3>
					</div>
					<ul>
						<c:choose>
							<c:when test="${sessionScope.payMonth[0]=='true'}"><li><a href="Payment?action=printDay&year=${year}&month=1">Jan</a></li></c:when>
							<c:otherwise><li>Jan</li></c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${sessionScope.payMonth[1]=='true'}"><li><a href="Payment?action=printDay&year=${year}&month=2">Feb</a></li></c:when>
							<c:otherwise><li>Feb</li></c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${sessionScope.payMonth[2]=='true'}"><li><a href="Payment?action=printDay&year=${year}&month=3">Mar</a></li></c:when>
							<c:otherwise><li>Mar</li></c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${sessionScope.payMonth[3]=='true'}"><li><a href="Payment?action=printDay&year=${year}&month=4">Apr</a></li></c:when>
							<c:otherwise><li>Apr</li></c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${sessionScope.payMonth[4]=='true'}"><li><a href="Payment?action=printDay&year=${year}&month=5">May</a></li></c:when>
							<c:otherwise><li>May</li></c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${sessionScope.payMonth[5]=='true'}"><li><a href="Payment?action=printDay&year=${year}&month=6">Jun</a></li></c:when>
							<c:otherwise><li>Jun</li></c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${sessionScope.payMonth[6]=='true'}"><li><a href="Payment?action=printDay&year=${year}&month=7">Jul</a></li></c:when>
							<c:otherwise><li>Jul</li></c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${sessionScope.payMonth[7]=='true'}"><li><a href="Payment?action=printDay&year=${year}&month=8">Aug</a></li></c:when>
							<c:otherwise><li>Aug</li></c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${sessionScope.payMonth[8]=='true'}"><li><a href="Payment?action=printDay&year=${year}&month=9">Sep</a></li></c:when>
							<c:otherwise><li>Sep</li></c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${sessionScope.payMonth[9]=='true'}"><li><a href="Payment?action=printDay&year=${year}&month=10">Oct</a></li></c:when>
							<c:otherwise><li>Oct</li></c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${sessionScope.payMonth[10]=='true'}"><li><a href="Payment?action=printDay&year=${year}&month=11">Nov</a></li></c:when>
							<c:otherwise><li>Nov</li></c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${sessionScope.payMonth[11]=='true'}"><li><a href="Payment?action=printDay&year=${year}&month=12">Dec</a></li></c:when>
							<c:otherwise><li>Dec</li></c:otherwise>
						</c:choose>
					
					</ul>
					<c:remove var="level" scope="session"/>
				</c:when>
				
				<c:when test="${sessionScope.level=='Day'}">
					<div id="printyearTop">
						<h3>Year ${year} Month ${month}</h3>
					</div>
					<ul>
						<c:forEach var="result" items="${sessionScope.payDay}">
							<li><a href="Payment?dop=${result.getDop()}&payno=${result.getPayNo()}&action=print"><c:out value='${result.getDop()} ${result.getPayNo()}'/></a></li>		
						</c:forEach>
					</ul>
					<c:remove var="level" scope="session"/>
				</c:when>
				
				<c:otherwise></c:otherwise>
			</c:choose>
		
		</div>
	<div style="clear: both;">&nbsp;</div>
	</div>
	<!-- end #page -->
</div>
		<!-- Java Script to generate footer -->
		<script type="text/javascript">
				generateFooter();
		</script>
	</body>
</html>