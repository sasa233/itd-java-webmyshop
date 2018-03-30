<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
    This is my JSP page. <br>
    您好！谢谢~ <br>
    
    <!-- 所有资源前必须添加工程名 -->
    <form action="${pageContext.request.contextPath}/productController/save.mvc" method="post">
    	商品名称：<input type="text" name="name" value="nana" /><br />
    	商品价格：<input type="text" name="price" value="99.9" /><br />
    	商品备注：<input type="text" name="remark" value="我是备注"/><br />
    	<!--  
    	商品日期：<input type="text" name="date" value="??"/> 
    	--> 
    	<button type="submit">提交</button>
    </form>
  </body>
</html>
