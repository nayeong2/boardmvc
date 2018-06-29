package com.kitri.common.dao;

import java.util.Map;

import com.kitri.util.PageNavigation;

public interface CommonDao {
	int getNextSeq();
	void updateHit(int seq);
	
	int getNewArticleCount(int bcode);
	int getTotalArticleCount(Map<String, String> map);
}
