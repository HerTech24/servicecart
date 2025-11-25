package com.cartService.carrito.controller;

import com.cartService.carrito.entity.Cart;
import com.cartService.carrito.entity.CartItem;
import com.cartService.carrito.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CartController {

    private final CartService cartService;

    // Obtener carrito por ID
    @GetMapping("/{cartId}")
    public Cart getCart(@PathVariable Long cartId) {
        return cartService.getCart(cartId);
    }

    // Obtener todos los carritos de un usuario
    @GetMapping("/user/{userId}")
    public List<Cart> getCartsByUser(@PathVariable Long userId) {
        return cartService.getCartsByUser(userId);
    }

    // Crear un carrito
    @PostMapping
    public Cart createCart(@RequestBody Cart cart) {
        return cartService.createCart(cart);
    }

    // Agregar ítem
    @PostMapping("/{cartId}/items")
    public CartItem addItem(
            @PathVariable Long cartId,
            @RequestBody CartItem item
    ) {
        return cartService.addItem(cartId, item);
    }

    // Eliminar ítem
    @DeleteMapping("/{cartId}/items/{itemId}")
    public void removeItem(
            @PathVariable Long cartId,
            @PathVariable Long itemId
    ) {
        cartService.removeItem(cartId, itemId);
    }
}
