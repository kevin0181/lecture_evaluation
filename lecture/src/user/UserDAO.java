package user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.DatabaseUtil;

public class UserDAO {

	public int login(String userID, String userPassword) { // 로그인

		String SQL = "select userPassword from user where userID = ?";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				if (rs.getString(1).equals(userPassword)) {
					return 1; // 로그인 성공
				} else {
					return 0; // 비밀번호 틀림
				}
			}
			return -1; // 아이디없음

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close(); // conn 해제
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close(); // pstmt 해제
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (rs != null) {
				try {
					rs.close(); // rs 해제
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return -2; // 데이터베이스 오류
	}

	public int join(UserDTO user) { //회원가입

		String SQL = "INSERT INTO USER VALUES (?,?,?,?,false)";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, user.getUserID());
			pstmt.setString(2, user.getUserPassword());
			pstmt.setString(3, user.getUserEmail());
			pstmt.setString(4, user.getUserEmailHash());

			return pstmt.executeUpdate(); // 성공이면 1값을 반환.

		} catch (Exception e) {
			e.printStackTrace();
			return -1; // 회원가입 실패
		} finally {
			if (conn != null) {
				try {
					conn.close(); // conn 해제
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close(); // pstmt 해제
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (rs != null) {
				try {
					rs.close(); // rs 해제
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public String getUserEmail(String userID) { //이메일주소 반환
		String SQL = "select userEmail from user where userID = ?";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				return rs.getString(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close(); // conn 해제
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close(); // pstmt 해제
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (rs != null) {
				try {
					rs.close(); // rs 해제
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return null; // 데이터베이스 오류
	}

	public boolean getUserEmailChecked(String userID) { //이메일인증되었는지 확인해주는부분

		String SQL = "SELECT userEmailChecked from user where userID = ?";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				return rs.getBoolean(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close(); // conn 해제
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close(); // pstmt 해제
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (rs != null) {
				try {
					rs.close(); // rs 해제
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return false; // 데이터베이스 오류
	}

	public boolean setUserEmailChecked(String userID) { //특정 사용자의 이메일 인증

		String SQL = "update user set userEmailChecked = true where userID = ?";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			pstmt.executeUpdate(); // 성공이면 1값을 반환.

			return true;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close(); // conn 해제
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close(); // pstmt 해제
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (rs != null) {
				try {
					rs.close(); // rs 해제
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return false; // 데이터베이스 오류
	}

}
