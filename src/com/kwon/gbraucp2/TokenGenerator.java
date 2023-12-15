package com.kwon.gbraucp2;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

@Service
public class TokenGenerator {
	private SimpleDateFormat sdf;

	public TokenGenerator() {
		sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
	}
	
	public void generate(HttpServletRequest req) {
		Date now = new Date();
		String token = sdf.format(now);
		req.setAttribute("token", token);
	}
	
}





