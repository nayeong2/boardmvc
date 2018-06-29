package com.kitri.common.dao;

import java.sql.*;
import java.util.Map;

import com.kitri.util.PageNavigation;
import com.kitri.util.db.DBClose;
import com.kitri.util.db.DBConnection;

public class CommonDaoImpl implements CommonDao {
	private static CommonDao commonDao;
	
	private CommonDaoImpl() {}
	
	static {
		commonDao= new CommonDaoImpl();
	}
	
	public static CommonDao getCommonDao() {
		return commonDao;
	}

	@Override
	public int getNextSeq() {
		int seq= 0;
		
		Connection con= null;
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		
		try {
			con= DBConnection.makeConnection();
			
			StringBuffer sql= new StringBuffer();
			sql.append("select board_seq.nextval seq \n");
			sql.append("from dual");
			
			pstmt= con.prepareStatement(sql.toString());
			rs= pstmt.executeQuery();
			
			rs.next();
			seq= rs.getInt("seq");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(con, pstmt, rs);
		}
		
		return seq;
	}

	@Override
	public void updateHit(int seq) {
		Connection con= null;
		PreparedStatement pstmt= null;
		
		try {
			con= DBConnection.makeConnection();
			
			StringBuffer sql= new StringBuffer();
			sql.append("update board set hit= hit+1 \n");
			sql.append("where seq= ?");
			
			pstmt= con.prepareStatement(sql.toString());
			pstmt.setInt(1, seq);
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(con, pstmt);
		}
	}

	@Override
	public int getNewArticleCount(int bcode) {
		int cnt= 0;
		
		Connection con= null;
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		
		try {
			con= DBConnection.makeConnection();
			
			StringBuffer sql= new StringBuffer();
			sql.append("select count(seq) cnt \n");
			sql.append("from board \n");
			sql.append("where bcode= ? and to_char(logtime, 'yymmdd')= to_char(sysdate, 'yymmdd')");
			
			pstmt= con.prepareStatement(sql.toString());
			pstmt.setInt(1, bcode);
			
			rs= pstmt.executeQuery();
			
			rs.next();
			cnt= rs.getInt("cnt");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(con, pstmt, rs);
		}
		
		return cnt;
	}

	@Override
	public int getTotalArticleCount(Map<String, String> map) {
		int cnt= 0;
		
		Connection con= null;
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		
		try {
			con= DBConnection.makeConnection();
			
			StringBuffer sql= new StringBuffer();
			sql.append("select count(seq) cnt \n");
			sql.append("from board \n");
			sql.append("where bcode= ? \n");
			
			String key = map.get("key");
			String word = map.get("word");
			if(!key.isEmpty() && !word.isEmpty()) {
				if(key.equals("subject")) {
					sql.append("		and subject like '%'||?||'%' \n");
				} else {//name, seq, id
					sql.append("		and " + key + " = ? \n");
				}
			}
			
			pstmt= con.prepareStatement(sql.toString());
			pstmt.setString(1, map.get("bcode"));
			if(!key.isEmpty() && !word.isEmpty()) {
				pstmt.setString(2, word);
			}
			
			rs= pstmt.executeQuery();
			
			rs.next();
			cnt= rs.getInt("cnt");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(con, pstmt, rs);
		}
		
		return cnt;
	}
}