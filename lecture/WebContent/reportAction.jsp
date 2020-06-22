<%@page import="javax.mail.internet.InternetAddress"%>
<%@page import="javax.mail.internet.MimeMessage"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="javax.mail.*" %>
<%@ page import="java.util.Properties" %>
<%@ page import="util.Gmail" %>
<%@ page import="user.UserDAO" %>
<%@ page import="util.SHA256" %>
<%@ page import="java.io.PrintWriter" %>
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
	
	String reportTitle = null;
	String reportContent = null;
	if(request.getParameter("reportTitle") != null){
		reportTitle = request.getParameter("reportTitle");
	}
	if(request.getParameter("reportContent") != null){
		reportContent = request.getParameter("reportContent");
	}
	
	if(reportContent == null || reportTitle == null){
		out.println("<script>");
		out.println("alert('입력이 안 된 사항이 있습니다.')");
		out.println("history.back()");
		out.println("</script>");
	}
	
	
	
	
	//구글 smtp가 기본적으로 제공하는 부분
	String host = "http://localhost:8081/lecture/";
	String from = "kevin018111@gmail.com";
	String to = "kevin018111@gmail.com";
	String subject = "강의평가 사이트에서 접수된 신고 메일입니다.";
	String content = "신고자 : "+userID+
						"<br>제목 : "+reportTitle+
						"<br>내용 : "+reportContent;
						
	
	Properties p = new Properties();
	p.put("mail.smtp.user",from);
	p.put("mail.smtp.host","smtp.googlemail.com");
	p.put("mail.smtp.port","465");
	p.put("mail.smtp.starttls.enable","true");
	p.put("mail.smtp.auth","true");
	p.put("mail.smtp.debug","true");
	p.put("mail.smtp.socketFactory.port","465");
	p.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
	p.put("mail.smtp.socketFactory.fallback","false");
	

	try{
		Authenticator auth = new Gmail();
		Session ses = Session.getInstance(p, auth);
		ses.setDebug(true);
		MimeMessage msg = new MimeMessage(ses);
		msg.setSubject(subject);
		Address formAddr = new InternetAddress(from);
		msg.setFrom(formAddr);
		Address toAddr = new InternetAddress(to);
		msg.addRecipient(Message.RecipientType.TO, toAddr);
		msg.setContent(content, "text/html;charset=UTF8");
		Transport.send(msg);
		
		
	}catch(Exception e){
		e.printStackTrace();
		out.println("<script>");
		out.println("alert('오류가 발생했습니다.')");
		out.println("history.back()");
		out.println("</script>");
	}
	out.println("<script>");
	out.println("alert('신고가 완료되었습니다.')");
	out.println("location.href='index.jsp'");
	out.println("</script>");
	
%>