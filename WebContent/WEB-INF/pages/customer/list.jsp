<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ include file="/commons/common.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>客户基本信息管理</title>
<script type="text/javascript">
	$(function(){
		$("img[id^='del-']").click(function(){
			var name = $(this).parents("tr").children("td:eq(1)").text();
			var flag = confirm("是否将【"+name+"】这个客户标记为'删除'？");
			if(!flag){
				return false;
			}
			$this = $(this);
			var url = $(this).next(":hidden").val();
			var params = {"_method":"DELETE","time":new Date()};
			var callback = function(data){
				if(data == "1"){
					$this.hide();
					$this.parents("tr").children("td:eq(5)").text("删除");
					alert("操作成功！");
				}
			};
			$.post(url,params,callback);
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

	<div class="page_title">客户基本信息管理</div>
	<div class="button_bar">
		<button class="common_button" id="clear">清除条件</button>
		<button class="common_button" onclick="document.forms[1].submit();">查询</button>
	</div>
	
	<form action="customer/list" method="POST" id="command">
		<table class="query_form_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th>客户名称</th>
				<td>
					<input type="text" name="search_LIKES_name" value="${LIKES_name }"/>
				</td>
				<th>地区</th>
				<td>
					<select name="search_EQS_region" >
						<option value="">全部</option>
						<c:forEach items="${regions }" var="region">
							<option value="${region }"
								<c:if test="${EQS_region == region}">
									selected="selected"
								</c:if>
							>${region }</option>
						</c:forEach>
					</select>
				</td>
				<th>&nbsp;</th>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<th>客户经理</th>
				<td><input type="text" name="search_LIKES_managerName" value="${LIKES_managerName}"/></td>
				
				<th>客户等级</th>
				<td>
					<select name="search_EQS_level">
						<option value="">全部</option>
						<c:forEach items="${levels }" var="level">
							<option value="${level }"
								<c:if test="${EQS_level == level}">
									selected="selected"
								</c:if>
							>${level }</option>
						</c:forEach>
					</select>
				</td>
				
				<th>状态</th>
				<td>
					<select name="search_EQS_state">
						<option value="">全部</option>
						<option value="正常"
							<c:if test="${EQS_state == '正常'}">
								selected="selected"
							</c:if>
						>正常</option>
						<option value="流失预警"
							<c:if test="${EQS_state == '流失预警'}">
								selected="selected"
							</c:if>
						>流失预警</option>
						<option value="流失"
							<c:if test="${EQS_state == '流失'}">
								selected="selected"
							</c:if>
						>流失</option>
						<option value="删除"
							<c:if test="${EQS_state == '删除'}">
								selected="selected"
							</c:if>
						>删除</option>					
					</select>
				</td>
			</tr>
		</table>
		
		<br />
		
		<!-- 列表数据 -->
		<c:if test="${page != null && page.totalElements > 0 }">
			<table class="data_list_table" border="0" cellPadding="3"
				cellSpacing="0">
				<tr>
					<th>客户编号</th>
					<th>客户名称</th>
					<th>地区</th>
					<th>客户经理</th>
					<th>客户等级</th>
					<th>状态</th>
					<th>操作</th>
				</tr>
				
				<c:forEach var="customer" items="${page.content }">
					<tr>
						<td class="list_data_text">${customer.no }&nbsp;</td>
						<td class="list_data_ltext">${customer.name }&nbsp;</td>
						<td class="list_data_text">${customer.region }&nbsp;</td>
						<td class="list_data_text">${customer.manager.name }&nbsp;</td>
						<td class="list_data_text">${customer.level }&nbsp;</td>
						<td class="list_data_text">${customer.state}&nbsp;</td>
						<td class="list_data_op">
							<img onclick="window.location.href='customer/${customer.id}'"
								title="编辑" src="static/images/bt_edit.gif" class="op_button" alt="进入编辑页面" /> 
							<img onclick="window.location.href='contact/list/${customer.id }'"
								title="联系人" src="static/images/bt_linkman.gif" class="op_button" alt="联系人信息" /> 
							<img onclick="window.location.href='activity/list/${customer.id }'"
								title="交往记录" src="static/images/bt_acti.gif" class="op_button" alt="交往记录" /> 
							<img onclick="window.location.href='order/list/${customer.id }'"
								title="历史订单" src="static/images/bt_orders.gif" class="op_button" alt="历史订单" /> 
								<c:if test="${customer.state != '删除' }">
									<img id="del-${customer.id}" 
									title="删除" src="static/images/bt_del.gif" class="op_button" alt="删除" />
									<input type="hidden" value="customer/delete/${customer.id}"/>
								</c:if>
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
