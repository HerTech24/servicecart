package com.cartService.carrito.service;

import com.cartService.carrito.entity.Cart;
import com.cartService.carrito.entity.CartItem;
import java.util.List;

public interface CartService {

    Cart getCart(Long id);

    List<Cart> getCartsByUser(Long userId);

    Cart createCart(Cart cart);

    CartItem addItem(Long cartId, CartItem item);

    void removeItem(Long cartId, Long itemId);
}
