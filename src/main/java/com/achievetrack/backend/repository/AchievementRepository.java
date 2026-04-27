package com.achievetrack.backend.repository;

import com.achievetrack.backend.model.Achievement;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AchievementRepository extends MongoRepository<Achievement, String> {
    List<Achievement> findByStudentId(String studentId);
    List<Achievement> findByStatus(Achievement.AchievementStatus status);
}
