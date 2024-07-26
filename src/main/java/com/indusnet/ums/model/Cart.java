package com.indusnet.ums.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "carts")
public class Cart {

    @Id
    private String id;
    private String userId; // Storing userId as a reference
    private List<CartItem> items = new ArrayList<>();

    // Additional Methods
    public void addItem(CartItem item) {
        items.add(item);
    }

    public void removeItem(CartItem item) {
        items.remove(item);
    }

    public double getTotal() {
        return items.stream()
                .mapToDouble(CartItem::getTotalPrice)
                .sum();
    }
}
