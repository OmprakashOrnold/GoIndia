package com.goindia.service;


import com.goindia.entities.PlanCategory;
import com.goindia.entities.TravelPlan;
import com.goindia.repos.PlanCategoryRepo;
import com.goindia.repos.TravelPlanRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TravelPlanMgmtServiceImpl implements TravelPlanMgmtService {

    private final TravelPlanRepo travelPlanRepo;

    private final PlanCategoryRepo planCategoryRepo;

    @Override
    public String registerTravelPlan(TravelPlan travelPlan) {
        TravelPlan savedTravelPlan =travelPlanRepo.save( travelPlan );
        return savedTravelPlan.getPlanId()!=null?"Travel plan saved successfully with planId "+savedTravelPlan.getPlanId():"Problem with saving TravelPlan";
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
        return travelPlanRepo.findById( planId ).orElseThrow(()->new IllegalArgumentException("plan id not found"));
    }

    @Override
    public String updateTravelPlan(TravelPlan travelPlan) {
        Optional<TravelPlan> travelPlanOptional = travelPlanRepo.findById( travelPlan.getPlanId() );
        if(travelPlanOptional.isPresent()) {
             travelPlanRepo.save( travelPlan );
             return travelPlan.getPlanId()+" Travel plan updated successfully";
        }
        return  travelPlan.getPlanId()+" Travel plan  not found";
    }

    @Override
    public String deleteTravelPlan(Long planId ) {
        Optional<TravelPlan> travelPlanOptional = travelPlanRepo.findById(planId);
        if(travelPlanOptional.isPresent()) {
            travelPlanRepo.deleteById( planId );
            return planId +" Travel plan deleted successfully";
        }
        return planId +" Travel plan not found";
    }

    @Override
    public String changeTravelPlan(Long planId, String status) {
        Optional<TravelPlan> travelPlanOptional = travelPlanRepo.findById(planId);
        if(travelPlanOptional.isPresent()) {
            TravelPlan travelPlan = travelPlanOptional.get();
            travelPlan.setActiveSW( status );
            travelPlanRepo.save( travelPlan );
            return planId +" Travel plan status has changed";
        }else{
            return planId+" Travel plan not found";
        }
    }
}
