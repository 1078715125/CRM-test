<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ include file="/commons/common.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>客户构成分析</title>
</head>
<body>

	<div class="page_title">客户构成分析</div>
	<div class="button_bar">
		<button class="common_button" onclick="document.forms[1].submit();">
			查询</button>
	</div>

	<form action="report/consist" method="post" id="command">
		<div id="listView" style="display: block;">
			<table class="query_form_table" border="0" cellPadding="3"
				cellSpacing="0">
				<tr>
					<th>查询方式</th>
					<td><select name="search_EQS_type">
							<option value="level"
								<c:if test="${EQS_type== 'level' }">
									selected="selected"
								</c:if>>按等级</option>
							<option value="credit"
								<c:if test="${EQS_type== 'credit' }">
									selected="selected"
								</c:if>>按信用度</option>
							<option value="satify"
								<c:if test="${EQS_type== 'satify' }">
									selected="selected"
								</c:if>>按满意度</option>
					</select></td>
					<th>&nbsp;</th>
					<td>&nbsp;</td>
				</tr>
			</table>
			<!-- 列表数据 -->
			<br />

			<c:if test="${page != null && page.totalElements > 0 }">
				<table class="data_list_table" border="0" cellPadding="3"
					cellSpacing="0">
					<tr>
						<th>序号</th>
						<th>${EQS_type}</th>
						<th>客户数量</th>
					</tr>

					<c:forEach var="objects" items="${page.content }"
						varStatus="status">
						<tr>
							<td class="list_data_number">${status.count}</td>
							<td class="list_data_ltext">${objects['name']}</td>
							<td class="list_data_number">${objects['count']}</td>
						</tr>
					</c:forEach>
				</table>
				<tags:page page="${page}" formId="command" />
			</c:if>
			<c:if test="${page == null || page.totalElements == 0 }">
				没有任何数据
			</c:if>
		</div>
	</form>
</body>
</html>