package com.cloudeagle.integration.Repository;


import com.cloudeagle.integration.Model.TempUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TempUserRepository extends JpaRepository<TempUser, String> {
}
