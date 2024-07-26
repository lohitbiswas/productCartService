package com.indusnet.ums.repository;

import com.indusnet.ums.model.CartItem;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CartItemRepository extends MongoRepository<CartItem,String> {
}
