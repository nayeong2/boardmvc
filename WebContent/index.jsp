<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import= "com.kitri.member.model.MemberDto"%>
<%
String root = request.getContextPath();

MemberDto memberDto= new MemberDto();
memberDto.setId("skdud");
memberDto.setName("최나영");
memberDto.setEmail1("skdud");
memberDto.setEmail2("naver.com");

session.setAttribute("userInfo", memberDto);

response.sendRedirect(root+ "/boardadmin?act=boardmenu");
%>    