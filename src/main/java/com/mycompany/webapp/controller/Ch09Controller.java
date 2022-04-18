package com.mycompany.webapp.controller;

import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mycompany.webapp.dto.Ch09Dto;

import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/ch09")
@Log4j2
public class Ch09Controller {
	
	@RequestMapping("/content")
	public String content() {
		return "ch09/content";
	}
	
	@PostMapping("/fileupload")
	public String fileupload(String title, String desc, MultipartFile attach) throws Exception {
		log.info("실행");
		log.info("title : " + title);
		log.info("desc : " + desc);
		
		log.info("file original name : " + attach.getOriginalFilename());
		log.info("file content type : " + attach.getContentType());
		log.info("file size : " + attach.getSize());
		
		// 경로는 보안사항이어서 서버로 전송되지 않는다
		// 받은 파일을 DB에 저장할 때
		//byte[] bytes = attach.getBytes();
		//InputStream is = attach.getInputStream();
		
		// 받은 파일을 서버 파일 시스템에 저장할 때
		// 동일한 이름의 파일의 저장을 막기 위해 long타입의 time값을 더해서 이름을 저장한다
		String saveFileName = new Date().getTime() + "_" + attach.getOriginalFilename();
		File file = new File("C:/Temp/uploadfiles/" + saveFileName);
		attach.transferTo(file);
		
		return "redirect:/ch09/content";
	}
	
	@PostMapping(value="/fileuploadAjax", produces="application/json; charset=UTF-8")
	@ResponseBody
	public String fileuploadAjax(Ch09Dto dto) throws Exception {
		log.info("실행");
		log.info("title : " + dto.getTitle());
		log.info("desc : " + dto.getDesc());
		
		// 다 db에 저장
		log.info("file original name : " + dto.getAttach().getOriginalFilename());
		log.info("file content type : " + dto.getAttach().getContentType());
		log.info("file size : " + dto.getAttach().getSize());
		
		// 경로는 보안사항이어서 서버로 전송되지 않는다
		// 받은 파일을 DB에 저장할 때
		//byte[] bytes = attach.getBytes();
		//InputStream is = attach.getInputStream();
		
		// 받은 파일을 서버 파일 시스템에 저장할 때
		// 동일한 이름의 파일의 저장을 막기 위해 long타입의 time값을 더해서 이름을 저장한다
		String saveFileName = new Date().getTime() + "_" + dto.getAttach().getOriginalFilename();
		File file = new File("C:/Temp/uploadfiles/" + saveFileName);
		dto.getAttach().transferTo(file);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", "success");
		jsonObject.put("saveFileName", saveFileName);
		String json = jsonObject.toString();
		
		return json;
		
	}
	
	@RequestMapping("/filedownload")
	   public void filedownload(int fileNo, HttpServletResponse response, @RequestHeader("User-Agent") String userAgent) throws Exception {
	      //DB에서 가져올 정보
	      String contentType = "image/png";
	      String originalFilename = "like.png";
	      String saveFilename = "1650009252957_like.png";
	      
	      //응답 내용의 데이터 타입을 응답 헤더에 추가
	      response.setContentType(contentType);
	      
	      //다운로드할 파일명을 헤더에 추가
	      if(userAgent.contains("Trident") || userAgent.contains("MSIE")) {
	         //IE 브라우저일 경우
	         originalFilename = URLEncoder.encode(originalFilename, "UTF-8");
	      } else {
	         //크롬, 엣지, 사파리일 경우
	         originalFilename = new String(originalFilename.getBytes("UTF-8"), "ISO-8859-1");
	      }
	      response.setHeader("Content-Disposition", "attachment; filename=\"" + originalFilename + "\"");
	      
	      //파일 데이터를 응답 본문에 실기
	      File file = new File("C:/Temp/uploadfiles/" + saveFilename);
	      if(file.exists()) {
	         FileCopyUtils.copy(new FileInputStream(file), response.getOutputStream());
	      }
	   }
}
