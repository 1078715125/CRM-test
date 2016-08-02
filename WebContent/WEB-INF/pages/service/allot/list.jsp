<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ include file="/commons/common.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>客户服务管理</title>
	<script type="text/javascript">
		$(function(){
			$(".allotTo").change(function(){
				
				var allotId = $(this).val();
				if(allotId != ""){
					var label = $(this).find("option:selected").text();
					var flag = confirm("确定要分配给【" + label + "】吗?");
					
					if(flag){
						var $tr = $(this).parents("tr");
						
						var id = $(this).prev(":hidden").val();
						var url = "service/allot";
						var args = {"id":id, "allotTo.id":allotId,"_method":"PUT","time":new Date()};
						$.post(url, args, function(data){
							if(data == "1"){
								alert("分配成功!");
								$tr.remove();
							}else{
								alert("分配失败!");
							}
						});
					}else{
						$(this).find("#noUser").attr("selected", "selected");
					}
				}
				return false;
			});
			
			$("img[id^='delete-']").click(function(){
				var name = $(this).parents("tr").children("td:eq(0)").text().trim();
				var flag = confirm("确认要删除编号为【"+name+"】的服务数据吗?");
				if(!flag){
					return false;
				}
				var id = this.id.split("-")[1];
				var url = "service/delete/"+id;
				$("#_method").val("DELETE");
				$("#hiddenForm").prop("action",url).submit();
				return false;
			});
			
			$("#clear").click(function(){
				$("[name^='search_']").val("");
				return false;
			});
		})
	</script>
</head>
<body>

	<div class="page_title">
		客户服务管理
	</div>
	<div class="button_bar">
		<button class="common_button" onclick="window.location.href='service/create';">
			新建
		</button>
		<button class="common_button" id="clear">清除条件</button>
		<button class="common_button" onclick="document.forms[1].submit();">
			查询
		</button>
	</div>
	
	<form action="service/allot" method="POST" id="command">
		<table class="query_form_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th>
					服务类型
				</th>
				<td>
					<input type="text" name="search_LIKES_serviceType" value="${LIKES_serviceType }"/>
				</td>
				<th>
					概要
				</th>
				<td>
					<input type="text" name="search_LIKES_serviceTitle" value="${LIKES_serviceTitle }" />
				</td>
			</tr>
			<tr>
				<th>
					客户
				</th>
				<td>
					<input type="text" name="search_LIKES_customerName" value="${LIKES_customerName }"/>
				</td>
				<th>
					创建时间
				</th>
				<td>
					<input type="text" name="search_GTD_createDateGt" size="10"  value="${GTD_createDateGt }"/>
					-
					<input type="text" name="search_LTD_createDateLt" size="10"  value="${LTD_createDateLt }"/>
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
						服务类型
					</th>
					<th>
						概要
					</th>
					<th>
						客户
					</th>
					<th>
						创建人
					</th>
					<th>
						创建时间
					</th>
					<th>
						分配给
					</th>
					<th>
						操作
					</th>
				</tr>
				<c:forEach var="service" items="${page.content }">
					<tr>
						<td class="list_data_number">
							${service.id}
						</td>
						<td class="list_data_text">
							${service.serviceType}
						</td>
						<td class="list_data_ltext">
							${service.serviceTitle}
						</td>
	
						<td class="list_data_text">
							${service.customer.name}
						</td>
						<td class="list_data_text">
							${service.createdby.name}
						</td>
						<td class="list_data_text">
							<fmt:formatDate value="${service.createDate }" pattern="yyyy-MM-dd"/>
						</td>
	
						<td class="list_data_text">
							<input type="hidden" name="id" value="${service.id }"/>
							<select name="allotTo" class="allotTo">
								<option value="" id="noUser">
									未指定
								</option>
								<c:forEach items="${users }" var="user">
									<option value="${user.id}">${user.name}</option>
								</c:forEach>
							</select>
						</td>
						<td class="list_data_op">
							<img id="delete-${service.id }"
								title="删除" src="static/images/bt_del.gif" class="op_button" />
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
