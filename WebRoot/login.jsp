<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'index.jsp' starting page</title>
  </head>
  <body>
      <!-- System.out.println() -->
      <!-- 所有的资源前面必须要添加工程名 -->
      <form action="/webmyshop/servlet/UsersServlet" method="post">
            <!-- ctrl + alt + 方向键 -->
      		username:<input type="text" name="name" value="admin" /><br />
      		password:<input type="text" name="password" value="admin123" /><br />
      		<button type="submit">登录</button>
      		${requestScope.error}|${error}
      </form>
  </body>
</html>