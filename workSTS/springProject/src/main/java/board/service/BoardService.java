package board.service;

import java.util.List;
import java.util.Map;

import board.bean.BoardDTO;

public interface BoardService {

	public void boardWrite(BoardDTO boardDTO);

	public List<BoardDTO> getBoardList(Map<String, Integer> map);

	public int getTotalA();

	public BoardDTO getBoardView(int seq);

	public List<BoardDTO> getBoardSearchList(Map<String, Object> map);

	public int getSearchTotalA(Map<String, Object> map);

}
