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
@Alias("Com1CeoDto")
public class Com1CeoDto {
    private int comId;
    private int empNo;
    private String eName;
    private String role;
    private String eCall;
    private String ePwd;
}
