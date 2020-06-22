<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="util.Gmail"%>
<%@ page import="user.UserDAO"%>
<%@ page import="util.SHA256"%>
<%@ page import="evaluation.*"%>
<%@ page import="likey.*"%>
<%!
	public static String getCilentIP(HttpServletRequest request){ //사용자의 ip를 가져오는 부분
		
		String ip = request.getHeader("X-FORWARDED-FOR");
		if (ip == null || ip.length() == 0) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
%>
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
	LikeyDAO likeyDAO = new LikeyDAO();
	int result = likeyDAO.like(userID, evaluationID, getCilentIP(request));
	if(result == 1){
		if(result == 1){
			result = evaluationDAO.like(evaluationID);
			if(result == 1){
				out.println("<script>");
				out.println("alert('추천이 완료되었습니다.')");
				out.println("location.href='index.jsp'");
				out.println("</script>");
			}
			
		}else{
			out.println("<script>");
			out.println("alert('데이터베이스 오류가 발생했습니다.')");
			out.println("history.back()");
			out.println("</script>");
		}
	}else{
		out.println("<script>");
		out.println("alert('이미 추천을 누른 글입니다.')");
		out.println("history.back()");
		out.println("</script>");
	}
	
		
	
%>