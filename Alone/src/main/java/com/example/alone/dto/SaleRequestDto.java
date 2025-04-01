package com.example.alone.dto;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Alias("SaleRequestDto")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleRequestDto {
    private String salesDate;
    private int storeNum;
    private int year;
    private int month;
    //private int week;
}