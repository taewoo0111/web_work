package com.example.alone.dto;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
@Alias("UsingDto")
public class UsingDto {
    private int comNum;
    private String comName;
    private int comId;
    private String createdAt;
}