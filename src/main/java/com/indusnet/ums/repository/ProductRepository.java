package com.indusnet.ums.repository;

import com.indusnet.ums.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product,String> {

}
