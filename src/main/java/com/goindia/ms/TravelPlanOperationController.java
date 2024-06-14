package com.goindia.ms;

import com.goindia.entities.TravelPlan;
import com.goindia.service.TravelPlanMgmtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/travelplan/api")
@RequiredArgsConstructor
public class TravelPlanOperationController {

    private final TravelPlanMgmtService travelPlanMgmtService;

    @GetMapping("/categories")
    public ResponseEntity<?> showTravelPlanCategories() {
        try {
            Map<Long, String> categories = travelPlanMgmtService.getTravelPlanCategories();
            return new ResponseEntity<Map<Long, String>>( categories, HttpStatus.OK );
        } catch (Exception exception) {
            exception.printStackTrace();
            return new ResponseEntity<>( exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR );
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> saveTravelPlan(@RequestBody TravelPlan travelPlan) {
        try {
            String responseMessage = travelPlanMgmtService.registerTravelPlan( travelPlan );
            return new ResponseEntity<String>( responseMessage, HttpStatus.CREATED );
        } catch (Exception exception) {
            exception.printStackTrace();
            return new ResponseEntity<>( exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR );
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllTravelPlans() {
        try {
            List<TravelPlan> travelPlanList = travelPlanMgmtService.showTravelPlan();
            return new ResponseEntity<List<TravelPlan>>( travelPlanList, HttpStatus.OK );
        } catch (Exception exception) {
            exception.printStackTrace();
            return new ResponseEntity<>( exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR );
        }
    }

    @GetMapping("/find/{planId}")
    public ResponseEntity<?> getTravelPlanId(@PathVariable Long planId){
        try {
            TravelPlan travelPlan = travelPlanMgmtService.getTravelPlanById( planId );
            return new ResponseEntity<TravelPlan>( travelPlan, HttpStatus.OK );
        } catch (Exception exception) {
            exception.printStackTrace();
            return new ResponseEntity<>( exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR );
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateTravelPlan(@RequestBody TravelPlan travelPlan) {
        try {
            String responseMessage = travelPlanMgmtService.updateTravelPlan( travelPlan );
            return new ResponseEntity<String>( responseMessage, HttpStatus.OK );
        } catch (Exception exception) {
            exception.printStackTrace();
            return new ResponseEntity<>( exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR );
        }

    }


    @DeleteMapping("/delete/{planId}")
    public ResponseEntity<?> removeTravelPlanById(@PathVariable Long planId){
        try {
            String responseMessage =  travelPlanMgmtService.deleteTravelPlan( planId );
            return new ResponseEntity<String>( responseMessage, HttpStatus.OK );
        } catch (Exception exception) {
            exception.printStackTrace();
            return new ResponseEntity<>( exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR );
        }
    }

    @GetMapping("/status-change/{planId}/{status}")
    public ResponseEntity<?> changeStatusOfTravelPlan(@PathVariable Long planId ,@PathVariable String status){
        try {
            String responseMessage =  travelPlanMgmtService.changeTravelPlan( planId ,status );
            return new ResponseEntity<String>( responseMessage, HttpStatus.OK );
        } catch (Exception exception) {
            exception.printStackTrace();
            return new ResponseEntity<>( exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR );
        }
    }

}
