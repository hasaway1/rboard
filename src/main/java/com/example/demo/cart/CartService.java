package com.example.demo.cart;

import com.example.demo.exception.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

//@Service
public class CartService {
//  @Autowired
//  private CartItemDao cartItemDao;
//  @Autowired
//  private ProductDao productDao;
//
//  public List<CartItem> findByUsername(String loginId) {
//    return cartItemDao.findByUsername(loginId);
//  }
//
//  public List<CartItem> addToCart(int pno, String loginId) {
//    Product product = null;
//    // 1. 추가하려는 상품 정보를 읽어온다(없으면 예외 발생)
//    product = productDao.findByPNo(pno).orElseThrow(()->new EntityNotFoundException("상품을 찾을 수 없습니다"));
//    // 2. 사용자의 장바구니에 지금 추가하려는 상품이 담겨있는 지 확인
//    CartItem cartItem =  cartItemDao.findByUsernameAndProductNo(loginId, pno).orElse(null);
//    if(cartItem==null) {
//      cartItemDao.save(new CartItem(null, ));
//    // 3. 재고 확인
//    if(cartItem.getQuantity()>=product.getStock())
//      throw new JobFailException(product.getStock() + "개까지 구입할 수 있습니다");
//      cartItemDao.increaseQuantity(pno, loginId);
//    } catch(ProductNotFoundException e) {
//      return "상품 정보를 찾을 수 없습니다";
//    } catch(NoSuchElementException e) {
//      cartDao.save(new Cart(loginId, productNo, 1, product.getPrice(), product.getPrice()));
//      return "장바구니에 1개 담았습니다";
//    }
//  }
//
//  public Cart increaseItemQuantity(int pno, String loginId) {
//    return null;
//  }
//
//  public Cart decreaseItemQuantity(int pno, String loginId) {
//    return null;
//  }
}
