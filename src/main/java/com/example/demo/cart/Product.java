package com.example.demo.cart;

import lombok.*;

@Data
@AllArgsConstructor
public class Product {
  private Integer pno;
  private String vendor;
  private String name;
  private Integer price;
  private Integer stock;
}
