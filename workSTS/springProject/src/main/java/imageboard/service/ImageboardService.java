package imageboard.service;

import java.util.List;
import java.util.Map;

import imageboard.bean.ImageboardDTO;
import imageboard.bean.ImageboardQnaDTO;

public interface ImageboardService {

	public void imageboardWrite(ImageboardDTO imageboardDTO);

	public List<ImageboardDTO> getImageboardList(Map<String, Integer> map);

	public void deleteImage(String[] check);

	public ImageboardDTO getImageboardView(int seq);

	public int getImageboardTotalA();

	public void imageboardQnaWrite(Map<String, String> map);

	public List<ImageboardQnaDTO> getImageboardQnaList(Map<String, String> map);

}
