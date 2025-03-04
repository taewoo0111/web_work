package com.example.spring11.dto;

import java.text.SimpleDateFormat;
import java.util.Locale;

import com.example.spring11.entity.Gallery;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class GalleryDto {
	private long id;
	private String writer;
	private String title;
	private String createdAt;
	
	public static GalleryDto toDto(Gallery entity) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd E a hh:mm:ss", Locale.KOREA);
		String result = sdf.format(entity.getCreatedAt());
		
		return GalleryDto.builder().id(entity.getId()).writer(entity.getWriter()).title(entity.getTitle())
				.createdAt(result).build();
	}
}
