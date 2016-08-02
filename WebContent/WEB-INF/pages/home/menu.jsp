<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" type="text/css" href="static/jquery/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="static/jquery/themes/icon.css">
<script type="text/javascript" src="static/jquery/jquery.easyui.min.js"></script>

<script type="text/javascript">
	$(function(){
		$('#tree').tree({
			onClick: function(node){
				//在 javascript 中. 可以使用 undefined 来作为 false. 
				//若 node 节点没有 attributes 属性, 或 attributes 没有 url 属性,
				//则值为 undefined. 相反的若存在该属性, 则可以作为 true 来使用. 
				if(node.url){
// 					alert(node.attributes.url);
					window.parent.document.getElementById("content").src = node.url;
				}
			}
		});
	})
</script>

</head>
<body>
	
	<!-- 
		如何使用 easyui tree. 
		1. 如何运行 easyui 所带的 demo ?
		1). 解压 jquery-easyui-1.3.5.zip
		2). 把解压后的文件 Copy 到 apache-tomcat-7.0.69\webapps 目录下.
		3). 修改 apache-tomcat-7.0.69\conf\web.xml 文件 DefaultServlet 的 listings 参数值为 true
		4). 点击 apache-tomcat-7.0.69\bin 下的 startup.bat 启动 tomcat 服务器
		5). 在浏览器的地址栏输入: http://localhost:8080/jquery-easyui-1.3.5/demo/tree/animation.html 
		
		如何使点击 tree 的节点后, 能使浏览器右半部分发生改变 ?
		
	-->
	<ul id="tree" class="easyui-tree" data-options="url:'user/navigate',method:'get',animate:true"/>
	
</body>
</html>