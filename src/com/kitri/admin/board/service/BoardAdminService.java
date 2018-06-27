package com.kitri.admin.board.service;

import java.util.List;

import com.kitri.admin.board.model.*;

public interface BoardAdminService {
	List<BoardListDto> getBoardMenu();
	List<CategoryDto> getCategory();
	void makeCategory(String cname);
	
	List<BoardTypeDto> getBoardType();
	void makeBoard(BoardListDto boardList);
	
	//TODO 게시판 카테고리변경, 이름변경, 타입변경 하기!
}
