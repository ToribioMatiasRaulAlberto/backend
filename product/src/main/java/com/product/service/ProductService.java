package com.product.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.product.exceptions.BadResourceException;
import com.product.exceptions.ResourceNotFoundException;
import com.product.model.Product;
import com.product.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;

	public Product showByID(Long id) {
		Product product = productRepository.findById(id).orElse(null);
		if (product == null) {
			return null;
		} else
			return product;
	}

	public List<Product> showAll() {
		List<Product> products = new ArrayList<>();
		productRepository.findAll().forEach(products::add);
		return products;
	}

	public Product save(Product product) throws BadResourceException {
		if (StringUtils.hasLength(product.getName())) {
			return productRepository.save(product);
		} else {
			BadResourceException exc = new BadResourceException("Falled save");
			exc.addErrorMessage("Product is null or empty");
			throw exc;
		}
	}

	public void delete(Long id) throws ResourceNotFoundException {
		if (!productRepository.existsById(id)) {
			throw new ResourceNotFoundException("Id not found: " + id);
		} else {
			productRepository.deleteById(id);

		}
	}

	public void actualizar(Product product) throws BadResourceException, ResourceNotFoundException {
		if (StringUtils.hasLength(product.getName())) {
			productRepository.save(product);
			throw new ResourceNotFoundException("Actualizado correctamente: " + product.getId());
		} else {
			BadResourceException exc = new BadResourceException("Fallo al actualizar");
			exc.addErrorMessage("Product is null or empty");
			throw exc;
		}
	}

}
