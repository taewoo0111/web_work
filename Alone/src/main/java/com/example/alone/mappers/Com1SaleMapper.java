package com.example.alone.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.alone.dto.Com1SaleDto;
import com.example.alone.dto.SaleRequestDto;

@Mapper
public interface Com1SaleMapper {
    // 일 매출 추가하는 메소드
    int insert(Com1SaleDto dto);
    // 일 매출 수정하는 메소드
    int update(Com1SaleDto dto);
    // 한 매장의 일 매출 가져오는 메소드
    int getStoreDaySale(SaleRequestDto dto);
    // 한 매장의 월 매출 가져오는 메소드
    int getStoreMonthSale(SaleRequestDto dto);
    // 한 매장의 년 매출 가져오는 메소드
    int getStoreYearSale(SaleRequestDto dto);
    // 모든 매장의 일 매출 리스트를 가져오는 메소드
    List<Com1SaleDto> getListAll();
    // 모든 매장의 년 매출 리스트를 가져오는 메소드
    List<Com1SaleDto> getListSaleByYear();
    // 모든 매장의 월 매출 리스트를 가져오는 메소드
    List<Com1SaleDto> getListSaleByMonth();
    // 특정 매장의 모든 일 매출을 리스트로 가져오는 메소드
    List<Com1SaleDto> getListSaleByStore(int storeNum);
    // 특정 매장의 모든 월 매출을 리스트로 가져오는 메소드
    List<Com1SaleDto> getListMonthSale(int storeNum);
    // 특정 매장의 모든 년 매출을 리스트로 가져오는 메소드
    List<Com1SaleDto> getListYearSale(int storeNum);
    // 특정 날짜로 모든 매장 일 매출 조회하는 메소드
    List<Com1SaleDto> getListByDate(String date);
}
