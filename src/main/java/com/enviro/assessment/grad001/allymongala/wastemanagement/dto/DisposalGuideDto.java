package com.enviro.assessment.grad001.allymongala.wastemanagement.dto;

import com.enviro.assessment.grad001.allymongala.wastemanagement.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DisposalGuideDto {
    private Long id;
    private String guide;
    private LocalDate dayCreated;
    private Category category;
}
