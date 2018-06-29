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
			// session���� ������ ��
			// reboardDto.setSeq(seq);
			// controller�� web�� ���õ� ���� ó��. ���⼭ seq�� ó���ϴ°��� db���� ���𰡸� ���;���
			// ���� seq�� ������ ó���Ұ���
			reboardDto.setId(memberDto.getId());
			reboardDto.setName(memberDto.getName());
			reboardDto.setEmail(memberDto.getEmail1() + "@" + memberDto.getEmail2());
			// �Ķ���Ϳ��� ������ ��
			reboardDto.setSubject(request.getParameter("subject"));
			reboardDto.setContent(request.getParameter("content"));
			reboardDto.setBcode(ParameterCheck.naNToZero(request.getParameter("bcode")));
			// controller���� ó�����־����Ƿ� bcode�� ����� 0�� �ƴϴ�

			int seq = ReboardServiceImpl.getReboardService().writeArticle(reboardDto);
			
			if(seq!= 0) {
				path= "/reboard/writeok.jsp?seq="+ seq+ "&"; //�������� ��
			} 
		} else {
			// path= "/login/login.jsp";
			path = "/index.jsp?";
		}

		return path;
	}

}
