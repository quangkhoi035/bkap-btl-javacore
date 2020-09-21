package bkap.run;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import bkap.entity.Categories;
import bkap.entity.Product;

public class ShopManagement {
	static Scanner scan = new Scanner(System.in);
	static List<Categories> listCatalog = new ArrayList<>();
	static List<Product> listProduct = new ArrayList<>();
	public static final String fileCatalogName = "Categories.txt";
	public static final String fileProductName = "Products.txt";

	public static void main(String[] args) {
		readCatalogFromFile();
		readProductFromFile();
		;
		while (true) {
			System.out.println("**********MENU**********");
			System.out.println("1. Quan ly danh muc");
			System.out.println("2. Quan ly san pham");
			System.out.println("3. Thoat");
			System.out.print("Nhap lua chon cua ban : ");
			int choiceParentMenu = -1;
			try {
				choiceParentMenu = Integer.parseInt(scan.nextLine());
			} catch (Exception e) {
				System.err.println("Vui long nhap vao 1 so nguyen!");
			}
			switch (choiceParentMenu) {
			case 1:
				int choiceCatalogManagement = 0;
				while (choiceCatalogManagement != 5) {
					System.out.println("**********QUAN LY DANH MUC**********");
					System.out.println("1. Danh sach danh muc");
					System.out.println("2. Them danh muc");
					System.out.println("3. Xoa danh muc");
					System.out.println("4. Tim kiem danh muc");
					System.out.println("5. Quay lai");
					System.out.print("Nhap lua chon cua ban : ");
					try {
						choiceCatalogManagement = Integer.parseInt(scan.nextLine());
					} catch (Exception e) {
						System.err.println("Vui long nhap vao 1 so nguyen!");
					}
					switch (choiceCatalogManagement) {
					case 1:
						int choiceCatalogList = 0;
						while (choiceCatalogList != 3) {
							System.out.println("**********DANH SACH DANH MUC**********");
							System.out.println("1. Danh sach cay danh muc");
							System.out.println("2. Thong tin chi tiet danh muc");
							System.out.println("3. Quay lai");
							System.out.print("Nhap lua chon cua ban : ");
							try {
								choiceCatalogList = Integer.parseInt(scan.nextLine());
							} catch (Exception e) {
								System.err.println("Vui long nhap vao 1 so nguyen!");
							}
							switch (choiceCatalogList) {
							case 1:
								showCatalogTree();
								break;
							case 2:
								System.out.print("Nhap ten danh muc can tim kiem : ");
								String str = scan.nextLine();
								searchCatalogByName(str);
								break;
							default:
								if (choiceCatalogList == 3) {
									System.out.println("Quay lai menu truoc thanh cong!");
								} else if (choiceCatalogList < 1 || choiceCatalogList > 3) {
									System.err.println("Vui long nhap tu 1 -> 3!");
								}
							}
						}
						break;
					case 2:
						addCatalog(scan);
						break;
					case 3:
						deleteCatalogById(scan);
						break;
					case 4:
						System.out.print("Nhap ten danh muc can tim kiem : ");
						String str = scan.nextLine();
						searchCatalogByName(str);
						break;
					default:
						if (choiceCatalogManagement == 5) {
							System.err.println("Quay lai menu truoc thanh cong!");
						} else if (choiceCatalogManagement < 1 || choiceCatalogManagement > 5) {
							System.err.println("Vui long nhap tu 1 -> 5!");
						}
					}
				}
				break;
			case 2:
				int choiceProductManagement = 0;
				while (choiceProductManagement != 7) {
					System.out.println("**********QUAN LY SAN PHAM**********");
					System.out.println("1. Them san pham moi");
					System.out.println("2. Tinh loi nhuan san pham");
					System.out.println("3. Hien thi thong tin san pham");
					System.out.println("4. Sap xep san pham");
					System.out.println("5. Cap nhat thong tin san pham");
					System.out.println("6. Cap nhat trang thai san pham ");
					System.out.println("7. Quay lai");
					System.out.print("Nhap lua chon cua ban : ");
					try {
						choiceProductManagement = Integer.parseInt(scan.nextLine());
					} catch (Exception e) {
						System.err.println("Vui long nhap vao 1 so nguyen!");
					}
					switch (choiceProductManagement) {
					case 1:
						addProduct(scan);
						break;
					case 2:
						for (Product product : listProduct) {
							product.calProfit();
						}
						System.out.println("Da tinh xong loi nhuan!");
						break;
					case 3:
						int choiceProductInformation = 0;
						while (choiceProductInformation != 3) {
							System.out.println("**********THONG TIN SAN PHAM**********");
							System.out.println("1. Hien thi san pham theo danh muc");
							System.out.println("2. Hien thi chi tiet san pham");
							System.out.println("3. Quay lai");
							System.out.println("Nhap lua chon cua ban : ");
							try {
								choiceProductInformation = Integer.parseInt(scan.nextLine());
							} catch (Exception e) {
								System.err.println("Vui long nhap vao 1 so nguyen!");
							}
							switch (choiceProductInformation) {
							case 1:
								displayProductByCatalog();
								break;
							case 2:
								searchProductByName(scan);
								break;
							default:
								if (choiceProductInformation == 3) {
									System.out.println("Tro lai menu truoc thanh cong!");
								} else if (choiceProductInformation <= 0 && choiceProductInformation > 3) {
									System.err.println("Vui long nhap tu 1-3!");
								}
							}
						}
						break;
					case 4:
						int sortProductManagement = 0;
						while (sortProductManagement != 3) {
							System.out.println("*********SAP XEP SAN PHAM**********");
							System.out.println("1. Sap xep san pham theo gia xuat tang dan");
							System.out.println("2. Sap xep san pham theo loi nhuan giam dan");
							System.out.println("3. Quay lai");
							System.out.print("Nhap lua chon cua ban :");
							try {
								sortProductManagement = Integer.parseInt(scan.nextLine());
							} catch (Exception e) {
								System.err.println("Vui long nhap vao 1 so nguyen!");
							}
							switch (sortProductManagement) {
							case 1:
								sortProductByExportPrice();
								System.out.println("Da sap xep san pham theo gia xuat tang dan xong!");
								break;
							case 2:
								sortProductByProfit();
								System.out.println("Da sap xep san pham theo loi nhuan giam dan xong!");
								break;
							default:
								if (sortProductManagement == 3) {
									System.out.println("Quay lai menu truoc thanh cong!");
								} else if (sortProductManagement <= 0 || sortProductManagement > 3) {
									System.err.println("Vui long nhap tu 1-3!");
								}
							}
						}
						break;
					case 5:
						updateProductInfoById(scan);
						break;
					case 6:
						updateProductStatusById(scan);
						break;
					default:
						if (choiceProductManagement == 7) {
							System.err.println("Tro lai menu truoc thanh cong!");
						} else if (choiceProductManagement <= 0 || choiceProductManagement > 7) {
							System.err.println("Vui long nhap tu 1-7 !");
						}
					}
				}
				break;
			case 3:
				writeCatalogToFile();
				writeProductToFile();
				System.out.println("Ghi du lieu thong tin danh muc va san pham vao file thanh cong!");
				System.err.println("Thoat chuong trinh thanh cong!");
				System.exit(0);
			default:

			}
		}
	}

