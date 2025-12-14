package com.cloudeagle.integration.Service;

import com.cloudeagle.integration.DTO.UserDto;
import com.cloudeagle.integration.Model.ApiConfig;
import com.cloudeagle.integration.Model.TempUser;
import com.cloudeagle.integration.Repository.ApiConfigRepository;
import com.cloudeagle.integration.Repository.TempUserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GenericApiService {

    private final ApiConfigRepository apiConfigRepo;
    private final TempUserRepository tempUserRepo;
    private final WebClient webClient = WebClient.create();

    public GenericApiService(ApiConfigRepository apiConfigRepo, TempUserRepository tempUserRepo) {
        this.apiConfigRepo = apiConfigRepo;
        this.tempUserRepo = tempUserRepo;
    }

    // Update token for a specific API
    public void updateToken(String apiName, String newToken) {
        ApiConfig config = apiConfigRepo.findByApiName(apiName);
        if (config == null) throw new RuntimeException("API config not found");

        Map<String, String> authDetails = config.getAuthDetails();
        if (authDetails == null) authDetails = new HashMap<>();
        authDetails.put("token", newToken);
        config.setAuthDetails(authDetails);

        apiConfigRepo.save(config);
    }

    public List<UserDto> fetchUsers(String apiName) {
        ApiConfig config = apiConfigRepo.findByApiName(apiName);
        if (config == null) throw new RuntimeException("API config not found");

        String url = config.getBaseUrl() + config.getEndpoint();

        Map response = webClient.get()
                .uri(url)
                .header("Authorization", config.getAuthType() + " " + config.getAuthDetails().get("token"))
                .retrieve()
                .bodyToMono(Map.class)
                .block();

        List<Map<String, Object>> usersRaw = (List<Map<String, Object>>) response.get("collection");

        List<UserDto> result = new ArrayList<>();

        for (Map<String, Object> userMap : usersRaw) {
            UserDto dto = new UserDto();
            dto.setId((String) userMap.get("uri"));
            dto.setName((String) userMap.get("name"));
            dto.setEmail((String) userMap.get("email"));
            dto.setSource(apiName);
            result.add(dto);

            // Save to temp_users table
            TempUser temp = tempUserRepo.findById(dto.getId()).orElse(new TempUser());
            temp.setId(dto.getId());
            temp.setName(dto.getName());
            temp.setEmail(dto.getEmail());
            temp.setSource(dto.getSource());
            tempUserRepo.save(temp);
        }

        return result;
    }
}
