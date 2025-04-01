package com.example.alone.dto;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Alias("Com1WaitDto")
public class Com1WaitDto {
    private int comId;
    private int storeNum;
    private int empNo;
    private String eName;
    private String role;
    private String eCall;
    private String ePwd;
    private String email;
    private String createdAt;
}
