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
/*
	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
		
	}*/
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
	
/*
	//private int amount;
    //private int rowStart;
    //private int rowEnd;

	public PageVo( ) {
	 
	    int amount = 10;
	}


    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
*/
	
	private int rowStart;
    private int rowEnd;
	private int perPageNum; 
	
	public PageVo() {
	    this.pageNo = 1;
	    this.perPageNum = 10;
	}
	
	public void setPageNo(int pageNo) {
        if (pageNo <= 0) {
            this.pageNo = 1;
            return;
        }
        this.pageNo = pageNo;
    }
    
	 public void setPerPageNum(int perPageNum) {
	     if(perPageNum <= 0 || perPageNum > 100) {
	         this.perPageNum = 10;
	         return;
	     }
	     this.perPageNum = perPageNum;
	 }
	 public int getPerPageNum() {
	      return this.perPageNum;
	 }
	 
	 public int getPageNo() {
	        return pageNo;
	    }
	    
    public int getRowStart() {
        rowStart = ((pageNo - 1) * perPageNum) +1;
        return rowStart;
    }
    public int getRowEnd() {
        rowEnd = rowStart + perPageNum -1;
        return rowEnd;
    }
    
  
	    
    public String toString() {
        return "PageVo [pageNo=" + pageNo + ",perPageNum=" + perPageNum + ",checkList=" + checkList + "]";
        
    }
    
	
	/*
	public void setPage(int page) {
		if(page <= 0) {
			this.page = 1; 
			return;
		}
		this.page = page;
	}
	public int getPage() {
		return page;
	}
	
	public void setPerPageNum(int perPageNum) {
		if(perPageNum <= 0 || perPageNum > 100) {
			this.perPageNum = 10;
			return;
		}
		this.perPageNum = perPageNum;
	}
	public int getPerPageNum() {
		return this.perPageNum;
	}
	public int getRowStart() {
		rowStart = ((page - 1) * perPageNum) +1;
		return rowStart;
	}
	public int getRowEnd() {
		rowEnd = rowStart + perPageNum -1;
		return rowEnd;
	}
	*/
	
}
