package com.indusnet.ums.service.impl;

import com.indusnet.ums.model.Cart;
import com.indusnet.ums.model.CartItem;
import com.indusnet.ums.repository.CartRepository;
import com.indusnet.ums.repository.CartItemRepository;
import com.indusnet.ums.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    @Override
    public Cart createCart(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public Optional<Cart> getCartById(String id) {
        return cartRepository.findById(id);
    }

    @Override
    public Cart addOrUpdateItem(String cartId, CartItem item) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new IllegalArgumentException("Cart not found"));
        item.setCartId(cartId);
        cart.addItem(item);
        cartItemRepository.save(item);
        return cartRepository.save(cart);
    }

    @Override
    public Cart removeItem(String cartId, String itemId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new IllegalArgumentException("Cart not found"));
        CartItem item = cartItemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("Cart item not found"));
        cart.removeItem(item);
        cartItemRepository.delete(item);
        return cartRepository.save(cart);
    }

    @Override
    public double getCartTotal(String cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new IllegalArgumentException("Cart not found"));
        return cart.getTotal();
    }
}

