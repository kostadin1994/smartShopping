<%@ page import ="java.sql.*" %>
<%@ page import ="javax.sql.*" %>
<%@ page import ="com.dotnetbg.server.UserDao" %>
<%
String username=request.getParameter("username"); 
String pass=request.getParameter("password");
String fname=request.getParameter("fname");
String lname=request.getParameter("lname");
Class.forName("com.mysql.jdbc.Driver"); 
java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/uniproject","root","innovate"); 
UserDao dao=new UserDao();
 
boolean exists= dao.checkUser(username, pass);
if (exists== false){
dao.createUser(username, pass, fname, lname);
response.sendRedirect("index.jsp");
} else { 
	System.out.println("user exists");
	   response.sendRedirect("index.jsp");
}


%>