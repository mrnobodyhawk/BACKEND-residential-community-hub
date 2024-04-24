package com.communityhub.notificationservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("USER-SERVICE")
public interface UserInterface {
	@GetMapping("/communityhub/user/getUserFullName/{userId}")
    public String getUserFullName(@PathVariable Long userId);
}
