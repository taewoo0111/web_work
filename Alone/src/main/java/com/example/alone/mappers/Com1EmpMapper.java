package com.example.alone.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.alone.dto.Com1EmpDto;

@Mapper
public interface Com1EmpMapper {
    // 대기 테이블에서 사원 테이블로 이동(추가)하는 메소드
    int insert(Com1EmpDto dto);
    // 사원번호로 사원의 정보를 삭제하는 메소드
    int delete(int empNo);
    // 사원번호로 사원의 정보를 가져오는 메소드
    Com1EmpDto getData(int empNo);
    // 사원의 정보를 수정하는 메소드
    int update(Com1EmpDto dto);
    // 사원의 목록을 가져오는 메소드
    List<Com1EmpDto> getList();
    // Admin 사원의 목록을 가져오는 메소드
    List<Com1EmpDto> getListAdmin();
    // Staff 사원의 목록을 가져오는 메소드
    List<Com1EmpDto> getListStaff();
    // Part 사원의 목록을 가져오는 메소드
    List<Com1EmpDto> getListPart();
    // 매장번호로 매장의 사원들만 가져오는 메소드
    List<Com1EmpDto> getListByStoreNum(int storeNum);
    // 전체 사원의 목록을 페이징 처리하는 메소드
    List<Com1EmpDto> getListPaging(Com1EmpDto dto);
    // Staff 사원의 목록을 페이징 처리하는 메소드
    List<Com1EmpDto> getListPagingStaff(Com1EmpDto dto);
    // Part 사원의 목록을 페이징 처리하는 메소드
    List<Com1EmpDto> getListPagingPart(Com1EmpDto dto);
    // 사원의 전화번호가 중복인지 확인하는 메소드
    long isDuplicateECall(String eCall);
    // 사원의 이메일이 중복인지 확인하는 메소드
    long isDuplicateEmail(String email);
    // 전체 사원의 수를 가져오는 메소드
    long getCount(Com1EmpDto dto);
}