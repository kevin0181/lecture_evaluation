package likey;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.DatabaseUtil;

public class LikeyDAO {

	public int like(String userID, String evaluationID, String userIP) { //추천 부분.

		String SQL = "INSERT INTO likey VALUES (?,?,?)";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			pstmt.setString(2, evaluationID);
			pstmt.setString(3, userIP);

			return pstmt.executeUpdate(); // 성공이면 1값을 반환.

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
		return -1; // 추천 중복 오류
	}

}
