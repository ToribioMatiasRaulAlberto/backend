package com.product.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "quantity")
	private long quantity;
	
	@Column(name = "code")
	private String code;
	
	public Product() {
		
	}

	public Product(String name, long quantity, String code) {
		this.name = name;
		this.quantity = quantity;
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id =id;
	}
	@Override
	public String toString() {
		return "Product [item=" + id + ", name=" + name + ", quantity=" + quantity + ", code=" + code + "]";
	}
	
	

	

	
}
