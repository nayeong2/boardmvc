package com.kitri.board.model;

public class ReboardDto extends BoardDto {
	private int rseq; // 일련번호
	private int ref; // 그룹번호
	private int lev; // 들여쓰기
	private int step; // 답글 안에서의 정렬
	private int pseq; // 원글의 글번호
	private int reply; // 답글의 개수, reply가 0이 아니면 못지움

	public int getRseq() {
		return rseq;
	}

	public void setRseq(int rseq) {
		this.rseq = rseq;
	}

	public int getRef() {
		return ref;
	}

	public void setRef(int ref) {
		this.ref = ref;
	}

	public int getLev() {
		return lev;
	}

	public void setLev(int lev) {
		this.lev = lev;
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}

	public int getPseq() {
		return pseq;
	}

	public void setPseq(int pseq) {
		this.pseq = pseq;
	}

	public int getReply() {
		return reply;
	}

	public void setReply(int reply) {
		this.reply = reply;
	}
}
