package com.example.alone.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.alone.dto.UsingDto;

@Mapper
public interface UsingMapper {
    // comName 으로 Using 테이블에 insert 하는 메소드
    int insertUsing(String comName);
    // comId 로 Using 테이블에서 delete 하는 메소드
    int deleteUsing(int comId);
    // Using 테이블의 목록 가져오는 메소드
    List<UsingDto> getList();
    // Using 테이블의 comId 를 목록으로 가져오는 메소드
    List<Integer> getComIdList();
    // comId 로 comName 을 가져오는 메소드
    String getComName(int comId);
}