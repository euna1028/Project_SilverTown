package com.kwon.gbraucp2.dataroom;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kwon.gbraucp2.TokenGenerator;
import com.kwon.gbraucp2.member.MemberDAO;
import com.kwon.gbraucp2.sns.SNSDAO;

@Controller
public class DataroomController {

	@Autowired
	private DataroomDAO dDAO;

	@Autowired
	private MemberDAO mDAO;

	@Autowired
	private SNSDAO sDAO;

	@Autowired
	private TokenGenerator tg;

	@RequestMapping(value = "/dataroom.delete", method = RequestMethod.GET)
	public String dataroomDelete(DataroomFile df, HttpServletRequest req) {
		if (mDAO.isLogined(req)) {
			dDAO.delete(df, req);
			dDAO.get(req);
			req.setAttribute("contentPage", "dataroom/dataroom.jsp");
		} else {
			sDAO.get(1, req);
			req.setAttribute("contentPage", "home.jsp");
		}
		tg.generate(req);
		return "index";
	}

	@RequestMapping(value = "/dataroom.download", method = RequestMethod.GET)
	public String dataroomDownload(
			DataroomFile df, HttpServletRequest req, HttpServletResponse res) {
		if (mDAO.isLogined(req)) {
			dDAO.download(df, req, res);
			dDAO.get(req);
			req.setAttribute("contentPage", "dataroom/dataroom.jsp");
		} else {
			sDAO.clearSearch(req);
			sDAO.get(1, req);
			req.setAttribute("contentPage", "home.jsp");
		}
		tg.generate(req);
		return "index";
	}

	@RequestMapping(value = "/dataroom.go", method = RequestMethod.GET)
	public String dataroomGo(HttpServletRequest req) {
		if (mDAO.isLogined(req)) {
			dDAO.get(req);
			req.setAttribute("contentPage", "dataroom/dataroom.jsp");
		} else {
			sDAO.clearSearch(req);
			sDAO.get(1, req);
			req.setAttribute("contentPage", "home.jsp");
		}
		tg.generate(req);
		return "index";
	}

	@RequestMapping(value = "/dataroom.upload", method = RequestMethod.POST)
	public String dataroomUpload(DataroomFile df, HttpServletRequest req) {
		if (mDAO.isLogined(req)) {
			dDAO.upload(df, req);
			dDAO.get(req);
			req.setAttribute("contentPage", "dataroom/dataroom.jsp");
		} else {
			sDAO.get(1, req);
			req.setAttribute("contentPage", "home.jsp");
		}
		tg.generate(req);
		return "index";
	}
}