	// ***********************PHUONG THUC QUAN LY DANH MUC *****
	public static void showCatalogTree() {
		int n1 = 1;
		for (Categories categories1 : listCatalog) {
			if (categories1.getParentId() == 0) {
				System.out.println(n1 + ". " + categories1.getCatalogName());
				int n2 = 1;
				for (Categories categories2 : listCatalog) {
					if (categories2.getParentId() == categories1.getCatalogId()) {
						System.out.printf("\t%d.%d%s\n", n1, n2, categories2.getCatalogName());
						int n3 = 1;
						for (Categories categories3 : listCatalog) {
							if (categories3.getParentId() == categories2.getCatalogId()) {
								System.out.printf("\t\t%d.%d.%d%s\n", n1, n2, n3, categories3.getCatalogName());
								n3++;
							}
						}
						n2++;
					}
				}
				n1++;
			}
		}
	}

	public static void addCatalog(Scanner scan) {
		System.out.print("Nhap so luong danh muc can them : ");
		int n = -1;
		while (true) {
			try {
				n = Integer.parseInt(scan.nextLine());
				break;
			} catch (Exception e) {
				System.err.println("Vui long nhap vao 1 so nguyen!");
			}
		}
		for (int i = 0; i < n; i++) {
			Categories tempCatalog = new Categories();
			int tempCatalogId = -1;
			int tempCatalogParentId = -1;
			while (true) {
				System.out.print("Nhap Id danh muc :");
				while (true) {
					try {
						tempCatalogId = Integer.parseInt(scan.nextLine());
						if (tempCatalogId > 0) {
							break;
						} else {
							System.err.println("Vui long nhap so lon hon 0!");
						}
					} catch (Exception e) {
						System.err.println("Vui long nhap vao 1 so nguyen!");
					}
				}
				if (checkIdCatalogExist(tempCatalogId)) {
					System.err.println("Id danh muc da ton tai, vui long nhap lai!");
				} else {
					break;
				}
			}
			while (true) {
				boolean checkTonTaiParentId = false;
				System.out.print("Nhap Id cua danh muc cha : ");
				while (true) {
					try {
						tempCatalogParentId = Integer.parseInt(scan.nextLine());
						break;
					} catch (Exception e) {
						System.err.println("Vui long nhap vao 1 so nguyen!");
					}
				}
				if (tempCatalogParentId == 0) {
					checkTonTaiParentId = true;
				} else {
					for (Categories categories1 : listCatalog) {
						if (categories1.getParentId() == 0) {
							if (categories1.getCatalogId() == tempCatalogParentId) {
								checkTonTaiParentId = true;
								break;
							}
							for (Categories categories2 : listCatalog) {
								if (categories2.getParentId() == categories1.getCatalogId()) {
									if (categories2.getCatalogId() == tempCatalogParentId) {
										checkTonTaiParentId = true;
										break;
									}
								}
							}
						}
					}
				}

				if (checkTonTaiParentId) {
					break;
				} else {
					System.err.println("Id cua danh muc cha khong ton tai hoac vuot qua 3 cap , vui long nhap lai!");
				}
			}
			tempCatalog.setCatalogId(tempCatalogId);
			tempCatalog.setParentId(tempCatalogParentId);
			tempCatalog.inputData();
			listCatalog.add(tempCatalog);
		}
	}

