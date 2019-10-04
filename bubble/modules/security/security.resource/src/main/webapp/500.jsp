<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true"%>  
<%response.setStatus(HttpServletResponse.SC_OK);%> 
HAS ERROR 500
<% exception.printStackTrace(response.getWriter()); %>