<%@page import="edu.coursera.CourseDetail"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
table, th, td {
    border: 1px solid black;
    border-collapse: collapse;
}
th, td {
    padding: 5px;
    text-align: left;    
}
</style>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<table style="width:100%">
	<tr>
	
	  <th><b>Course Name</b></th>
	  <th><b>Instructor Name</b></th>
	  <th><b>Partners Name</b></th>
	  <th><b>Logo</b></th>
	
	</tr>

	<%
		ArrayList<CourseDetail> courseInfo = (ArrayList<CourseDetail>)request.getAttribute("courseInfo");
		for(int i=0;i<courseInfo.size();i++)
		{
			out.println("<tr>");
			out.println("<td>" + courseInfo.get(i).getCourseName() + "</td>");
			out.println("<td>" + courseInfo.get(i).getInstructorsName() + "</td>");
			out.println("<td>" + courseInfo.get(i).getPartnersName() + "</td>");
			out.println("<td><img src=" + "\"" + courseInfo.get(i).getPartnersImageUrl().get(0) + "\"" + "width=\"100\" height=\"100\"></td>");
			out.println("<tr>");
		}
	%>
			
</table>
	
</body>
</html>