	public static void deleteCatalogById(Scanner scan) {
		int tempId = -1;
		while (true) {
			System.out.print("Nhap Id danh muc can xoa : ");
			while (true) {
				try {
					tempId = Integer.parseInt(scan.nextLine());
					break;
				} catch (Exception e) {
					System.err.println("Vui long nhap vao 1 so nguyen!");
				}
			}
			if (checkIdCatalogExist(tempId)) {
				for (int i = 0; i < listCatalog.size(); i++) {
					if (listCatalog.get(i).getCatalogId() == tempId) {
						listCatalog.remove(i);
					}
				}
				for (int j = 0; j < listCatalog.size(); j++) {
					if (listCatalog.get(j).getParentId() == tempId) {
						for (int k = 0; k < listCatalog.size(); k++) {
							if (listCatalog.get(k).getParentId() == listCatalog.get(j).getCatalogId()) {
								listCatalog.remove(k);
							}
						}
						listCatalog.remove(j);
					}
				}
				break;
			} else {
				System.err.println("Id danh muc khong ton tai, vui long nhap lai!");
			}
		}

	}

	public static boolean checkIdCatalogExist(int inputId) {
		for (Categories categories : listCatalog) {
			if (categories.getCatalogId() == inputId) {
				return true;
			}
		}
		return false;
	}

	public static void searchCatalogByName(String inputName) {
		for (Categories categories : listCatalog) {
			if (categories.getCatalogName().startsWith(inputName)) {
				categories.displayData();
			}
		}
	}

