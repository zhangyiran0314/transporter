<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML>
<%
request.setCharacterEncoding("utf-8");
%>
<html>
  <head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>文件上传</title>
  </head>
  
  <body>
    <form action="<%=request.getContextPath()%>/login" method="post">
    
     用户名:<input type="text" name="email" id="email"><br/>
      密码:<input type="text" name="pswd" id="pswd"><br/>
      出差文件:<input type="file" name="file4" id="file4"><br/>
     <input type="submit" value="提交">
    </form>
  </body>
  <script type="text/javascript">
  
  </script>
</html>
