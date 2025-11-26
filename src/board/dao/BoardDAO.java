package board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import board.util.DBUtil;
import board.vo.BoardVO;

public class BoardDAO {

	public List<BoardVO> selectAll() {
		List<BoardVO> list = new ArrayList<>();
		String sql = "SELECT * FROM board ORDER BY board_id DESC";

		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pst = conn.prepareStatement(sql);
				ResultSet rs = pst.executeQuery()) {

			while (rs.next()) {
				BoardVO vo = new BoardVO();
				vo.setBoardId(rs.getInt("board_id"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setWriter(rs.getString("writer"));
				vo.setWriteDate(rs.getDate("write_date"));
				list.add(vo);
			}

		} catch (Exception e) {
			System.out.println("selectAll 오류: " + e.getMessage());
		}

		return list;
	}

	public int insert(String title, String content, String writer) {
		String sql = "INSERT INTO board(title, content, writer) VALUES(?, ?, ?)";
		int result = 0;

		try (Connection conn = DBUtil.getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {

			pst.setString(1, title);
			pst.setString(2, content);
			pst.setString(3, writer);

			result = pst.executeUpdate();

		} catch (Exception e) {
			System.out.println("insert 오류: " + e.getMessage());
		}

		return result;
	}

	public void delete(int boardId) {
		String sql = "delete from board where board_id = ? ";
		int result = 0;

		try (Connection conn = DBUtil.getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {

			pst.setInt(1, boardId);
			
			result = pst.executeUpdate();

		} catch (Exception e) {
			System.out.println("insert 오류: " + e.getMessage());
		}

		System.out.println("삭제가 완료되었습니다");;
	}

	public boolean configureID(int id) {
		String sql = "SELECT 1 FROM board WHERE board_id = ?";
	    try (Connection conn = DBUtil.getConnection();
	         PreparedStatement pst = conn.prepareStatement(sql)) {

	    	pst.setInt(1, id);
	        ResultSet rs = pst.executeQuery();

	        return rs.next(); 
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return false;
	}

	public int update(int id, String title, String content, String writer) {
		 String sql = "UPDATE board "
	               + "SET title = ?, content = ?, writer = ? "
	               + "WHERE board_id = ?";
		int result = 0;

		try (Connection conn = DBUtil.getConnection(); PreparedStatement pst = conn.prepareStatement(sql)) {

			pst.setString(1, title);
			pst.setString(2, content);
			pst.setString(3, writer);
			pst.setInt(4, id);


			result = pst.executeUpdate();

		} catch (Exception e) {
			System.out.println("insert 오류: " + e.getMessage());
		}

		return result;
	}



}
