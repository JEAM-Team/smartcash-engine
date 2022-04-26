package com.smartcash.engine.repository;

import com.smartcash.engine.models.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
