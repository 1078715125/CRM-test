<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ include file="/commons/common.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>交往记录管理</title>
<script type="text/javascript">
	$(function(){
		$("img[id^='del-']").click(function(){
			var name = $(this).parents("tr").children("td:eq(2)").text().trim();
			var flag = confirm("确定要删除【"+name+"】这个概要的记录吗？");
			if(!flag){
				return false;
			}
			
			var url = $(this).next(":hidden").val();
			$("#_method").val("DELETE");
			$("#hiddenForm").attr("action",url).submit();
			return false;
		});
		
		
	});
</script>
</head>
<body>
	
	<div class="page_title">
		交往记录管理
	</div>
	<div class="button_bar">
		<button class="common_button"
			onclick="window.location.href='activity/toCreate/${customer.id }'">
			新建
		</button>
		<button class="common_button" onclick="javascript:history.go(-1);"> 
			返回
		</button>
	</div>
	
	<form action="activity/list/${customer.id }" method="POST" id="command">
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
						时间
					</th>
					<th>
						地点
					</th>
					<th>
						概要
					</th>
					<th>
						详细信息
					</th>
					<th>
						操作
					</th>
				</tr>
				<c:forEach var="activity" items="${page.content }">
					<tr>
						<td class="list_data_text">
							<fmt:formatDate value="${activity.date }" pattern="yyyy-MM-dd"/>
						</td>
						<td class="list_data_ltext">
							${activity.place}
						</td>
						<td class="list_data_ltext">
							${activity.title}
						</td>
						<td class="list_data_ltext">
							${activity.description}
						</td>
						<td class="list_data_op">
							<img onclick="window.location.href='activity/toEdit/${activity.id }'" 
								title="编辑" src="static/images/bt_edit.gif" class="op_button" />
							<img id="del-${activity.id }" 
								title="删除" src="static/images/bt_del.gif" class="op_button" />
							<input type="hidden" value="activity/delete/${activity.id }/${customer.id }"/>
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