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
		reboardDto.setSeq(seq); //seq����. controller�� �ƴ� service�ܿ��� ó�����ش�
		reboardDto.setRef(seq); //�����϶��� �׷��ȣ�� �۹�ȣ�� ���� ��ȣ�� ����ִ´�
								//����� ����ؼ� �ִ´�.
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
		// �ΰ� ��ġ �ٲٸ� �� �� ��������, ���� ������ �������� �ٲ� �� ����
		
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
