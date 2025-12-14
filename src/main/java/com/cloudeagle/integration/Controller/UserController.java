package com.cloudeagle.integration.Controller;

import com.cloudeagle.integration.DTO.UserDto;
import com.cloudeagle.integration.Service.GenericApiService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final GenericApiService apiService;

    public UserController(GenericApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping("/{apiName}")
    public List<UserDto> getUsers(@PathVariable String apiName) {
        return apiService.fetchUsers(apiName);
    }
}
