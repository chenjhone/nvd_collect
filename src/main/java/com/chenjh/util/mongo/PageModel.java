//package com.chenjh.util.mongo;
//
//import java.io.Serializable;
//
//import org.springframework.data.domain.Sort;
//
//public class PageModel implements Serializable{
//
//	private static final long serialVersionUID = -1845285353960771748L;
//	// 当前页
//    private Integer currentPage = 1;
//    // 当前页面条数
//    private Integer pagesize = 10;
//    // 排序条件
//    private Sort sort;
//
//	public Integer getCurrentPage() {
//		return currentPage;
//	}
//	public void setCurrentPage(Integer currentPage) {
//		this.currentPage = currentPage;
//	}
//	public Integer getPagesize() {
//		return pagesize;
//	}
//	public void setPagesize(Integer pagesize) {
//		if(pagesize!=null) {
//			this.pagesize = pagesize;
//		}
//	}
//	public Sort getSort() {
//		return sort;
//	}
//	public void setSort(Sort sort) {
//		this.sort = sort;
//	}
//}
