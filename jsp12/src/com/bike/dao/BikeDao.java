package com.bike.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.bike.vo.BikeVo;

import common.JDBCTemplate;

public class BikeDao extends JDBCTemplate {
	public int insert(List<BikeVo> vos) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		
		String sql = " INSERT INTO BIKE_TB VALUES(?,?,?,?,?,?,?) ";
		
		try {
			pstm = con.prepareStatement(sql);
			int cnt = 0;
			for(int i=0; i<vos.size(); i++) {
				BikeVo vo = vos.get(i);
				pstm.setString(1, vo.getAddr_gu());
				pstm.setInt(2, vo.getContent_id());
				pstm.setString(3, vo.getContent_nm());
				pstm.setString(4, vo.getNew_addr());
				pstm.setInt(5, vo.getCradle_count());
				pstm.setDouble(6, vo.getLongitude());
				pstm.setDouble(7, vo.getLatitude());
				
				pstm.addBatch();
				cnt++;
			}
			System.out.println("query 준비: 총 "+cnt+"개");
			int[] result = pstm.executeBatch();
			
			for(int i=0; i<result.length; i++) {
				if(result[i] == -2) {
					res++;
				}
			}
			if(res == vos.size()) {
				commit(con);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstm, con);
		}
		return res;
	}
	public int delete() {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		
		String sql =" DELETE FROM BIKE_TB ";
		
		try {
			pstm = con.prepareStatement(sql);
			res = pstm.executeUpdate();
			if(res>0) {
				commit(con);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstm, con);
		}
		return res;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
