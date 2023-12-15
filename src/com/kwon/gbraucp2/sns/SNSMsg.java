package com.kwon.gbraucp2.sns;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class SNSMsg {
	private BigDecimal gs_no;
	private String gs_writer;
	private String gs_txt;
	private Date gs_date;
	private String gs_color;
	private String gm_photo;
	private List<SNSReply> gs_reply;

	public SNSMsg() {
		// TODO Auto-generated constructor stub
	}

	public SNSMsg(BigDecimal gs_no, String gs_writer, String gs_txt, Date gs_date, String gs_color, String gm_photo,
			List<SNSReply> gs_reply) {
		super();
		this.gs_no = gs_no;
		this.gs_writer = gs_writer;
		this.gs_txt = gs_txt;
		this.gs_date = gs_date;
		this.gs_color = gs_color;
		this.gm_photo = gm_photo;
		this.gs_reply = gs_reply;
	}

	public BigDecimal getGs_no() {
		return gs_no;
	}

	public void setGs_no(BigDecimal gs_no) {
		this.gs_no = gs_no;
	}

	public String getGs_writer() {
		return gs_writer;
	}

	public void setGs_writer(String gs_writer) {
		this.gs_writer = gs_writer;
	}

	public String getGs_txt() {
		return gs_txt;
	}

	public void setGs_txt(String gs_txt) {
		this.gs_txt = gs_txt;
	}

	public Date getGs_date() {
		return gs_date;
	}

	public void setGs_date(Date gs_date) {
		this.gs_date = gs_date;
	}

	public String getGs_color() {
		return gs_color;
	}

	public void setGs_color(String gs_color) {
		this.gs_color = gs_color;
	}

	public String getGm_photo() {
		return gm_photo;
	}

	public void setGm_photo(String gm_photo) {
		this.gm_photo = gm_photo;
	}

	public List<SNSReply> getGs_reply() {
		return gs_reply;
	}

	public void setGs_reply(List<SNSReply> gs_reply) {
		this.gs_reply = gs_reply;
	}

}
