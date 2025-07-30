package com.example.demo.cart;

import org.apache.ibatis.annotations.*;

import java.util.*;

//@Mapper
public interface ProductDao {
  Optional<Product> findByPNo(int pno);
}
