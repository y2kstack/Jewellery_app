package com.tutorialsee.cart;

import com.tutorialsee.ItemDetails;

public class CartItem {
	private ItemDetails product;
	private int quantity;
	
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public ItemDetails getProduct() {
		return product;
	}
	public void setProduct(ItemDetails product) {
		this.product = product;
	}
	
	
}
