package imageboard.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import imageboard.bean.ImageboardDTO;
import imageboard.dao.ImageboardDAO;

@Service("imageboardService")
public class ImageboardServiceImpl implements ImageboardService {
	@Autowired
	private ImageboardDAO imageboardDAO;

	@Override
	public void imageboardWrite(ImageboardDTO imageboardDTO) {
		imageboardDAO.imageboatdWrite(imageboardDTO);
		
	}

	@Override
	public List<ImageboardDTO> getImageboardList(Map<String, Integer> map) {
		return imageboardDAO.getImageboardList(map);
	}

	@Override
	public void deleteImage(String[] check) {
		imageboardDAO.deleteImage(check);
		
	}

	@Override
	public ImageboardDTO getImageView(String seq) {
		return imageboardDAO.getImageView(seq);
	}

}
