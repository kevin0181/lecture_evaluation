<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="user.UserDAO" %>
<%@ page import="util.SHA256" %>
<%
	request.setCharacterEncoding("UTF-8");

	String code = null;
	String userID = null;
	UserDAO userDAO = new UserDAO();
	if(request.getParameter("code") != null){
		code = (String) request.getParameter("code");
	}
	if(session.getAttribute("userID") != null){
		userID = (String) session.getAttribute("userID");
	}
	if(userID == null){
		out.println("<script>");
		out.println("alert('로그인을 해주세요.')");
		out.println("location.href='userLogin.jsp'");
		out.println("</script>");
	}
	
	
	//이메일 받아오는 부분
	String userEmail = userDAO.getUserEmail(userID);
	boolean isRight = (new SHA256().getSHA256(userEmail).equals(code)) ? true : false;
	
	if(isRight == true){
		userDAO.setUserEmailChecked(userID);
		out.println("<script>");
		out.println("alert('인증에 성공했습니다.')");
		out.println("location.href='index.jsp'");
		out.println("</script>");
	}else{
		out.println("<script>");
		out.println("alert('유효하지 않은 코드입니다.')");
		out.println("location.href='index.jsp'");
		out.println("</script>");
	}
	
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>