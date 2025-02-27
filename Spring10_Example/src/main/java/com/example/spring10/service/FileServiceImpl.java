package com.example.spring10.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.spring10.dto.FileDto;
import com.example.spring10.dto.FileListDto;
import com.example.spring10.repository.FileDao;

@Service
public class FileServiceImpl implements FileService{

	final int PAGE_ROW_COUNT = 5;
	final int PAGE_DISPLAY_COUNT = 5;
	
	@Autowired private FileDao fileDao;
	
	@Override
	public FileListDto getFiles(int pageNum, FileDto search) {
		int startRowNum = 1 + (pageNum - 1) * PAGE_ROW_COUNT;
		int endRowNum = pageNum * PAGE_ROW_COUNT;
		int startPageNum = 1 + ((pageNum - 1) / PAGE_DISPLAY_COUNT) * PAGE_DISPLAY_COUNT;
		int endPageNum = startPageNum + PAGE_DISPLAY_COUNT - 1;
		int totalRow = fileDao.getCount(search);
		int totalPageCount = (int) Math.ceil(totalRow / (double) PAGE_ROW_COUNT);
		if (endPageNum > totalPageCount) {
			endPageNum = totalPageCount; // 보정해 준다.
		}
		search.setStartRowNum(startRowNum);
		search.setEndRowNum(endRowNum);
		List<FileDto> list = fileDao.getList(search);
		String findQuery = "";
		if(search.getKeyword() != null) {
			findQuery = "&keyword=" + search.getKeyword() + "&condition=" + search.getCondition();
		}
		FileListDto dto = FileListDto.builder()
				.list(list).startPageNum(startPageNum).endPageNum(endPageNum)
				.totalPageCount(totalPageCount).pageNum(pageNum)
				.totalRow(totalRow).findQuery(findQuery).condition(search.getCondition())
				.keyword(search.getKeyword()).build();
		return dto;
	}

	@Override
	public long uploadFile(FileDto dto) {
		String uploader = SecurityContextHolder.getContext().getAuthentication().getName();
		dto.setUploader(uploader);
		long num = fileDao.getSequence();
		dto.setNum(num);
		fileDao.insert(dto);
		return num;
	}

	@Override
	public FileDto getDetail(FileDto dto) {
		return fileDao.getDetail(dto);
	}

	@Override
	public void deleteFile(long num) {
		fileDao.delete(num);
	}

	@Override
	public FileDto getByNum(long num) {
		return fileDao.getData(num);
	}

}
