package com.svistun.bookshoop.controller.odrers;

import com.svistun.bookshoop.dto.OrdersDto;
import com.svistun.bookshoop.service.cart.ShoppingCartServiceImpl;
import com.svistun.bookshoop.service.order.OrderServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/orders")
public class OrdersController {
    private final OrderServiceImpl orderService;
    private final ShoppingCartServiceImpl shoppingCartService;

    @GetMapping
    public ResponseEntity<Collection<OrdersDto>> myOrdersByStatus(Authentication authentication,
                                                       @RequestParam(defaultValue = "IN_PROGRESS")
                                                      String status) {
        return ResponseEntity.ok(orderService.myOrdersByStatus(authentication.getName(),status));
    }

    @PostMapping("/shopping-cart/add/{bookID}")
    public ResponseEntity<Void> saveOrder(Authentication authentication, @PathVariable Long bookID) {
        orderService.saveOrder(authentication,bookID);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrders(@PathVariable Long id) {
         orderService.deleteOrder(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/shopping-cart/delete/{shoppingCartID}")
    public ResponseEntity<Void> deleteShoppingCardInOrder(@PathVariable Long shoppingCartID){
        orderService.deleteShoppingCardInOrder(shoppingCartService.findByShoppingCardID(shoppingCartID));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PatchMapping("/pay/{ordersID}")
    public ResponseEntity<Void> payProduct(@PathVariable Long ordersID){
        orderService.payOrder(ordersID);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
