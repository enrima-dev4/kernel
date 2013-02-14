package at.ac.iiasa.ime.enrima.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import at.ac.iiasa.ime.enrima.client.datawrapper.DataWrapperClient;
import at.ac.iiasa.ime.enrima.client.datawrapper.SymbolicModelSpecificationHelper;
import at.ac.iiasa.ime.enrima.client.domain.ObjectFactory;


@SessionAttributes({ "model","modelId","dataId", "modelInstanceId","symbolicModelSpecificationHelper" })
@Controller
public class ModelController {
	
	@Autowired private  DataWrapperClient dataWrapperClient;
	@Value("#{prop['modelId']}") private int modelId;
	@Value("#{prop['dataId']}") private int dataId;
	@Value("#{prop['modelInstanceId']}") private int modelInstanceId;
	public ObjectFactory objectFactory = new ObjectFactory();
		
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		return "redirect:/model/"+modelId;
	}
	@RequestMapping(value = "/model/{modelId}", method = RequestMethod.GET)
	public String populateModel(
			@PathVariable("modelId") int modelId, Model model) {
		 populateSMS(modelId, model);
		 model.addAttribute("whereWeAre", "sms");
		 return "model";
	}

	@RequestMapping(value = "/model/{modelId}/data/{dataId}", method = RequestMethod.GET)
	public String populateDashboard( Model model) {
	    model.addAttribute("whereWeAre", "data");
		return "data/dashboard";
	}
	
	@RequestMapping(value = "/timeout", method = RequestMethod.GET)
	public String timeout(Model model) {
		return "timeout";
	}
	
	private void populateSMS(int modelId, Model model) {
		model.addAttribute("modelId", modelId);
		model.addAttribute("dataId", dataId);
		model.addAttribute("modelInstanceId", modelInstanceId);
		model.addAttribute("model", dataWrapperClient.getModelById(modelId));
		model.addAttribute("symbolicModelSpecificationHelper",
				new SymbolicModelSpecificationHelper());
	}

}
