package com.kitri.board.dao;

import java.util.List;
import java.util.Map;

import com.kitri.board.model.ReboardDto;

public class ReboardDaoImpl implements ReboardDao {
	private static ReboardDaoImpl reboardDao;
	
	private ReboardDaoImpl() {}
	
	static {
		reboardDao= new ReboardDaoImpl();
	}

	public static ReboardDaoImpl getReboardDao() {
		return reboardDao;
	}

	@Override
	public int writeArticle(ReboardDto reboardDto) {
		System.out.println("연결성공");
		return 0;
	}

	@Override
	public int replyArticle(ReboardDto reboardDto) {
		return 0;
	}

	@Override
	public List<ReboardDto> listArticle(Map<String, String> map) {
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
