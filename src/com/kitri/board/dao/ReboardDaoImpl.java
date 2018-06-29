package com.kitri.board.dao;

import java.sql.*;
import java.util.*;

import com.kitri.board.model.ReboardDto;
import com.kitri.util.db.DBClose;
import com.kitri.util.db.DBConnection;

public class ReboardDaoImpl implements ReboardDao {
	private static ReboardDaoImpl reboardDao;

	private ReboardDaoImpl() {
	}

	static {
		reboardDao = new ReboardDaoImpl();
	}

	public static ReboardDaoImpl getReboardDao() {
		return reboardDao;
	}

	@Override
	public int writeArticle(ReboardDto reboardDto) {
		int cnt= 0;
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DBConnection.makeConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("insert all \n");
			sql.append("	into board (seq, name, id, email, subject, content, hit, logtime, bcode) \n");
			sql.append("	values(?, ?, ?, ?, ?, ?, 0, sysdate, ?) \n");
			sql.append("	into reboard (rseq, seq, ref, lev, step, pseq, reply) \n");
			sql.append("	values(reboard_rseq.nextval, ?, ?, 0, 0, 0, 0) \n");
			sql.append("select * from dual");
			// rseq : 일련번호. 답글을 지울 때 글번호로 지우게 되면 안된다..? 글 중에서 일련번호를 지우는것
			// ref : 그룹번호
			// lev : 들여쓰기(첫글, 답글) 여기서는 첫글이기 때문에 lev 0
			// step : 정렬 (답글순서) step도 새글이라는 가정하에 항상 0 가장 꼭대기
			// pseq : 원글이 존재할 수 없으니까 0, null을 해도 상관은 없음
			//		  getInt로 가져오기 때문에 null이 아닌 0으로 집어넣음
			// reply : 답글의 갯수

			pstmt = con.prepareStatement(sql.toString());
			
			int idx= 0;
			pstmt.setInt(++idx, reboardDto.getSeq());
			pstmt.setString(++idx, reboardDto.getName());
			pstmt.setString(++idx, reboardDto.getId());
			pstmt.setString(++idx, reboardDto.getEmail());
			pstmt.setString(++idx, reboardDto.getSubject());
			pstmt.setString(++idx, reboardDto.getContent());
			pstmt.setInt(++idx, reboardDto.getBcode());
			pstmt.setInt(++idx, reboardDto.getSeq());
			pstmt.setInt(++idx, reboardDto.getRef());
			
			cnt= pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(con, pstmt);
		}

		return cnt;
	}

	@Override
	public int replyArticle(ReboardDto reboardDto) {
		return 0;
	}

	@Override
	public List<ReboardDto> listArticle(Map<String, String> map) {
		List<ReboardDto> list= new ArrayList<ReboardDto>();
		
		Connection con= null;
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		
		try {
			con= DBConnection.makeConnection();
			
			StringBuffer sql= new StringBuffer();
			sql.append("select b.* \n");
			sql.append("from ( \n");
			sql.append("	select rownum rn, a.* \n");
			sql.append("	from ( \n");
			sql.append("		select b.seq, b.id, b.name, b.email, b.subject, b.content, b.hit, b.bcode, \n");
			sql.append("				r.rseq, r.ref, r.lev, r.step, r.pseq, r.reply, \n");
			sql.append(" 				case  \n");
			sql.append("					when to_char(logtime, 'yymmdd') = to_char(sysdate, 'yymmdd') \n");
			sql.append("					then to_char(logtime, 'hh24:mi:ss') \n");
			sql.append("					else to_char(logtime, 'yy.mm.dd') \n");
			sql.append("				end logtime \n");
			sql.append("		from board b, reboard r \n");
			sql.append("		where b.seq = r.seq \n");
			sql.append("		and b.bcode = ? \n");
			String key = map.get("key");
			String word = map.get("word");
			if(!key.isEmpty() && !word.isEmpty()) {
				if(key.equals("subject")) {
					sql.append("		and b.subject like '%'||?||'%' \n");
				} else {//name, seq, id
					sql.append("		and b." + key + " = ? \n");
				}
			}
			sql.append("		order by seq desc \n");
			sql.append("		) a \n");
			sql.append("	where rownum <= ? \n");
			sql.append("	) b \n");
			sql.append("where b.rn > ? \n");
			
			pstmt = con.prepareStatement(sql.toString());
			int idx = 0;
			pstmt.setString(++idx, map.get("bcode"));
			if(!key.isEmpty() && !word.isEmpty()) {
				pstmt.setString(++idx, word);
			}
			pstmt.setString(++idx, map.get("end"));
			pstmt.setString(++idx, map.get("start"));
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ReboardDto dto= new ReboardDto();
				
				dto.setSeq(rs.getInt("seq"));
				dto.setId(rs.getString("id"));
				dto.setName(rs.getString("name"));
				dto.setEmail(rs.getString("email"));
				dto.setSubject(rs.getString("subject"));
				dto.setContent(rs.getString("content"));
				dto.setHit(rs.getInt("hit"));
				dto.setLogtime(rs.getString("logtime"));
				dto.setBcode(rs.getInt("bcode"));
				dto.setRseq(rs.getInt("rseq"));
				dto.setRef(rs.getInt("ref"));
				dto.setLev(rs.getInt("lev"));
				dto.setStep(rs.getInt("step"));
				dto.setPseq(rs.getInt("pseq"));
				dto.setReply(rs.getInt("reply"));
				
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(con, pstmt, rs);
		}
		
		return list;
	}

	@Override
	public ReboardDto viewArticle(int seq) {
		System.out.println("dao in");
		
		ReboardDto dto= null;
		
		Connection con= null;
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		
		try {
			con= DBConnection.makeConnection();
			
			StringBuffer sql= new StringBuffer();
			sql.append("select b.seq, b.id, b.name, b.email, b.subject, b.content, b.hit, b.bcode, b.logtime, \n");
			sql.append("	r.rseq, r.ref, r.lev, r.step, r.pseq, r.reply \n");
			sql.append("from board b, reboard r \n");
			sql.append("where b.seq = r.seq \n");
			sql.append("and b.seq = ? \n");
			
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, seq);
			
			rs= pstmt.executeQuery();
			
			if(rs.next()) {
				dto= new ReboardDto();
				
				dto.setSeq(rs.getInt("seq"));
				dto.setId(rs.getString("id"));
				dto.setName(rs.getString("name"));
				dto.setEmail(rs.getString("email"));
				dto.setSubject(rs.getString("subject"));
				dto.setContent(rs.getString("content"));
				dto.setHit(rs.getInt("hit"));
				dto.setLogtime(rs.getString("logtime"));
				dto.setBcode(rs.getInt("bcode"));
				dto.setRseq(rs.getInt("rseq"));
				dto.setRef(rs.getInt("ref"));
				dto.setLev(rs.getInt("lev"));
				dto.setStep(rs.getInt("step"));
				dto.setPseq(rs.getInt("pseq"));
				dto.setReply(rs.getInt("reply"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(con, pstmt, rs);
		}
		
		return dto;
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
