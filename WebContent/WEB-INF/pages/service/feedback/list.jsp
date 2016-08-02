<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ include file="/commons/common.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>客户服务管理</title>
<script type="text/javascript">
	$(function(){
		$("#clear").click(function(){
			$("[name^='search_']").val("");
			return false;
		});
	});
</script>
</head>
<body>

	<div class="page_title">客户服务管理</div>
	<div class="button_bar">
		<button class="common_button" onclick="window.location.href='service/create';">新建</button>
		<button class="common_button" id="clear">清除条件</button>
		<button class="common_button" onclick="document.forms[1].submit();">
			查询</button>
	</div>

	<form action="service/feedback" method="post" id="command">
		<table class="query_form_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th>服务类型</th>
				<td><input type="text" name="search_LIKES_serviceType" value="${LIKES_serviceType }"/></td>
				<th>概要</th>
				<td><input type="text" name="search_LIKES_serviceTitle" value="${LIKES_serviceTitle }"/></td>
			</tr>
			<tr>
				<th>客户</th>
				<td><input type="text" name="search_LIKES_customerName" value="${LIKES_customerName }"/></td>
				<th>创建时间</th>
				<td><input type="text" name="search_GED_createDateGt" size="10" value="${GED_createDateGt }"/>
					- <input type="text" name="search_LED_createDateLt" size="10" value="${LED_createDateLt }"/></td>
			</tr>
		</table>

		<!-- 列表数据 -->
		<br />

		<c:if test="${page != null && page.totalElements > 0 }">
			<table class="data_list_table" border="0" cellPadding="3"
				cellSpacing="0">
				<tr>
					<th>编号</th>
					<th>服务类型</th>
					<th>概要</th>
					<th>客户</th>
					<th>创建人</th>
					<th>创建时间</th>
					<th>操作</th>
				</tr>
				<c:forEach var="service" items="${page.content }">
					<tr>
						<td class="list_data_number">${service.id}</td>
						<td class="list_data_text">${service.serviceType}</td>
						<td class="list_data_ltext">${service.serviceTitle}</td>

						<td class="list_data_text">${service.customer.name}</td>
						<td class="list_data_text">${service.createdby.name}</td>
						<td class="list_data_text"><fmt:formatDate
								value="${service.createDate }" pattern="yyyy-MM-dd" /></td>
						<td class="list_data_op"><img
							onclick="window.location.href='service/feedback/${service.id}'"
							title="反馈" src="static/images/bt_feedback.gif"
							class="op_button" /></td>
					</tr>
				</c:forEach>
			</table>
			<tags:page page="${page}" formId="command"/>
		</c:if>
		<c:if test="${page == null || page.totalElements == 0 }">
			没有任何数据
		</c:if>
	</form>
</body>
</html>

