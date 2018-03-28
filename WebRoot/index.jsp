<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
  </head>
  
  <body>
    This is my JSP page. <br />
    您好！谢谢~ <br>
    <%=application %>>
    <hr />
    <c:forEach items="${applicationScope.proList}" var="product" varStatus="num">
    name: ${product.name}, price: ${product.price}, remark: ${product.remark} 
    	<c:if test="${num.count%2==0}">
    		<br />
    	</c:if>
    </c:forEach>
  </body>
</html>
