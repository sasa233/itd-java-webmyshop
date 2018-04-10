<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>My JSP 'index.jsp' starting page</title>
<%@ include file="/public.jspf"%>
</head>
<body>
	<!-- System.out.println() -->
	<div class="container">
		<!--	
		<!-- 所有的资源前面必须要添加工程名 --
		<form action="${myshop}/userController/login.mvc" method="post">
			<!-- ctrl + alt + 方向键 --
			username:<input type="text" name="name" value="admin" /><br />
			password:<input type="text" name="password" value="admin123" /><br />
			<button type="submit">登录</button>
			${requestScope.error}|${error}
		</form>
		-->
		
		<form action="${myshop}/userController/login.mvc" method="post"
			class="form-horizontal"
			style="margin: 20px">
			<div class="form-group">
				<label for="userName" class="col-sm-2 control-label">username</label>
				<div class="col-sm-6">
					<input type="text" name="name" value="admin" class="form-control"
						id="userName" />
				</div>
			</div>
			<div class="form-group">
				<label for="password" class="col-sm-2 control-label">password</label>
				<div class="col-sm-6">
					<input type="text" name="pass" class="form-control"
						id="password" />
				</div>
			</div>
			
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<div class="checkbox">
						<label> <input type="checkbox"> Remember me
						</label>
					</div>
				</div>
			</div>
	
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10" style="border:1px solid #ffffff;">
					<button type="submit" class="btn btn-primary" >
						<span class="glyphicon"></span> 
						登录
					</button>
					${requestScope.error}|${error}
				</div>
			</div>
		</form>
		.
	</div>
</body>
</html>