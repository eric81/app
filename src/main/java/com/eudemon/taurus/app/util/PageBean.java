package com.eudemon.taurus.app.util;

import java.io.Serializable;
import java.util.*;

public class PageBean implements Serializable {
	private static final long serialVersionUID = 794061903922978856L;
	
	public PageBean() {
		length = 10;
		results = new ArrayList();
		condition = new HashMap();
	}

	public PageBean(int currentPage, int length) {
		this.length = 10;
		results = new ArrayList();
		condition = new HashMap();
		setCurrentPage(currentPage);
		setLength(length);
	}

	public List getResults() {
		return results;
	}

	public void setResults(List results) {
		this.results = results;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		if (length < 1) {
			throw new IllegalArgumentException("length must greater than 0 !");
		} else {
			this.length = length;
			return;
		}
	}

	public int getTotalPages() {
		return totalPages;
	}

	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int count) {
		totalRecords = count;
		totalPages = totalRecords / length;
		if (totalRecords % length != 0)
			totalPages++;
		if (totalPages != 0) {
			if (currentPage < 1)
				currentPage = 1;
			if (currentPage > totalPages)
				currentPage = totalPages;
		} else {
			currentPage = 1;
		}
	}

	public boolean canToFirst() {
		return currentPage > 1;
	}

	public boolean canToLast() {
		return currentPage < totalPages;
	}

	public boolean canToNext() {
		return currentPage < totalPages;
	}

	public boolean canToPre() {
		return currentPage > 1;
	}

	public int getRsFirstNumber() {
		return (currentPage - 1) * length + 1;
	}

	public int getRsLastNumber() {
		return currentPage * length + 1;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		return sb.append((new StringBuilder("totalRecords=")).append(totalRecords).toString())
				.append((new StringBuilder(";totalPages=")).append(totalPages).toString())
				.append((new StringBuilder(";currentPage=")).append(currentPage).toString())
				.append((new StringBuilder(";length=")).append(length).toString()).toString();
	}

	public Map getCondition() {
		return condition;
	}

	public void setCondition(Map condition) {
		this.condition = condition;
	}

	private int currentPage;
	private int length;
	private int totalPages;
	private int totalRecords;
	private List results;
	private Map condition;
}