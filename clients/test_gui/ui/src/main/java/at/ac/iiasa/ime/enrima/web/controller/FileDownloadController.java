package at.ac.iiasa.ime.enrima.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;


@Controller      
@RequestMapping("/fileDownload")
public class FileDownloadController {
	@Value("#{prop['download_file_path']}") private String filePath;
	@RequestMapping(method = RequestMethod.GET)
	    public void handleFileDownload(HttpServletResponse response) {
	        File file = new File(filePath);
	        response.setContentType("application/zip"); 
	        response.setContentLength(new Long(file.length()).intValue());
	        response.setHeader("Content-Disposition","attachment; filename="+"examples.zip");
	 
	        try {
	            FileCopyUtils.copy(new FileInputStream(file), response.getOutputStream());
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return;
	    }
	
	
}