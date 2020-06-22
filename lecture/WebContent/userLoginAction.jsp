<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="user.UserDTO" %>
<%@ page import="user.UserDAO" %>
<%@ page import="util.SHA256" %>
<%@ page import="java.io.PrintWriter" %>
<%
	request.setCharacterEncoding("UTF-8");

	String userID = null;
	String userPassword = null;
	
	
	if(request.getParameter("userID") != null){
		userID = request.getParameter("userID");
	}
	if(request.getParameter("userPassword") != null){
		userPassword = request.getParameter("userPassword");
	}
	
	if(userID == null || userPassword == null){
		out.println("<script>");
		out.println("alert('입력이 안된 사항이 있습니다.')");
		out.println("history.back()");
		out.println("</script>");
	}
	
	
	UserDAO userDAO = new UserDAO();
	int result = userDAO.login(userID, userPassword);
	
	if (result == 1) { //로그인 성공
		session.setAttribute("userID", userID);
		out.println("<script>");
		out.println("alert('로그인되었습니다.')");
		out.println("location.href='index.jsp'");
		out.println("</script>");
	} else if(result == 0) { //로그인 실패
		out.println("<script>");
		out.println("alert('비밀번호가 틀렸습니다.')");
		out.println("histroy.back()");
		out.println("</script>");
	} else if(result == -1){
		out.println("<script>");
		out.println("alert('존재하지 않는 아이디입니다.')");
		out.println("location.href='userLogin.jsp'");
		out.println("</script>");
	} else if(result == -2){
		out.println("<script>");
		out.println("alert('데이터베이스 오류가 발생했습니다.')");
		out.println("histroy.back()");
		out.println("</script>");
	}
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
</body>
</html>