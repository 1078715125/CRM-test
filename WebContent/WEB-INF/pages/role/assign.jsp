<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>角色管理 - 分配权限</title>
<script type="text/javascript">
	$(function(){
		//进入页面当子权限全选中时，父权限也有选中
		$(":checkbox[id^='parent-']").each(function(){
			var pId = this.id.split("-")[1];
			var flag = 	($(".sub-"+pId).size() == $(".sub-"+pId+":checked").size());	
			if(flag){
				$(this).prop("checked",true);
			}
		});
		
		//点击父权限，子权限全选中
		$(":checkbox[id^='parent-']").click(function(){
			var pId = this.id.split("-")[1];
			var flag = $(this).prop("checked");
			if(flag){
				$(".sub-"+pId).prop("checked",true);
			}else{
				$(".sub-"+pId).prop("checked",false);
			}
		});
		
		//点击子权限，若子权限全选中则父权限也选中
		$(":checkbox[class^='sub-']").click(function(){
			var pId = $(this).attr("class").split("-")[1];
			var flag = 	($(".sub-"+pId).size() == $(".sub-"+pId+":checked").size());	
			if(flag){
				$("#parent-"+pId).prop("checked",true);
			}else{
				$("#parent-"+pId).prop("checked",false);
			}
		});
	})
</script>
</head>
<body class="main">
 	<form:form action="role/assign" method="post" modelAttribute="role">
 	
		<input type="hidden" name="id" value="${role.id}" />
		<input type="hidden" name="_method" value="PUT"/>
		
		<div class="page_title">
			角色管理 &gt; 分配权限
		</div>
		
		<div class="button_bar">
			<button class="common_button" onclick="javascript:history.back(-1);">
				返回
			</button>
			<button class="common_button" onclick="document.forms[1].submit();">
				保存
			</button>
		</div>

		<table class="query_form_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th class="input_title" width="10%">
					角色名
				</th>
				<td class="input_content" width="20%">
					${role.name}
				</td>
				<th class="input_title" width="10%">
					角色描述
				</th>
				<td class="input_content" width="20%">
					${role.description}
				</td>
				<th class="input_title" width="10%">
					状态
				</th>
				<td class="input_content" width="20%">
					${role.enabled? "有效" : "无效"}
				</td>
			</tr>
			<tr>
				<th class="input_title">
					权限
				</th>
				<td class="input_content" colspan="5" valign="top">
					<c:forEach var="pa" items="${parentAuthorities }">
						<input type="checkbox" id="parent-${pa.id}"/>
						<input type="hidden" value="${pa.displayName}"/>
						<label for="parent-${pa.id}">${pa.displayName }:</label>
						<br>
						
						&nbsp;&nbsp;&nbsp;
						<form:checkboxes items="${pa.subAuthorities }" path="authorities2"
							itemLabel="displayName" itemValue="id" 
							delimiter="<br>&nbsp;&nbsp;&nbsp;&nbsp;"
							cssClass="sub-${pa.id}" /> 
						
						<br><br>	
					</c:forEach>
					
				</td>
			</tr>
		</table>
	</form:form>
	
</body>
</html>
