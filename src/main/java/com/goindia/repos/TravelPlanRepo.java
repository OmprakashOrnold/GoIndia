package com.goindia.repos;

import com.goindia.entities.TravelPlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TravelPlanRepo extends JpaRepository<TravelPlan,Long> {
}
