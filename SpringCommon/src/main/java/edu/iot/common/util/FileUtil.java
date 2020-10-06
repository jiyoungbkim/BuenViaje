package edu.iot.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;


public class FileUtil {
	
	public static List<String> readLines(File file) { // 읽을 파일에 대한 객체, 부르면 리스트 스트링 호출
/*		List<String> list = new ArrayList<>();
		try (
			BufferedReader br = new BufferedReader( // 한줄씩 읽기 위한
									new FileReader(file)); // 버퍼드 리더에 파일리더를 연결
		){	
			String lineString;
			while((lineString= br.readLine()) != null) { // 널이 아닐때 까지 돌면서 읽는다
				list.add(lineString);
			}
		
		} catch(Exception e) {
			e.printStackTrace();
		}*/
		return readLines(file,"UTF-8");
	}
	public static List<String> readLines(File file, String charset) { // 문자셋 변경
		List<String> list = new ArrayList<>();
		try (
			BufferedReader br = new BufferedReader( 
			new InputStreamReader(new FileInputStream(file), charset));){
			
			String lineString;
			while((lineString= br.readLine()) != null) { 
				list.add(lineString);
			}
		
		} catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	/*직렬화 역직렬화 2018-08-14 scorpio */
	public static void serialize(String path, Object obj) throws Exception {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))
		){
			oos.writeObject(obj);

		}
	}
	
	public static Object deserialize(String path) throws Exception {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))
		
		){
			return ois.readObject();
		}
	}
	/*Gson 2018-09-06*/
	public static String readAll(String path) throws Exception {
		StringBuilder sb = new StringBuilder();
		try(
			BufferedReader br = new BufferedReader(
					new FileReader(path));
				
		){
			int ch;
			while((ch=br.read())!=-1) {
				sb.append((char)ch);
			}
		}
		return sb.toString();
	}
	/* 클래스 패스 상에 있는 파일을 절대 경로로 변환 2018-09-07*/
	public static String readResource(String path) throws Exception {
		path = FileUtil.class.getResource(path).getPath();
		return readAll(path);
	}
	/*파일을 읽어서 byte[] 배열 얻기 2018-09-11*/
	public static byte[] bytes(File file) throws Exception {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try(BufferedInputStream bis = new BufferedInputStream(
				new FileInputStream(file)) ){
			int data;
			while((data=bis.read())!=-1) {
				out.write(data);
			}
			return out.toByteArray();
		}
	}
	/*byte[] 배열을 파일에 저장하기 2018-09-11*/
	public static void save(File file, byte[] data) throws Exception {
		try( 
			FileOutputStream fos = new FileOutputStream(file) 
		){ 
			fos.write(data); 
		}
	}
	/*갤러리 이미지 2018-10-12*/
	public static void copy(String path, OutputStream out) throws Exception {
		try(
			BufferedInputStream bis = new BufferedInputStream(
					new FileInputStream(path));
			BufferedOutputStream bos = new BufferedOutputStream(out)
		){
			int data;
			while((data=bis.read())!=-1) {
				bos.write(data);
			} 
			bos.flush();
		}
	}	
}
