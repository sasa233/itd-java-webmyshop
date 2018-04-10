<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>My JSP 'index.jsp' starting page</title>
<%@ include file="/public.jspf"%>
</head>

<body>
	This is my JSP page.
	<br> 您好！谢谢~
	<br>

	<div class="container">
		<!-- 所有资源前必须添加工程名 -->
		<!--  
		<form action="${myshop}/productController/update.mvc" method="post">
			<!-- 
    		request.getAttribute(arg0)
    		request.setAttribute(arg0, arg1)
    		request.getParameter(arg0) 用于获取前端数据（表单、地址栏等）
    		 --
			商品名称：<input type="text" name="name" value="${product.name}" /><br />
			<!-- EL表达式搜索顺序为requestScope,sessionScope,applicationScope --
			商品价格：<input type="text" name="price" value="${requestScope.product.price}" /><br /> 
			商品备注：<input type="text" name="remark" value="${requestScope.product.remark}" /><br />
			<!-- 
    	    商品日期：<input type="text" name="date" value=""/><br /> 
    		--
			<button type="submit">提交</button>
			<!--  
    		<input type="hidden" name="type" value="update">
    		--
			<input type="hidden" name="id" value="${requestScope.product.id}">
			获取日期为：${requestScope.product}
		</form>
		--> 
		
		<form action="${myshop}/productController/update.mvc" method="post"
			class="form-horizontal"
			style="margin: 20px">
			<div class="form-group">
				<label for="updateName" class="col-sm-2 control-label">商品名称</label>
				<div class="col-sm-6">
					<input type="text" name="name" value="nana" class="form-control"
						id="updateName" />
				</div>
			</div>
			<div class="form-group">
				<label for="updatePrice" class="col-sm-2 control-label">商品价格</label>
				<div class="col-sm-4">
					<input type="number" name="price" value="999.9"
						class="form-control" id="updatePrice" />
				</div>
			</div>
			<!--  
			<div class="form-group">
				<label for="updateImg" class="col-sm-2 control-label">商品图片</label>
				<div class="col-sm-4">
					<input type="file" name="img" class="form-control" id="updateImg"
						placeholder="请选择图片" />
				</div>
			</div>
			-->
			<div class="form-group">
				<label for="updateRemark" class="col-sm-2 control-label">商品备注</label>
				<div class="col-sm-6">
					<textarea name="remark" class="form-control" id="updateRemark"
						placeholder="请输入备注"></textarea>
				</div>
			</div>


			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10"
					style="border:1px solid #ffffff;">
					<button type="submit" class="btn btn-primary">
						<span class="glyphicon glyphicon-refresh"></span> 更新商品
					</button>
				</div>
			</div>
			
			<input type="hidden" name="id" value="${requestScope.product.id}">
			
		</form>
	</div>

</body>
</html>
