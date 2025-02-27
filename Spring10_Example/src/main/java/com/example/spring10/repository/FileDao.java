package com.example.spring10.repository;

import java.util.List;

import com.example.spring10.dto.FileDto;

public interface FileDao {
	public List<FileDto> getList(FileDto dto);
	public int insert(FileDto dto);
	public int delete(long num);
	public int getCount(FileDto dto);
	public long getSequence();
	public FileDto getDetail(FileDto dto);
	public FileDto getData(long num);
}
