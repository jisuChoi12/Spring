package board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import board.bean.BoardDTO;

@Transactional
@Repository("boardDAO")
public class BoardDAOMyBatis implements BoardDAO{
	@Autowired
	private SqlSession sqlSession;

	@Override
	public void writeBoard(BoardDTO boardDTO) {
		sqlSession.insert("boardSQL.boardWrite", boardDTO);
	}

	@Override
	public List<BoardDTO> getBoardList(Map<String, Integer> map) {
		return sqlSession.selectList("boardSQL.getBoardList", map);
	}

	@Override
	public int getTotalA() {
		return sqlSession.selectOne("boardSQL.getTotalA");
	}

	@Override
	public BoardDTO getBoardView(int seq) {
		return sqlSession.selectOne("boardSQL.getBoardView", seq);
	}

	@Override
	public List<BoardDTO> getBoardSearchList(Map<String, String> map) {
		return sqlSession.selectList("boardSQL.getBoardSearchList", map);
	}

	@Override
	public int getSearchTotalA(Map<String, String> map) {
		return sqlSession.selectOne("boardSQL.getSearchTotalA",map);
	}

	@Override
	public void boardModify(Map<String, Object> map) {
		sqlSession.update("boardSQL.boardModify",map);
		
	}
}
