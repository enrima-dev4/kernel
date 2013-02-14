package at.ac.iiasa.ime.enrima.web.controller;

import java.io.File;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import at.ac.iiasa.ime.enrima.client.datawrapper.DataWrapperClient;
import at.ac.iiasa.ime.enrima.client.domain.ObjectFactory;


@RequestMapping("/model/{modelId}/data/{dataId}/datawrapper")
@Controller
public class DataWrapperController {

	@Value("#{prop['uploaded_file_path']}") private String filePath;
	@Autowired private  DataWrapperClient dataWrapperClient;
	public ObjectFactory objectFactory = new ObjectFactory();
	
	@ModelAttribute
	public void dataWrapper(Model model) {
		 model.addAttribute("whereWeAre", "datawrapper");
	}

	@RequestMapping(method = RequestMethod.GET)
	public String populateDashboard(Model model) {
		return "datawrapper/dashboard";
	}
	
	@RequestMapping(value = "/{type}",method = RequestMethod.GET)
	public String fileUploadForm(
			@PathVariable("type") String type,
			Model model) {
		if(type.equalsIgnoreCase("mainset"))
		{
			model.addAttribute("title", "Upload mainset elements:");
		}
		else if(type.equalsIgnoreCase("entity"))
		{
			model.addAttribute("title", "Upload entity values:");
		}
		else
		{
			model.addAttribute("title", "Unknow data entry:");
		}
		
		return "datawrapper/fileUpload";
	}

	@RequestMapping(value = "/{type}",method = RequestMethod.POST)
	public String processUpload(
			@PathVariable("type") String type,
			@PathVariable("modelId") Integer modelId,
			@PathVariable("dataId") Integer dataId,
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
		String result="";
		if(type.equalsIgnoreCase("mainset"))
		{
			 result =dataWrapperClient.uploadMainSet(filePath+file.getOriginalFilename(), modelId, dataId);
			
		}
		else if(type.equalsIgnoreCase("entity"))
		{
			 result =dataWrapperClient.uploadEntitiesValues(filePath+file.getOriginalFilename(), modelId, dataId);
		}
		else
		{
			model.addAttribute("title", "Unknow data entry:");
		}
		
		model.addAttribute("message", result);
		
		return "datawrapper/fileUpload";
	}
	

}
