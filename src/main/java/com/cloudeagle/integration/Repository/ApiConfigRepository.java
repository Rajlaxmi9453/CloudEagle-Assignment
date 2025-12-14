package com.cloudeagle.integration.Repository;


import com.cloudeagle.integration.Model.ApiConfig;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiConfigRepository extends JpaRepository<ApiConfig, Long> {
    ApiConfig findByApiName(String apiName);
}
