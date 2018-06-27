package com.kitri.action.reboard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.kitri.action.Action;
import com.kitri.board.model.ReboardDto;
import com.kitri.board.service.ReboardServiceImpl;
import com.kitri.member.model.MemberDto;
import com.kitri.util.ParameterCheck;
import com.sun.org.apache.xalan.internal.xsltc.runtime.Parameter;

public class ReboardWriteAction implements Action {
	private static ReboardWriteAction reboardWriteAction;
	
	private ReboardWriteAction() {}
	
	static {
		reboardWriteAction= new ReboardWriteAction();
	}
	
	public static ReboardWriteAction getReboardWriteAction() {
		return reboardWriteAction;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path= "";
		
		HttpSession session= request.getSession();
		MemberDto memberDto= (MemberDto)session.getAttribute("userInfo");
		
		if(memberDto!= null) {
			ReboardDto reboardDto= new ReboardDto();
			int bcode= ParameterCheck.naNToZero(request.getParameter("bcode"));
			String subject= request.getParameter("subejct");
			String content= request.getParameter("content");
			
			int seq= ReboardServiceImpl.getReboardService().writeArticle(reboardDto);
//			int cnt= 
		} else {
//			path= "/login/login.jsp";
			path= "/index.jsp";
		}
		
		return path;
	}

}
