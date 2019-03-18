package com.product.service;

import static com.product.util.ProductUtil.buildUrl;
import static com.product.util.ProductUtil.isNotPresent;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.product.domain.Review;
import com.product.exceptions.OperationFailed;
import com.product.exceptions.ReviewNotFoundException;
import com.product.util.LoadProperties;
import com.product.util.ProductUtil;

@Service
public class ProductReviewService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private LoadProperties loadProperties;

	
	public ProductReviewService() {
	}

	/**
	 * @param productId
	 * @param entity
	 * @return
	 * @throws RestClientException
	 */
	public Review saveProductReview(Integer productId, Review review1) throws RestClientException {

		HttpEntity<Review> entity = new HttpEntity<Review>(review1);

		ResponseEntity<Review> response = restTemplate.exchange(
				buildUrl(loadProperties) + "/" + productId + "/reviews", HttpMethod.POST, entity, Review.class);
		Review review = response.getBody();
		
		return review;
	}

	/**
	 * @param productId
	 * @param reviewId
	 * @param review1
	 * @return
	 * @throws RestClientException
	 * @throws ReviewNotFoundException
	 */
	public Integer updateProductReview(Integer productId, Integer reviewId, Review review1) throws RestClientException {

		
		Optional<Review> review = getProductReviewById(productId, reviewId);
		
		review1.setReviewId(review.get().getReviewId());

		HttpEntity<Review> entity = new HttpEntity<Review>(review1);

		ResponseEntity<Integer> response = restTemplate.exchange(
				buildUrl(loadProperties) + "/" + productId + "/reviews", HttpMethod.PUT, entity, Integer.class);

		if (response.getBody() == null)
			throw new OperationFailed("Data not stored in database");

		return response.getBody();
	}
	
	/**
	 * @param productId
	 * @param reviewId
	 * @return
	 * @throws RestClientException
	 * @throws ReviewNotFoundException
	 */
	public void deleteProductReview(Integer productId, Integer reviewId)
			throws RestClientException, ReviewNotFoundException {
		Optional<Review> review = getProductReviewById(productId, reviewId);		
		restTemplate.delete(ProductUtil.buildUrl(loadProperties) + "/" + productId + "/reviews/" + review.get().getReviewId());
		
	}


	/**
	 * @param productId
	 * @param reviewId
	 * @return
	 * @throws RestClientException
	 */
	@SuppressWarnings("unchecked")
	public Optional<Review> getProductReviewById(Integer productId, Integer reviewId)
			throws RestClientException, ReviewNotFoundException {
		Optional<Review> review = restTemplate
				.getForObject(buildUrl(loadProperties) + "/" + productId + "/reviews/" + reviewId, Optional.class);
		if (isNotPresent(review)) {
			throw new ReviewNotFoundException(reviewId + "- Requested review Details not Found..!");
		}
		return review;
	}
}
