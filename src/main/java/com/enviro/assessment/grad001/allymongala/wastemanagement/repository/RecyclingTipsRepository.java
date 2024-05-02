package com.enviro.assessment.grad001.allymongala.wastemanagement.repository;

import com.enviro.assessment.grad001.allymongala.wastemanagement.entity.RecyclingTips;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecyclingTipsRepository extends JpaRepository<RecyclingTips, Long> {
}
