package com.kitri.admin.board.service;

import java.util.List;

import com.kitri.admin.board.dao.BoardAdminDaoImpl;
import com.kitri.admin.board.model.*;

public class BoardAdminServiceImpl implements BoardAdminService {
	private static BoardAdminServiceImpl boardAdminServiceImpl;
	private BoardAdminServiceImpl() {}
	
	static {
		boardAdminServiceImpl= new BoardAdminServiceImpl();
	}

	public static BoardAdminServiceImpl getBoardAdminServiceImpl() {
		return boardAdminServiceImpl;
	}

	@Override
	public List<BoardListDto> getBoardMenu() {
		return BoardAdminDaoImpl.getBoardAdminDaoImpl().getBoardMenu();
	}

	@Override
	public List<CategoryDto> getCategory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void makeCategory(String cname) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<BoardTypeDto> getBoardType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void makeBoard(BoardListDto boardList) {
		// TODO Auto-generated method stub

	}

}
