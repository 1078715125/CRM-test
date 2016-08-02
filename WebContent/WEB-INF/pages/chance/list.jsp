<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="gyx"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>销售机会管理</title>
	<script type="text/javascript">
		$(function(){
			
			$("#new").click(function(){
				
				window.location.href = "${ctp}/chance/";
				return false;
			});
			
			$("img[id^=del-]").click(function(){
				
// 				var id = $(this).parents("tr").children("td:first").text();
// 				var name = $(this).parents("tr").children("td:eq(1)").text();

				var id = $(this).next(":hidden").val();
				var name = $(this).parents("tr").children("td:eq(1)").text();
				
				var flag = confirm("确定要删除 【"+name+"】 这个客户吗？");
				if(flag){
					url = "chance/" + id;
					$("#hiddenForm").attr("action",url);
					$("#_method").val("DELETE");
					$("#hiddenForm").submit();
				}
				
				return false;
				
			});
			
			$("#clear").click(function(){
				$("[name^='search_']").val("");
				return false;
			});
		})
	</script>
</head>

<body class="main">
	<form id="command" action="chance/list" method="post">
		<div class="page_title">
			销售机会管理
		</div>
		
		<div class="button_bar">
			<button class="common_button" id="new">
				新建
			</button>
			<button class="common_button" id="clear">清除条件</button>
			<button class="common_button" onclick="document.forms[1].submit();">
				查询
			</button>
		</div>
		
		<table class="query_form_table" border="0" cellPadding="3" cellSpacing="0">
			<tr>
				<th class="input_title">
					客户名称
				</th>
				<td class="input_content">
<!-- 					<input type="text" name="search_LIKE_custName" /> -->
					<input type="text" name="search_LIKES_custName" value="${LIKES_custName }"/>
				</td>
				
				<th class="input_title">
					概要
				</th>
				<td class="input_content">
<!-- 					<input type="text" name="search_LIKE_title" /> -->
					<input type="text" name="search_LIKES_title" value="${LIKES_title }"/>
				</td>
				
				<th class="input_title">
					联系人
				</th>
				<td class="input_content">
<!-- 					<input type="text" name="search_LIKE_contact" /> -->
					<input type="text" name="search_LIKES_contact" value="${LIKES_contact }"/>
				</td>
			</tr>
		</table>
		<br />
		
		<!-- 列表数据 -->
		<c:if test="${empty page.content }">
			暂时没有任何数据！
		</c:if>
		<c:if test="${!empty page.content }">
			<table class="data_list_table" border="0" cellPadding="3" cellSpacing="0">
				<tr>
					<th>
						编号
					</th>
					<th>
						客户名称
					</th>
					<th>
						概要
					</th>
					<th>
						联系人
					</th>
					<th>
						联系人电话
					</th>
					<th>
						创建时间
					</th>
					<th>
						操作
					</th>
				</tr>
				<c:forEach var="chance" items="${page.content }">
					<tr>
						<td class="list_data_number">${chance.id }</td>
						<td class="list_data_text">${chance.custName }</td>
						<td class="list_data_text">${chance.title }</td>
						<td class="list_data_text">${chance.contact }</td>
						<td class="list_data_text">${chance.contactTel }</td>
						<td class="list_data_text">
							<fmt:formatDate value="${chance.createDate }" pattern="yyyy-MM-dd"/>
						</td>
						<td class="list_data_op">
							<img onclick="window.location.href='chance/dispatch/${chance.id}'" 
								title="指派" src="static/images/bt_linkman.gif" class="op_button" />
							<img onclick="window.location.href='chance/${chance.id}'" 
								title="编辑" src="static/images/bt_edit.gif"
								class="op_button" />
							<img title="删除" src="static/images/bt_del.gif" class="op_button" 
							id="del-${chance.id }"/>
							<input type="hidden" value="${chance.id }"/>
						</td>
					</tr>
				</c:forEach>
			</table>
			
			<gyx:page page="${page }" formId="command"/>
		</c:if>
	</form>

	
</body>
</html>
