<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ include file="/commons/common.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>客户贡献分析</title>
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

	<div class="page_title">客户贡献分析</div>
	<div class="button_bar">
		<button class="common_button" id="clear">清除条件</button>
		<button class="common_button" onclick="document.forms[1].submit();">
			查询</button>
	</div>

	<form action="report/pay" method="post" id="command">
		<div id="listView" style="display: block;">
			<table class="query_form_table" border="0" cellPadding="3"
				cellSpacing="0">
				<tr>
					<th>客户名称</th>
					<td><input type="text" name="search_LIKES_name" value="${LIKES_name }"/></td>
					<th>日期</th>
					<td><input type="text" name="search_GED_dateGe" size="10" value="${GED_dateGe }"/> - <input
						type="text" name="search_LED_dateLe" size="10" value="${LED_dateLe }"/></td>
				</tr>
			</table>
			<!-- 列表数据 -->
			<br />

			<c:if test="${page != null && page.totalElements > 0 }">
				<table class="data_list_table" border="0" cellPadding="3"
					cellSpacing="0">
					<tr>

						<th>客户名称</th>
						<th>订单金额（元）</th>
					</tr>
					<c:forEach var="customer" items="${page.content }">
						<tr>

							<td align="center">${customer['name']}</td>
							<td align="center">${customer['orderMoney'] }</td>

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
	<br><br>
	<img alt="jFree" src="report/jFree">
</body>
</html>