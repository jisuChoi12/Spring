package board.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import board.bean.BoardDTO;
import board.dao.BoardDAO;

@Service(value="boardService")
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardDAO boardDAO;

	@Override
	public void boardWrite(BoardDTO boardDTO) {
		boardDAO.writeBoard(boardDTO);
		
	}

	@Override
	public List<BoardDTO> getBoardList(Map<String, Integer> map) {
		return boardDAO.getBoardList(map);
	}

	@Override
	public int getTotalA() {
		return boardDAO.getTotalA();
	}

	@Override
	public BoardDTO getBoardView(int seq) {
		return boardDAO.getBoardView(seq);
	}

	@Override
	public List<BoardDTO> getBoardSearchList(Map<String, Object> map) {
		return boardDAO.getBoardSearchList(map);
	}

	@Override
	public int getSearchTotalA(Map<String, Object> map) {
		return boardDAO.getSearchTotalA(map);
	}

}
