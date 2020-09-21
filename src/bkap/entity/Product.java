package bkap.entity;

import java.io.Serializable;
import java.util.Scanner;

import bkap.itf.IProduct;

public class Product implements IProduct, Serializable {
	private String productId;
	private String produtName;
	private String title;
	private float importPrice;
	private float exportPrice;
	private float profit;
	private String desciptions;
	private boolean productStatus;
	private Categories catalog;

	public Product() {
		super();
	}

	public Product(String productId, String produtName, String title, float importPrice, float exportPrice,
			float profit, String desciptions, boolean productStatus, Categories catalog) {
		super();
		this.productId = productId;
		this.produtName = produtName;
		this.title = title;
		this.importPrice = importPrice;
		this.exportPrice = exportPrice;
		this.profit = profit;
		this.desciptions = desciptions;
		this.productStatus = productStatus;
		this.catalog = catalog;
	}
	
	

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProdutName() {
		return produtName;
	}

	public void setProdutName(String produtName) {
		this.produtName = produtName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public float getImportPrice() {
		return importPrice;
	}

	public void setImportPrice(float importPrice) {
		this.importPrice = importPrice;
	}

	public float getExportPrice() {
		return exportPrice;
	}

	public void setExportPrice(float exportPrice) {
		this.exportPrice = exportPrice;
	}

	public float getProfit() {
		return profit;
	}

	public void setProfit(float profit) {
		this.profit = profit;
	}

	public String getDesciptions() {
		return desciptions;
	}

	public void setDesciptions(String desciptions) {
		this.desciptions = desciptions;
	}

	public boolean isProductStatus() {
		return productStatus;
	}

	public void setProductStatus(boolean productStatus) {
		this.productStatus = productStatus;
	}

	public Categories getCatalog() {
		return catalog;
	}

	public void setCatalog(Categories catalog) {
		this.catalog = catalog;
	}

	@Override
	public void inputData() {
		Scanner scan = new Scanner(System.in);
		System.out.print("Nhap tieu de san pham : ");
		while (true) {
			this.title = scan.nextLine();
			if (this.title.length() >= 6 && this.title.length() <= 30) {
				break;
			} else {
				System.err.println("Vui long nhap tieu de san pham tu 6-30 ky tu!");
			}
		}
		System.out.print("Nhap gia nhap san pham : ");
		while (true) {
			try {
				this.importPrice = Float.parseFloat(scan.nextLine());
				if (this.importPrice > 0) {
					break;
				} else {
					System.err.println("Vui long nhap vao so lon hon 0!");
				}
			} catch (Exception e) {
				System.err.println("Vui long nhap vao mot so thuc!");
			}
		}
		System.out.print("Nhap gia ban san pham : ");
		while (true) {
			try {
				this.exportPrice = Float.parseFloat(scan.nextLine());
				if (this.exportPrice > this.importPrice * (1 + MIN_INTEREST_RATE)) {
					break;
				} else {
					System.err.printf("Vui long nhap gia ban lon hon %f\n", this.importPrice * (1 + MIN_INTEREST_RATE));
				}

			} catch (Exception e) {
				System.err.println("Vui long nhap vao 1 so nguyen!");
			}
		}
		System.out.print("Nhap mo ta san pham : ");
		while (true) {
			this.desciptions = scan.nextLine();
			if (this.desciptions.length() == 0 || this.desciptions == null) {
				System.err.println("Mo ta san pham khong duoc de trong, vui long nhap lai!");
			} else {
				break;
			}
		}
		System.out.print("Nhap trang thai cua san pham : ");
		String tempProductStatusString;
		while (true) {
			tempProductStatusString = scan.nextLine();
			if (tempProductStatusString.equals("true") || tempProductStatusString.equals("false")) {
				this.productStatus = Boolean.parseBoolean(tempProductStatusString);
				break;
			} else {
				System.err.println("Vui long nhap true hoac false!");
			}
		}
	}

	@Override
	public void displayData() {
		String tempProductStatusString;
		if(productStatus) {
			tempProductStatusString = "Hoat dong";
		}else {
			tempProductStatusString = "Khong hoat dong";
		}
		System.out.printf("Ma san pham : %s - Ten san pham : %s - Tieu de : %s\n",this.productId,this.produtName,this.title);
		System.out.printf("Gia nhap : %f - Gia xuat %f - Loi nhuan : %f\n", this.importPrice,this.exportPrice,this.profit);
		System.out.printf("Mo ta : %s - Trang thai : %s - Danh muc san pham : %s\n", this.desciptions,tempProductStatusString,this.catalog.getCatalogName());
	}

	@Override
	public void calProfit() {
		this.profit = this.exportPrice - this.importPrice;

	}

}
