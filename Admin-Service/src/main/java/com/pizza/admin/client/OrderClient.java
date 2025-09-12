package com.pizza.admin.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "order-service", url = "http://localhost:8083/orders")
public interface OrderClient {

    @PutMapping("/status/{id}")
    void updateStatus(@PathVariable("id") Long orderId,
                      @RequestParam("status") String status,
                      @RequestParam(value = "message", required = false) String message);
}
