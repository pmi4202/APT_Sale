package com.ssafy.final_project.view;

import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

import com.ssafy.final_project.model.dto.FileDto;
import com.ssafy.util.PathManager;


@Component(value = "fileDownLoadView")
public class FileDownLoadView extends AbstractView {

	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> paramMap, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		FileDto fileDto = (FileDto) paramMap.get("fileDto");

		ServletContext ctx = getServletContext();

		String filePath = PathManager.TEST_UPLOAD_PATH+File.separator+"upload" + File.separator + fileDto.getSaveFolder() + File.separator
				+ fileDto.getSaveFileName();

		File file = new File(filePath);

		response.setContentType("apllication/download; charset=UTF-8");
		response.setContentLength((int) file.length());

		String header = request.getHeader("User-Agent");
		boolean isIE = header.indexOf("MSIE") > -1 || header.indexOf("Trident") > -1;
		String fileName = null;
		// IE는 다르게 처리
		if (isIE) {
			fileName = URLEncoder.encode(fileDto.getOriginalFileName(), "UTF-8").replaceAll("\\+", "%20");
		} else {
			fileName = new String(fileDto.getOriginalFileName().getBytes("UTF-8"), "ISO-8859-1");
		}
		
		//attachment이면 다운로드 하는 걸로 알고 있음
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
	    response.setHeader("Content-Transfer-Encoding", "binary");
	        
		
		try (FileInputStream fis = new FileInputStream(file)){
			FileCopyUtils.copy(fis, response.getOutputStream());
			//인풋얻어와서 , 바이트로 아웃풋 스트림으로 쏨 ... 버퍼드리더, 버퍼드 와이터 쓰면 안됌?
		}catch(Exception e) {
			e.printStackTrace();
		}
		//response는 알아서 닫히겠지.
		response.getOutputStream().flush();

		
	}

}
