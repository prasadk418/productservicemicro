package com.product.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.hateoas.ResourceSupport;

@Entity
public class Product implements Serializable{ //extends ResourceSupport 
	
private static final long serialVersionUID = 123456L;
@Id	
@GeneratedValue
private Integer productId;
@Column
private String productName;
@Column
private Integer prodcutStock;
@Column
private String description;
@Column
private String imgUrl;

public Product(){}

public Product(Integer productId, String productName, Integer prodcutStock, String description, String imgUrl) {
	super();
	this.productId = productId;
	this.productName = productName;
	this.prodcutStock = prodcutStock;
	this.description = description;
	this.imgUrl=imgUrl;
}
public Integer getProductId() {
	return productId;
}
public void setProductId(Integer productId) {
	this.productId = productId;
}
public String getProductName() {
	return productName;
}
public void setProductName(String productName) {
	this.productName = productName;
}

public Integer getProdcutStock() {
	return prodcutStock;
}
public void setProdcutStock(Integer prodcutStock) {
	this.prodcutStock = prodcutStock;
}

public String getDescription() {
	return description;
}

public void setDescription(String description) {
	this.description = description;
}

public String getImgUrl() {
	return imgUrl;
}

public void setImgUrl(String imgUrl) {
	this.imgUrl = imgUrl;
}

}
