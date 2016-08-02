<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ attribute name="page" required="true" rtexprvalue="true" type="com.atguigu.crm.model.Page"%>
<%@ attribute name="formId" required="true" rtexprvalue="true" type="java.lang.String" %>

<div style="text-align: right; padding: 6px 6px 0 0;">

	共 ${page.totalElements } 条记录 &nbsp;&nbsp; 当前第 ${page.pageNo } 页/共
	${page.totalPageNo } 页 &nbsp;&nbsp;

	<c:if test="${page.hasPrev }">
		<a href='我是首页' class="page" id="?pageNo=1">首页</a>
		&nbsp;&nbsp;
		<a href='我是上一页' class="page" id="?pageNo=${page.prevPageNo}">上一页</a>
		&nbsp;&nbsp;
	</c:if>

	<c:if test="${page.hasNext }">
		<a href='我是下一页' class="page" id="?pageNo=${page.nextPageNo}">下一页</a>
		&nbsp;&nbsp;
		<a href='我是末页' class="page" id="?pageNo=${page.totalPageNo}">末页</a>
		&nbsp;&nbsp;
	</c:if>
	<%-- 	<c:if test="${page.hasPrev }"> --%>
	<!-- 		<a href='chance/list?pageNo=1&flag=**'>首页</a> -->
	<!-- 		&nbsp;&nbsp; -->
	<%-- 		<a href='chance/list?pageNo=${page.prevPageNo}&flag=**'>上一页</a> --%>
	<!-- 		&nbsp;&nbsp; -->
	<%-- 	</c:if> --%>

	<%-- 	<c:if test="${page.hasNext }"> --%>
	<%-- 		<a href='chance/list?pageNo=${page.nextPageNo}&flag=**'>下一页</a> --%>
	<!-- 		&nbsp;&nbsp; -->
	<%-- 		<a href='chance/list?pageNo=${page.totalPageNo}&flag=**'>末页</a> --%>
	<!-- 		&nbsp;&nbsp; -->
	<%-- 	</c:if> --%>
	转到 <input id="pageNo" size='1' /> 页 &nbsp;&nbsp;

</div>

<script type="text/javascript">

	$(function(){
		
		$("#pageNo").change(function(){
			var pageNo = $(this).val();
			var reg = /^\d+$/;
			if(!reg.test(pageNo)){
				$(this).val("");
				alert("输入的页码不合法");
				return;
			}
			
			var pageNo2 = parseInt(pageNo);
			if(pageNo2 < 1 || pageNo2 > parseInt("${page.totalPageNo}")){
				$(this).val("");
				alert("输入的页码不合法");
				return;
			}
			
			//查询条件需要放入到 class='condition' 的隐藏域中. 
			var url = window.location.pathname + "?pageNo=" + pageNo2;
			$("#${formId}").attr("action",url).submit();
			
		});
		
		//把分页的超链接转换成表单post请求提交
		$(".page").click(function(){
			var url = window.location.pathname + this.id;
 			$("#${formId}").attr("action",url).submit();
			
			return false;
		});
	})
</script>
