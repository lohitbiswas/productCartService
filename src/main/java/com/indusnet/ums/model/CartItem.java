package com.indusnet.ums.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "cartItems")
public class CartItem {

    @Id
    private String id;
    private String cartId; // Storing cartId as a reference
    private Product product;
    private Integer quantity;

    // Additional Methods
    public double getTotalPrice() {
        return product.getPrice() * quantity;
    }
}

