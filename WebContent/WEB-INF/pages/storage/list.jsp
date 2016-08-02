<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ include file="/commons/common.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>库存查询</title>
<script type="text/javascript">
	$(function(){
		$("img[id^='delete-']").click(function(){
			var name = $(this).parents("tr").children("td:eq(1)").text().trim();
			var flag = confirm("确认要删除【"+name+"】这个名称的产品库存吗?");
			if(!flag){
				return false;
			}
			var id = this.id.split("-")[1];
			var url = "storage/create/"+id;
			$("#_method").val("DELETE");
			$("#hiddenForm").prop("action",url).submit();
			return false;
		});
		
		$("#clear").click(function(){
			$("[name^='search_']").val("");
			return false;
		});
	});
</script>
</head>
<body>
	<div class="page_title">
		库存管理
	</div>
	<div class="button_bar">
		<button class="common_button"
			onclick="window.location.href='storage/create'">
			库存添加
		</button>
		<button class="common_button" id="clear">清除条件</button>
		<button class="common_button" onclick="document.forms[1].submit();">
			查询
		</button>
	</div>
	
	<form action="storage/list" method="POST" id="command">
		<table class="query_form_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th>
					产品
				</th>
				<td>
					<input type="text" name="search_LIKES_productName" value="${LIKES_productName }"/>
				</td>
				<th>
					仓库
				</th>
				<td>
					<input type="text" name="search_LIKES_wareHouse" value="${LIKES_wareHouse }"/>
				</td>
				<th>
					&nbsp;
				</th>
				<td>
					&nbsp;
				</td>
			</tr>
		</table>
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
					产品
				</th>
				<th>
					仓库
				</th>
				<th>
					货位
				</th>
				<th>
					件数
				</th>
				<th>
					备注
				</th>
				<th>
					操作
				</th>
			</tr>
			<c:forEach var="storage" items="${page.content }">
				<tr>
					<td class="list_data_number">
						${storage.id}
					</td>
					<td class="list_data_ltext">
						${storage.product.name}
					</td>
					<td class="list_data_ltext">
						${storage.wareHouse}
					</td>
					<td class="list_data_text">
						${storage.stockWare}
					</td>

					<td class="list_data_number">
						${storage.stockCount}
					</td>
					<td class="list_data_ltext">
						${storage.memo}
					</td>
					<td class="list_data_op">
						<img onclick="window.location.href='storage/create/${storage.id }'" 
							title="修改" src="static/images/bt_edit.gif" class="op_button" />
						<img id="delete-${storage.id }"
							title="删除" src="static/images/bt_del.gif" class="op_button" />

					</td>
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