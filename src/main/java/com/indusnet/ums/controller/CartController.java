package com.indusnet.ums.controller;


import com.indusnet.ums.model.Cart;
import com.indusnet.ums.model.CartItem;
import com.indusnet.ums.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping
    public ResponseEntity<Cart> createCart(@RequestBody Cart cart) {
        Cart createdCart = cartService.createCart(cart);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCart);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cart> getCartById(@PathVariable String id) {
        Optional<Cart> cart = cartService.getCartById(id);
        return cart.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/items")
    public ResponseEntity<Cart> addOrUpdateItem(@PathVariable String id, @RequestBody CartItem item) {
        try {
            Cart updatedCart = cartService.addOrUpdateItem(id, item);
            return ResponseEntity.ok(updatedCart);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}/items/{itemId}")
    public ResponseEntity<Cart> removeItem(@PathVariable String id, @PathVariable String itemId) {
        try {
            Cart updatedCart = cartService.removeItem(id, itemId);
            return ResponseEntity.ok(updatedCart);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/total")
    public ResponseEntity<Double> getCartTotal(@PathVariable String id) {
        try {
            double total = cartService.getCartTotal(id);
            return ResponseEntity.ok(total);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
