package board.dao;

import java.util.List;
import java.util.Map;

import board.bean.BoardDTO;

public interface BoardDAO {

	void writeBoard(BoardDTO boardDTO);

	List<BoardDTO> getBoardList(Map<String, Integer> map);

	int getTotalA();

	BoardDTO getBoardView(int seq);

	List<BoardDTO> getBoardSearchList(Map<String, Object> map);

	int getSearchTotalA(Map<String, Object> map);

}
