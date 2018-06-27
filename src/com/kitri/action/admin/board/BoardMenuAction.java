package com.kitri.action.admin.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kitri.action.Action;
import com.kitri.admin.board.model.BoardListDto;
import com.kitri.admin.board.service.BoardAdminServiceImpl;

public class BoardMenuAction implements Action {
	private static BoardMenuAction boardMenuAction;

	private BoardMenuAction() {
	}

	static {
		boardMenuAction = new BoardMenuAction();
		// static이기 때문에 재귀가 1번밖에 일어나지 않는다
		// singleton pattern 싱글톤패턴, 하나만 만들겠다.
		// 싱글톤패턴은 set이 필요하지 않다.
	}

	public static BoardMenuAction getBoardMenuAction() {
		return boardMenuAction;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<BoardListDto> menulist= BoardAdminServiceImpl.getBoardAdminServiceImpl().getBoardMenu();
		request.setAttribute("menulist", menulist);
		return "/admin/board/boardmenu.jsp";
	}
}
