package com.product.controller;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.product.domain.Product;
import com.product.domain.Review;

public class ProductDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Product product;	
	private Integer reviewId;
	private Integer rating;
	
		
	public ProductDTO(Product product) {		
		this.product = product;
		/*this.reviewId = reviewId;
		this.rating = rating;*/
	}
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Integer getReviewId() {
		return reviewId;
	}
	public void setReviewId(Integer reviewId) {
		this.reviewId = reviewId;
	}
	public Integer getRating() {
		return rating;
	}
	public void setRating(Integer rating) {
		this.rating = rating;
	}
	
	
	
	
}
