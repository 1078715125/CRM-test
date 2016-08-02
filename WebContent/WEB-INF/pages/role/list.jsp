<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>

<%@ include file="/commons/common.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>角色管理</title>
<script type="text/javascript">
	$(
			function() {
				$("img[id^='delete-']").click(
						function() {
							var name = $(this).parents("tr").children(
									"td:eq(1)").text().trim();
							var flag = confirm("确认要删除【" + name + "】这个角色吗?");
							if (!flag) {
								return false;
							}
							var id = this.id.split("-")[1];
							var url = "role/create/" + id;
							$("#_method").val("DELETE");
							$("#hiddenForm").prop("action", url).submit();
							return false;
						});

			})
</script>
</head>

<body class="main">

	<div class="page_title">角色管理</div>

	<div class="button_bar">
		<button class="common_button"
			onclick="window.location.href='role/create'">新建</button>

	</div>

	<form action="role/list" method="post" id="command">

		<!-- 列表数据 -->
		<br />
		<c:if test="${page != null && page.totalElements > 0 }">
			<table class="data_list_table" border="0" cellPadding="3"
				cellSpacing="0">
				<tr>
					<th class="data_title">编号</th>
					<th class="data_title">角色名</th>
					<th class="data_title">角色描述</th>
					<th class="data_title">状态</th>
					<th class="data_title">操作</th>
				</tr>

				<c:forEach var="role" items="${page.content }">
					<tr>
						<td class="data_cell" style="text-align: right; padding: 0 10px;">${role.id}</td>
						<td class="data_cell" style="text-align: center;">${role.name}</td>
						<td class="data_cell" style="text-align: left;">${role.description}</td>
						<td class="data_cell" style="text-align: center;">${role.enabled ? "有效":"无效"}</td>
						<td class="data_cell"><img
							onclick="window.location.href='role/assign/${role.id}'"
							title="分配权限" src="static/images/bt_linkman.gif" class="op_button" />
							<img onclick="window.location.href='role/create/${role.id}'"
							class="op_button" src="static/images/bt_edit.gif" title="编辑" />
							<img id="delete-${role.id}'" title="删除"
							src="static/images/bt_del.gif" class="op_button" /></td>
					</tr>
				</c:forEach>

			</table>
			<tags:page page="${page}" formId="command" />
		</c:if>

		<c:if test="${page == null || page.totalElements == 0 }">
			没有任何数据
		</c:if>

	</form>
</body>
</html>

