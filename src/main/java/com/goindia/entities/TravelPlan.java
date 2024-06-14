package com.goindia.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Entity
@Table(name = "TRAVEL_PLAN")
@Data
public class TravelPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PLAN_ID")
    private Long planId;

    @Column(name = "PLAN_NAME" ,length = 30)
    private String planName;

    @Column(name = "PLAN_DESCRIPTION" ,length = 50)
    private String planDescription;

    @Column(name = "PLAN_MIN_BUDGET")
    private Double planMinBudget;

    @Column(name = "PLAN_CATEGORY_ID")
    private Long planCategoryID;

    @Column(name = "ACTIVE_SW",length = 15)
    private String activeSW;

    @Column(name = "CREATED_DATE",updatable = false)
    @CreationTimestamp
    private LocalDateTime createdDate;

    @Column(name = "UPDATED_DATE",updatable = true,insertable = false)
    @UpdateTimestamp
    private LocalDateTime updatedDate;

    @Column(name = "CREATED_BY",length = 20)
    private String createdBy;

    @Column(name = "UPDATED_BY",length = 20)
    private String updatedBy;
}
