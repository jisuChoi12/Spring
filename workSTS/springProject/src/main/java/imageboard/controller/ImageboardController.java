package imageboard.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import imageboard.bean.ImageboardDTO;
import imageboard.service.ImageboardService;

@Controller
public class ImageboardController {
	@Autowired
	private ImageboardService imageboardService;

	@RequestMapping(value="/imageboard/imageboardWriteForm", method=RequestMethod.GET)
	public String imageboardWriteForm(Model model) {
		model.addAttribute("display","/imageboard/imageboardWriteForm.jsp");
		return "/main/index";
	}
	
//	name="img"가 하나일 경우
//	@RequestMapping(value="/imageboard/imageboardWrite", method = RequestMethod.POST)
//	@ResponseBody
//	public void imageboardWrite(@ModelAttribute ImageboardDTO imageboardDTO,
//								@RequestParam MultipartFile img,
//								Model model) {
//		String filePath = "C:/Spring/workSTS/springProject/src/main/webapp/storage"; // 원하는 위치
//		String fileName = img.getOriginalFilename(); // 파일의 원래 이름
//		File file = new File(filePath, fileName); // 파일 생성
//		
//		try {
//			FileCopyUtils.copy(img.getInputStream(), new FileOutputStream(file)); // spring저장소에서 storage로 복사
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		imageboardDTO.setImage1(fileName); // DTO에 재명명한 이름 넣기
//		
//		imageboardService.imageboardWrite(imageboardDTO);
//	}
	
//	name="img"가 하나 이상일 경우 - 배열로 받는다
//	@RequestMapping(value="/imageboard/imageboardWrite", method = RequestMethod.POST)
//	@ResponseBody
//	public void imageboardWrite(@ModelAttribute ImageboardDTO imageboardDTO,
//								@RequestParam MultipartFile[] img, 
//								Model model) {
//		String filePath = "C:/Spring/workSTS/springProject/src/main/webapp/storage"; // 원하는 위치
//		String fileName; // 파일의 원래 이름
//		File file; // 파일 생성
//		
//		if(img[0]!=null) {
//			fileName = img[0].getOriginalFilename();
//			file = new File(filePath, fileName);
//			try {
//				FileCopyUtils.copy(img[0].getInputStream(), new FileOutputStream(file)); // spring저장소에서 storage로 복사
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			imageboardDTO.setImage1(fileName); // DTO에 재명명한 이름 넣기			
//		} else {
//			imageboardDTO.setImage1(null); 
//		}
//		if(img[1]!=null) {
//			fileName = img[1].getOriginalFilename();
//			file = new File(filePath, fileName);
//			try {
//				FileCopyUtils.copy(img[1].getInputStream(), new FileOutputStream(file)); // spring저장소에서 storage로 복사
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			imageboardDTO.setImage2(fileName); // DTO에 재명명한 이름 넣기			
//		} else {
//			imageboardDTO.setImage2(null); 
//		}
//		
//		
//		imageboardService.imageboardWrite(imageboardDTO);
//	}
	
//	드래그 해서 한번어 여러개의 파일을 선택
	@RequestMapping(value="/imageboard/imageboardWrite", method = RequestMethod.POST)
	@ResponseBody
	public void imageboardWrite(@ModelAttribute ImageboardDTO imageboardDTO,
								@RequestParam("img[]") List<MultipartFile> list,
								Model model) {
		String filePath = "C:/Spring/workSTS/springProject/src/main/webapp/storage"; // 원하는 위치
		
		for(MultipartFile img : list) {
			String fileName = img.getOriginalFilename(); // 파일의 원래 이름
			File file = new File(filePath, fileName); // 파일 생성
			try {
				FileCopyUtils.copy(img.getInputStream(), new FileOutputStream(file)); // spring저장소에서 storage로 복사
			} catch (IOException e) {
				e.printStackTrace();
			}
			imageboardDTO.setImage1(fileName); // DTO에 재명명한 이름 넣기

			imageboardService.imageboardWrite(imageboardDTO); // 여기서 바로 디비 여러번 가게되면 엄청 느림
		}//for
	}
}


// $('#imageboardWriteForm').serialize() -> ImageboardDTO
//        img1                                   image1
// img1 image1 이름을 다르게 해서 db로 바로 못가게 하고 multipart로 업로드
// 업로드 되는 위치는 spring이 제공한다 : 스프링이 제공하는 위치/~~~.tmp
// 스프링이 제공하는 위치에서 임시파일을 받아서 -> 원하는 위치로 복사하고 파일 재명명 -> 바뀐 파일명을 ImageboardDTO에 있는 image1에 넣고
// DB에 가서 저장하기