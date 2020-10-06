package edu.iot.app.view;

import java.io.File;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

import edu.iot.common.util.FileUtil;

@Component("download")
public class DownloadView extends AbstractView {

	@Override
	protected void renderMergedOutputModel(
			Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String path = (String) model.get("path");
		String type = (String) model.get("type");
		String fileName = (String) model.get("fileName");
		fileName = URLEncoder.encode(fileName, "utf-8");

		File file = new File(path);
		// 응답 헤더 설정
		response.setContentType(type);
		response.setContentLength((int) file.length());
		response.setHeader("Content-Disposition", // 컨텐츠의 성격은 
				"attachment; filename=\"" + fileName + "\";"); // 첨부파일 그때 사용할 첨부파일 명
		response.setHeader("Content-Transfer-Encoding", "binary");

		//FileUtil.copy(path, response.getOutputStream()); // 이미지 파일 경로에서 읽어서 내보내라
		FileUtils.copyFile(file, response.getOutputStream()); //base 폼에 디펜던시에 정의 되어있는
		// 메소드
	}				
}
