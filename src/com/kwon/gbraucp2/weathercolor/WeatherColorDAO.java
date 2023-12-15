package com.kwon.gbraucp2.weathercolor;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.apache.ibatis.session.SqlSession;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kwon.gbraucp2.sns.SNSMsg;

@Service
public class WeatherColorDAO {

	@Autowired
	private SqlSession ss;

	public void reg(SNSMsg sm) {
		HttpsURLConnection huc = null;
		try {
			URL u = new URL(
					"https://api.openweathermap.org/data/2.5/weather?appid=baff8f3c6cbc28a4024e336599de28c4&q=seoul&units=metric&lang=kr");
			huc = (HttpsURLConnection) u.openConnection();
			InputStream is = huc.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "utf-8");

			JSONParser jp = new JSONParser();
			JSONObject weatherData = (JSONObject) jp.parse(isr);
			JSONArray weather = (JSONArray) weatherData.get("weather");
			JSONObject weather0 = (JSONObject) weather.get(0);
			JSONObject main = (JSONObject) weatherData.get("main");

			// String o = main.get("temp").toString();
			// BigDecimal bd = new BigDecimal(o);

			WeatherColor wc = new WeatherColor();
			wc.setGwc_temp(new BigDecimal(main.get("temp").toString()));
			wc.setGwc_humidity(new BigDecimal(main.get("humidity").toString()));
			wc.setGwc_description(weather0.get("description").toString());
			wc.setGwc_color(sm.getGs_color());

			ss.getMapper(WeatherColorMapper.class).reg(wc);
		} catch (Exception e) {
		}
		huc.disconnect();
	}
}
