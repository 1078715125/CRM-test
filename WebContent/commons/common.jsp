<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>


<c:set var="ctp" value="${pageContext.request.contextPath }"></c:set>
<base href="http://${pageContext.request.serverName }:${pageContext.request.serverPort }${pageContext.request.contextPath }/"/>
<link href="static/css/styles.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="static/jquery/jquery-1.9.1.min.js"></script>

<c:if test="${message != null }">
	<script type="text/javascript">
		alert("${message}");
	</script>
</c:if>
<form method="post" id="hiddenForm">
	<input type="hidden" name="_method" id="_method"/>
</form>
