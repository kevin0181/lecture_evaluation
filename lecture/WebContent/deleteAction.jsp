<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="util.Gmail"%>
<%@ page import="user.UserDAO"%>
<%@ page import="util.SHA256"%>
<%@ page import="evaluation.*"%>
<%@ page import="likey.*"%>
<%
	request.setCharacterEncoding("UTF-8");
	
	UserDAO userDAO = new UserDAO();
	String userID = null;
	if(session.getAttribute("userID") != null){
		userID = (String) session.getAttribute("userID");
	}
	if(userID == null){
		out.println("<script>");
		out.println("alert('로그인을 해주세요.')");
		out.println("location.href='userLogin.jsp'");
		out.println("</script>");
	}
	
	String evaluationID = null;
	
	if(request.getParameter("evaluationID") != null){
		evaluationID = request.getParameter("evaluationID");
	}
	
	EvaluationDAO evaluationDAO = new EvaluationDAO();
	
	if(userID.equals(evaluationDAO.getUserID(evaluationID))){
		int result = new EvaluationDAO().delete(evaluationID);
		if(result == 1){
			out.println("<script>");
			out.println("alert('삭제가 완료되었습니다.')");
			out.println("location.href='index.jsp'");
			out.println("</script>");
		}else{
			out.println("<script>");
			out.println("alert('데이터베이스 오류가 발생했습니다.')");
			out.println("history.back()");
			out.println("</script>");
		}
	}else{
		out.println("<script>");
		out.println("alert('자신이 쓴 글만 삭제 가능합니다.')");
		out.println("history.back()");
		out.println("</script>");
	}
		
	
%>