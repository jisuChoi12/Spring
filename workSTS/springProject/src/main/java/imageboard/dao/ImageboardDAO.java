package imageboard.dao;

import java.util.List;
import java.util.Map;

import imageboard.bean.ImageboardDTO;
import imageboard.bean.ImageboardQnaDTO;

public interface ImageboardDAO {

	public void imageboatdWrite(ImageboardDTO imageboardDTO);

	public List<ImageboardDTO> getImageboardList(Map<String, Integer> map);

	public void deleteImage(String[] check);

	public ImageboardDTO getImageboardView(int seq);

	public int getImageboardTotalA();

	public void imageboardQnaWrite(Map<String, String> map);

	public List<ImageboardQnaDTO> getImageboardQnaList(Map<String, String> map);

	public ImageboardQnaDTO getImageboardQna(Map<String, String> map);

	public void imageboardQnaModify(Map<String, String> map);

}
