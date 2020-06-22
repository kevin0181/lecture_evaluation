<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="user.UserDTO" %>
<%@ page import="user.UserDAO" %>
<%@ page import="util.SHA256" %>
<%@ page import="java.io.PrintWriter" %>
<%
	String userID = null;
	if(session.getAttribute("userID") != null){
		userID = (String) session.getAttribute("userID");
	}

	if(userID != null){
		out.println("<script>");
		out.println("alert('로그인이 된 상태입니다.')");
		out.println("location.href='userLogin.jsp'");
		out.println("</script>");
	}




	request.setCharacterEncoding("UTF-8");

	String userPassword = null;
	String userEmail = null;
	
	
	if(request.getParameter("userID") != null){
		userID = request.getParameter("userID");
	}
	if(request.getParameter("userPassword") != null){
		userPassword = request.getParameter("userPassword");
	}
	if(request.getParameter("userEmail") != null){
		userEmail = request.getParameter("userEmail");
	}
	
	if(userID == null || userPassword == null || userEmail == null){
		out.println("<script>");
		out.println("alert('입력이 안된 사항이 있습니다.')");
		out.println("history.back()");
		out.println("</script>");
	}
	
	
	UserDAO userDAO = new UserDAO();
	int result = userDAO.join(new UserDTO(userID, userPassword, userEmail, SHA256.getSHA256(userEmail), false));
	
	if (result == -1) {
		out.println("<script>");
		out.println("alert('이미 존재하는 아이디입니다.')");
		out.println("history.back()");
		out.println("</script>");
	} else {
		session.setAttribute("userID", userID);
		out.println("<script>");
		out.println("alert('회원가입이 완료 되었습니다.')");
		out.println("location.href='emailSendAction.jsp'");
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