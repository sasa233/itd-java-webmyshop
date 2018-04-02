<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<html>
<head>
<title>My JSP 'index.jsp' starting page</title>
<%@ include file="/publicjspf.jsp"%>
</head>

<body>
	This is my JSP page.
	<br> 您好！谢谢~
	<br>
	<div class="container">
		<!-- 所有资源前必须添加工程名 -->
		<!-- 
		<form
			action="${myshop}/productController/save.mvc"
			method="post" enctype="multipart/form-data">
			商品名称：<input type="text" name="name" value="nana" /><br /> 
			商品价格：<input type="text" name="price" value="99.9" /><br /> 
			商品图片：<input type="file" name="img" /><br /> 
			商品备注：<input type="text" name="remark" value="我是备注" /><br />
    		商品日期：<input type="text" name="date" value="??"/> 
			<button type="submit">提交</button>
		</form>
		-->

		<form 
			action="${myshop}/productController/save.mvc"
			method="post" 
			enctype="multipart/form-data"
			class="form-horizontal"
			style="margin: 20px">
			<div class="form-group">
				<label for="inputName" class="col-sm-2 control-label">商品名称</label>
				<div class="col-sm-6">
					<input type="text" name="name" value="nana"
							class="form-control" id="inputName" />
				</div>
			</div>
			<div class="form-group">
				<label for="inputPrice" class="col-sm-2 control-label">商品价格</label>
				<div class="col-sm-4">
					<input type="number" name="price" value="999.9"
							class="form-control" id="inputPrice" />
				</div>
			</div>
			<div class="form-group">
				<label for="inputImg" class="col-sm-2 control-label">商品图片</label>
				<div class="col-sm-4">
					<input type="file" name="img" 
							class="form-control" id="inputImg" placeholder="请选择图片" />
				</div>
			</div>
			<div class="form-group">
				<label for="inputRemark" class="col-sm-2 control-label">商品备注</label>
				<div class="col-sm-6">
					<textarea name="remark" 
					class="form-control" id="inputRemark" placeholder="请输入备注"></textarea>
					<!--  
					<input type="text" name="remark" value="Come on!"
							class="form-control" id="inputRemark" />
					-->
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
				<div class="col-sm-offset-2 col-sm-10" style="border:1px solid #f3f3f3;">
					<button type="submit" class="btn btn-primary" >
						<span class="glyphicon glyphicon-plus"></span> 
						添加商品
					</button>
				</div>
			</div>
		</form>
	</div>

</body>
</html>
