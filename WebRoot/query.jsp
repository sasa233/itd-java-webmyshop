<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- JSTL标签本质上就是被封装的Java类 -->
<!-- alt + / 提示快捷键 -->
<!--  此处为HTML4的声明
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 此处为HTML5的声明 --> 
<!DOCTYPE HTML>
<html>
<head>
<title>My JSP 'index.jsp' starting page</title>
<%@ include file="/publicjspf.jsp"%>
<script></script>

</head>

<body>
	This is my JSP page.
	<br> 您好！谢谢~
	<br>

	<!-- 
    	post	可传输密码，安全，性能差，支持文件上传
    	get 	通过地址栏传输，安全性差，性能好;能传字符，不能传视频
     -->
	此网页用于查询。
	<div class="container">

		<!--  
		<form
			action="${myshop}/productController/query.mvc"
			method="get">
			关键字：<input type="text" name="keyword" /><br />
			<button type="submit">提交</button>
			<input type="hidden" name="type" value="query">
		</form>
		-->
		
		<form action="${myshop}/productController/query.mvc"
			method="get"
			class="form-inline"
			style="margin: 20px">
			<div class="form-group">
				<label for="keyword">Search: </label> 
				<input type="text" name="name" id="keyword"
				class="form-control"
					placeholder="全部记录" />
			</div>
			<button type="submit" class="btn btn-default">
				<span class="glyphicon glyphicon-search"></span> 
				Search
			</button>
		</form>
	</div>




	<table width=600 class="table table-striped table-hover table-bordered">
		<tr>
			<th>编号</th>
			<th>商品名称</th>
			<th>商品价格</th>
			<th>商品备注</th>
			<th>日期</th>
			<th>操作</th>
		</tr>
		<!-- 
    		应该使用循环迭代数据，可以采用JSTL标签来输出数据（JSTL与HTML可无缝整合）
    		c.tld是标签描述文件
    	 -->
		<!-- request.getAttribute("proList") -->
		<c:forEach items="${requestScope.proList}" var="product"
			varStatus="num">
			<tr>
				<td>${num.count}</td>
				<!-- 为访问私有属性，调用了相应的get方法 -->
				<td>${product.name}</td>
				<td>${product.price}</td>
				<td>${product.remark}</td>
				<td>${product.date}</td>
				<td>
					<a
					href="${pageContext.request.contextPath}/productController/delete.mvc?id=${product.id}">删除</a>
					|<a
					href="${pageContext.request.contextPath}/productController/getById.mvc?id=${product.id}">更新</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	
	
	获取数据为：<%=request.getAttribute("proList")%>
	<!-- EL表达式 -->
	获取数据为：${requestScope.proList}
</body>
</html>
