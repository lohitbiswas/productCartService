package com.indusnet.ums.service;

import com.indusnet.ums.model.Cart;
import com.indusnet.ums.model.CartItem;

import java.util.Optional;

public interface CartService {

    Cart createCart(Cart cart);

    Optional<Cart> getCartById(String id);

    Cart addOrUpdateItem(String cartId, CartItem item);

    Cart removeItem(String cartId, String itemId);

    double getCartTotal(String cartId);
}
