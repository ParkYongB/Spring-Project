package kr.spring.notice.vo;

import java.sql.Date;

import javax.validation.constraints.NotEmpty;

public class NoticeVO {
	private int notice_num;
	@NotEmpty
	private String notice_title;
	@NotEmpty
	private String notice_content;
	private Date notice_reg_date;
	private int notice_hits;
	private int mem_num;
	
	public int getNotice_num() {
		return notice_num;
	}
	public void setNotice_num(int notice_num) {
		this.notice_num = notice_num;
	}
	public String getNotice_title() {
		return notice_title;
	}
	public void setNotice_title(String notice_title) {
		this.notice_title = notice_title;
	}
	public String getNotice_content() {
		return notice_content;
	}
	public void setNotice_content(String notice_content) {
		this.notice_content = notice_content;
	}
	public Date getNotice_reg_date() {
		return notice_reg_date;
	}
	public void setNotice_reg_date(Date notice_reg_date) {
		this.notice_reg_date = notice_reg_date;
	}
	public int getNotice_hits() {
		return notice_hits;
	}
	public void setNotice_hits(int notice_hits) {
		this.notice_hits = notice_hits;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	
	@Override
	public String toString() {
		return "NoticeVO [notice_num=" + notice_num + ", notice_title=" + notice_title + ", notice_content="
				+ notice_content + ", notice_reg_date=" + notice_reg_date + ", notice_hits=" + notice_hits
				+ ", mem_num=" + mem_num + "]";
	}
	
	
	
}