	public static void inCatalogBacCuoi() {
		for (Categories categories1 : listCatalog) {
			boolean checkParentIdExist = false;
			for (Categories categories2 : listCatalog) {
				if (categories1.getCatalogId() == categories2.getParentId()) {
					checkParentIdExist = true;
					break;
				}
			}
			if (checkParentIdExist == false) {
				System.out.printf("[Ten danh muc : %s, ID : %d]\n", categories1.getCatalogName(),
						categories1.getCatalogId());
			}
		}
	}

	// *****************************PHUONG THUC QUAN LY SAN PHAM**********
	public static void addProduct(Scanner scan) {
		System.out.print("Nhap so san pham can them :");
		int n = 0;
		while (true) {
			try {
				n = Integer.parseInt(scan.nextLine());
				break;
			} catch (Exception e) {
				System.err.println("Vui long nhap 1 so nguyen!");
			}
		}
		for (int i = 0; i < n; i++) {
			Product tempProduct = new Product();
			updateProductInfo(tempProduct);
			listProduct.add(tempProduct);
		}
	}

	public static boolean checkProductNameExist(String inputProductName) {
		for (Product product : listProduct) {
			if (product.getProdutName().equals(inputProductName)) {
				return true;
			}
		}
		return false;
	}

	public static boolean checkProductIdExist(String inputProductId) {
		for (Product product : listProduct) {
			if (product.getProductId().equals(inputProductId)) {
				return true;
			}
		}
		return false;
	}

	public static void searchProductByName(Scanner scan) {
		System.out.print("Nhap ten san pham : ");
		String tempProductName = scan.nextLine();
		for (Product product : listProduct) {
			if (product.getProdutName().startsWith(tempProductName)) {
				product.displayData();
			}
		}
	}

	public static void displayProductByCatalog() {
		for (Categories categories1 : listCatalog) {
			boolean checkParentIdExist = false;
			for (Categories categories2 : listCatalog) {
				if (categories1.getCatalogId() == categories2.getParentId()) {
					checkParentIdExist = true;
					break;
				}
			}
			if (checkParentIdExist == false) {
				System.out.printf("[Ten danh muc : %s, ID : %d] :\n", categories1.getCatalogName(),
						categories1.getCatalogId());
				for (Product product : listProduct) {
					if (product.getCatalog().getCatalogId() == categories1.getCatalogId()) {
						product.displayData();
					}
				}
			}
		}
	}

	public static void sortProductByExportPrice() {
		for (int i = 0; i < listProduct.size() - 1; i++) {
			for (int j = i + 1; j < listProduct.size(); j++) {
				if (listProduct.get(i).getExportPrice() > listProduct.get(j).getExportPrice()) {
					Product tempProduct = listProduct.get(i);
					listProduct.set(i, listProduct.get(j));
					listProduct.set(j, tempProduct);
				}
			}
		}
	}

	public static void sortProductByProfit() {
		for (int i = 0; i < listProduct.size() - 1; i++) {
			for (int j = i + 1; j < listProduct.size(); j++) {
				if (listProduct.get(i).getProfit() < listProduct.get(j).getProfit()) {
					Product tempProduct = listProduct.get(i);
					listProduct.set(i, listProduct.get(j));
					listProduct.set(j, tempProduct);
				}
			}
		}
	}

