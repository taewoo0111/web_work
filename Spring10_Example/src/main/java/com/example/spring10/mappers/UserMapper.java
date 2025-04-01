package com.example.spring10.mappers;

import org.apache.ibatis.annotations.Mapper;

import com.example.spring10.dto.UserDto;

@Mapper
public interface UserMapper {
	public UserDto getDataByNum(long num);
	public UserDto getDataByUserName(String userName);
	public int insert(UserDto dto);
	public int updatePassword(UserDto dto);
	public int update(UserDto dto);
}
