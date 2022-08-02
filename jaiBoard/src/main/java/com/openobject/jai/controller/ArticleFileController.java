package com.openobject.jai.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.openobject.jai.commons.util.MediaUtils;
import com.openobject.jai.commons.util.UploadFileUtils;
import com.openobject.jai.service.ArticleService;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import javax.annotation.Resource;

@RestController
@RequestMapping("/file")
public class ArticleFileController {
  private static final Logger logger = LoggerFactory.getLogger(ArticleFileController.class);
  
  private final ArticleService articleService;
  
  @Inject
  public ArticleFileController(ArticleService articleService) {
    this.articleService = articleService;
  }
  
  @Resource(name = "uploadPath")
  private String uploadPath;
  
  @RequestMapping(value = "/upload", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
  public ResponseEntity<String> uploadFile(MultipartFile file) throws Exception {
    System.out.println("파일 받기");
    return new ResponseEntity<String>(UploadFileUtils.uploadFile(uploadPath, file.getOriginalFilename(), file.getBytes()), HttpStatus.CREATED);
  }
  
  @RequestMapping(value = "/list/{article_no}")
  public ResponseEntity<List<String>> fileList(@PathVariable("article_no") Integer article_no) throws Exception {
    ResponseEntity<List<String>> entity = null;
    try {
      entity = new ResponseEntity<List<String>>(articleService.getAttach(article_no), HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
      entity = new ResponseEntity<List<String>>(HttpStatus.BAD_REQUEST);
    }
    return entity;
  }
  
  @RequestMapping(value = "/display")
  public ResponseEntity<byte[]> displayFile(String fileName) throws Exception {
    InputStream inputStream = null;
    ResponseEntity<byte[]> entity = null;
    
    logger.info("file name : " + fileName);
    try {
      String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
      
      MediaType mediaType = MediaUtils.getMediaType(formatName);
      
      HttpHeaders httpHeaders = new HttpHeaders();
      
      inputStream = new FileInputStream(uploadPath + fileName);
      
      if (mediaType != null) {
        httpHeaders.setContentType(mediaType);
      } else {
        fileName = fileName.substring(fileName.indexOf("_") + 1);
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        httpHeaders.add("Content-Disposition", "attachment; filename=\"" + new String(fileName.getBytes("UTF-8"), "ISO-8859-1") + "\"");
      }
      entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(inputStream), httpHeaders, HttpStatus.CREATED);
    } catch (Exception e) {
      e.printStackTrace();
      entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
    } finally {
      inputStream.close();
    }
    return entity;
  }
  
  @ResponseBody
  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  public ResponseEntity<String> boardWriteRemoveFile(String fileName) throws Exception {
    UploadFileUtils.removeFile(uploadPath, fileName);
    
    return new ResponseEntity<String>("DELETED", HttpStatus.OK);
  }
  
  @Transactional
  @ResponseBody
  @RequestMapping(value = "/delete/{article_no}", method = RequestMethod.POST)
  public ResponseEntity<String> boardModifyRemoveFile(@PathVariable("article_no") Integer article_no, String fileName, HttpServletRequest request) throws Exception {
    articleService.deleteAttach(fileName);
    articleService.updateAttachCnt(article_no);
    UploadFileUtils.removeFile(uploadPath, fileName);
    return new ResponseEntity<String>("DELETED", HttpStatus.OK);
  }
  
  @ResponseBody
  @RequestMapping(value = "/delete/all", method = RequestMethod.POST)
  public ResponseEntity<String> boardDeleteRemoveAllFiles(@RequestParam("file[]") String[] files) {
    if (files == null || files.length == 0) {
      return new ResponseEntity<String>("DELETED", HttpStatus.OK);
    }
    
    for (String fileName : files) {
      UploadFileUtils.removeFile(uploadPath, fileName);
    }
    return new ResponseEntity<String>("DELETED", HttpStatus.OK);
  }
}
