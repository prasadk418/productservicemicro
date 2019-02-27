package com.product.service;

import java.util.List;

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
}
