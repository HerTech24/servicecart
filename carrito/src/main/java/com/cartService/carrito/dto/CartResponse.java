package com.cartService.carrito.dto;

import lombok.Data;
import java.util.List;

@Data
public class CartResponse {
    private Long userId;
    private List<CartItemResponse> items;
}
