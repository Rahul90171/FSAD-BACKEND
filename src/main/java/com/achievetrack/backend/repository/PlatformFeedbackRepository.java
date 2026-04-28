package com.achievetrack.backend.repository;

import com.achievetrack.backend.model.PlatformFeedback;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlatformFeedbackRepository extends MongoRepository<PlatformFeedback, String> {
}
