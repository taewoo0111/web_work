package com.example.alone.dto;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Data
@Alias("Com1SaleDto")
public class Com1SaleDto {
    private String salesDate;
    private int storeNum;
    private int dailySales;

    private int year;
    private int month;
    //private int week;
    private int yearlySales;
    private int monthlySales;
    //private int weeklySales;

    private String sYear;
    private String sMonth;
    //private String saleWeek;
    private String sDay;
}