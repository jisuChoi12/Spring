package board.dao;

import java.util.List;
import java.util.Map;

import board.bean.BoardDTO;

public interface BoardDAO {

	public void writeBoard(BoardDTO boardDTO);

	public List<BoardDTO> getBoardList(Map<String, Integer> map);

	public int getTotalA();

	public BoardDTO getBoardView(int seq);

	public List<BoardDTO> getBoardSearchList(Map<String, String> map);

	public int getSearchTotalA(Map<String, String> map);

	public void boardModify(Map<String, Object> map);

	public void boardReply(Map<String, String> map);

	public void boardDelete(int seq);

}
