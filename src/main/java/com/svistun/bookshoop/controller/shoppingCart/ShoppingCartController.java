package com.svistun.bookshoop.controller.shoppingCart;

import com.svistun.bookshoop.entity.ShoppingCart;
import com.svistun.bookshoop.service.shoppingCart.ShoppingCartServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/shopping-cart")
public class ShoppingCartController {
    private final ShoppingCartServiceImpl shoppingCartService;
    @PostMapping("/add/{bookID}")
    private ResponseEntity<ShoppingCart> addToCart(Authentication authentication,
                                                   @PathVariable Long bookID){
        shoppingCartService.addToCart(bookID,authentication);
        return ResponseEntity.ok().build();
    }

}
