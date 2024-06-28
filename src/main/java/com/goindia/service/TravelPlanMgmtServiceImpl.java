package com.goindia.service;


import com.goindia.config.AppConfigProperties;
import com.goindia.constants.TravelPlanConstants;
import com.goindia.entities.PlanCategory;
import com.goindia.entities.TravelPlan;
import com.goindia.repos.PlanCategoryRepo;
import com.goindia.repos.TravelPlanRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class TravelPlanMgmtServiceImpl implements TravelPlanMgmtService {

    @Autowired
    private  TravelPlanRepo travelPlanRepo;

    @Autowired
    private  PlanCategoryRepo planCategoryRepo;

    public Map<String,String> messages;

    @Autowired
    private  TravelPlanMgmtServiceImpl(AppConfigProperties properties){
        messages=properties.getMessages();
    }


    @Override
    public String registerTravelPlan(TravelPlan travelPlan) {
        TravelPlan savedTravelPlan =travelPlanRepo.save( travelPlan );
        return savedTravelPlan.getPlanId()!=null?messages.get( TravelPlanConstants.SAVE_SUCCESS )+savedTravelPlan.getPlanId():messages.get( TravelPlanConstants.SAVE_FAILURE );
    }

    @Override
    public Map<Long, String> getTravelPlanCategories() {
        List<PlanCategory> planCategoryList =planCategoryRepo.findAll();
        Map<Long,String> planCategoryMap =new HashMap<Long,String>();
        planCategoryList.forEach( planCategory ->{
                planCategoryMap.put( planCategory.getCategoryId(),planCategory.getCategoryName());
                } );
        return planCategoryMap;
    }

    @Override
        public List<TravelPlan> showTravelPlan() {
        return travelPlanRepo.findAll();
    }

    @Override
    public TravelPlan getTravelPlanById(Long planId) {
        return travelPlanRepo.findById( planId ).orElseThrow(()->new IllegalArgumentException(messages.get( TravelPlanConstants.PLAN_ID_NOT_FOUND )));
    }

    @Override
    public String updateTravelPlan(TravelPlan travelPlan) {
        Optional<TravelPlan> travelPlanOptional = travelPlanRepo.findById( travelPlan.getPlanId() );
        if(travelPlanOptional.isPresent()) {
             travelPlanRepo.save( travelPlan );
             return travelPlan.getPlanId()+messages.get( TravelPlanConstants.TRAVEL_PLAN_UPDATED );
        }
        return  travelPlan.getPlanId()+messages.get( TravelPlanConstants.TRAVEL_PLAN_NOT_FOUND );
    }

    @Override
    public String deleteTravelPlan(Long planId ) {
        Optional<TravelPlan> travelPlanOptional = travelPlanRepo.findById(planId);
        if(travelPlanOptional.isPresent()) {
            travelPlanRepo.deleteById( planId );
            return planId +messages.get( TravelPlanConstants.TRAVEL_PLAN_DELETED );
        }
        return planId +messages.get( TravelPlanConstants.TRAVEL_PLAN_NOT_FOUND );
    }

    @Override
    public String changeTravelPlan(Long planId, String status) {
        Optional<TravelPlan> travelPlanOptional = travelPlanRepo.findById(planId);
        if(travelPlanOptional.isPresent()) {
            TravelPlan travelPlan = travelPlanOptional.get();
            travelPlan.setActiveSW( status );
            travelPlanRepo.save( travelPlan );
            return planId +messages.get( TravelPlanConstants.TRAVEL_PLAN_STATUS );
        }else{
            return planId+messages.get( TravelPlanConstants.TRAVEL_PLAN_NOT_FOUND );
        }
    }
}
