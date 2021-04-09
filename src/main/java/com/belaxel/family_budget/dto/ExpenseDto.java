package com.belaxel.family_budget.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseDto {

    private Integer id;
    private String name;
    private LocalDateTime time;
    private Double amount;
    private String consumer;
    private String organization;
    private String category;

}
