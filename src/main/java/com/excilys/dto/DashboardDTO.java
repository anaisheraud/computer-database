package com.excilys.dto;

public class DashboardDTO {

	private String orderBy;
	private String search;
	private String lister;
	private String ListComputers;
	private String countComputers;
	private String selection;
	
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public String getLister() {
		return lister;
	}
	public void setLister(String lister) {
		this.lister = lister;
	}
	public String getListComputers() {
		return ListComputers;
	}
	public void setListComputers(String listComputers) {
		ListComputers = listComputers;
	}
	public String getCountComputers() {
		return countComputers;
	}
	public void setCountComputers(String countComputers) {
		this.countComputers = countComputers;
	}
	public String getSelection() {
		return selection;
	}
	public void setSelection(String selection) {
		this.selection = selection;
	}
	
}
