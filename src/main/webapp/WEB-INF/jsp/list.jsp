<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html;charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<a href="logout">退出</a>
<table>
	<c:forEach items="${users }" var="user" varStatus="index">
		<tr>
			<td>${user.username }</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>