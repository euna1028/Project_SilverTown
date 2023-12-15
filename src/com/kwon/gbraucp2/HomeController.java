package com.kwon.gbraucp2;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kwon.gbraucp2.member.MemberDAO;
import com.kwon.gbraucp2.sns.SNSDAO;

@Controller
public class HomeController {
	private boolean isFirstReq;

	@Autowired
	private MemberDAO mDAO;

	@Autowired
	private SNSDAO sDAO;

	@Autowired
	private TokenGenerator tg;

	// JVM이 OS위에 올라가고
	// -> static들 만들어지고
	// -> main으로 가서 시작

	// JSP Model 2 :
	// DAO singleton을 수동으로 작업
	// -> static영역에 만들어놨으니
	// -> DAO가 만들어지고 -> HomeController만들어짐

	// (Spring + Maven + MyBatis) Framework :
	// DAO singleton을 Spring이 자동으로
	// DAO를 servlet-context.xml에 하나 등록해놨음
	// C도 servlet-context.xml에 하나 등록해놨음
	// SqlSession도 servlet-context.xml에 하나 등록해놨음
	// servlet-context.xml에 등록해놓으면 자동으로 객체 만들어줌
	// -> static영역이 아님
	// DAO가 먼저? C가 먼저? SqlSession이 먼저?
	public HomeController() {
		isFirstReq = true;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpServletRequest req) {
		// 서비스 시작해놓고 어떤 사용자가 첫 접속때
		if (isFirstReq) {
			sDAO.setAllMsgCount();
			isFirstReq = false;
		}
		mDAO.isLogined(req);
		tg.generate(req);
		sDAO.clearSearch(req);
		sDAO.get(1, req);
		req.setAttribute("contentPage", "home.jsp");
		return "index";
	}
	
	//////////////////////////////////////////////////////////////
	//요양원 페이지로 가는 거 
	//채팅으로 가는 거 
	  @RequestMapping(value = "/map.go", method = RequestMethod.GET)
	   public String mapGo(HttpServletRequest req) {
	      if (mDAO.isLogined(req)) {
	         req.setAttribute("contentPage", "map.jsp");         
	      } else {
	         tg.generate(req);      
	         sDAO.get(1, req);
	         req.setAttribute("contentPage", "home.jsp");         
	      }
	      return "index";
	   }
	
	//채팅으로 가는 거 
	  @RequestMapping(value = "/chat.go", method = RequestMethod.GET)
	   public String chatGo(HttpServletRequest req) {
	      if (mDAO.isLogined(req)) {
	         req.setAttribute("contentPage", "chat.jsp");         
	      } else {
	         tg.generate(req);      
	         sDAO.get(1, req);
	         req.setAttribute("contentPage", "home.jsp");         
	      }
	      return "index";
	   }

	@RequestMapping(value = "/index.do", method = RequestMethod.GET)
	public String indexDo(HttpServletRequest req) {
		return home(req);
	}
}
