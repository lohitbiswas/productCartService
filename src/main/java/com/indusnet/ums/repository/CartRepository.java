package com.indusnet.ums.repository;

import com.indusnet.ums.model.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CartRepository extends MongoRepository<Cart,String> {
}
