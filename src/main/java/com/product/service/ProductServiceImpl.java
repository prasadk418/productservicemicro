package com.product.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.domain.Product;
import com.product.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepository repo;
	
	public List<Product> getAllProducts(){
		return repo.findAll();
	}

	@Override
	public Optional<Product> getProduct(Integer productId) {
		// TODO Auto-generated method stub
		return repo.findById(productId);
	}

	@Override
	public Product saveProduct(Product product) {
		 return repo.save(product);
		
	}

	@Override
	public void deleteProduct(Integer productId) {
		repo.deleteById(productId);
		
	}

	
	@Override
	public Product updateProduct(Product product) {
 
		return repo.save(product);
	}
}
