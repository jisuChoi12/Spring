package board.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import board.bean.BoardDTO;

@Transactional
@Repository("boardDAO")
public class BoardDAOMyBatis implements BoardDAO{
	@Autowired
	private SqlSession SqlSession;

	@Override
	public void writeBoard(BoardDTO boardDTO) {
		SqlSession.insert("boardSQL.boardWrite", boardDTO);
	}
}
