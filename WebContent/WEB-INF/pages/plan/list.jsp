<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="gyx" tagdir="/WEB-INF/tags" %>
<%@ include file="/commons/common.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>客户开发计划</title>
<script type="text/javascript">
	$(function(){
		
		$("img[id^='finish-']").click(function(){
			var id = this.id.split("-")[1];
			$("#_method").val("PUT");
			$("#hiddenForm").attr("action","plan/chance/finish/"+id).submit();

			return false;
		});
		
		$("#clear").click(function(){
			$("[name^='search_']").val("");
			return false;
		});
		
	});

</script>

</head>

<body class="main">
	<form action="plan/chance/list" method="post" id="command">
		<div class="page_title">
			客户开发计划
		</div>
		<div class="button_bar">
			<button class="common_button" id="clear">清除条件</button>
			<button class="common_button" onclick="document.forms[1].submit();">
				查询
			</button>
		</div>
		<table class="query_form_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th class="input_title">
					客户名称
				</th>
				<td class="input_content">
<!-- 					<input type="text" name="search_LIKE_custName" /> -->
					<input type="text" name="search_LIKES_custName" value="${LIKES_custName}"/>
				</td>
				<th class="input_title">
					概要
				</th>
				<td class="input_content">
<!-- 					<input type="text" name="search_LIKE_title" /> -->
					<input type="text" name="search_LIKES_title" value="${LIKES_title}"/>
				</td>
				<th class="input_title">
					联系人
				</th>
				<td class="input_content">
<!-- 					<input type="text" name="search_LIKE_contact" /> -->
					<input type="text" name="search_LIKES_contact" value="${LIKES_contact}"/>
				</td>
			</tr>
		</table>
		<br />
		
		<c:if test="${page != null && page.totalElements > 0 }">
			<table class="data_list_table" border="0" cellPadding="3"
				cellSpacing="0">
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
						状态
					</th>
					<th>
						操作
					</th>
				</tr>
				<c:forEach var="chance" items="${page.content }">
					<tr>
						<td class="list_data_number">
							${chance.id}
						</td>
						<td class="list_data_text">
							${chance.custName}
						</td>
						<td class="list_data_text">
							${chance.title}
						</td>
						<td class="list_data_text">
							${chance.contact}
						</td>
						<td class="list_data_text">
							${chance.contactTel}
						</td>
						<td class="list_data_text">
							<fmt:formatDate value="${chance.createDate }" pattern="yyyy-MM-dd"/>
						</td>
						<td class="list_data_text">
						
							<c:choose >
								<c:when test="${chance.status == 2}">
									<font color="blue">开发中</font>
								</c:when>
								<c:when test="${chance.status == 3}">
									<font color="green">开发成功</font>
								</c:when>
								<c:when test="${chance.status == 4}">
									<font color="red">开发失败</font>
								</c:when>
							</c:choose>
							
						</td>
						<td class="list_data_op">
							<c:if test='${chance.status==2}'>
								<img
									onclick="window.location.href='plan/make/${chance.id}'"
									title="制定计划" src="static/images/bt_plan.gif" class="op_button" />
								<img
									onclick="window.location.href='plan/execute/${chance.id}'"
									title="执行计划" src="static/images/bt_feedback.gif" class="op_button" />
								<img id="finish-${chance.id}"
									title="开发成功" src="static/images/bt_yes.gif" class="op_button" />
							</c:if>
							<c:if test='${chance.status!="2"}'>
								<img
									onclick="window.location.href='plan/details/${chance.id}'"
									title="查看" src="static/images/bt_detail.gif" class="op_button" />
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</table>
			
			<gyx:page page="${page }" formId="command"/>
		
		</c:if>
		<c:if test="${page == null || page.totalElements == 0 }">
			没有任何数据
		</c:if>
		
		</form>
</body>
</html>
