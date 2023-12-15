package com.kwon.gbraucp2.sns;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kwon.gbraucp2.GBRAUCP2Attribute;
import com.kwon.gbraucp2.member.Member;
import com.kwon.gbraucp2.weathercolor.WeatherColorDAO;

@Service
public class SNSDAO {
	private int allMsgCount;

	@Autowired
	private GBRAUCP2Attribute ga;

	@Autowired
	private SqlSession ss;

	@Autowired
	private WeatherColorDAO wcDAO;

	public void clearSearch(HttpServletRequest req) {
		req.getSession().setAttribute("search", null);
	}

	public void delete(SNSMsg sm, HttpServletRequest req) {
		try {
			if (ss.getMapper(SNSMapper.class).delete(sm) == 1) {
				req.setAttribute("result", "삭제성공");
				allMsgCount--;
			} else {
				req.setAttribute("result", "삭제실패");
			}
		} catch (Exception e) {
			req.setAttribute("result", "삭제실패");
		}
	}
	
	public void deleteReply(SNSReply sr, HttpServletRequest req) {
		try {
			if (ss.getMapper(SNSMapper.class).deleteReply(sr) == 1) {
				req.setAttribute("result", "삭제성공");
			} else {
				req.setAttribute("result", "삭제실패");
			}
		} catch (Exception e) {
			req.setAttribute("result", "삭제실패");
		}
	}

	public void get(int page, HttpServletRequest req) {
		try {
			int msgCount = allMsgCount;
			String search = (String) req.getSession().getAttribute("search");
			SNSSelector sSel = new SNSSelector(search, null, null);
			if (search == null) {
				sSel.setSearch("");
			} else {
				msgCount = ss.getMapper(SNSMapper.class).getMsgCount(sSel);
			}

			int pageCount = (int) Math.ceil(msgCount / (double) ga.getSnsMsgPerPage());
			int start = (page - 1) * ga.getSnsMsgPerPage() + 1;
			int end = page * ga.getSnsMsgPerPage();
			req.setAttribute("pageCount", pageCount);
			req.setAttribute("page", page);

			sSel.setStart(new BigDecimal(start));
			sSel.setEnd(new BigDecimal(end));

			List<SNSMsg> msgs = ss.getMapper(SNSMapper.class).get(sSel);
			for (SNSMsg snsMsg : msgs) {
				snsMsg.setGs_reply(ss.getMapper(SNSMapper.class).getReply(snsMsg));
			}
			req.setAttribute("msgs", msgs);
		} catch (Exception e) {
		}
	}

	public void search(HttpServletRequest req) {
		req.getSession().setAttribute("search", req.getParameter("search"));
	}

	public void setAllMsgCount() {
		SNSSelector sSel = new SNSSelector("", null, null);
		allMsgCount = ss.getMapper(SNSMapper.class).getMsgCount(sSel);
	}

	public void setAllMsgCount(int msgCount) {
		allMsgCount = allMsgCount - msgCount;
	}

	public void update(SNSMsg sm, HttpServletRequest req) {
		try {
			sm.setGs_txt(sm.getGs_txt().replace("\r\n", "<br>"));

			if (ss.getMapper(SNSMapper.class).update(sm) == 1) {
				req.setAttribute("result", "수정성공");
			} else {
				req.setAttribute("result", "수정실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("result", "수정실패");
		}
	}

	public void write(SNSMsg sm, HttpServletRequest req) {
		try {
			String token = req.getParameter("token");
			String lastToken = (String) req.getSession().getAttribute("successToken");
			if (lastToken != null && token.equals(lastToken)) {
				req.setAttribute("result", "글쓰기실패");
				return;
			}

			Member m = (Member) req.getSession().getAttribute("loginMember");
			sm.setGs_writer(m.getGm_id());
			sm.setGs_txt(sm.getGs_txt().replace("\r\n", "<br>"));

			if (ss.getMapper(SNSMapper.class).write(sm) == 1) {
				req.setAttribute("result", "글쓰기성공");
				req.getSession().setAttribute("successToken", token);
				wcDAO.reg(sm);
				allMsgCount++;
			}
		} catch (Exception e) {
			req.setAttribute("result", "글쓰기실패");
		}
	}

	public void writeReply(SNSReply sr, HttpServletRequest req) {
		try {
			String token = req.getParameter("token");
			String lastToken = (String) req.getSession().getAttribute("successToken");
			if (lastToken != null && token.equals(lastToken)) {
				req.setAttribute("result", "댓글쓰기실패");
				return;
			}
			Member m = (Member) req.getSession().getAttribute("loginMember");
			sr.setGsr_writer(m.getGm_id());

			if (ss.getMapper(SNSMapper.class).writeReply(sr) == 1) {
				req.setAttribute("result", "댓글쓰기성공");
				req.getSession().setAttribute("successToken", token);
			}
		} catch (Exception e) {
			req.setAttribute("result", "댓글쓰기실패");
		}
	}
}
