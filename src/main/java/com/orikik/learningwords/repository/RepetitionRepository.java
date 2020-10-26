package com.orikik.learningwords.repository;

import com.orikik.learningwords.entity.RepetitionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RepetitionRepository extends JpaRepository<RepetitionEntity, Long> {
    Optional<List<RepetitionEntity>> findByUserEntityId(Long userId);
}
