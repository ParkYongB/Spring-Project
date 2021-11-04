package kr.spring.houseBoard.vo;

public class HMarkVO {
	private int mem_num; // 회원번호
	private int house_num; // 글번호
	private int house_recom; // 추천수(좋아요 수)
	private int scrap_num; // 스크랩수
	
	public int getMem_num() {
		return mem_num;
	}
	
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	
	public int getHouse_num() {
		return house_num;
	}
	
	public void setHouse_num(int house_num) {
		this.house_num = house_num;
	}
	
	public int getHouse_recom() {
		return house_recom;
	}
	
	public void setHouse_recom(int house_recom) {
		this.house_recom = house_recom;
	}
	
	public int getScrap_num() {
		return scrap_num;
	}
	
	public void setScrap_num(int scrap_num) {
		this.scrap_num = scrap_num;
	}

	@Override
	public String toString() {
		return "HMarkVO [mem_num=" + mem_num + ", house_num=" + house_num + ", house_recom=" + house_recom
				+ ", scrap_num=" + scrap_num + "]";
	}
	
}
