package com.kitri.board.service;

import java.util.List;

import com.kitri.board.dao.ReboardDaoImpl;
import com.kitri.board.model.ReboardDto;

public class ReboardServiceImpl implements ReboardService {
	private static ReboardService reboardService;
	
	private ReboardServiceImpl() {}
	
	static {
		reboardService= new ReboardServiceImpl();
	}
	
	public static ReboardService getReboardService() {
		return reboardService;
	}

	@Override
	public int writeArticle(ReboardDto reboardDto) {
		return ReboardDaoImpl.getReboardDao().writeArticle(reboardDto);
	}

	@Override
	public int replyArticle(ReboardDto reboardDto) {
		return 0;
	}

	@Override
	public List<ReboardDto> listArticle(int bcode, int pg, String key, String word) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReboardDto viewArticle(int seq) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReboardDto getArticle(int seq) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void modifyArticle(ReboardDto reboardDto) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteArticle(int seq) {
		// TODO Auto-generated method stub

	}

}
