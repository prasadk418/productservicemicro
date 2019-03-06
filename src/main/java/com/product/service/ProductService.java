package com.product.service;

import java.util.List;
import java.util.Optional;

import com.product.domain.Product;

public interface ProductService {
	public List<Product> getAllProducts();
	public Optional<Product> getProduct(Integer productId);
	public Product saveProduct(Product product);
	public Product updateProduct(Product product);
	public void deleteProduct(Integer productId);		
}
