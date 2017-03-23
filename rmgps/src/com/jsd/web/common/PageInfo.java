/**
 *
 */
package com.jsd.web.common;

import java.io.Serializable;

/**
 * @author Admin
 *
 */

public class PageInfo implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private int totalRowsCount;//the total record

	private int pageSize = 20;//the record of one page

	private int currentPage = 1;//the current page number

	private int nextPage;//the next page number

	private int previousPage;//the previous page number

	private int totalPages;//the total page

	private int startRow;//the start row number of record

	private int endRow;//the end row number of record

	private boolean hasNext;//does it have the next page

	private boolean hasPrevious;//does it have the previous page

	public PageInfo(int intTotal) {
		setTotalRowsCount(intTotal);
	}
	public PageInfo() {

	}
	public void setTotalRowsCount(int arg) {
		totalRowsCount = arg;
		totalPages = (totalRowsCount - 1) / pageSize + 1;
		if (currentPage > totalPages) {
			currentPage = totalPages;
		}
	}

	public int getTotalRowsCount() {
		return totalRowsCount;
	}

	public void setPageSize(int arg) {
		if (arg > 0) {
			pageSize = arg;
		} else {
			throw new RuntimeException("Page size can't be negative!");
		}
	}

	public int getPageSize() {
		return pageSize;
	}

	//it must be exe after totalRowsCount and currentPage has init
	public void pageRenew() {
		nextPage = currentPage + 1;
		previousPage = currentPage - 1;

		if (currentPage * pageSize < totalRowsCount) {
			endRow = currentPage * pageSize;
			startRow = endRow - pageSize + 1;
		} else {
			endRow = totalRowsCount;
			startRow = pageSize * (totalPages - 1) + 1;
		}

		if (nextPage > totalPages) {
			hasNext = false;
		} else {
			hasNext = true;
		}

		if (previousPage <= 0) {
			hasPrevious = false;
		} else {
			hasPrevious = true;
		}

	}

	public void setCurrentPage(int arg) {
		if (arg > 0) {
			currentPage = arg;

		} else {
			throw new RuntimeException("Current page number can't be negative!");
		}
	}

	public void setCurrentPage(String arg) {
		if (arg == null) {
			arg = "1";
		}
		int iCurrent = 0;
		try {
			iCurrent = Integer.parseInt(arg);
			if (iCurrent < 1) {
				iCurrent = 1;
			}
		} catch (Exception e) {
			iCurrent = 1;
		}

		currentPage = iCurrent;

	}

	public int getCurrentPage() {
		return currentPage;
	}

	public int getNextPage() {
		return nextPage;
	}

	public int getPreviousPage() {
		return previousPage;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public int getStartRow() {
		return startRow;
	}

	public int getEndRow() {
		return endRow;
	}

	public boolean isHasNext() {
		return hasNext;
	}

	public boolean isHasPrevious() {
		return hasPrevious;
	}


}
