package com.enviro.assessment.grad001.allymongala.wastemanagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecyclingTips {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String tip;
    private LocalDate dayCreated;
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

}
