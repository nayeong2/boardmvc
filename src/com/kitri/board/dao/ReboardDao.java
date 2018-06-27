package com.kitri.board.dao;

import java.util.List;
import java.util.Map;

import com.kitri.board.model.ReboardDto;

public interface ReboardDao {
	int writeArticle(ReboardDto reboardDto);
	int replyArticle(ReboardDto reboardDto);
	List<ReboardDto> listArticle(Map<String, String> map);
	//Map(<String, Object>);µµ °¡´É
	ReboardDto viewArticle(int seq);
	ReboardDto getArticle(int seq);
	void modifyArticle(ReboardDto reboardDto);
	void deleteArticle(int seq);
}
