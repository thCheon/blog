package com.cos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.cos.dto.MemberVO;
import com.cos.util.DBManager;

public class MemberDAO {
	private static Connection conn = null;
	private static PreparedStatement pstmt = null;
	private static ResultSet rs = null;
	
	// insert
	public int insert(MemberVO mvo) {
		String SQL = "INSERT INTO member VALUES(?,?,?,?,?,false)";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, mvo.getId());
			pstmt.setString(2, mvo.getPassword());
			pstmt.setString(3, mvo.getUsername());
			pstmt.setString(4, mvo.getEmail());
			pstmt.setString(5, mvo.getSalt());
			pstmt.executeUpdate();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		return -1;
	}
	
	public int login(String id, String pw) {
		String SQL = "SELECT emailcheck FROM member WHERE id = ? AND password = ?";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			rs = pstmt.executeQuery();
			if(rs.next()) if(rs.getBoolean("emailcheck"))return 1;else return 2;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public int select_emailcheck(String id) {
		String SQL = "SELECT emailcheck From member WHERE id = ?";
		conn = DBManager.getConnection();
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				boolean emailcheck = rs.getBoolean("emailcheck");
				if(emailcheck) {
					return 1; // 인증
				} else {
					return 2; // 미인증
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return -1; // 오류
	}
	
	public String select_salt(String id) {
		String SQL = "SELECT salt From member WHERE id = ?";
		conn = DBManager.getConnection();
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String salt = rs.getString("salt");
				return salt;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return null; // 오류
	}
	
	public String select_email(String id) {
		String SQL = "SELECT email From member WHERE id = ?";
		conn = DBManager.getConnection();
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String email = rs.getString("email");
				return email;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return null; // 오류
	}
	
	public int update_emailcheck(String id) {
		String SQL = "UPDATE member SET emailcheck = true WHERE id = ?";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		return -1;
	}
}
