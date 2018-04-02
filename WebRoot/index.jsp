<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <%@ include file="/publicjspf.jsp"%>
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
