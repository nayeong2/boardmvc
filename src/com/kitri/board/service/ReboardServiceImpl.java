package com.kitri.board.service;

import java.util.*;

import com.kitri.board.dao.ReboardDaoImpl;
import com.kitri.board.model.ReboardDto;
import com.kitri.common.dao.CommonDaoImpl;
import com.kitri.util.KitriConstance;

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
		int seq= CommonDaoImpl.getCommonDao().getNextSeq();
		reboardDto.setSeq(seq); //seq셋팅. controller가 아닌 service단에서 처리해준다
		reboardDto.setRef(seq); //새글일때는 그룹번호와 글번호를 같은 번호를 집어넣는다
								//답글은 계산해서 넣는다.
		return ReboardDaoImpl.getReboardDao().writeArticle(reboardDto)== 0 ? 0 : seq;
	}

	@Override
	public int replyArticle(ReboardDto reboardDto) {
		return 0;
	}

	@Override
	public List<ReboardDto> listArticle(int bcode, int pg, String key, String word) {
		int end= pg* KitriConstance.BOARD_LIST_SIZE;
		int start= end- KitriConstance.BOARD_LIST_SIZE;
		
		Map<String, String> map= new HashMap<String, String>();
		map.put("bcode", bcode+ "");
		map.put("start", start+ "");
		map.put("end", end+ "");
		map.put("key", key);
		map.put("word", word);
		
		return ReboardDaoImpl.getReboardDao().listArticle(map);
	}

	@Override
	public ReboardDto viewArticle(int seq) {
		CommonDaoImpl.getCommonDao().updateHit(seq);
		ReboardDto reboardDto= ReboardDaoImpl.getReboardDao().viewArticle(seq);
		// 두개 위치 바꾸면 들어갈 때 증가할지, 내가 나가고 증가할지 바꿀 수 있음
		
		if(reboardDto!= null)
			reboardDto.setContent(reboardDto.getContent().replaceAll("\n", "<br>"));
		
		return reboardDto;
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
