package edu.iot.common.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

public class ImageUtil {

	public static void saveThumb(File src) throws Exception {
		
		File thumb = new File(src.getParent() + "/thumb",
						src.getName());
		//Thumbnail 파일 생성
		Thumbnails
			.of(src) // 원본 지정
			.crop(Positions.CENTER) // 가로 세로 길이 상관없이 중앙에 놓고 이미지 crop
			.size(200, 200) // 크기
			.toFile(thumb); // thumb에 저장하라
	}
	public static void saveImage(File src) throws Exception {
		// getParent() 부모 디렉토리의 경로 가져오기
		// File 의 첫번째 인자는 디렉토리, 두번째 인자는 그 디렉토리에서의 파일명
		File image = new File(src.getParent() + "/image", src.getName());
		
		// Thumbnail 파일 생성
		Thumbnails.of(src)					// 원본 지정
				 .crop(Positions.CENTER)	// 이미지 crop(이미지를 중앙에서 뽑겠다)
				 .size(400, 300)			// 크기는 200*200
				 .toFile(image);			// 저장소 지정
	}
	
	public static byte[] makeThumb(byte [] data) throws Exception {
		return makeThumb(data, 100, 100);
	}
	
	public static byte[] makeThumb(byte [] data, int width, int height) 
			throws Exception {
		
		try(
			ByteArrayInputStream in = new ByteArrayInputStream(data);
			ByteArrayOutputStream out = new ByteArrayOutputStream();
		){
			Thumbnails.of(in)
				.size(width,height)
				.crop(Positions.CENTER)
				.toOutputStream(out);
			return out.toByteArray();
		}
	}
}
