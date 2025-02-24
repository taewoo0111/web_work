package com.example.spring10.service;

import java.io.File;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.spring10.dto.UserDto;
import com.example.spring10.exception.PasswordException;
import com.example.spring10.repository.UserDao;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired private UserDao dao;
	@Autowired private PasswordEncoder encoder;
	@Value("${file.location}") private String fileLocation;
	
	@Override
	public UserDto getByNum(long num) {
		return dao.getData(num);
	}

	@Override
	public UserDto getByUserName(String userName) {
		return dao.getData(userName);
	}

	@Override
	public void createUser(UserDto dto) {
		// 저장할 때 비밀번호를 암호화 한 후에 저장되도록 한다.
		String encodedPwd = encoder.encode(dto.getPassword());
		dto.setPassword(encodedPwd);
		
		int rowCount = dao.insert(dto);
		if(rowCount == 0) {
			throw new RuntimeException("회원정보 저장 실패!");
		}
	}

	@Override
	public void updateUserInfo(UserDto dto) {
		MultipartFile image = dto.getProfileFile();
		if(!image.isEmpty()) {
			String orgFileName = image.getOriginalFilename();
			String saveFileName = UUID.randomUUID().toString() + orgFileName;
			String filePath = fileLocation + File.separator + saveFileName;
			try {
				File saveFile = new File(filePath);
				image.transferTo(saveFile);
			}catch(Exception e) {
				e.printStackTrace();
			}
			dto.setProfileImage(saveFileName);
		}
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		dto.setUserName(userName);
		dao.update(dto);
	}

	@Override
	public void changePassword(UserDto dto) {
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		String encodedPwd = dao.getData(userName).getPassword();
		boolean isValid = BCrypt.checkpw(dto.getPassword(), encodedPwd);
		if(!isValid) {
			throw new PasswordException("기존 비밀번호가 일치하지 않습니다!");
		}
		dto.setNewPassword(encoder.encode(dto.getNewPassword()));
		dto.setUserName(userName);
		dao.updatePassword(dto);
	}
}
