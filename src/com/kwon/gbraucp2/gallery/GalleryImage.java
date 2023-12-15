package com.kwon.gbraucp2.gallery;

import java.math.BigDecimal;
import java.util.Date;

public class GalleryImage {
	private BigDecimal gg_no;
	private String gg_uploader;
	private String gg_title;
	private String gg_file;
	private Date gg_date;

	public GalleryImage() {
		// TODO Auto-generated constructor stub
	}

	public GalleryImage(BigDecimal gg_no, String gg_uploader, String gg_title, String gg_file, Date gg_date) {
		super();
		this.gg_no = gg_no;
		this.gg_uploader = gg_uploader;
		this.gg_title = gg_title;
		this.gg_file = gg_file;
		this.gg_date = gg_date;
	}

	public BigDecimal getGg_no() {
		return gg_no;
	}

	public void setGg_no(BigDecimal gg_no) {
		this.gg_no = gg_no;
	}

	public String getGg_uploader() {
		return gg_uploader;
	}

	public void setGg_uploader(String gg_uploader) {
		this.gg_uploader = gg_uploader;
	}

	public String getGg_title() {
		return gg_title;
	}

	public void setGg_title(String gg_title) {
		this.gg_title = gg_title;
	}

	public String getGg_file() {
		return gg_file;
	}

	public void setGg_file(String gg_file) {
		this.gg_file = gg_file;
	}

	public Date getGg_date() {
		return gg_date;
	}

	public void setGg_date(Date gg_date) {
		this.gg_date = gg_date;
	}

}
