package com.kwon.gbraucp2.member;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.kwon.gbraucp2.sns.SNSDAO;
import com.kwon.gbraucp2.sns.SNSMapper;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@Service
public class MemberDAO {
	@Autowired
	private SNSDAO sDAO;

	@Autowired
	private SqlSession ss;
	
	private SimpleDateFormat sdf;

	public MemberDAO() {
		sdf = new SimpleDateFormat("yyyyMMdd");
	}

	public void bye(HttpServletRequest req) {
		try {
			Member m = (Member) req.getSession().getAttribute("loginMember");
			int msgCount = ss.getMapper(SNSMapper.class).getMsgCountByWriter(m);

			if (ss.getMapper(MemberMapper.class).bye(m) == 1) {
				req.setAttribute("result", "탈퇴성공");

				sDAO.setAllMsgCount(msgCount);
				
				String path = req.getSession().getServletContext().getRealPath("resources/img");
				String file = URLDecoder.decode(m.getGm_photo(), "utf-8");
				new File(path + "/" + file).delete();

				logout(req);
				isLogined(req);
			} else {
				req.setAttribute("result", "탈퇴실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("result", "탈퇴실패");
		}
	}

	public boolean isLogined(HttpServletRequest req) {
		Member m = (Member) req.getSession().getAttribute("loginMember");
		if (m != null) {
			req.setAttribute("loginPage", "menu.jsp");
			return true;
		} else {
			req.setAttribute("loginPage", "member/login.jsp");
			return false;
		}
	}
	/*public boolean kakaoisLogined(HttpServletRequest req) {
		Member m = (Member) req.getSession().getAttribute("loginMember");
		if (m != null) {
			req.setAttribute("loginPage", "menu2.jsp");
			return true;
		} else {
			req.setAttribute("loginPage", "member/login.jsp");
			return false;
		}
	}*/

//	public Member getMemASdasd(Member m) {
//		return ss.getMapper(MemberMapper.class).getMemberIDByID(m).get(0);
//	}
	
	public Members getMemberIDById(Member m) {
		return new Members(ss.getMapper(MemberMapper.class).getMemberIDByID(m));
	}
	public String getAccessToken (String authorize_code) {
		String access_Token = "";
		String refresh_Token = "";
		String reqURL = "https://kauth.kakao.com/oauth/token";

		try {
			URL url = new URL(reqURL);
            
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			// POST 요청을 위해 기본값이 false인 setDoOutput을 true로
            
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			// POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
            
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			StringBuilder sb = new StringBuilder();
			sb.append("grant_type=authorization_code");
            
			sb.append("&client_id=80c4a0c24bcf50c71fed5fe920402b1c"); //본인이 발급받은 key
			sb.append("&redirect_uri=http://localhost:8081/gbraucp2/member.kakaoLogin.go"); // 본인이 설정한 주소
            
			sb.append("&code=" + authorize_code);
			bw.write(sb.toString());
			bw.flush();
            
			// 결과 코드가 200이라면 성공
			int responseCode = conn.getResponseCode();
			System.out.println("responseCode : " + responseCode);
            
			// 요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = "";
			String result = "";
            
			while ((line = br.readLine()) != null) {
				result += line;
			}
			System.out.println("response body : " + result);
            
			// Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
			JsonParser parser = new JsonParser();
			JsonElement element = parser.parse(result);
            
			access_Token = element.getAsJsonObject().get("access_token").getAsString();
			refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();
			System.out.println("access_token : " + access_Token);
			System.out.println("refresh_token : " + refresh_Token);
            
			br.close();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return access_Token;
	}
	
	public Member getUserInfo(String access_Token,HttpServletRequest req) {

		// 요청하는 클라이언트마다 가진 정보가 다를 수 있기에 HashMap타입으로 선언
		//HashMap<String, Object> userInfo = new HashMap<String, Object>();
		Member userInfo = new Member();
		String reqURL = "https://kapi.kakao.com/v2/user/me";
		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");

			// 요청에 필요한 Header에 포함될 내용
			conn.setRequestProperty("Authorization", "Bearer " + access_Token);

			int responseCode = conn.getResponseCode();
			System.out.println("responseCode : " + responseCode);

			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			String line = "";
			String result = "";

			while ((line = br.readLine()) != null) {
				result += line;
			}
			System.out.println("response body : " + result);

			JsonParser parser = new JsonParser();
			JsonElement element = parser.parse(result);

			JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
			JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();

			String nickname = properties.getAsJsonObject().get("nickname").getAsString();
			String email = kakao_account.getAsJsonObject().get("email").getAsString();
			String photo = properties.getAsJsonObject().get("profile_image").getAsString();
			String addr = "기본값!기본값!기본값";
			String pw = "기본값";
			String id = element.getAsJsonObject().get("id").getAsString();
			//String gender = kakao_account.getAsJsonObject().get("gender").getAsString();
			//userInfo.put("nickname", nickname);
			//userInfo.put("email", email);
			userInfo.setGm_email(email);
			userInfo.setGm_name(nickname);
			userInfo.setGm_photo(photo);
			userInfo.setGm_addr(addr);
			userInfo.setGm_pw(pw);
			userInfo.setGm_id(id);
			//userInfo.setGm_gender(gender);
			if(userInfo != null) {
				req.getSession().setAttribute("loginMember", userInfo);
				req.getSession().setMaxInactiveInterval(600);
				req.getSession().setAttribute("access_token", access_Token);
				req.setAttribute("contentPage", "home.jsp");
				req.setAttribute("result", "카카오 로그인 성공");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return userInfo;
	}
	public void kakaoLogout(String access_Token, HttpServletRequest req) {
	    String reqURL = "https://kapi.kakao.com/v1/user/logout";
	    try {
	        URL url = new URL(reqURL);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("POST");
	        conn.setRequestProperty("Authorization", "Bearer " + access_Token);
	        System.out.println(access_Token);
	        int responseCode = conn.getResponseCode();
	        System.out.println("responseCode : " + responseCode);
	     
	        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        
	        String result = "";
	        String line = "";
	        
	        while ((line = br.readLine()) != null) {
	            result += line;
	        }
	        req.getSession().setAttribute("loginMember", null);
	        req.getSession().setAttribute("access_token", null);
	        req.setAttribute("result", "카카오 로그아웃 성공");
	        System.out.println(result);
	    } catch (IOException e) {
	        e.printStackTrace();
	        req.setAttribute("result", "카카오 로그아웃 에러");
	    }
	}


	public void find(Member findMember, HttpServletRequest req) {
		try {
			System.out.println(findMember.getGm_id());
			System.out.println("비번찾기 진행중");
			Member dbMember = ss.getMapper(MemberMapper.class).getMemberPWByIDNAME(findMember);
			System.out.println(dbMember);

			if(dbMember != null) {
				System.out.println("비번찾기 진행중 2");
				req.setAttribute("password", dbMember.getGm_pw());
				sendEmail(dbMember.getGm_email(), "ProjectY Senior Web", "비밀번호 찾기\n\n비밀번호는" + dbMember.getGm_pw() + " 입니다.",req);
				//req.setAttribute("contentPage", "member/index.jsp");
				req.setAttribute("contentPage", "home.jsp");
			}else {
				req.setAttribute("result", "회원 정보 불일치");
			}
		}catch(Exception e) {
			//e.printStackTrace();
			req.setAttribute("result", "비밀번호 찾기 실패(DB)");
		}
	}
	public void sendEmail(String recipientEmail, String subject, String messageText,HttpServletRequest req) {
	    // SMTP 서버 설정
	    String host = "smtp.gmail.com"; // SMTP 서버 호스트 주소
	    String username = "jeonyongtae66@gmail.com"; // SMTP 계정 사용자 이름
	    subject = "ProjectY Senior Web";

	    Properties properties = new Properties();
	    properties.put("mail.smtp.host", host);
	    properties.put("mail.smtp.auth", "true");
	    properties.put("mail.smtp.starttls.enable", "true"); // TLS를 활성화합니다.
	    properties.put("mail.smtp.port", "587"); // TLS 포트 번호 (587 또는 465를 사용할 수 있습니다.)
	    String appPassword = "jewceozkvwxrbhna";
	    // 세션 생성
	    Session session = Session.getInstance(properties, new Authenticator() {
	        @Override
	        protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(username, appPassword);
	        }
	    });
	    
	    try {
	        // 메시지 생성
	        Message message = new MimeMessage(session);
	        message.setFrom(new InternetAddress(username));
	        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
	        message.setSubject(subject);
	        message.setText(messageText);

	        // 메시지 전송
	        Transport.send(message);
	        req.setAttribute("result", "이메일로 비밀번호를 발송 했습니다.");
	        System.out.println("이메일 발송 성공!");
	    } catch (MessagingException e) {
	        e.printStackTrace();
	        System.out.println("이메일 발송 실패: " + e.getMessage());
	    }
	}

	public void join(Member m, HttpServletRequest req) {
		// 원래
		// 1) String 변수명 = req.getParameter("파라메터명");
		// 2) 형변환
		// 3) JavaBean객체로
		// SpringMVC가 1, 2, 3을 자동으로 해주는데
		// 파일업로드때는 mr.getParameter("파라메터명")으로 시작이니...
		MultipartRequest mr = null;
		String path = req.getSession().getServletContext().getRealPath("resources/img");
		try {
			mr = new MultipartRequest(req, path, 31457280, "utf-8", new DefaultFileRenamePolicy());
		} catch (Exception e) {
			req.setAttribute("result", "가입실패(프사)");
			return;
		}

		try {
			m.setGm_id(mr.getParameter("gm_id"));
			m.setGm_pw(mr.getParameter("gm_pw"));
			m.setGm_name(mr.getParameter("gm_name"));
			m.setGm_email(mr.getParameter("gm_email"));
			m.setGm_grade(1);
			String jumin1 = mr.getParameter("gm_jumin1");
			int jumin2 = Integer.parseInt(mr.getParameter("gm_jumin2"));
			if (jumin2 < 3) {
				jumin1 = "19" + jumin1;
			} else {
				jumin1 = "20" + jumin1;
			}
			if(jumin2 == 1) {
				m.setGm_gender("M");
			}else {
				m.setGm_gender("F");
			}
			
			m.setGm_birth(sdf.parse(jumin1));

			String addr1 = mr.getParameter("gm_address1");
			String addr2 = mr.getParameter("gm_address2");
			String addr3 = mr.getParameter("gm_address3");
			m.setGm_addr(addr2 + "!" + addr3 + "!" + addr1);

			String photo = mr.getFilesystemName("gm_photo");
			photo = URLEncoder.encode(photo, "utf-8");
			photo = photo.replace("+", " ");
			m.setGm_photo(photo);
			System.out.println("Gender: " + m.getGm_gender()); // 출력 추가

	        System.out.println("Grade: " + m.getGm_grade()); // 출력 추가
			if (ss.getMapper(MemberMapper.class).join(m) == 1) {
				req.setAttribute("result", "가입성공");
			}
		} catch (Exception e) {
			req.setAttribute("result", "가입실패");
			new File(path + "/" + mr.getFilesystemName("gm_photo")).delete();
		}
	}

	public void login(Member inputMember, HttpServletRequest req) {
		try {
			Member dbMember = ss.getMapper(MemberMapper.class).getMemberByID(inputMember);
			if (dbMember != null) {
				if (inputMember.getGm_pw().equals(dbMember.getGm_pw())) {
					req.getSession().setAttribute("loginMember", dbMember);
					req.getSession().setMaxInactiveInterval(600);
				} else {
					req.setAttribute("result", "로그인실패(PW)");
				}
			} else {
				req.setAttribute("result", "로그인실패(없는계정)");
			}
		} catch (Exception e) {
			req.setAttribute("result", "로그인실패(DB)");
		}
	}

	public void logout(HttpServletRequest req) {
		req.getSession().setAttribute("loginMember", null);
	}

	public void splitAddr(HttpServletRequest req) {
		Member m = (Member) req.getSession().getAttribute("loginMember");
		String addr = m.getGm_addr();
		String[] addr2 = addr.split("!");
		req.setAttribute("addr1", addr2[2]);
		req.setAttribute("addr2", addr2[0]);
		req.setAttribute("addr3", addr2[1]);
	}

	public void update(Member newMember, HttpServletRequest req) {
		MultipartRequest mr = null;
		String path = req.getSession().getServletContext().getRealPath("resources/img");
		try {
			mr = new MultipartRequest(req, path, 31457280, "utf-8", new DefaultFileRenamePolicy());
		} catch (Exception e) {
			req.setAttribute("result", "수정실패(프사)");
			return;
		}

		Member oldMember = (Member) req.getSession().getAttribute("loginMember");
		String oldFile = oldMember.getGm_photo();
		String newFile = null;
		try {
			newMember.setGm_id(oldMember.getGm_id());
			newMember.setGm_pw(mr.getParameter("gm_pw"));
			newMember.setGm_name(mr.getParameter("gm_name"));

			String addr1 = mr.getParameter("gm_address1");
			String addr2 = mr.getParameter("gm_address2");
			String addr3 = mr.getParameter("gm_address3");
			newMember.setGm_addr(addr2 + "!" + addr3 + "!" + addr1);

			newFile = mr.getFilesystemName("gm_photo");
			if (newFile == null) {
				newFile = oldFile;
			} else {
				newFile = URLEncoder.encode(newFile, "utf-8").replace("+", " ");
			}
			newMember.setGm_photo(newFile);

			if (ss.getMapper(MemberMapper.class).update(newMember) == 1) {
				req.setAttribute("result", "수정성공");
				if (!newFile.equals(oldFile)) {
					oldFile = URLDecoder.decode(oldFile, "utf-8");
					new File(path + "/" + oldFile).delete();
				}
				// newMember = ss.getMapper(MemberMapper.class).getMemberByID(newMember);
				newMember.setGm_birth(oldMember.getGm_birth());
				req.getSession().setAttribute("loginMember", newMember);
			} else {
				req.setAttribute("result", "수정실패");
				if (!newFile.equals(oldFile)) {
					newFile = URLDecoder.decode(newFile, "utf-8");
					new File(path + "/" + newFile).delete();
				}
			}
		} catch (Exception e) {
			req.setAttribute("result", "수정실패(DB)");
			if (!newFile.equals(oldFile)) {
				try {
					newFile = URLDecoder.decode(newFile, "utf-8");
					new File(path + "/" + newFile).delete();
				} catch (UnsupportedEncodingException e1) {
				}
			}
		}

	}



}
