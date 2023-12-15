package com.kwon.gbraucp2.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kwon.gbraucp2.TokenGenerator;
import com.kwon.gbraucp2.mail.AuthNumber;
import com.kwon.gbraucp2.sns.SNSDAO;

@Controller
public class MemberController {

	@Autowired
	JavaMailSenderImpl mailSender;

	@Autowired
	private MemberDAO mDAO;

	@Autowired
	private SNSDAO sDAO;

	@Autowired
	private TokenGenerator tg;

	@RequestMapping(value = "/member.bye", method = RequestMethod.GET)
	public String memberBye(HttpServletRequest req) {
		if (mDAO.isLogined(req)) {
			mDAO.bye(req);
		}
		tg.generate(req);
		sDAO.clearSearch(req);
		sDAO.get(1, req);
		req.setAttribute("contentPage", "home.jsp");
		return "index";
	}

	@RequestMapping(value = "/member.get", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public @ResponseBody Members memberGet(Member m) {
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		return mDAO.getMemberIDById(m);
	}

	@RequestMapping(value = "/member.info.go", method = RequestMethod.GET)
	public String memberInfoGo(HttpServletRequest req) {
		if (mDAO.isLogined(req)) {
			mDAO.splitAddr(req);
			req.setAttribute("contentPage", "member/info.jsp");
		} else {
			tg.generate(req);
			sDAO.clearSearch(req);
			sDAO.get(1, req);
			req.setAttribute("contentPage", "home.jsp");
		}
		return "index";
	}

	@RequestMapping(value = "/member.info.update", method = RequestMethod.POST)
	public String memberInfoUpdate(Member m, HttpServletRequest req) {
		if (mDAO.isLogined(req)) {
			mDAO.update(m, req);
			mDAO.splitAddr(req);
			req.setAttribute("contentPage", "member/info.jsp");
		} else {
			tg.generate(req);
			sDAO.clearSearch(req);
			sDAO.get(1, req);
			req.setAttribute("contentPage", "home.jsp");
		}
		return "index";
	}

	@RequestMapping(value = "/member.join", method = RequestMethod.POST)
	public String memberJoin(Member m, HttpServletRequest req) {
		mDAO.join(m, req);
		mDAO.isLogined(req);
		tg.generate(req);
		sDAO.clearSearch(req);
		sDAO.get(1, req);
		req.setAttribute("contentPage", "home.jsp");
		return "index";
	}

	@RequestMapping(value = "/member.join.go", method = RequestMethod.GET)
	public String memberJoinGo(HttpServletRequest req) {
		mDAO.isLogined(req);
		req.setAttribute("contentPage", "member/join.jsp");
		return "index";
	}

	@RequestMapping(value = "/member.login", method = RequestMethod.POST)
	public String memberLogin(Member m, HttpServletRequest req) {
		System.out.println(m);
		System.out.println(m.getGm_id());
		mDAO.login(m, req);
		mDAO.isLogined(req);
		tg.generate(req);
		sDAO.clearSearch(req);
		sDAO.get(1, req);
		req.setAttribute("contentPage", "home.jsp");
		return "index";
	}

	@RequestMapping(value = "/member.logout", method = RequestMethod.GET)
	public String memberLogout(HttpServletRequest req) {
		mDAO.logout(req);
		mDAO.isLogined(req);
		tg.generate(req);
		sDAO.clearSearch(req);
		sDAO.get(1, req);
		req.setAttribute("contentPage", "home.jsp");
		return "index";
	}

	@RequestMapping(value = "/member.email", method = RequestMethod.POST)
	@ResponseBody
	public int memberEmail(HttpServletRequest req){
		// 랜덤한 숫자문자를 합친 문자열을 전달받은 map의 email로 보낸다.
		String email = req.getParameter("gm_email");
		String randomVerificationCode = AuthNumber.generateRandomNumber(100000, 999999);
		mDAO.sendEmail(email, "", "회원가입\n\n인증번호는" + randomVerificationCode + " 입니다.",req);
	
		return Integer.parseInt(randomVerificationCode);
	}
	
	@RequestMapping(value = "/member.find", method = RequestMethod.POST)
    public String memberFind(Member m, HttpServletRequest req) {
        // passwordResetDTO를 사용하여 비밀번호 재설정 로직을 구현합니다.
		System.out.println("###########");
		System.out.println(req);
		System.out.println("###########");
		mDAO.isLogined(req);
		System.out.println("member.find");
		System.out.println(m);
		System.out.println(m.getGm_id());
		System.out.println(m.getGm_name());
		mDAO.isLogined(req);
		mDAO.find(m,req);		
		return "index";
    }

	@RequestMapping(value = "/member.find.go", method = RequestMethod.GET)
	public String memberFindGo(HttpServletRequest req) {
		mDAO.isLogined(req);
		req.setAttribute("contentPage", "member/find.jsp");
		return "index";
	}
	
	
	@RequestMapping(value="/member.kakaoLogin.go", method=RequestMethod.GET)
	public String kakaoLogin(@RequestParam(value = "code", required = false) String code,HttpServletRequest req) throws Exception {
		System.out.println("#########" + code);
		String access_Token = mDAO.getAccessToken(code);
		Member userInfo = mDAO.getUserInfo(access_Token,req);
		System.out.println("###access_Token#### : " + access_Token);
		//System.out.println("###nickname#### : " + userInfo.get("nickname"));
		//System.out.println("###email#### : " + userInfo.get("email"));
		System.out.println("###nickname#### : " + userInfo.getGm_name());
		System.out.println("###email#### : " + userInfo.getGm_email());
		//System.out.println("###gender#### : " + userInfo.getGm_gender());
		System.out.println("###photo#### : " + userInfo.getGm_photo());
		mDAO.isLogined(req);
		return "index";
    	}
	@RequestMapping(value="/member.kakaoLogout.go", method = RequestMethod.GET)
	public String logout(HttpSession session, HttpServletRequest req) {
		System.out.println("----------------------------");
		System.out.println(req.getSession());
		System.out.println(req.getAttribute("access_token"));
		System.out.println(session.getAttribute("access_token"));
		System.out.println("----------------------------");
		mDAO.kakaoLogout((String)session.getAttribute("access_token"),req);
		
		session.invalidate();
		mDAO.isLogined(req);
		tg.generate(req);
		sDAO.clearSearch(req);
		sDAO.get(1, req);
		req.setAttribute("contentPage", "home.jsp");
		return "index";
	}
}
