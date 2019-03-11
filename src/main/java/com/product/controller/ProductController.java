package com.product.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
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
import org.springframework.web.client.RestTemplate;

import com.product.domain.Product;
import com.product.domain.Review;
import com.product.exceptions.OperationFailed;
import com.product.exceptions.ProductNotFoundException;
import com.product.exceptions.ReviewNotFoundException;
import com.product.service.ProductService;
import com.product.util.LoadProperties;
import com.product.util.ProductUtil;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productSrervice;

	@Autowired
	private LoadProperties loadProperties;
	
	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/")
	public ResponseEntity<List<Product>> getProduct() {
		return new ResponseEntity<List<Product>>(productSrervice.getAllProducts(), HttpStatus.OK);
	}

	@GetMapping("/{productid}")
	public ResponseEntity<Integer> getProductById(@PathVariable("productid") Integer productId) {
		Product prod = productSrervice.getProduct(productId).orElseThrow(() -> new ProductNotFoundException("Prodict details not found in DB"));		
			return new ResponseEntity<Integer>(prod.getProductId(), HttpStatus.OK);		
	}

	@PostMapping("/")
	public ResponseEntity<Product> createProduct(@Valid @RequestBody Product productDetails) {
		Product product = productSrervice.saveProduct(productDetails);
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}

	@PutMapping("/{productid}")
	public ResponseEntity<Product> updateProduct(@PathVariable("productid") Integer productId,
			@Valid @RequestBody Product productDetails) throws Exception {
		Product product = productSrervice.getProduct(productId).orElseThrow(() -> new ProductNotFoundException("Prodict details not found in DB"));
		product.setProductName(productDetails.getProductName());
		product.setProdcutStock(productDetails.getProdcutStock());

		final Product p = productSrervice.updateProduct(product);
		return new ResponseEntity<>(p, HttpStatus.OK);
	}

	@DeleteMapping("/{productid}")
	public ResponseEntity<?> deleteProduct(@PathVariable("productid") Integer productId) {
		Product product = productSrervice.getProduct(productId).orElseThrow(() -> new ProductNotFoundException("Prodict details not found in DB"));
		
		productSrervice.deleteProduct(product.getProductId());
		
		if(productSrervice.getProduct(productId).isPresent())
			throw new OperationFailed("Product Details Not deleted from DB");
		
		return new ResponseEntity<>("Product Deleted successfully", HttpStatus.OK);
	}

	@PostMapping(value = "/{productid}/reviews")
	public ResponseEntity<?> addReview(@PathVariable("productid") Integer productId, @RequestBody Review review1) {

		review1.setProductId(productId);

		HttpEntity<Review> entity = new HttpEntity<Review>(review1);

		ResponseEntity<Integer> response = restTemplate.exchange(
				ProductUtil.buildUrl(loadProperties) + "/" + productId + "/reviews", HttpMethod.POST, entity,
				Integer.class);
		Integer reviewID = response.getBody();

		if (reviewID == null)
			throw new OperationFailed("Data not stored in database");

		return new ResponseEntity<>(reviewID, HttpStatus.CREATED);

	}

	@SuppressWarnings("unchecked")
	@PutMapping(value = "/{productid}/reviews/{reviewid}")
	public ResponseEntity<?> updateReview(@PathVariable("productid") Integer productId,
			@PathVariable("reviewid") Integer reviewId, @RequestBody Review review1) {
		
		Optional<Review> review = restTemplate.getForObject(
				ProductUtil.buildUrl(loadProperties) + "/" + productId + "/reviews/" + reviewId, Optional.class);

		Integer reviewID  = review.map(r -> {
			HttpEntity<Review> entity = new HttpEntity<Review>(review1);

			ResponseEntity<Integer> response = restTemplate.exchange(
					ProductUtil.buildUrl(loadProperties) + "/" + productId + "/reviews", HttpMethod.PUT, entity,
					Integer.class);
			
			if (response.getBody() == null)
				throw new OperationFailed("Data not stored in database");
			
			return response.getBody();
		}).orElseThrow(() -> new ReviewNotFoundException("Review details not found in DB "));
		

		return new ResponseEntity<>(reviewID, HttpStatus.CREATED);

	}

	@SuppressWarnings("unchecked")
	@DeleteMapping(value = "/{productid}/reviews/{reviewid}")
	public ResponseEntity<?> deleteReview(@PathVariable("productid") Integer productId,
			@PathVariable("reviewid") Integer reviewId) {

		Optional<Review> review = restTemplate.getForObject(
				ProductUtil.buildUrl(loadProperties) + "/" + productId + "/reviews/" + reviewId, Optional.class);

		String msg=review.map(r -> {
			restTemplate.delete(ProductUtil.buildUrl(loadProperties) + "/" + productId + "/reviews/" + reviewId);
			Optional<Review> rv= restTemplate.getForObject(ProductUtil.buildUrl(loadProperties) + "/" + productId + "/reviews/" + reviewId, Optional.class);
			if(rv.isPresent())
				throw new OperationFailed("Data not deleted from DB");
			
			return "Review Deleted successfully";

		})
		.orElseThrow(() -> new ReviewNotFoundException("Review details not found in DB "));
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}

}
