package com.project.somsea.domain;

public class Category {
	private String collection;
	private int categoryId;
	private String categoryType;

	public int getCategoryId() { return categoryId; }
	public void setCategoryId(int categoryId) { this.categoryId = categoryId; }

	public String getCategoryType() { return categoryType; }
	public void setCategoryType(String categoryType) { this.categoryType = categoryType; }


	public String toString() {
	    return getCategoryType();
	  }
}
