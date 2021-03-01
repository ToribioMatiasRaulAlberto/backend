package com.product.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.product.exceptions.BadResourceException;
import com.product.exceptions.ResourceAlreadyExistsException;
import com.product.exceptions.ResourceNotFoundException;
import com.product.model.Product;
import com.product.service.ProductService;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class ProductController {
	@Autowired
	private ProductService productService;

	@GetMapping(value = "/product", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Product>> findAll(@RequestParam(required = false) String name) {
		return ResponseEntity.ok(productService.showAll());

	}
	@GetMapping(value = "/product/{Id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> mostrarCategoria(@PathVariable long Id) {
        try {
        	Product product = productService.showByID(Id);
            return ResponseEntity.ok(product);  // 
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // 
        }
    }
	@PostMapping(path = "/product", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Product> saveProduct( @RequestBody Product product) 
            throws URISyntaxException {
        try {
        	Product newProduct = productService.save(product);
            return ResponseEntity.created(new URI("/api/showproduct/" + newProduct.getId()))
                    .body(product);
        } catch (ResourceAlreadyExistsException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (BadResourceException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
	
	@DeleteMapping(value = "/product/{Id}")
    @ResponseBody
    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<Object> deleteProduct(@PathVariable long Id) {
        try {
        	productService.delete(Id);
            return ResponseEntity.ok().build();
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
	
	@PutMapping(path = "/product/{Id}")
	public ResponseEntity<Product> updateProduct( @PathVariable long Id,@RequestBody Product product) {
		try {
			product.setId(Id);
			productService.actualizar(product);
			return ResponseEntity.ok().build();
		} catch (ResourceNotFoundException ex) {
			return ResponseEntity.notFound().build();
		} catch (BadResourceException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
 

}
