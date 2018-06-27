package com.kitri.admin.board.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.kitri.admin.board.model.*;
import com.kitri.util.db.DBClose;
import com.kitri.util.db.DBConnection;

public class BoardAdminDaoImpl implements BoardAdminDao {
	private static BoardAdminDaoImpl boardAdminDaoImpl;

	private BoardAdminDaoImpl() {
	}

	static {
		boardAdminDaoImpl = new BoardAdminDaoImpl();
	}

	public static BoardAdminDaoImpl getBoardAdminDaoImpl() {
		return boardAdminDaoImpl;
	}

	@Override
	public List<BoardListDto> getBoardMenu() {
		List<BoardListDto> menu= new ArrayList<BoardListDto>();
		
		Connection con= null;
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		
		try {
			con= DBConnection.makeConnection();
			StringBuffer sql= new StringBuffer();
			sql.append("SELECT b.bcode, b.bname, b.btype, c.ccode, c.cname, \n");
			sql.append("		case \n");
			sql.append("			when b.btype= 5 then 'reboard' \n");
			sql.append("			when b.btype= 6 then 'album' \n");
			sql.append("			when b.btype= 7 then 'bbs' \n");
			sql.append("			else 'board' \n");
			sql.append("		end control \n");
			sql.append("FROM board_list b, category c \n");
			sql.append("WHERE b.ccode= c.ccode \n");
			sql.append("ORDER BY b.bcode asc \n");
			
			pstmt= con.prepareStatement(sql.toString());
			
			rs= pstmt.executeQuery();
			
			while(rs.next()) {
				BoardListDto dto= new BoardListDto();
				
				dto.setBcode(rs.getInt("bcode"));
				dto.setBname(rs.getString("bname"));
				dto.setBtype(rs.getInt("btype"));
				dto.setCcode(rs.getInt("ccode"));
				dto.setCname(rs.getString("cname"));
				dto.setControl(rs.getString("control"));
				
				menu.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(con, pstmt, rs);
		}
		return menu;
	}

	@Override
	public List<CategoryDto> getCategory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void makeCategory(String cname) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<BoardTypeDto> getBoardType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void makeBoard(BoardListDto boardList) {
		// TODO Auto-generated method stub

	}

}
