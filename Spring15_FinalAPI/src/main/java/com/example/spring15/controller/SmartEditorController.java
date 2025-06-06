package com.example.spring15.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;
/*
 * 	이 컨트롤러가 정상동작 하기 위해서는 
 * 
 *  1. SmartEditor 폴더를 static 폴더에 붙여 넣기 하고 

    2. /upload/{imageName}  요청에 대해서 이미지를 출력해주는 ImageController 가 있어야하고 
 * 
 *  3. SmartEditor/photo_uploader/popup/attach_photo.js  에 있는 코드를
 *  
 *  아래와 같이 수정해야 한다. 
 *  
 *  333번째 라인
 *  
 *      function html5Upload() {	
    	var tempFile,
    		sUploadURL;
    	
    	//sUploadURL= 'file_uploader_html5.jsp'; 	//upload URL
    	//jsp 페이지에 요청하던 요청경로를 SmartEditorController 에 요청을 하도록 수정한다.
    	sUploadURL="/spring10/editor_upload";
    	
    여기서 /spring10 는  context path 이기 때문에 상황에 맞게 변경해야 한다. 
 *  
 */
@Controller
public class SmartEditorController {
	// 업로드된 이미지를 저장할 서버의 경로 읽어오기
	@Value("${file.location}")
	private String fileLocation;

	// ajax 업로드 요청에 대해 응답을 하는 컨트롤러 메소드
	@PostMapping("/editor_upload")
	@ResponseBody
	public String upload(HttpServletRequest request) throws IOException {

		// 파일정보
		String sFileInfo = "";
		// 파일명을 받는다 - 일반 원본파일명
		String filename = request.getHeader("file-name");
		// 파일 확장자
		String filename_ext = filename.substring(filename.lastIndexOf(".") + 1);
		// 확장자를소문자로 변경
		filename_ext = filename_ext.toLowerCase();

		// 이미지 검증 배열변수
		String[] allow_file = { "jpg", "png", "bmp", "gif" };

		// 돌리면서 확장자가 이미지인지
		int cnt = 0;
		for (int i = 0; i < allow_file.length; i++) {
			if (filename_ext.equals(allow_file[i])) {
				cnt++;
			}
		}

		// 이미지가 아님
		if (cnt == 0) {
			/*
			 * 이미지가 아니면 클라이언트에게
			 * 
			 * NOTALLOW_파일명
			 * 
			 * 을 응답한다.
			 */
			// out.println("NOTALLOW_" + filename);
			return "NOTALLOW_" + filename;
		} else {
			// 이미지이므로 신규 파일로 디렉토리 설정 및 업로드

			// 파일 기본경로 _ 상세경로
			String filePath = fileLocation + File.separator;
			File file = new File(filePath);
			if (!file.exists()) {
				file.mkdirs();
			}
			String realFileNm = "";
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
			String today = formatter.format(new java.util.Date());
			realFileNm = today + UUID.randomUUID().toString() + filename.substring(filename.lastIndexOf("."));
			String rlFileNm = filePath + realFileNm;
			///////////////// 서버에 파일쓰기 /////////////////
			InputStream is = request.getInputStream();
			OutputStream os = new FileOutputStream(rlFileNm);
			int numRead;
			byte b[] = new byte[Integer.parseInt(request.getHeader("file-size"))];
			while ((numRead = is.read(b, 0, b.length)) != -1) {
				os.write(b, 0, numRead);
			}
			if (is != null) {
				is.close();
			}
			os.flush();
			os.close();
			///////////////// 서버에 파일쓰기 /////////////////
			String contextPath = request.getContextPath();
			// 업로드된 이미지의 정보를 클라이언트에게 출력
			sFileInfo += "&bNewLine=true";
			sFileInfo += "&sFileName=" + filename;
			sFileInfo += "&sFileURL=" + contextPath + "/upload/" + realFileNm;
			// out.println(sFileInfo);
			return sFileInfo;
		}
	}
}
