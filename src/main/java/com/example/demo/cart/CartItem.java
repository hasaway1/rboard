package com.example.demo.cart;

import lombok.*;

@Data
@AllArgsConstructor
public class CartItem {
  private String username;
  private Integer productId;
  private Integer quantity;
  private Integer cartPrice;
}
