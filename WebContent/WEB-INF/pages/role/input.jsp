<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>维护角色</title>
</head>
<body>
	<c:if test="${role.id == null }">
		<div class="page_title">系统角色添加</div>
	</c:if>
	<c:if test="${role.id != null }">
		<div class="page_title">系统角色修改</div>
	</c:if>
	<div class="button_bar">
		<button class="common_button" onclick="javascript:history.back(-1);">
			返回</button>
		<button class="common_button" onclick="document.forms[1].submit();">
			保存</button>
	</div>

	<form:form action="role/create" method="post" modelAttribute="role">
		<c:if test="${role.id != null }">
			<form:hidden path="id" />
			<input type="hidden" name="_method" value="PUT" />
		</c:if>
		<table class="query_form_table">
			<tr>
				<th>角色名称</th>
				<td><form:input path="name" /></td>
				<th>角色描述</th>
				<td><form:input path="description" /></td>
			</tr>
			<tr>
				<th>状态</th>
				<td><form:select path="enabled">
						<option value="1" selected="selected"
							<c:if test="${role.enabled }">selected="selected"</c:if>>有效</option>
						<option value="0"
							<c:if test="${!role.enabled }">selected="selected"</c:if>>无效</option>
					</form:select> <span class="red_star">*</span></td>
			</tr>
		</table>
	</form:form>
</body>
</html>
