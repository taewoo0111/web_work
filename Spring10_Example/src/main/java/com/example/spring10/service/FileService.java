package com.example.spring10.service;

import com.example.spring10.dto.FileDto;
import com.example.spring10.dto.FileListDto;

public interface FileService {
	public FileListDto getFiles(int pageNum, FileDto search);
	public long uploadFile(FileDto dto);
	//public FileDto getDetail(FileDto dto);
	public void deleteFile(long num);
	public FileDto getByNum(long num);
}
