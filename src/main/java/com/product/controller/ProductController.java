package com.product.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.domain.Product;
import com.product.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productSrervice;
	
	@GetMapping("/getall")
	public ResponseEntity<List<Product>> getProduct(){
		return new ResponseEntity<List<Product>>(productSrervice.getAllProducts(), HttpStatus.OK);
		
	}
	
	@GetMapping("/get/{productid}")
	public ResponseEntity<Product> getProductById(@PathVariable("productid") Integer productId){
		Optional<Product> prod=productSrervice.getProduct(productId);
		if(prod.isPresent())			
			return new ResponseEntity<Product>(prod.get(), HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);		
	}
	
	@PutMapping("/create")
	public ResponseEntity<Product> createProduct(@Valid @RequestBody Product productDetails){
		Product product=productSrervice.saveProduct(productDetails);
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}
	
	@PostMapping("update/{productid}")
	public ResponseEntity<Product> updateProduct(@PathVariable("productid") Integer productId, @Valid @RequestBody Product productDetails) throws Exception{		
		Product product=productSrervice.getProduct(productId).orElseThrow(() -> new Exception());
		product.setProductName(productDetails.getProductName());
		product.setProdcutStock(productDetails.getProdcutStock());
		
		final Product p=productSrervice.updateProduct(product);
		return new ResponseEntity<>(p, HttpStatus.OK); 
	}
	
	@DeleteMapping("delete/{productid}")
	public void deleteProduct(@PathVariable("productid") Integer productId){
		productSrervice.deleteProduct(productId);
	}
}
