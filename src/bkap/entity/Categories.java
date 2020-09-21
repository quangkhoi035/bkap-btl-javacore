package bkap.entity;

import java.io.Serializable;
import java.util.Scanner;

import bkap.itf.ICategories;

public class Categories implements ICategories, Serializable {
	private int catalogId;
	private String catalogName;
	private String descriptions;
	private boolean catalogStatus;
	private int parentId;

	public Categories() {
		super();
	}

	public Categories(int catalogId, String calalogName, String descriptions, boolean catalogStatus, int parentId) {
		super();
		this.catalogId = catalogId;
		this.catalogName = calalogName;
		this.descriptions = descriptions;
		this.catalogStatus = catalogStatus;
		this.parentId = parentId;
	}
	
	public int getCatalogId() {
		return catalogId;
	}

	public void setCatalogId(int catalogId) {
		this.catalogId = catalogId;
	}

	public String getCatalogName() {
		return catalogName;
	}

	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}

	public String getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}

	public boolean isCatalogStatus() {
		return catalogStatus;
	}

	public void setCatalogStatus(boolean catalogStatus) {
		this.catalogStatus = catalogStatus;
	}
   
	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	@Override
	public void inputData() {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		System.out.print("Nhap ten danh muc : ");	
		while(true) {
			this.catalogName = scan.nextLine();
			if(this.catalogName.length() >= 6 && this.catalogName.length() <= 30) {
				break;
			} else {
				System.err.println("Vui long nhap ten danh muc tu 6 -> 30 ky tu");
			}
		}
		System.out.print("Nhap mo ta danh muc : ");
		while(true) {
			this.descriptions = scan.nextLine();
			if(this.descriptions.length() == 0 || this.descriptions == null) {
				System.err.println("Ma danh muc khong duoc de trong, vui long nhap lai!");
			} else {
				break;
			}
		}
		System.out.print("Nhap trang thai danh muc : ");
		String tempCatalogStatus;
		while(true) {
			tempCatalogStatus = scan.nextLine();
			if(tempCatalogStatus.equals("true") || tempCatalogStatus.equals("false")) {
				this.catalogStatus = Boolean.parseBoolean(tempCatalogStatus);
				break;
			} else {
				System.err.println("Vui long nhap true hoac false!");;
			} 
		}
	}

	@Override
	public void displayData() {
		String catalogStatusString;
		if(this.catalogStatus) {
			catalogStatusString = "Hoat dong";
		} else {
			catalogStatusString = "Khong hoat dong";
		}
		System.out.printf("Ma danh muc : %d - Ten danh muc : %s\n", this.catalogId,this.catalogName);
		System.out.printf("Mo ta : %s\n", this.descriptions);
		System.out.printf("Danh muc cha : %d - Trang thai : %s\n" , this.parentId, catalogStatusString);
	}

}
