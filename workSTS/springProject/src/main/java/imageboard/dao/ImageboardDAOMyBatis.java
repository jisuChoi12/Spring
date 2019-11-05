package imageboard.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import imageboard.bean.ImageboardDTO;
import imageboard.bean.ImageboardQnaDTO;

@Transactional
@Repository("imageboardDAO")
public class ImageboardDAOMyBatis implements ImageboardDAO {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public void imageboatdWrite(ImageboardDTO imageboardDTO) {
		sqlSession.insert("imageboardSQL.imageboardWrite", imageboardDTO);
		
	}

	@Override
	public List<ImageboardDTO> getImageboardList(Map<String, Integer> map) {
		return sqlSession.selectList("imageboardSQL.getImageboardList", map);
	}

	@Override
	public void deleteImage(String[] check) {
		Map<String, String[]> map = new HashMap<String, String[]>();
		map.put("check", check);
		sqlSession.delete("imageboardSQL.deleteImage",map);
		
	}

	@Override
	public ImageboardDTO getImageboardView(int seq) {
		return sqlSession.selectOne("imageboardSQL.getImageboardView", seq);
	}
	
	@Override
	public int getImageboardTotalA() {
		return sqlSession.selectOne("imageboardSQL.getImageboardTotalA");
	}

	@Override
	public void imageboardQnaWrite(Map<String, String> map) {
		sqlSession.insert("imageboardSQL.imageboardQnaWrite", map);
		
	}

	@Override
	public List<ImageboardQnaDTO> getImageboardQnaList(Map<String, String> map) {
		return sqlSession.selectList("imageboardSQL.getImageboardQnaList", map);
	}

	@Override
	public ImageboardQnaDTO getImageboardQna(Map<String, String> map) {
		return sqlSession.selectOne("imageboardSQL.getImageboardQna", map);
	}

	@Override
	public void imageboardQnaModify(Map<String, String> map) {
		sqlSession.update("imageboardSQL.imageboardQnaModify", map);
		
	}
}
