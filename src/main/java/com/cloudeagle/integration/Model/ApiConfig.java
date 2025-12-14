package com.cloudeagle.integration.Model;

import com.cloudeagle.integration.Config.JsonConverter;
import jakarta.persistence.*;
import lombok.Data;
import java.util.Map;

@Entity
@Data
@Table(name = "api_config")
public class ApiConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String apiName;
    private String baseUrl;
    private String endpoint;
    private String authType;

    @Convert(converter = JsonConverter.class)
    private Map<String, String> authDetails;

    @Convert(converter = JsonConverter.class)
    private Map<String, String> fieldMapping;
}
