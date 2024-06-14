package com.goindia.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "PLAN_CATEGORY")
@Data
public class PlanCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CATEGORY_ID")
    private Long categoryId;

    @Column(name = "CATEGORY_NAME" ,length = 30)
    private String categoryName;

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
