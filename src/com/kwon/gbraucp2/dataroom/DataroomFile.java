package com.kwon.gbraucp2.dataroom;

import java.math.BigDecimal;
import java.util.Date;

public class DataroomFile {
	private BigDecimal gd_no;
	private String gd_uploader;
	private String gd_title;
	private String gd_file;
	private String gd_category;
	private Date gd_date;

	public DataroomFile() {
		// TODO Auto-generated constructor stub
	}

	public DataroomFile(BigDecimal gd_no, String gd_uploader, String gd_title, String gd_file, String gd_category,
			Date gd_date) {
		super();
		this.gd_no = gd_no;
		this.gd_uploader = gd_uploader;
		this.gd_title = gd_title;
		this.gd_file = gd_file;
		this.gd_category = gd_category;
		this.gd_date = gd_date;
	}

	public BigDecimal getGd_no() {
		return gd_no;
	}

	public void setGd_no(BigDecimal gd_no) {
		this.gd_no = gd_no;
	}

	public String getGd_uploader() {
		return gd_uploader;
	}

	public void setGd_uploader(String gd_uploader) {
		this.gd_uploader = gd_uploader;
	}

	public String getGd_title() {
		return gd_title;
	}

	public void setGd_title(String gd_title) {
		this.gd_title = gd_title;
	}

	public String getGd_file() {
		return gd_file;
	}

	public void setGd_file(String gd_file) {
		this.gd_file = gd_file;
	}

	public String getGd_category() {
		return gd_category;
	}

	public void setGd_category(String gd_category) {
		this.gd_category = gd_category;
	}

	public Date getGd_date() {
		return gd_date;
	}

	public void setGd_date(Date gd_date) {
		this.gd_date = gd_date;
	}

}
