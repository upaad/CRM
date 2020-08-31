%--
  Created by IntelliJ IDEA.
  User: hjf
  Date: 2020/8/14
  Time: 9:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>你好啊！</h1>
1- ${ename}<br/>
2- <%=request.getAttribute("ename")%><br/>
2- <%=request.getParameter("ename")%><br/>
</body>
</html>
