package com.ujiuye.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
/**
 * 文件上传
 *
 * @author junguang
 *
 */
public class FileUploadUtils {

	public static String fileUploadMethod(HttpServletRequest request) {
		String fileName=null;
		try {
			Part part = request.getPart("file");
			fileName = part.getSubmittedFileName();
			if(fileName.equals("")){
				return null;
			}
			fileName=UUID.randomUUID()+fileName;
			File file = new File("D:/xm_upload");
			if(!file.exists()){
				file.mkdir();
			}
			String path =file+"/"+fileName;
			part.write(path);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileName;
	}

}
