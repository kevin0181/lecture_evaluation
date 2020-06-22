package evaluation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import util.DatabaseUtil;

public class EvaluationDAO {

	public int write(EvaluationDTO evaluationDTO) { // 글쓰기

		String SQL = "insert into evaluation values(null,?,?,?,?,?,?,?,?,?,?,?,?,0)";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, evaluationDTO.getUserID().replaceAll("<", "&lt;").replaceAll(">","&gt;").replaceAll("\r\n", "<br>"));
			pstmt.setString(2, evaluationDTO.getLectureName().replaceAll("<", "&lt;").replaceAll(">","&gt;").replaceAll("\r\n", "<br>"));
			pstmt.setString(3, evaluationDTO.getProfessorName().replaceAll("<", "&lt;").replaceAll(">","&gt;").replaceAll("\r\n", "<br>"));
			pstmt.setInt(4, evaluationDTO.getLectureYear());
			pstmt.setString(5, evaluationDTO.getSemesterDivide().replaceAll("<", "&lt;").replaceAll(">","&gt;").replaceAll("\r\n", "<br>"));
			pstmt.setString(6, evaluationDTO.getLectureDivide().replaceAll("<", "&lt;").replaceAll(">","&gt;").replaceAll("\r\n", "<br>"));
			pstmt.setString(7, evaluationDTO.getEvaluationTitle().replaceAll("<", "&lt;").replaceAll(">","&gt;").replaceAll("\r\n", "<br>"));
			pstmt.setString(8, evaluationDTO.getEvaluationContent().replaceAll("<", "&lt;").replaceAll(">","&gt;").replaceAll("\r\n", "<br>"));
			pstmt.setString(9, evaluationDTO.getTotalScore().replaceAll("<", "&lt;").replaceAll(">","&gt;").replaceAll("\r\n", "<br>"));
			pstmt.setString(10, evaluationDTO.getCreditScore().replaceAll("<", "&lt;").replaceAll(">","&gt;").replaceAll("\r\n", "<br>"));
			pstmt.setString(11, evaluationDTO.getComfortableScore().replaceAll("<", "&lt;").replaceAll(">","&gt;").replaceAll("\r\n", "<br>"));
			pstmt.setString(12, evaluationDTO.getLectureScore().replaceAll("<", "&lt;").replaceAll(">","&gt;").replaceAll("\r\n", "<br>"));

			return pstmt.executeUpdate();

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
		return -1; // 데이터베이스 오류
	}

	public ArrayList<EvaluationDTO> getList(String lectureDivide, String searchType, String search, int pageNumber) {
		if (lectureDivide.equals("전체")) {
			lectureDivide = "";
		}

		ArrayList<EvaluationDTO> evaluationList = null;

		String SQL = "";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			if (searchType.equals("최신순")) {
				SQL = "select * from evaluation where lectureDivide LIKE ? and concat(lectureName, professorName, evaluationTitle, evaluationContent) LIKE "
						+ "? order by evaluationID desc LIMIT " + pageNumber * 5 + "," + pageNumber * 5 + 6;
			} else if (searchType.equals("추천순")) {
				SQL = "select * from evaluation where lectureDivide LIKE ? and concat(lectureName, professorName, evaluationTitle, evaluationContent) LIKE "
						+ "? order by likeCount desc LIMIT " + pageNumber * 5 + "," + pageNumber * 5 + 6;
			}

			conn = DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, "%" + lectureDivide + "%");
			pstmt.setString(2, "%" + search + "%");
			rs = pstmt.executeQuery();

			evaluationList = new ArrayList<EvaluationDTO>();

			while (rs.next()) {
				EvaluationDTO evaluation = new EvaluationDTO(rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getString(4), rs.getInt(5), rs.getString(6), rs.getString(7), rs.getString(8),
						rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13),
						rs.getInt(14));
				evaluationList.add(evaluation);
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
		return evaluationList; // 결과물이 없으면 null 값이 들어옴.
	}

	public int like(String evaluationID) { // 좋아요 갯수

		String SQL = "update evaluation set likeCount = likeCount+1 where evaluationID = ?";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, Integer.parseInt(evaluationID));

			return pstmt.executeUpdate();

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
		return -1; // 데이터베이스 오류
	}

	public int delete(String evaluationID) { // 삭제

		String SQL = "delete from evaluation where evaluationID = ?";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, Integer.parseInt(evaluationID));

			return pstmt.executeUpdate();

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
		return -1; // 데이터베이스 오류
	}

	public String getUserID(String evaluationID) { // 특정한 강의평가글 유저아이디 가져옴

		String SQL = "SELECT userID from evaluation where evaluationID = ?";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, Integer.parseInt(evaluationID));

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
		return null; // 존재하지않는 유저

	}
}
