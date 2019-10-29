package imageboard.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import imageboard.bean.ImageboardDTO;

@Transactional
@Repository("imageboardDAO")
public class ImageboardDAOMyBatis implements ImageboardDAO {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public void imageboatdWrite(ImageboardDTO imageboardDTO) {
		sqlSession.insert("imageboardSQL.imageboardWrite", imageboardDTO);
		
	}
}
