package com.example.alone.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.alone.dto.Com1WaitDto;

@Mapper
public interface Com1WaitMapper {
    // 대기 테이블에 사원의 정보를 추가하는 메소드
    int insert(Com1WaitDto dto);
    // 사원번호로 대기 테이블에서 사원의 정보를 삭제하는 메소드
    int delete(int empNo);
    // 사원 번호로 대기 테이블에서 특정 사원의 정보를 가져오는 메소드
    Com1WaitDto getData(int empNo);
    // Admin 인 사람들의 정보만 가져오는 메소드
    List<Com1WaitDto> getListAdmin();
    // Staff 인 사람들의 정보만 가져오는 메소드
    List<Com1WaitDto> getListStaff();
    // Part 인 사람들의 정보만 가져오는 메소드
    List<Com1WaitDto> getListPart();
}

