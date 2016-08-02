<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ include file="/commons/common.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>联系人管理</title>
<script type="text/javascript">

	$(function(){
		
		$("img[id^='del-']").click(function(){
			var id = this.id.split("-")[1];
			var managerId = "${customer.manager.id}";
			if(id == managerId){
				alert("当前联系人为客户经理，不能删除！");
				return false;
			}
// 			var number = $("img[id^='del-']").size();
			var number = "${page.totalElements}";
			if(number <= 1){
				alert("该联系人不能被删除！");
				return false;
			}
			var name = $(this).parents("tr").children("td:first").text().trim();
			var flag = confirm("确认要删除【"+name+"】这个联系人吗？");
			if(!flag){
				return false;
			}
			var url = $(this).next(":hidden").val();
			$("#_method").val("DELETE");
			$("#hiddenForm").attr("action",url).submit();
			return false;
		});
		
// 		var number = $("img[id^='del-']").size();
// 		if(number <= 1){
// 			$("img[id^='del-']").hide();
// 			$("img[id^='del-']").parents("td").append("<font color='red'>唯一联系人 </font>");
// 		} else {
			$("img[id^='del-']").each(function(){
				var id = this.id.split("-")[1];
				var managerId = "${customer.manager.id}";
				if(id == managerId){
					$(this).hide();
					$(this).parents("td").append("<font color='red'>客户经理 </font>");
				
 				}
			});
		
	});
</script>
</head>

<body>

	<div class="page_title">
		联系人管理
	</div>
	<div class="button_bar">

		<button class="common_button" onclick="window.location.href='contact/toCreate/${customer.id }'">
			新建
		</button>
		<button class="common_button" onclick="javascript:history.go(-1);">
			返回
		</button>
	</div>
	
	<form action="contact/list/${customer.id }" method="post" id="command">
		<table class="query_form_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th>
					客户编号
				</th>
				<td>${customer.no}</td>
				<th>
					客户名称
				</th>
				<td>${customer.name}</td>
			</tr>
		</table>
		<!-- 列表数据 -->
		<br />
		
		<c:if test="${page != null && page.totalElements > 0 }">
			<table class="data_list_table" border="0" cellPadding="3"
				cellSpacing="0">
				<tr>
					<th>
						姓名
					</th>
					<th>
						性别
					</th>
					<th>
						职位
					</th>
					<th>
						办公电话
					</th>
					<th>
						手机
					</th>
					<th>
						备注
					</th>
					<th>
						操作
					</th>
				</tr>
	
				<c:forEach var="contact" items="${page.content }">
					<tr>
						<td class="list_data_text">
							${contact.name}
						</td>
						<td class="list_data_text">
							${contact.sex}
						</td>
						<td class="list_data_text">
							${contact.position}
						</td>
						<td class="list_data_text">
							${contact.tel}
						</td>
						<td class="list_data_text">
							${contact.mobile}
						</td>

						<td class="list_data_ltext">
							${contact.memo}
						</td>
						<td class="list_data_op">
							<img onclick="window.location.href='contact/toEdit/${contact.id }'" 
								title="编辑" src="static/images/bt_edit.gif" class="op_button" />
							<img id="del-${contact.id }" 
								title="删除" src="static/images/bt_del.gif" class="op_button" />
							<input type="hidden" value="contact/delete/${contact.id }/${customer.id }"/>
						</td>
					</tr>
				</c:forEach>
			</table>
			<tags:page page="${page }" formId="command"/>
		</c:if>
		<c:if test="${page == null || page.totalElements == 0 }">
			没有任何数据
		</c:if>
	</form>
</body>
</html>