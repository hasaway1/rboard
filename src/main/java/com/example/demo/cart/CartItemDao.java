package com.example.demo.cart;

import org.apache.ibatis.annotations.*;

import java.util.*;

//@Mapper
public interface CartItemDao {
  List<CartItem> findByUsername(String username);

  Optional<CartItem> findByUsernameAndProductNo(String loginId, int pno);

  int increaseQuantity(int pno, String loginId);

  void save(CartItem cartItem);
}
