package com.goindia.service;

import com.goindia.entities.TravelPlan;

import java.util.List;
import java.util.Map;

public interface TravelPlanMgmtService {

    String registerTravelPlan(TravelPlan travelPlan);

    Map<Long, String> getTravelPlanCategories();

    List<TravelPlan> showTravelPlan();

    TravelPlan getTravelPlanById(Long planId);

    String updateTravelPlan(TravelPlan travelPlan);

    String deleteTravelPlan(Long planId);

    String changeTravelPlan(Long planId,String status);




}
