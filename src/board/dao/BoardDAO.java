package board.dao;

import java.sql.*;
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

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, title);
            pst.setString(2, content);
            pst.setString(3, writer);

            result = pst.executeUpdate();

        } catch (Exception e) {
            System.out.println("insert 오류: " + e.getMessage());
        }

        return result;
    }
    
    public void delete(String board_id) {
    	String sql = "delete from board where board_id = ?";
    	int result = 0;

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, board_id);
           
            result = pst.executeUpdate();
            

        } catch (Exception e) {
            System.out.println("delete 오류: " + e.getMessage());
        }

        System.out.println("삭제가 완료되었습니다.");
    	
    }
}
