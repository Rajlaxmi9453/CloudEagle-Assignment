package com.cloudeagle.integration.Model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "temp_users")
public class TempUser {

    @Id
    private String id;

    private String name;
    private String email;
    private String source;
}
