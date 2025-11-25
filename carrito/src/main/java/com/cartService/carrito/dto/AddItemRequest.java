package com.cartService.carrito.dto;

import lombok.Data;

@Data
public class AddItemRequest {
    private Long productId;
    private String productName;
    private Integer quantity;
    private Double price;
}
