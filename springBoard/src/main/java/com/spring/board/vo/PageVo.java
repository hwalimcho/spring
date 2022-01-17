package com.spring.board.vo;

public class PageVo {
	
	private int pageNo = 0;
	private String checkRow; //jsp에서 선택된 체크박스 codeID
	private String codeID;
	private String codeName;
	private String[] checkList;
	
	
	
	
	
	public String[] getCheckList() {
		return checkList;
	}

	public void setCheckList(String[] checkList) {
		this.checkList = checkList;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public String getCheckRow() {
		return checkRow;
	}

	public void setCheckRow(String checkRow) {
		this.checkRow = checkRow;
	}
	public String getCodeID() {
		return codeID;
	}
	public void setCodeID(String codeID) {
		this.codeID = codeID;
	}
	public String getCodeName() {
		return codeName;
	}
	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}
}
