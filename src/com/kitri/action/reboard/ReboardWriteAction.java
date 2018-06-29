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

	private ReboardWriteAction() {
	}

	static {
		reboardWriteAction = new ReboardWriteAction();
	}

	public static ReboardWriteAction getReboardWriteAction() {
		return reboardWriteAction;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = "/reboard/writefail.jsp";

		HttpSession session = request.getSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("userInfo");

		if (memberDto != null) {
			ReboardDto reboardDto = new ReboardDto();
			// session에서 얻어오는 것
			// reboardDto.setSeq(seq);
			// controller는 web에 관련된 것을 처리. 여기서 seq를 처리하는것은 db에서 무언가를 얻어와야함
			// 따라서 seq는 다음에 처리할것임
			reboardDto.setId(memberDto.getId());
			reboardDto.setName(memberDto.getName());
			reboardDto.setEmail(memberDto.getEmail1() + "@" + memberDto.getEmail2());
			// 파라미터에서 얻어오는 것
			reboardDto.setSubject(request.getParameter("subject"));
			reboardDto.setContent(request.getParameter("content"));
			reboardDto.setBcode(ParameterCheck.naNToZero(request.getParameter("bcode")));
			// controller에서 처리해주었으므로 bcode는 절대로 0이 아니다

			int seq = ReboardServiceImpl.getReboardService().writeArticle(reboardDto);
			
			if(seq!= 0) {
				path= "/reboard/writeok.jsp?seq="+ seq+ "&"; //성공했을 때
			} 
		} else {
			// path= "/login/login.jsp";
			path = "/index.jsp?";
		}

		return path;
	}

}
