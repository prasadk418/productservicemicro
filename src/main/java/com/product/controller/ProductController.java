package com.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.domain.Product;
import com.product.service.ProductService;

@RestController
public class ProductController {

	@Autowired
	private ProductService productSrervice;
	
	@RequestMapping("/getAllProducts")
	public List<Product> getProduct(){
		return productSrervice.getAllProducts();
	}
}
