package com.kwon.gbraucp2.weathercolor;

import java.math.BigDecimal;

public class WeatherColor {
	private BigDecimal gwc_temp;
	private BigDecimal gwc_humidity;
	private String gwc_description;
	private String gwc_color;

	public WeatherColor() {
	}

	public WeatherColor(BigDecimal gwc_temp, BigDecimal gwc_humidity, String gwc_description, String gwc_color) {
		super();
		this.gwc_temp = gwc_temp;
		this.gwc_humidity = gwc_humidity;
		this.gwc_description = gwc_description;
		this.gwc_color = gwc_color;
	}

	public BigDecimal getGwc_temp() {
		return gwc_temp;
	}

	public void setGwc_temp(BigDecimal gwc_temp) {
		this.gwc_temp = gwc_temp;
	}

	public BigDecimal getGwc_humidity() {
		return gwc_humidity;
	}

	public void setGwc_humidity(BigDecimal gwc_humidity) {
		this.gwc_humidity = gwc_humidity;
	}

	public String getGwc_description() {
		return gwc_description;
	}

	public void setGwc_description(String gwc_description) {
		this.gwc_description = gwc_description;
	}

	public String getGwc_color() {
		return gwc_color;
	}

	public void setGwc_color(String gwc_color) {
		this.gwc_color = gwc_color;
	}

}
