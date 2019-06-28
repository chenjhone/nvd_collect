//package com.chenjh.util.mongo;
//
//import java.io.Serializable;
//
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//
//public class SpringbootPageable implements Serializable, Pageable {
//
//	private static final long serialVersionUID = -7117046566286751843L;
//
//	PageModel page;
//
//	public PageModel getPage() {
//		return page;
//	}
//
//	public void setPage(PageModel page) {
//		this.page = page;
//	}
//
//	@Override
//	public Pageable first() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public long getOffset() {
//		// TODO Auto-generated method stub
//		return (page.getCurrentPage() - 1) * page.getPagesize();
//	}
//
//	@Override
//	public int getPageNumber() {
//		// TODO Auto-generated method stub
//		return page.getCurrentPage();
//	}
//
//	@Override
//	public int getPageSize() {
//		// TODO Auto-generated method stub
//		return page.getPagesize();
//	}
//
//	@Override
//	public boolean hasPrevious() {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public Pageable next() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Pageable previousOrFirst() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Sort getSort() {
//		// TODO Auto-generated method stub
//		return page.getSort();
//	}
//}
