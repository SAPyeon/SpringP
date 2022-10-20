package org.sap.model;

import lombok.Data;

@Data
public class PageVO {
	private int startPage;
	private int endPage;
	private boolean prev;
	private boolean next;
	private CriteriaVO cri;
	private int total;
	private int realEnd;
	
	public PageVO(CriteriaVO cri, int total) {
		this.cri = cri;
		this.total = total;
		this.endPage = (int)Math.ceil(cri.getPageNum()/10.0)*10;
		this.startPage = this.endPage - 9;
		this.realEnd = (int)Math.ceil((double)total/cri.getAmount());
		if(realEnd < this.endPage) {
			this.endPage = realEnd;
		}
		
		this.prev = this.startPage > 1;
		this.next = this.endPage < realEnd;
	}
	
	@Override
	public String toString() {
		return "PageVO [startPage=" + startPage + ", endPage=" + endPage + ", prev=" + prev + ", next=" + next
				+ ", cri=" + cri + ", total=" + total + ", realEnd=" + realEnd + "]";
	}
}
