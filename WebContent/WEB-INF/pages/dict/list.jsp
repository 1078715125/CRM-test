<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ include file="/commons/common.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>管理</title>
<script type="text/javascript">
	$(function(){
		$("#clear").click(function(){
			$("[name^='search_']").val("");
			return false;
		});
		
		$("img[id^='delete-']").click(function(){
			var name = $(this).parents("tr").children("td:eq(2)").text().trim();
			var flag = confirm("确认要删除【"+name+"】这个条目的字典数据吗");
			if(!flag){
				return false;
			}
			var id = this.id.split("-")[1];
			var url = "dict/create/"+id;
			$("#_method").val("DELETE");
			$("#hiddenForm").prop("action",url).submit();
			return false;
		});
	});
</script>
</head>
<body>
	<div class="page_title">
		基础数据管理
	</div>
	<div class="button_bar">
		<button class="common_button" onclick="window.location.href='dict/create'">
			新建
		</button>
		<button class="common_button" id="clear">清除条件</button>
		<button class="common_button" onclick="document.forms[1].submit();">
			查询
		</button>
	</div>
	
	<form action="dict/list" method="POST" id="command">
		<table class="query_form_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th>
					类别
				</th>
				<td>
					<input type="text" name="search_LIKES_type" value="${LIKES_type }"/>
				</td>
				<th>
					条目
				</th>
				<td>
					<input type="text" name="search_LIKES_item" value="${LIKES_item }"/>
				</td>
				<th>
					值
				</th>
				<td>
					<input type="text" name="search_LIKES_value" value="${LIKES_value }"/>
				</td>
			</tr>
		</table>
	</form>
	<!-- 列表数据 -->
	<br />
	
	<c:if test="${page != null && page.totalElements > 0 }">
		<table class="data_list_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th>
					编号
				</th>
				<th>
					类别
				</th>
				<th>
					条目
				</th>
				<th>
					值
				</th>
				<th>
					操作
				</th>
			</tr>
			<c:forEach var="dict" items="${page.content }">
				<tr>
					<td class="list_data_number">
						${dict.id}
					</td>
					<td class="list_data_text">
						${dict.type}
					</td>
					<td class="list_data_text">
						${dict.item}
					</td>
					<td class="list_data_text">
						${dict.value}
					</td>

					<td class="list_data_op">
						<c:if test="${dict.editable}">
							<img onclick="window.location.href='dict/create/${dict.id }'" 
								title="编辑" src="static/images/bt_edit.gif" class="op_button" />
							<img id="delete-${dict.id }"
								title="删除" src="static/images/bt_del.gif" class="op_button" />
						</c:if>
					</td>
				</tr>
			</c:forEach>			
		</table>
		<tags:page page="${page }" formId="command"/>
	</c:if>
	<c:if test="${page == null || page.totalElements == 0 }">
		没有任何数据
	</c:if>
	
</body>
</html>