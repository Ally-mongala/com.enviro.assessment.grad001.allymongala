package com.enviro.assessment.grad001.allymongala.wastemanagement.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDto{

private Long id;
private String name;
private String description;
private LocalDate dayaCreated;

}
