package com.cloudeagle.integration.Service;

import com.cloudeagle.integration.DTO.UserDto;
import com.cloudeagle.integration.Model.ApiConfig;
import com.cloudeagle.integration.Repository.ApiConfigRepository;
import com.cloudeagle.integration.Repository.TempUserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@Service
public class GenericApiService {

    private final ApiConfigRepository apiConfigRepo;
    private final TempUserRepository tempUserRepo;
    private final WebClient webClient = WebClient.create();

    public GenericApiService(ApiConfigRepository apiConfigRepo, TempUserRepository tempUserRepo) {
        this.apiConfigRepo = apiConfigRepo;
        this.tempUserRepo = tempUserRepo;
    }

    public List<UserDto> fetchUsers(String apiName) {

        ApiConfig config = apiConfigRepo.findByApiName(apiName);

        // API call logic will come next
        return new ArrayList<>();
    }
}
