package com.example.newsapp.repository;

import com.example.newsapp.entity.User;
import com.example.newsapp.entity.UserPreference;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserPreferencesRepository extends JpaRepository<UserPreference, Long> {

    List<UserPreference> findByUser(User user);

    // ✅ Add this method
    void deleteByUser(User user);
}
