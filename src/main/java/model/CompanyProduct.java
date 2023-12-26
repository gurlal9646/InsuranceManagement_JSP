package model;


public class CompanyProduct {
	private int CompanyProductId;
	private String CompanyProductName, Category;
	private float Price;
	public int getCompanyProductId() {
		return CompanyProductId;
	}
	public void setCompanyProductId(int companyProductId) {
		CompanyProductId = companyProductId;
	}
	public String getCompanyProductName() {
		return CompanyProductName;
	}
	public void setCompanyProductName(String companyProductName) {
		CompanyProductName = companyProductName;
	}
	public String getCategory() {
		return Category;
	}
	public void setCategory(String category) {
		Category = category;
	}
	public float getPrice() {
		return Price;
	}
	public void setPrice(float price) {
		Price = price;
	}

	
	
}