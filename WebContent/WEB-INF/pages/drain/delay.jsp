<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>暂缓流失</title>
<script type="text/javascript">
	$(function(){
		$("#delay").click(function(){
			var id = $("#id").val();		
			var delay = $("#delayText").val();
			if(delay.trim() == ''){
				alert("追加暂缓措施不能为空！");
				return false;
			}
			var count = $("th[id^='count-']").size() + 1;
			var url = "drain/delay";
			var params = {"id":id,"delay":delay,"_method":"PUT","count":count,"time":new Date()};
			var callback = function(data){
				if(data == 1){
					
					$tr = $("<tr></tr>").append("<th id='count-"+count+"'>暂缓措施-"+count+"</th>")
										.append("<td colspan='3'>"+delay+"</td>");
					$("#textArea").before($tr);
					$("#delayText").val("");
					alert("追加成功！");
				}
			};
			$.post(url,params,callback);
			return false;	
		});
	});

</script>
</head>

<span class="page_title">暂缓流失</span>
<div class="button_bar">
	<button class="common_button" onclick="javascript:history.go(-1);">返回</button>
	<button class="common_button"
		onclick="window.location.href='drain/confirm/${drain.id }'">确认流失</button>
	<button class="common_button" id="delay" >保存</button>
</div>

<body class="main">
	<form action="drain/delay" method="post">
		<input
			type="hidden" name="id" value="${drain.id}" id="id"/>
		<table class="query_form_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th>编号</th>
				<td>${drain.id}</td>
				<th>客户</th>
				<td>${drain.customer.name}</td>
			</tr>
			<tr>
				<th>客户经理</th>
				<td>${drain.customer.manager.name}</td>
				<th>最后一次下单时间</th>
				<td><fmt:formatDate value="${drain.lastOrderDate }"
						pattern="yyyy-MM-dd" /></td>
			</tr>
			<c:forTokens items="${drain.delay}" delims="`" var="delay"
				varStatus="status">
				<c:if test="${delay != '' }">
					<tr>
						<th id="count-${status.count }">暂缓措施-${status.count }</th>
						<td colspan="3">${delay}</td>
					</tr>
				</c:if>
			</c:forTokens>
			<tr id="textArea">
				<th>追加暂缓措施</th>
				<td colspan="3"><textarea name="delay" cols="50" rows="6" id="delayText"></textarea>&nbsp;</td>
			</tr>
		</table>
	</form>
</body>
</html>