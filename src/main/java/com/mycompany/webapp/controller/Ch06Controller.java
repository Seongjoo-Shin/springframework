package com.mycompany.webapp.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/ch06")
@Log4j2
public class Ch06Controller {
	
	@RequestMapping("/content")
	public String content() {
		return "ch06/content";
	}
	
	@GetMapping("/forward")
	public String forward() {
		return "ch06/forward";
	}
	
	@GetMapping("/redirect")
	public String redirect() {
		return "redirect:/";
	}
	
	@GetMapping("/getFragmentHtml")
	public String getFragmentHtml() {
		// html 조각을 반드시 생성해서 보내줘야함
		return "ch06/fragmentHtml";
	}
	
	@GetMapping("/getJson1")
	public void getJson1(HttpServletResponse response) throws Exception { // 컨트롤러에서 직접 응답을 전해주기 때문에 리턴 타입을 void로 선언
		// {"fileName": "photo6.jpg"}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("fileName", "photo6.jpg");
		String json = jsonObject.toString();
		
		// 요청에 대한 응답을 컨트롤러가 직접 만드는 경우
		// return값이 필요가 없다
		response.setContentType("application/json; charset=UTF-8"); // 요청에 대한 응답으로 넘겨줄 content의 타입, 문자가 한글인 경우 charset도 지정해줘야함
		PrintWriter pw = response.getWriter();
		pw.write(json);
		pw.flush();
		pw.close();
		
	}
	
	@GetMapping(value="/getJson2", produces="application/json; charset=UTF-8") // produces의 값은 헤더행에 Content-Type의 값으로 들어감
	@ResponseBody //리턴되는 내용이 응답 본문에 들어간다는 표시
	public String getJson2() throws Exception { 
		// {"fileName": "photo6.jpg"}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("fileName", "photo7.jpg");
		String json = jsonObject.toString();
		return json;
	}
	
	@GetMapping("/getJson3")
	public String getJson3() {
		
		return "";
	}
}
