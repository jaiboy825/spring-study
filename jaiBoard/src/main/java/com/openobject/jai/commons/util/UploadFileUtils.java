package com.openobject.jai.commons.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.util.FileCopyUtils;

public class UploadFileUtils {
  private static final Logger logger = LoggerFactory.getLogger(UploadFileUtils.class);
  
  public static String uploadFile(String uploadPath, String originalName, byte[] fileData) throws Exception {
    // 중복된 이름의 파일을 저장하지 않기 위해 UUID 키값 생성
    UUID uuid = UUID.randomUUID();
    // 저장할 파일명
    String savedName = uuid.toString() + "_" + originalName;
    // 저장 경로
    String savedPath = calcPath(uploadPath);
    // 파일 객체 생성
    File target = new File(uploadPath + savedPath, savedName);
    // 파일 데이터를 객체에 복사
    FileCopyUtils.copy(fileData, target);
    // 파일 확장자
    String formatName = originalName.substring(originalName.lastIndexOf(".") + 1);
    // 업로드 파일명
    String uploadFileName = null;
    // 확장자 따라서 썸네일 생성 or 일반 아이콘 생성
    if (MediaUtils.getMediaType(formatName) != null) {
      // 썸네일 이미지 생성
      uploadFileName = makeThumbnail(uploadPath, savedPath, savedName);
    } else {
      // 아이콘 생성
      uploadFileName = makeIcon(uploadPath, savedPath, savedName);
    }
    // 파일명 반환
    System.out.println(uploadFileName);
    return uploadFileName;
  }
  
  // 경로 계산
  private static String calcPath(String uploadPath) {
    Calendar calendar = Calendar.getInstance();
    
    String yearPath = File.separator + calendar.get(Calendar.YEAR);
    String monthPath = yearPath + File.separator + new DecimalFormat("00").format(calendar.get(Calendar.MONTH) + 1);
    String datePath = monthPath + File.separator + new DecimalFormat("00").format(calendar.get(Calendar.DATE));
    makeDir(uploadPath, yearPath, monthPath, datePath);
    return datePath;
  }
  
  private static void makeDir(String uploadPath, String... paths) {
    if (new File(uploadPath + paths[paths.length - 1]).exists()) {
      return;
    }
    
    for (String path : paths) {
      File dirPath = new File(uploadPath + path);
      
      if (!dirPath.exists()) {
        dirPath.mkdir();
      }
    }
  }
  
  // 썸네일 생성
  private static String makeThumbnail(String uploadPath, String path, String fileName) throws Exception {
    // 실제 이미지가 아닌 메모리 상의 이미지 객체
    // 원본 파일을 메모리에 로딩
    System.out.println(fileName);
    BufferedImage sourceImg = ImageIO.read(new File(uploadPath + path, fileName));
    // 축소
    BufferedImage destImg = Scalr.resize(sourceImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT, 100);
    // 썸네일 이름
    String thumbnailName = uploadPath + path + File.separator + "s_" + fileName;
    // 썸네일 객체
    File newFile = new File(thumbnailName);
    // 파일 확장자
    String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
    // 파일 저장
    ImageIO.write(destImg, formatName.toUpperCase(), newFile);
    return thumbnailName.substring(uploadPath.length()).replace(File.separatorChar, '/');
  }
  
  // 아이콘 생성
  private static String makeIcon(String uploadPath, String savedPath, String fileName) throws Exception {
    // 아이콘 파일명
    String iconName = uploadPath + savedPath + File.separator + fileName;
    return iconName.substring(uploadPath.length()).replace(File.separatorChar, '/');
  }
  
  // 파일 삭제
  public static void removeFile(String uploadPath, String fileName) {
    // 파일 확장자
    String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
    // 파일 확장자로 이미지 판별
    MediaType mediaType = MediaUtils.getMediaType(formatName);
    // 이미지 파일일 경우 원본 삭제
    if (mediaType != null) {
      // 경로 + 파일명 추출 & 날짜ㄱ 경로 추출
      String front = fileName.substring(0, 12);
      // UUID + 파일명 추출
      String end = fileName.substring(14);
      
      // 원본 이미지 파일 삭제
      new File(uploadPath + (front + end).replace('/', File.separatorChar)).delete();
    }
    // 파일 삭제
    new File(uploadPath + fileName.replace('/', File.separatorChar)).delete();
  }
}
