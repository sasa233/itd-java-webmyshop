<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'index.jsp' starting page</title>
    

  </head>
  
  <body>
    This is my JSP page. <br>
    此页面必须登录后才能被访问。（为实现此功能，需要重新创建一个filter）<br>
    
    您好${sessionScope.users.name}，欢迎登录管理员页面~
    
  </body>
</html>
