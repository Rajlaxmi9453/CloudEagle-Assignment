package com.cloudeagle.integration.Controller;

import com.cloudeagle.integration.DTO.UserDto;
import com.cloudeagle.integration.Service.GenericApiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    @PostMapping("/{apiName}/update-token")
    public ResponseEntity<String> updateToken(
            @PathVariable String apiName,
            @RequestBody Map<String, String> requestBody) {

        String newToken = requestBody.get("token");
        if (newToken == null || newToken.isEmpty()) {
            return ResponseEntity.badRequest().body("Token is missing");
        }

        apiService.updateToken(apiName, newToken);
        return ResponseEntity.ok("Token updated successfully");
    }

}
