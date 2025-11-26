package board.service;

import java.util.List;

import board.dao.BoardDAO;
import board.vo.BoardVO;

public class BoardService {

    BoardDAO dao = new BoardDAO();

    public List<BoardVO> selectAll() {
        return dao.selectAll();
    }

    public int insert(String title, String content, String writer) {
        return dao.insert(title, content, writer);
    }
<<<<<<< HEAD

	public List<BoardVO> detailSelect(String title) {
		return dao.detailSelect(title);
		
	}
=======
    
    public void delete(String board_id) {
    	dao.delete(board_id);
    }
>>>>>>> develop
}
