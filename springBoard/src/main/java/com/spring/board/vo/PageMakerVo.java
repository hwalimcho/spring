package com.spring.board.vo;

//import org.springframework.web.util.UriComponents;
//import org.springframework.web.util.UriComponentsBuilder;

	public class PageMakerVo {
		
		private int totalCount;
        private int startPage;
		private int endPage;
		private boolean prev;
		private boolean next;
		private int displayPageNum;
		private PageVo pageVo;
		
		/*
		public int getTotalCount() {
			return totalCount;
		}
		public void setTotalCount(int totalCount) {
			this.totalCount = totalCount;
			calcData();
		}
		public int getStartPage() {
			return startPage;
		}
		public int getEndPage() {
			return endPage;
		}
		public boolean isPrev() {
			return prev;
		}
		public boolean isNext() {
			return next;
		}
		public int getDisplayPageNum() {
			return displayPageNum;
		}
		public PageVo getPageVo() {
			return pageVo;
		}
		public void setPageVo(PageVo pageVo) {
			this.pageVo = pageVo;
		}

		public void calcData() {
			endPage = (int) (Math.ceil(pageVo.getPage() / (double)displayPageNum)*displayPageNum);
			startPage = (endPage - displayPageNum) + 1;
			
			int tempEndPage = (int) (Math.ceil(totalCount/(double)pageVo.getPerPageNum()));
			if (endPage > tempEndPage) {
				endPage = tempEndPage ;
			}
			prev = startPage == 1 ? false : true;
			next = endPage * pageVo.getPerPageNum() >= totalCount ? false : true ; 
		}
		public String makeQuery(int page) {
			UriComponents uriComponents =
			UriComponentsBuilder.newInstance()
								//.queryParam("checkRow", pageVo.getCheckRow()) //list를 가져와야할거같은데,,,, 우선 row도 nullㅇㅣ긴 함 왤까?
								.queryParam("page", page)
								.queryParam("perPageNum", pageVo.getPerPageNum())
								.build();
			
			return uriComponents.toUriString();
		}
*/

	      public int getTotalCount() {
	            return totalCount;
	        }

	        public void setTotalCount(int totalCount) {
	            this.totalCount = totalCount;
	        }
	        

	        public int getStartPage() {
	            return startPage;
	        }

	        public void setStartPage(int startPage) {
	            this.startPage = startPage;
	        }

	        public int getEndPage() {
	            return endPage;
	        }

	        public void setEndPage(int endPage) {
	            this.endPage = endPage;
	        }

	        public boolean isPrev() {
	            return prev;
	        }

	        public void setPrev(boolean prev) {
	            this.prev = prev;
	        }

	        public boolean isNext() {
	            return next;
	        }

	        public void setNext(boolean next) {
	            this.next = next;
	        }

	        public int getDisplayPageNum() {
	            return displayPageNum;
	        }

	        public void setDisplayPageNum(int displayPageNum) {
	            this.displayPageNum = displayPageNum;
	        }

	        public PageVo getPageVo() {
	            return pageVo;
	        }

	        public void setPageVo(PageVo pageVo) {
	            this.pageVo = pageVo;
	        }
		
		public PageMakerVo(PageVo pageVo, int totalCount) {
		    
		    this.pageVo = pageVo;
		    this.totalCount = totalCount;
		    this.displayPageNum = 5;
		 
		    this.endPage = (int)(Math.ceil(pageVo.getPageNo() / (double)displayPageNum))*displayPageNum;
		    this.startPage = this.endPage - displayPageNum + 1;
		    
		    int realEnd = (int)(Math.ceil(totalCount*1.0/10));
		    
		    if(realEnd < this.endPage) {
		        this.endPage = realEnd;
		    }
		    this.prev = this.startPage > 1;
		    this.next = this.endPage < realEnd;
		}
		
		@Override
	    public String toString() {
	        return "PageVo [startPage=" + startPage + ", endPage=" + endPage + ", prev=" + prev + ", next=" + next + ", totalCount=" + totalCount + ", displayPageNum=" + displayPageNum + ", pageVo=" + pageVo + "]"; 
	    }
	}

