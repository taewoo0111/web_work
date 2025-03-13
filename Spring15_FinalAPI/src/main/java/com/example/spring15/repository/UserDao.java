package com.example.spring15.repository;
 
 import com.example.spring15.dto.UserDto;
 
 public interface UserDao {
 	public UserDto getData(long num);
 	public UserDto getData(String userName);
 	public int insert(UserDto dto);
 	public int updatePassword(UserDto dto);
 	public int update(UserDto dto);
 }