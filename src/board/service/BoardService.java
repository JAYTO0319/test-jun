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

	public boolean configureID(int id) {
		return dao.configureID(id);
	}

}
