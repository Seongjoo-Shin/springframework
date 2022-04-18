package com.mycompany.webapp.view;

import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class Ch12FileDownloadView extends AbstractView {

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("실행");
		String fileName = (String) model.get("fileName");
		String userAgent = (String) model.get("userAgent");

		// DB에서 가져올 정보
		String contentType = request.getServletContext().getMimeType(fileName);
		String originalFilename = fileName;
		String saveFilename = fileName;

		// 응답 내용의 데이터 타입을 응답 헤더에 추가
		response.setContentType(contentType);

		// 다운로드할 파일명을 헤더에 추가
		if (userAgent.contains("Trident") || userAgent.contains("MSIE")) {
			// IE 브라우저일 경우
			originalFilename = URLEncoder.encode(originalFilename, "UTF-8");
		} else {
			// 크롬, 엣지, 사파리일 경우
			originalFilename = new String(originalFilename.getBytes("UTF-8"), "ISO-8859-1");
		}
		response.setHeader("Content-Disposition", "attachment; filename=\"" + originalFilename + "\"");

		// 파일 or Binary데이터를 응답 본문에 싣기 위하여
		File file = new File("C:/Temp/uploadfiles/" + saveFilename);
		if (file.exists()) {
			/*InputStream is = new FileInputStream(file);
			ServletOutputStream os = response.getOutputStream(); // 파일 그 자체로 사용하기 위해, ServletOutputStream은 OutputStream의 자식
			FileCopyUtils.copy(is, os);
			os.flush();
			is.close();
			os.close();*/
			FileCopyUtils.copy(new FileInputStream(file), response.getOutputStream());
		}
	}
}
