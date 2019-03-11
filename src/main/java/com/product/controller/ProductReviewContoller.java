package com.product.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductReviewContoller {
	
	@PostMapping(value="/products/{productid}/reviews")
	public void addReview(){
		
	}
	@PutMapping(value="/products/{productid}/reviews/{reviewid}")	
	public void updateReview(){
		
	}
	
	@DeleteMapping(value="/products/{productid}/reviews/{reviewid}")
	public void deleteReview()
	{
		
	}
	
	
	/*
	 * 

	  POST   /products/{id}/reviews                                  - Add new review to a product

	  PUT    /products/{id}/reviews/{reviewId}             - Update review of product

	  DELETE /products/{id}/reviews/{reviewId}          - Delete review of product
	 */

}
