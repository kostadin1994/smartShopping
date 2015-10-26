<%@ page import ="java.sql.*" %>
<%@ page import ="javax.sql.*" %>
<%@ page import ="com.dotnetbg.server.UserDao" %>
<%
String username=request.getParameter("username"); 
System.out.println(username);
String pass=request.getParameter("password");
Class.forName("com.mysql.jdbc.Driver"); 
java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/uniproject","root","innovate"); 
UserDao dao=new UserDao();
boolean valid = dao.checkUser(username, pass);
System.out.println("validity status:" + valid);
if (valid==true){
    
    response.sendRedirect("home.jsp");
} else {
	
    response.sendRedirect("index.jsp");
}


%>