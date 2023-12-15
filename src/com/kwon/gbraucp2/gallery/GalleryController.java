package com.kwon.gbraucp2.gallery;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kwon.gbraucp2.TokenGenerator;
import com.kwon.gbraucp2.member.MemberDAO;
import com.kwon.gbraucp2.sns.SNSDAO;

@Controller
public class GalleryController {

	@Autowired
	private GalleryDAO gDAO;

	@Autowired
	private MemberDAO mDAO;

	@Autowired
	private SNSDAO sDAO;

	@Autowired
	private TokenGenerator tg;

	@RequestMapping(value = "/gallery.delete", method = RequestMethod.GET)
	public String galleryDelete(GalleryImage gi, HttpServletRequest req) {
		if (mDAO.isLogined(req)) {
			gDAO.delete(gi, req);
			gDAO.get(req);
			req.setAttribute("contentPage", "gallery/gallery.jsp");
		} else {
			sDAO.get(1, req);
			req.setAttribute("contentPage", "home.jsp");
		}
		tg.generate(req);
		return "index";
	}

	@RequestMapping(value = "/gallery.go", method = RequestMethod.GET)
	public String galleryGo(HttpServletRequest req) {
		if (mDAO.isLogined(req)) {
			gDAO.get(req);
			req.setAttribute("contentPage", "gallery/gallery.jsp");
		} else {
			sDAO.get(1, req);
			req.setAttribute("contentPage", "home.jsp");
		}
		tg.generate(req);
		return "index";
	}

	@RequestMapping(value = "/gallery.upload", method = RequestMethod.POST)
	public String galleryUpload(GalleryImage gi, HttpServletRequest req) {
		if (mDAO.isLogined(req)) {
			gDAO.upload(gi, req);
			gDAO.get(req);
			req.setAttribute("contentPage", "gallery/gallery.jsp");
		} else {
			sDAO.get(1, req);
			req.setAttribute("contentPage", "home.jsp");
		}
		tg.generate(req);
		return "index";
	}

}
