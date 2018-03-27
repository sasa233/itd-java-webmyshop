<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!-- JSTL标签本质上就是被封装的Java类 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- alt + / 提示快捷键 -->
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
	<script></script>
	
  </head>
  
  <body>
    This is my JSP page. <br>
    您好！谢谢~ <br>
    
    <!-- 
    	post可传输密码，安全，性能差，支持文件上传
    	get 通过地址栏传输，安全性差，性能好
     -->
    此网页用于查询。
    <form action="/webmyshop/servlet/ProductServlet" method="get" >
       	关键字：<input type="text" name="keyword" /><br />
    	<button type="submit">提交</button>
    </form>
    
    获取数据为：<%=request.getAttribute("proList") %>
    <!-- EL表达式 -->
    获取数据为：${requestScope.proList}
    
    <table border=6 width=600>
    	<tr>
    		<th>编号</th>
    		<th>商品名称</th>
    		<th>商品价格</th>
    		<th>商品备注</th>
    		<th>操作</th>
    	</tr>
    	<!-- 
    		应该使用循环迭代数据，可以采用JSTL标签来输出数据（JSTL与HTML可无缝整合）
    		c.tld是标签描述文件
    	 -->
    	<!-- request.getAttribute("proList") -->
    	<c:forEach items="${requestScope.proList}" var="product" varStatus="num">
    	<tr>
    		<td>${num.count}</td>
    		<!-- 为访问私有属性，调用了相应的get方法 -->
    		<td>${product.name}</td>
    		<td>${product.price}</td>
    		<td>${product.remark}</td>
    		<td>删除|更新</td>
    	</tr>
    	</c:forEach>
    </table>
  </body>
</html>
