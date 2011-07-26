<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
	<c:when test="${isLoggedIn}">
	    <c:set var="title">Google Tasks</c:set>
	</c:when>
	<c:otherwise>
	    <c:set var="title">Log in</c:set>
	</c:otherwise>
</c:choose>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>${title}</title>
<link href="static/style.css" rel="stylesheet" type="text/css"></link>
</head>
<body>

<h1>${title}</h1>

<c:choose>
    <c:when test="${isLoggedIn}">
        <p>Authorized.</p>
    </c:when>
    <c:otherwise>
		<p>Authorize this application to access your Task list by clicking the button below.</p>	
		<a class="googleLogin openidLargeBtn" title="log in with Google" href="auth"></a>
	</c:otherwise>
</c:choose>

</body>
</html>