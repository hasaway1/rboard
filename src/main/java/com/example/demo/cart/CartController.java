package com.example.demo.cart;

import jakarta.annotation.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.security.access.prepost.*;
import org.springframework.web.bind.annotation.*;

import java.security.*;
import java.util.*;

//@RestController
public class CartController {
//  @Autowired
//  private CartService service;
//  private List<Product> products;
//
//  @PostConstruct
//  public void init() {
//    products.add(new Product(1, "해태", "홈런볼", 5760, 3));
//    products.add(new Product(2, "롯데", "마가렛트", 2990, 5));
//    products.add(new Product(3, "크라운", "초코하임", 2590, 3));
//  }
//
//
//  @GetMapping("/products")
//  public ResponseEntity<List<Product>> listProduct() {
//    return ResponseEntity.ok(products);
//  }
//
//  @PreAuthorize("isAuthenticated()")
//  @GetMapping("/carts")
//  public ResponseEntity<?> getCart(Principal principal) {
//    return ResponseEntity.ok(service.findByUsername(principal.getName()));
//  }
//
//  @PreAuthorize("isAuthenticated()")
//  @PostMapping("/carts/{pno}")
//  public ResponseEntity<?> addToCart(@PathVariable int pno, Principal principal) {
//    return ResponseEntity.ok(service.addToCart(pno, principal.getName()));
//  }
//
//  @PreAuthorize("isAuthenticated()")
//  @PutMapping("/products/inc/{pno}")
//  public ResponseEntity<?> increaseItemQuantity(@PathVariable int pno, Principal principal) {
//    return ResponseEntity.ok(service.increaseItemQuantity(pno, principal.getName()));
//  }
//
//  @PreAuthorize("isAuthenticated()")
//  @PutMapping("/products/dec/{pno}")
//  public ResponseEntity<?> decreaseItemQuantity(@PathVariable int pno, Principal principal) {
//    return ResponseEntity.ok(service.decreaseItemQuantity(pno, principal.getName()));
//  }
}
