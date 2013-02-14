package at.ac.iiasa.ime.enrima.web.controller;

import java.io.File;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import at.ac.iiasa.ime.enrima.client.datawrapper.DataWrapperClient;


@Controller
@RequestMapping("/fileUpload")
public class FileUploadController {;
	@Value("#{prop['uploaded_file_path']}") private String filePath;
	@Autowired private  DataWrapperClient dataWrapperClient;

	@ModelAttribute
	public void fileUpload(Model model) {
		 model.addAttribute("whereWeAre", "fileUpload");
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String fileUploadForm(
			Model model) {
		return "uploadXML/fileUpload";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String processUpload(
			@RequestParam MultipartFile file, Model model)
			throws IOException {
		File disFile = new File(filePath+file.getOriginalFilename());
		try {
			file.transferTo(disFile);
			
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String result = dataWrapperClient.consumeWS(disFile);
		model.addAttribute("message", result);
		
		return "uploadXML/fileUpload";
	}
	
}