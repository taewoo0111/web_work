package com.example.alone.dto;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Alias("Com1Dto")
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class Com1Dto {
    private int comNum;
    private String storeCall;
}