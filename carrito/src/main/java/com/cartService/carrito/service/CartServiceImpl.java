package com.cartService.carrito.service;

import com.cartService.carrito.entity.Cart;
import com.cartService.carrito.entity.CartItem;
import com.cartService.carrito.repository.CartRepository;
import com.cartService.carrito.repository.CartItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date; // IMPORTANTE: Cambiamos la importación
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    @Override
    public Cart getCart(Long id) {
        return cartRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));
    }

    @Override
    public List<Cart> getCartsByUser(Long userId) {
        return cartRepository.findByUserId(userId);
    }

    @Override
    public Cart createCart(Cart cart) {
        // Ajuste para usar java.util.Date
        if (cart.getFechaCreacion() == null) {
            cart.setFechaCreacion(new Date());
        }
        // Inicializar campos por defecto que están en la base de datos
        if (cart.getEstado() == null) {
            cart.setEstado("ABIERTO");
        }
        if (cart.getTotal() == null) {
            cart.setTotal(0.0);
        }
        return cartRepository.save(cart);
    }

    @Override
    public CartItem addItem(Long cartId, CartItem item) {
        Cart cart = getCart(cartId);
        item.setCart(cart);

        // El script SQL de Oracle requiere PRECIO_UNITARIO.
        // Si no lo envías en el JSON de Postman, fallará.
        // Aquí asumiremos un valor por defecto para que no falle al inicio.
        if (item.getPrecioUnitario() == null) {
            item.setPrecioUnitario(0.0); // Valor de prueba. En un microservicio real, llamarías al servicio de Producto.
        }

        return cartItemRepository.save(item);
    }

    @Override
    public void removeItem(Long cartId, Long itemId) {
        CartItem item = cartItemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item no encontrado"));

        if (!item.getCart().getId().equals(cartId)) {
            throw new RuntimeException("El item no pertenece a ese carrito");
        }

        cartItemRepository.delete(item);
    }
}