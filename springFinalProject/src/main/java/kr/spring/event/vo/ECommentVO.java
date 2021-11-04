package kr.spring.event.vo;

import kr.spring.util.DurationFromNow;

public class ECommentVO {

	private int comm_num;			//댓글 번호
	private String comm_reg_date;	//댓글 작성일;
	private String comm_mod_date;	//댓글 수정일;
	private String comm_content;	//댓글 내용
	private int event_num;			//event 번호
	private int mem_num;			//회원 번호
	private String nickname;		//닉네임
	private byte[] profile; 		//댓글 프로필 이미지
	private String profile_filename; // 프로필 사진 이름
	
	public int getComm_num() {
		return comm_num;
	}
	public void setComm_num(int comm_num) {
		this.comm_num = comm_num;
	}
	public String getComm_reg_date() {
		return comm_reg_date;
	}
	//날짜 표시 형식을 변경(예 5초전)
	public void setComm_reg_date(String comm_reg_date) {
		this.comm_reg_date = DurationFromNow.getTimeDiffLabel(comm_reg_date);
	}
	public String getComm_mod_date() {
		return comm_mod_date;
	}
	//날짜 표시 형식을 변경(예 5초전)
	public void setComm_mod_date(String comm_mod_date) {
		this.comm_mod_date = DurationFromNow.getTimeDiffLabel(comm_mod_date);
	}
	public String getComm_content() {
		return comm_content;
	}
	public void setComm_content(String comm_content) {
		this.comm_content = comm_content;
	}
	public int getEvent_num() {
		return event_num;
	}
	public void setEvent_num(int event_num) {
		this.event_num = event_num;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public byte[] getProfile() {
		return profile;
	}
	public void setProfile(byte[] profile) {
		this.profile = profile;
	}
	
	public String getProfile_filename() {
		return profile_filename;
	}
	public void setProfile_filename(String profile_filename) {
		this.profile_filename = profile_filename;
	}
	@Override
	public String toString() {
		return "ECommentVO [comm_num=" + comm_num + ", comm_reg_date=" + comm_reg_date + ", comm_mod_date="
				+ comm_mod_date + ", comm_content=" + comm_content + ", event_num=" + event_num + ", mem_num=" + mem_num
				+ ", nickname=" + nickname + ", profile_filename=" + profile_filename + "]";
	}


	
	
}