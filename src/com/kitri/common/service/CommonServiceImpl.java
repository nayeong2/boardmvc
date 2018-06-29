package com.kitri.common.service;

import java.util.HashMap;
import java.util.Map;

import com.kitri.common.dao.CommonDaoImpl;
import com.kitri.util.KitriConstance;
import com.kitri.util.PageNavigation;

public class CommonServiceImpl implements CommonService {
	private static CommonService commonService;
	
	private CommonServiceImpl() {}
	
	static {
		commonService= new CommonServiceImpl();
	}

	public static CommonService getCommonService() {
		return commonService;
	}

	@Override
	public void updateHit(int seq) {
		// TODO Auto-generated method stub

	}

	@Override
	public PageNavigation getPageNavigation(int bcode, int pg, String key, String word) {
		int listSize= KitriConstance.BOARD_LIST_SIZE; // TODO 앨범 게시판 만들 때 수정
		int pageSize= KitriConstance.NAVIGATOR_SIZE;
				
		PageNavigation navigator= new PageNavigation();
		
		navigator.setPageNo(pg);
		
		int newArticleCount= CommonDaoImpl.getCommonDao().getNewArticleCount(bcode);
		navigator.setNewArticleCount(newArticleCount);
		
		Map<String, String> map= new HashMap<String, String>();
		map.put("bcode", bcode+ "");
		map.put("key", key);
		map.put("word", word);
		
		int totalArticleCount= CommonDaoImpl.getCommonDao().getTotalArticleCount(map);
		navigator.setTotalArticleCount(totalArticleCount);
		
		int totalPageCount= (totalArticleCount -1)/ listSize+ 1;
		navigator.setTotalPageCount(totalPageCount);
		navigator.setNowFirst(pg<= pageSize);
		navigator.setNowEnd((totalPageCount-1)/ pageSize * pageSize <pg);
		
		return navigator;
	}
}
