<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>制定计划</title>
<script type="text/javascript">
$(function() {
	$("#addNew").click(function() {
			var date = $("#date").val();
			var todo = $("#todo").val();
			var chanceId = $("#chanceId").val();
	
			var url = "plan/make";
			var params = {
				"date" : date,
				"todo" : todo,
				"chance.id" : chanceId,
				"time" : new Date()
			};
			var callback = function(data) {
				//若返回的值是一个正整数, 则意味着成功!
				var reg = /^\d+$/;
				var flag = reg.test(data);
				if (flag) {
					var id = parseInt(data);
					if (id > 0) {
	
						$table = $("#listPlan");
						var $tr = $("<tr id='plan-"+id+"'></tr>");
						$tr.append("<td class='list_data_text'>" + date + "</td>");
						var $td = $("<td class='list_data_ltext'><input size='50' value='"+todo+"'id='todo-"+id+"' /></td>")
						$td.append("&nbsp;<button class='common_button' id='save-"+id+"'>保存</button>")
							.append("&nbsp;<button class='common_button' id='delete-"+id+"'>删除</button>")
							.append("<input type='hidden' value='"+id+"'/>");
						$tr.append($td);
						$tr.find("button[id^='save-']").click(function(){
							update(this);
							return false;
						});
						$tr.find("button[id^='delete-']").click(function(){
							del(this);
							return false;
						});
						$table.append($tr);
						$("#date").val("");
						$("#todo").val("");
					}
				}
			};
			$.post(url, params, callback);
			return false;
	});

	$("button[id^='save-']").click(function() {
		update(this);
		return false;
	});
	
	$("button[id^='delete-']").click(function(){
		del(this);
		return false;
	});

	var update = function(button) {

		var id = $(button).siblings(":hidden").val();
		var todo = $(button).siblings(":text").val();

		url = "plan/make";
		var params = {
			"id" : id,
			"todo" : todo,
			"_method" : "PUT",
			"time" : new Date()
		};

		var callback = function(data) {
			if (data == "1") {
				alert("修改成功！");
			}
		};
		$.post(url, params, callback,"text");
	}
	
	var del = function(button){
		var id = $(button).siblings(":hidden").val();
		var todo = $(button).siblings(":text").val();
		var flag = confirm("确认要删除【"+todo+"】这个计划项吗？");
		if(!flag){
			return;
		}
		url = "plan/make";
		var params = {
			"id" : id,
			"_method" : "DELETE",
			"time" : new Date()
		};

		var callback = function(data) {
			if (data == "1") {
				alert("删除成功！");
				console.log("#plan-"+id);
// 				$("#listPlan").remove("tr[id='plan-"+id+"']");
				$("tr[id='plan-"+id+"']").remove();
			}
		};
		$.post(url, params, callback,"text");
	}
})
</script>
</head>

<body class="main">
	<span class="page_title">制定计划</span>
	<div class="button_bar">
		<button class="common_button" id="execute" 
		onclick="window.location.href='plan/execute/${chance.id}'">执行计划</button>
		<button class="common_button" onclick="javascript:history.go(-1);">
			返回</button>
	</div>

	<form action="plan/make" method="post" id="addNewForm">
		<table class="query_form_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th>编号</th>

				<td>${chance.id}</td>
				<th>机会来源</th>

				<td>${chance.source}</td>
			</tr>
			<tr>
				<th>客户名称</th>
				<td>${chance.custName}</td>
				<th>成功机率（%）</th>

				<td>${chance.rate}</td>
			</tr>
			<tr>
				<th>概要</th>
				<td colspan="3">${chance.title}</td>
			</tr>
			<tr>
				<th>联系人</th>

				<td>${chance.contact}</td>
				<th>联系人电话</th>

				<td>${chance.contactTel}</td>
			</tr>
			<tr>
				<th>机会描述</th>
				<td colspan="3">${chance.description}</td>
			</tr>
			<tr>
				<th>创建人</th>
				<td>${chance.createBy.name}</td>
				<th>创建时间</th>
				<td><fmt:formatDate value="${chance.createDate }"
						pattern="yyyy-MM-dd" /></td>
			</tr>
			<tr>
				<th>指派给</th>
				<td>${chance.designee.name}</td>

			</tr>
		</table>

		<br />

		<table class="data_list_table" border="0" cellPadding="3"
			cellSpacing="0" id="listPlan">
			<tr>
				<th width="200px">日期</th>
				<th>计划项</th>
			</tr>
			<c:if test="${!empty chance.salesPlans }">
				<c:forEach items="${chance.salesPlans }" var="plan">
					<tr id="plan-${plan.id}">
						<td class="list_data_text"><fmt:formatDate
								value="${plan.date }" pattern="yyyy-MM-dd" /> &nbsp;</td>
						<td class="list_data_ltext"><c:if
								test="${plan.result == null }">
								<input type="text" size="50" value="${plan.todo}"
									id="todo-${plan.id}" />
								<button class="common_button" id="save-${plan.id}">保存</button>
								<button class="common_button" id="delete-${plan.id}">删除</button>
								<input type="hidden" value="${plan.id}" />
							</c:if> <c:if test="${plan.result != null }">
								<input type="text" size="50" value="${plan.todo}"
									readonly="readonly" />
								<input type="text" size="50" value="${plan.result}"
									readonly="readonly" />
							</c:if></td>
					</tr>
				</c:forEach>
			</c:if>
		</table>
		<div class="button_bar">
			<button class="common_button" id="addNew">新建</button>
		</div>
		<input type="hidden" name="chance.id" value="${chance.id}"
			id="chanceId" />
		<table class="query_form_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th>日期 <br /> (格式: yyyy-mm-dd)
				</th>
				<td><input type="text" name="date" id="date" /> &nbsp;</td>
				<th>计划项</th>
				<td><input type="text" name="todo" size="50" id="todo" />
					&nbsp;</td>
			</tr>
		</table>
	</form>
</body>
</html>
