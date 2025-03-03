package com.example.spring11.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring11.dto.MemberDto;
import com.example.spring11.entity.Member;
import com.example.spring11.repository.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService{

	@Autowired private MemberRepository repo;
	
	@Override
	public List<MemberDto> getAll() {
//		List<Member> entityList = repo.findAll();
//		List<MemberDto> list = new ArrayList<>();
//		for(Member tmp: entityList) {
//			list.add(MemberDto.toDto(tmp));
//		}
//		List<MemberDto> list = entityList.stream().map(item -> MemberDto.toDto(item)).toList();
		
		return repo.findAll().stream().map(MemberDto::toDto).toList();
	}

	@Override
	public void saveMember(MemberDto dto) {
		repo.save(Member.toEntity(dto));
	}

	@Override
	public void deleteMember(int num) {
		repo.deleteById(num);
	}

	@Override
	public MemberDto getMember(int num) {
		Member member = repo.findById(num)
			    .orElseThrow(() -> new IllegalStateException("Member not found"));
		return MemberDto.toDto(member);
	}

	@Override
	public void updateMember(MemberDto dto) {
		if (dto.getNum() != null && repo.existsById(dto.getNum())) { 
	        repo.save(Member.toEntity(dto));
	    } else {
	        throw new IllegalArgumentException("해당 회원이 존재하지 않습니다.");
	    }
	}

}