	public static void updateProductInfo(Product tempProduct) {
		Scanner scan = new Scanner(System.in);
		String tempProductId;
		String tempProductName;
		while (true) {
			System.out.print("Nhap Id san pham : ");
			tempProductId = scan.nextLine();
			if (checkProductIdExist(tempProductId)) {
				System.err.println("Id san pham da ton tai, vui long nhap lai!");
			} else {
				if (tempProductId.length() == 4) {
					if (tempProductId.startsWith("C")) {
						break;
					} else {
						System.err.println("Id san pham phai bat dau bang ky tu C, vui long nhap lai!");
					}
				} else {
					System.err.println("Id san pham phai bao gom 4 ki tu, vui long nhap lai!");
				}
			}
		}
		while (true) {
			System.out.print("Nhap ten san pham : ");
			tempProductName = scan.nextLine();
			if (checkProductNameExist(tempProductName)) {
				System.err.println("Ten san pham da ton tai, vui long nhap lai!");
			} else {
				if (tempProductName.length() >= 6 && tempProductName.length() <= 30) {
					break;
				} else {
					System.err.println("Ten san pham phai co do dai 6-30 ky tu, vui long nhap lai!");
				}
			}
		}
		int tempId = -1;
		while (true) {
			boolean checkIdBacCuoi = true;
			System.out.println("Nhap Id danh muc cua san pham (thuoc danh sach sau day) ");
			inCatalogBacCuoi();
			while (true) {
				try {
					tempId = Integer.parseInt(scan.nextLine());
					break;
				} catch (Exception e) {
					System.err.println("Vui long nhap vao 1 so nguyen");
				}
			}
			if (checkIdCatalogExist(tempId)) {
				for (Categories categories : listCatalog) {
					if (categories.getParentId() == tempId) {
						checkIdBacCuoi = false;
						break;
					}
				}
				if (checkIdBacCuoi) {
					break;
				} else {
					System.err.println("Id khong thoa man, vui long nhap Id thuoc danh sach!");
				}
			} else {
				System.err.println("Id vua nhap khong ton tai, vui long nhap lai!");
			}
		}
		for (Categories categories : listCatalog) {
			if (categories.getCatalogId() == tempId) {
				tempProduct.setCatalog(categories);
			}
		}
		tempProduct.setProductId(tempProductId);
		tempProduct.setProdutName(tempProductName);
		tempProduct.inputData();
	}

	public static void updateProductInfoById(Scanner scan) {
		System.out.print("Nhap Id san pham can cap nhat thong tin : ");
		String tempProductId = scan.nextLine();
		if (checkProductIdExist(tempProductId)) {
			for (Product product : listProduct) {
				if (product.getProductId().equals(tempProductId)) {
					product.setProductId("");
					updateProductInfo(product);
				}
			}
		} else {
			System.err.println("Id san pham khong ton tai!");
		}
	}

	public static void updateProductStatusById(Scanner scan) {
		System.out.print("Nhap Id san pham can cap nhat trang thai : ");
		while (true) {
			String tempProductId = scan.nextLine();
			if (checkProductIdExist(tempProductId)) {
				for (Product product : listProduct) {
					if (product.getProductId().equals(tempProductId)) {
						if (product.isProductStatus()) {
							product.setProductStatus(false);
							System.out.println("Da doi trang thai tu hoat dong sang khong hoat dong!");
						} else {
							product.setProductStatus(true);
							System.out.println("Da doi trang thai tu khong hoat dong sang hoat dong!");
						}
					}
				}
				break;
			} else {
				System.err.println("Id san pham khong ton tai, vui long nhap lai!");
			}
		}
	}
// *********PHUONG THUC DOC GHI DOI TUONG**************

	public static void writeCatalogToFile() {
		File file = null;
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		file = new File(fileCatalogName);
		try {
			fos = new FileOutputStream(file);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(listCatalog);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (oos != null) {
				try {
					oos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void writeProductToFile() {
		File file = null;
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		file = new File(fileProductName);
		try {
			fos = new FileOutputStream(file);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(listProduct);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (oos != null) {
				try {
					oos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void readProductFromFile() {
		File file = new File(fileProductName);
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		if (file.exists()) {
			try {
				fis = new FileInputStream(file);
				ois = new ObjectInputStream(fis);
				listProduct = (List<Product>) ois.readObject();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} finally {
				if (fis != null) {
					try {
						fis.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (ois != null) {
					try {
						ois.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}

	}

	public static void readCatalogFromFile() {
		File file = new File(fileCatalogName);
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		if (file.exists()) {
			try {
				fis = new FileInputStream(file);
				ois = new ObjectInputStream(fis);
				listCatalog = (List<Categories>) ois.readObject();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} finally {
				if (fis != null) {
					try {
						fis.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (ois != null) {
					try {
						ois.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}

	}
}
