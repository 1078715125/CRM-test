<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>执行计划</title>
    <script type="text/javascript">
    	$(function(){
    		$(":text[id^='result-']").each(function(){
    			var val = $(this).val();
    			if(val != null && $.trim(val) != ""){
    				$(this).attr("disabled", "disabled");
    			}
    		});
    		
    		$("button[id^='saveresult-']").click(function(){
    			var id = $(this).attr("id");
				id = id.split("-")[1];
				var result = $(this).siblings(":text").val();
				
				if(result == null || $.trim(result) == ""){
					return false;
				}
				var url = "plan/execute";
				var params = {"id":id,"result":result,"_method":"PUT","time":new Date()};
				
				$.post(url,params,function(data){
					
					if(data == "1"){
						alert("保存成功!");
						$("#result-"+id).attr("disabled", "disabled");
						$("#saveresult-"+id).hide("slow");
					}
				});
				return false;
    		});
    		
    		$("#finish").click(function(){
    			var id = $(this).siblings(":hidden").val();
    			$("#_method").val("PUT");
    			$("#hiddenForm").attr("action","plan/chance/finish/"+id).submit();
    			return false;
    		});
    		$("#stop").click(function(){
    			var id = $(this).siblings(":hidden").val();
    			$("#_method").val("PUT");
    			$("#hiddenForm").attr("action","plan/chance/stop/"+id).submit();
    			return false;
    		});
    	})
    </script>
  </head>

  <body class="main">
	<span class="page_title">执行计划</span>
	<div class="button_bar">
		<button class="common_button" id="stop" onclick="window.location.href='chance/stop?id=${chance.id }'">终止开发</button>
		<button class="common_button" onclick="window.location.href='plan/make/${chance.id }'">制定计划</button>
		<button class="common_button" onclick="javascript:history.go(-1);">返回</button>			
		<button class="common_button" id="finish" onclick="window.location.href='chance/finish/${chance.id }'">开发成功</button>
		<input type="hidden" value="${chance.id }"/>
	</div>
  	<form action="plan/execute" method="post">
		<table class="query_form_table" border="0" cellPadding="3" cellSpacing="0">
			<tr>
				<th>编号</th>
				<td>${chance.id }&nbsp;</td>
				
				<th>机会来源</th>
				<td>${chance.source }&nbsp;</td>
			</tr>
			<tr>
				<th>客户名称</th>
				<td>${chance.custName }&nbsp;</td>
				
				<th>成功机率（%）</th>
				<td>${chance.rate }&nbsp;</td>
			</tr>
			
			<tr><th>概要</th>
				<td colspan="3">${chance.title }&nbsp;</td>
			</tr>
			
			<tr>
				<th>联系人</th>
				<td>${chance.contact }&nbsp;</td>
				<th>联系人电话</th>
				<td>${chance.contactTel }&nbsp;</td>
			</tr>
			<tr>
				<th>机会描述</th>
				<td colspan="3">${chance.description }&nbsp;</td>
			</tr>
			<tr>
				<th>创建人</th>
				<td>${chance.createBy.name}&nbsp;</td>
				<th>创建时间</th>
				<td><fmt:formatDate value="${chance.createDate }" pattern="yyyy-MM-dd"/>&nbsp;</td>
			</tr>		
			<tr>					
				<th>指派给</th>
				<td>${chance.designee.name}&nbsp;</td>
			</tr>
		</table>
	
	<br />
	<c:if test="${not empty chance.salesPlans }">
		<table class="data_list_table" border="0" cellPadding="3" cellSpacing="0">
			<tr>
				<th width="200px">日期</th>
				<th>计划</th>
				<th>执行效果</th>
			</tr>
			<c:forEach items="${chance.salesPlans }" var="plan">
				<tr>
					<td class="list_data_text">
						<fmt:formatDate value="${plan.date }" pattern="yyyy-MM-dd"/>&nbsp;
					</td>
					<td class="list_data_ltext">${plan.todo}&nbsp;</td>
					<td>
						<input class="result" id="result-${plan.id }" type="text" 
						size="50" value="${plan.result}" 
						/>
<%-- 						<c:if test="${plan.result != null}">disabled</c:if> --%>
						<c:if test="${plan.result == null}">
							<button class="common_button" id="saveresult-${plan.id }">保存</button>
						</c:if>
					</td>
				</tr>
			</c:forEach>	
		</table>	
	</c:if>
	<c:if test="${empty chance.salesPlans }">
		还没有制定任何计划
	</c:if>
  </form>
  </body>
</html>
