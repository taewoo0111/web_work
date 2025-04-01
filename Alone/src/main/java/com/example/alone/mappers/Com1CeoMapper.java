package com.example.alone.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.alone.dto.Com1CeoDto;

@Mapper
public interface Com1CeoMapper {
    // 관리자 계정 추가하는 메소드
    int insert(Com1CeoDto dto);
    // 관리자 계정 삭제하는 메소드
    int delete(int empNo);
    // 관리자 계정 수정하는 메소드
    int update(Com1CeoDto dto);
    // 한 명의 관리자 계정의 정보를 가져오는 메소드
    Com1CeoDto getData(int empNo);
    // 관리자들의 목록을 가져오는 메소드
    List<Com1CeoDto> getList();
    // 전화번호가 중복인지 체크하는 메소드
    long isDuplicateECall(String eCall);
    // 이메일이 중복인지 체크하는 메소드
    long isDuplicateEmail(String email);
}
