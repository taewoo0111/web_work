package com.example.alone.dto;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
@Alias("Com1EmpDto")
public class Com1EmpDto {
    private int comId;
    private int storeNum;
    private int empNo;
    private String eName;
    private String role;
    private String eCall;
    private String ePwd;
    private int sal;
    private int hSal;
    private int workTime;
    private String email;
    private String contract;
    private String createdAt;
    // 페이징 처리
    private int startRowNum;
    private int endRowNum;
    // 검색조건
    private String condition;
    private String keyword;
}
