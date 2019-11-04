package imageboard.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.servlet.ModelAndView;

import imageboard.bean.ImageboardDTO;
import imageboard.bean.ImageboardPaging;
import imageboard.bean.ImageboardQnaDTO;
import imageboard.service.ImageboardService;

@Controller
public class ImageboardController {
	@Autowired
	private ImageboardService imageboardService;
	@Autowired
	private ImageboardPaging imageboardPaging;

	@RequestMapping(value = "/imageboard/imageboardWriteForm", method = RequestMethod.GET)
	public String imageboardWriteForm(Model model) {
		model.addAttribute("display", "/imageboard/imageboardWriteForm.jsp");
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
	@RequestMapping(value = "/imageboard/imageboardWrite", method = RequestMethod.POST)
	@ResponseBody
	public void imageboardWrite(@ModelAttribute ImageboardDTO imageboardDTO,
			@RequestParam("img[]") List<MultipartFile> list, Model model) {
		String filePath = "C:/Spring/workSTS/springProject/src/main/webapp/storage"; // 원하는 위치

		for (MultipartFile img : list) {
			String fileName = img.getOriginalFilename(); // 파일의 원래 이름
			File file = new File(filePath, fileName); // 파일 생성
			try {
				FileCopyUtils.copy(img.getInputStream(), new FileOutputStream(file)); // spring저장소에서 storage로 복사
			} catch (IOException e) {
				e.printStackTrace();
			}
			imageboardDTO.setImage1(fileName); // DTO에 재명명한 이름 넣기

			imageboardService.imageboardWrite(imageboardDTO); // 여기서 바로 디비 여러번 가게되면 엄청 느림
		} // for
	}

	@RequestMapping(value = "/imageboard/imageboardList", method = RequestMethod.GET)
	public String imageboadList(@RequestParam(defaultValue = "1", required = false) String pg, Model model) {
		model.addAttribute("pg", pg);
		model.addAttribute("display", "/imageboard/imageboardList.jsp");
		return "/main/index";
	}

	@RequestMapping(value = "/imageboard/getImageboardList", method = RequestMethod.POST)
	public ModelAndView getImageboardList(@RequestParam(defaultValue = "1", required = false) String pg) {
		// 1페이지당 3개씩
		int endNum = Integer.parseInt(pg)*3;
		int startNum = endNum-2;
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("startNum", startNum);
		map.put("endNum", endNum);
		
		List<ImageboardDTO> list = imageboardService.getImageboardList(map);
		
		// 페이징 처리
		int totalA = imageboardService.getImageboardTotalA();
		imageboardPaging.setCurrentPage(Integer.parseInt(pg));
		imageboardPaging.setPageBlock(3);
		imageboardPaging.setPageSize(3);
		imageboardPaging.setTotalA(totalA);
		imageboardPaging.makePagingHTML();
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("pg", pg);
		mav.addObject("list", list);
		mav.addObject("imageboardPaging", imageboardPaging);
		mav.setViewName("jsonView");
		
		return mav;
	}
	
	@RequestMapping(value="/imageboard/imageboardDelete", method = RequestMethod.POST)
	public String imageboardDelete (@RequestParam String[] check, Model model) {
		imageboardService.deleteImage(check);
		model.addAttribute("display","/imageboard/imageboardList.jsp");
		return "/main/index";
	}

	@RequestMapping(value="/imageboard/imageboardView", method = RequestMethod.GET)
	public String imageboardView(@RequestParam String seq, @RequestParam(defaultValue = "1", required = false) String pg, Model model) {
		model.addAttribute("seq", seq);
		model.addAttribute("pg", pg);
		model.addAttribute("display", "/imageboard/imageboardView.jsp");
		return "/main/index";
	}
	
	@RequestMapping(value="/imageboard/getImageboardView", method = RequestMethod.POST)
	public ModelAndView getImageboardView(@RequestParam String seq) {
		ImageboardDTO imageboardDTO = imageboardService.getImageboardView(Integer.parseInt(seq));
		ModelAndView mav = new ModelAndView();
		mav.addObject("imageboardDTO", imageboardDTO);
		mav.setViewName("jsonView");
		return mav;
	}
	
	@RequestMapping(value="/imageboard/imageboardQna", method = RequestMethod.GET)
	public String imageboardQna(@RequestParam String productCode, @RequestParam (defaultValue = "1", required = false) String pg, Model model, HttpSession session) {
		model.addAttribute("productCode", productCode);
		model.addAttribute("pg", pg);
		model.addAttribute("memId", (String)session.getAttribute("memId"));
		model.addAttribute("display", "/imageboard/imageboardQna.jsp");
		return "/main/index";
	}
	
	@RequestMapping(value="/imageboard/imageboardQnaWrite", method = RequestMethod.POST)
	@ResponseBody
	public void imageboardQnaWrite(@RequestParam Map<String, String > map) {
		//System.out.println(map.get("summernote"));
		System.out.println("상품코드 : "+map.get("productCode"));
		 if(map.get("secretCheckBox")==null) { // 비밀글 체크 안했을때
		 map.put("secretCheckBox", "0"); }
		 imageboardService.imageboardQnaWrite(map);
	}
	
	@RequestMapping(value="/imageboard/imageUpload", method = RequestMethod.POST, produces = "application/text; charset=utf-8")
	@ResponseBody
	public String handleFileUpload(@RequestParam("uploadFile") MultipartFile multiPartFile){
		String filePath = "C:/Spring/workSTS/springProject/src/main/webapp/storage"; // 원하는 위치
		String fileName = multiPartFile.getOriginalFilename();
		File file = new File (filePath, fileName);
		try {
			FileCopyUtils.copy(multiPartFile.getInputStream(), new FileOutputStream(file)); // spring저장소에서 storage로 복사
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileName;
	}
	
	@RequestMapping(value="/imageboard/imageboardQnaList", method = RequestMethod.GET)
	public String imageboardQnaList(@RequestParam(defaultValue = "1", required = false) String pg, String productCode, Model model) {
		model.addAttribute("productCode", productCode);
		model.addAttribute("pg", pg);
		model.addAttribute("display", "/imageboard/imageboardQnaList.jsp");
		return "/main/index";
	}
	
	@RequestMapping(value="/imageboard/getImageboardQnaList", method= RequestMethod.POST)
	public ModelAndView getImageboardQnaList(@RequestParam(defaultValue="1",required = false)String pg, String productCode) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("productCode", productCode);
		
		List<ImageboardQnaDTO> list = imageboardService.getImageboardQnaList(map);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("pg", pg);
		mav.addObject("list",list);
		mav.setViewName("jsonView");
		return mav;
	}
}

// $('#imageboardWriteForm').serialize() -> ImageboardDTO
//        img1                                   image1
// img1 image1 이름을 다르게 해서 db로 바로 못가게 하고 multipart로 업로드
// 업로드 되는 위치는 spring이 제공한다 : 스프링이 제공하는 위치/~~~.tmp
// 스프링이 제공하는 위치에서 임시파일을 받아서 -> 원하는 위치로 복사하고 파일 재명명 -> 바뀐 파일명을 ImageboardDTO에 있는 image1에 넣고
// DB에 가서 저장하기