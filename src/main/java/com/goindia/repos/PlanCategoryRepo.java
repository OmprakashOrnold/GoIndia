package com.goindia.repos;

import com.goindia.entities.PlanCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanCategoryRepo extends JpaRepository<PlanCategory,Long> {
}
