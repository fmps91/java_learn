package com.developer.backend.controller;

import com.developer.backend.entity.ApiResponse;
import com.developer.backend.entity.Order;
import com.developer.backend.entity.Person;
import com.developer.backend.entity.Product;
import com.developer.backend.service.OrderService;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
@Tag(name = "Order Controller", description = "Operations related to order")

public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public  ResponseEntity<ApiResponse<List<Order>>> getAllOrders() {
        try {
			List<Order> orders = orderService.findAll();
        	return ResponseEntity.status(200).body(new ApiResponse<>("success", orders , "Orders retrieved successfully"));

		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(400).body(new ApiResponse<>("error", null,e.getLocalizedMessage()));

		}
    
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Order>> getOrderById(@PathVariable Long id) {
        Optional<Order> obj = orderService.findById(id);

        return obj.map(value -> ResponseEntity.status(200).body(new ApiResponse<>("success", value, "Order retrieved successfully")))
                     .orElseGet(() -> ResponseEntity.status(404).body(new ApiResponse<>("error", null, "Order error getOrderById")));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Order>> createOrder(@RequestBody Order order) {
        
        order.setCreatedDate(null);
        Optional<Order> obj = Optional.of(orderService.save(order));

        return obj.map(value -> ResponseEntity.status(200).body(new ApiResponse<>("success", value, "Order retrieved successfully")))
                     .orElseGet(() -> ResponseEntity.status(404).body(new ApiResponse<>("error", null, "Order error save")));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Order>> updateOrder(@PathVariable Long id, @RequestBody Order orderDetails) {
        Optional<Order> order = orderService.findById(id);
        if (order.isPresent()) {
            Order updatedOrder = order.get();
            updatedOrder.setStatus(orderDetails.getStatus());
            updatedOrder.setPublicId(orderDetails.getPublicId());
            updatedOrder.setStripeSessionId(orderDetails.getStripeSessionId());
            //updatedOrder.setCreatedDate(orderDetails.getCreatedDate());
            updatedOrder.setLastModifiedDate(LocalDateTime.now());
            updatedOrder.setCustomer(orderDetails.getCustomer());
            return ResponseEntity.status(200).body(new ApiResponse<>("success", orderService.save(updatedOrder), "update successfully"));

        } else {
            return ResponseEntity.status(404).body(new ApiResponse<>("error", null, "order Not found"));

        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteOrder(@PathVariable Long id) {
        

        try {
			orderService.deleteById(id);
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(404).body(new ApiResponse<>("error", null, e.getLocalizedMessage()));
		}
		
        return ResponseEntity.status(200).body(new ApiResponse<>("success", null, "Orden deleted successfully"));
    }
}
