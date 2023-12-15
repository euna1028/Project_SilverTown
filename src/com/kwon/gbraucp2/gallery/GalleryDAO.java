package com.kwon.gbraucp2.gallery;

import java.io.File;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kwon.gbraucp2.member.Member;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@Service
public class GalleryDAO {

	@Autowired
	private SqlSession ss;

	public void delete(GalleryImage gi, HttpServletRequest req) {
		try {
			String file = ss.getMapper(GalleryMapper.class).getFile(gi);
			if (ss.getMapper(GalleryMapper.class).delete(gi) == 1) {
				req.setAttribute("result", "삭제성공");
				String path = req.getSession().getServletContext().getRealPath("resources/img");
				file = URLDecoder.decode(file, "utf-8");
				new File(path + "/" + file).delete();
			}
		} catch (Exception e) {
			req.setAttribute("result", "삭제실패");
		}
	}

	public void get(HttpServletRequest req) {
		req.setAttribute("images", ss.getMapper(GalleryMapper.class).get());
	}

	public void upload(GalleryImage gi, HttpServletRequest req) {
		MultipartRequest mr = null;
		String path = req.getSession().getServletContext().getRealPath("resources/img");
		try {
			mr = new MultipartRequest(req, path, 31457280, "utf-8", new DefaultFileRenamePolicy());
		} catch (Exception e) {
			req.setAttribute("result", "업로드실패");
			return;
		}

		try {
			String token = mr.getParameter("token");
			String lastToken = (String) req.getSession().getAttribute("successToken");
			if (lastToken != null && token.equals(lastToken)) {
				req.setAttribute("result", "업로드실패");
				return;
			}
			Member m = (Member) req.getSession().getAttribute("loginMember");
			gi.setGg_uploader(m.getGm_id());
			gi.setGg_title(mr.getParameter("gg_title"));
			String gg_file = URLEncoder.encode(mr.getFilesystemName("gg_file"), "utf-8").replace("+", " ");
			gi.setGg_file(gg_file);

			if (ss.getMapper(GalleryMapper.class).upload(gi) == 1) {
				req.setAttribute("result", "업로드성공");
				req.getSession().setAttribute("successToken", token);
			}
		} catch (Exception e) {
			req.setAttribute("result", "업로드실패");
			new File(path + "/" + mr.getFilesystemName("gg_file")).delete();
		}
	}
}
