package at.ac.iiasa.ime.enrima.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import at.ac.iiasa.ime.enrima.client.datawrapper.SymbolicModelSpecificationHelper;
import at.ac.iiasa.ime.enrima.client.domain.Analysis;
import at.ac.iiasa.ime.enrima.client.domain.DefineAnalysisRequest;
import at.ac.iiasa.ime.enrima.client.domain.DefinePreferenceRequest;
import at.ac.iiasa.ime.enrima.client.domain.GetAnalysesRequest;
import at.ac.iiasa.ime.enrima.client.domain.GetAnalysesResponse;
import at.ac.iiasa.ime.enrima.client.domain.GetPreferenceRequest;
import at.ac.iiasa.ime.enrima.client.domain.GetPreferenceResponse;
import at.ac.iiasa.ime.enrima.client.domain.GetSolutionRequest;
import at.ac.iiasa.ime.enrima.client.domain.GetSolutionResponse;
import at.ac.iiasa.ime.enrima.client.domain.GetSolverStatusRequest;
import at.ac.iiasa.ime.enrima.client.domain.ModelSpec;
import at.ac.iiasa.ime.enrima.client.domain.ObjectFactory;
import at.ac.iiasa.ime.enrima.client.domain.Preference;
import at.ac.iiasa.ime.enrima.client.domain.PreferenceType;
import at.ac.iiasa.ime.enrima.client.ws.ModelClient;
@SessionAttributes({ "model","modelId","dataId", "modelInstanceId","symbolicModelSpecificationHelper" })
@Controller
public class AnalysisController {

	@Autowired
	private ModelClient modelClient;
	public ObjectFactory objectFactory = new ObjectFactory();

	@ModelAttribute
	public void analysis(Model model) {
		 model.addAttribute("whereWeAre", "analysis");
	}
	
	@RequestMapping(value = "/model/{modelId}/instance/{modelInstanceId}/analysis", method = RequestMethod.GET)
	public String populateAnalyses(
			@PathVariable("modelInstanceId") int modelInstanceId, Model model) {
		// populateSMS(modelId, model);
		GetAnalysesRequest request =objectFactory.createGetAnalysesRequest();
		request.setIdModelInstance(modelInstanceId);
		GetAnalysesResponse response = modelClient.getAnalysesRequest(request);
		model.addAttribute("analyses", response.getAnalysis());
		return "analyses";
	}

	@RequestMapping(value = "/model/{modelId}/instance/{modelInstanceId}/analysis/new", method = RequestMethod.GET)
	public String populateAnalysisForm(
			@PathVariable("modelInstanceId") int modelInstanceId, Model model) {
		Analysis analysis = objectFactory.createAnalysis();
		analysis.setIdModelInstance(modelInstanceId);
		analysis.setStatus("Initial");
		model.addAttribute("analysis", analysis);
		return "analysis/form";
	}

	@RequestMapping(value = "/model/{modelId}/instance/{modelInstanceId}/analysis/new", method = RequestMethod.POST)
	public String saveAnalysis(
			@PathVariable("modelId") int modelId,
			@PathVariable("modelInstanceId") int modelInstanceId,
			@RequestParam(value = "_cancel", required = false) String canel,
			@RequestParam(value = "_create", required = false) String create,
			@ModelAttribute Analysis analysis,
			Model model) {
		if (canel != null) {
			return "redirect:/model/{modelId}/instance/{modelInstanceId}/analysis";
		}
	    DefineAnalysisRequest request = objectFactory.createDefineAnalysisRequest();
	    request.setAnalysis(analysis);
	    modelClient.saveAnalysis(request);
		return "redirect:/model/{modelId}/instance/{modelInstanceId}/analysis";
	}
	
	@RequestMapping(value = "/model/{modelId}/instance/{modelInstanceId}/analysis/{idAnalysis}", method = RequestMethod.GET)
	public String populateAnalysisDashboard(
			@PathVariable("modelInstanceId") int modelInstanceId,
			@PathVariable("idAnalysis") int idAnalysis,Model model) {
		//model.addAttribute("idAnalysis", idAnalysis);
		return "analysis/dashboard";
	}
	
	@RequestMapping(value = "/model/{modelId}/instance/{modelInstanceId}/analysis/{idAnalysis}/preference", method = RequestMethod.GET)
	public String populatePreference(
			@PathVariable("modelInstanceId") int modelInstanceId,
			@PathVariable("idAnalysis") int idAnalysis,Model model) {
		GetSolverStatusRequest request = objectFactory.createGetSolverStatusRequest();
		request.setIdAnalysis(idAnalysis);
		String status=modelClient.getSolverStatus(request).getResult();
		if(!status.equalsIgnoreCase("initial"))
		{
			model.addAttribute("editable", false);
		}
		else
		{
			model.addAttribute("editable", true);
		}
		GetPreferenceRequest getPreferenceRequest = objectFactory.createGetPreferenceRequest();
		getPreferenceRequest.setIdAnalysis(idAnalysis);
		GetPreferenceResponse response = modelClient.getPreferenceRequest(getPreferenceRequest);
		model.addAttribute("preferences", response.getPreference());
		
		return "analysis/preference";
	}
	@RequestMapping(value = "/model/{modelId}/instance/{modelInstanceId}/analysis/{idAnalysis}/preference/add", method = RequestMethod.GET)
	public String populatePreferenceForm(
			@PathVariable("modelInstanceId") int modelInstanceId,
			@PathVariable("idAnalysis") int idAnalysis,
			@ModelAttribute("model") ModelSpec modelSpec,
			Model model) {
		model.addAttribute("entities", SymbolicModelSpecificationHelper.getEntitySpecsCanbeUsedForPreference(modelSpec));
		return "analysis/preferenceForm";
	}
	
	@RequestMapping(value = "/model/{modelId}/instance/{modelInstanceId}/analysis/{idAnalysis}/preference/add", method = RequestMethod.POST)
	public String savePreference(
			@PathVariable("modelInstanceId") int modelInstanceId,
			@PathVariable("idAnalysis") int idAnalysis,
			@ModelAttribute("model") ModelSpec modelSpec,
			@RequestParam(value = "_cancel", required = false) String canel,
			@RequestParam(value = "idEntity", required = false) Integer idEntity,
			@RequestParam(value = "preferenceType", required = false) String preferenceType,
			@RequestParam(value = "value", required = false) Double value,
			Model model) {
		if (canel != null) {
			return "redirect:/model/{modelId}/instance/{modelInstanceId}/analysis/{idAnalysis}/preference";
		}
		DefinePreferenceRequest request = objectFactory.createDefinePreferenceRequest();
		request.setIdAnalysis(idAnalysis);
		Preference preference = objectFactory.createPreference();
		preference.setIdEntitySpec(idEntity);
		preference.setPrefValue(value);
		if(preferenceType.equals("MIN"))
		{
			preference.setPreferenceType(PreferenceType.MIN);
		}
		else
		{
			preference.setPreferenceType(PreferenceType.MAX);
		}
		
		request.getPreference().add(preference);
		modelClient.definePreferenceRequest(request);
		return "redirect:/model/{modelId}/instance/{modelInstanceId}/analysis/{idAnalysis}/preference";
	}

	@RequestMapping(value = "/model/{modelId}/instance/{modelInstanceId}/analysis/{idAnalysis}/preference/{idEntityspec}/delete", method = RequestMethod.GET)
	public String deletePreference(
			@PathVariable("modelInstanceId") int modelInstanceId,
			@PathVariable("idAnalysis") int idAnalysis,
			@PathVariable("idEntityspec") int idEntityspec,
			@ModelAttribute("model") ModelSpec modelSpec,
			Model model) {

		DefinePreferenceRequest request = objectFactory.createDefinePreferenceRequest();
		request.setIdAnalysis(idAnalysis);
		Preference preference = objectFactory.createPreference();
		preference.setIdEntitySpec(idEntityspec);
		//whatever
		preference.setPreferenceType(PreferenceType.MIN);
		preference.setPrefValue(null);
		request.getPreference().add(preference);
		modelClient.definePreferenceRequest(request);
		return "redirect:/model/{modelId}/instance/{modelInstanceId}/analysis/{idAnalysis}/preference";
	}
	
	@RequestMapping(value = "/model/{modelId}/instance/{modelInstanceId}/analysis/{idAnalysis}/solverStatus", method = RequestMethod.GET)
	public String populateSolverStatus(
			@PathVariable("idAnalysis") int idAnalysis,
			Model model) {

		GetSolverStatusRequest request = objectFactory.createGetSolverStatusRequest();
		request.setIdAnalysis(idAnalysis);
		String status = modelClient.getSolverStatus(request).getResult();
		model.addAttribute("solverStatus", status);
		return "analysis/solverStatus";
	}
	@RequestMapping(value = "/model/{modelId}/instance/{modelInstanceId}/analysis/{idAnalysis}/solver", method = RequestMethod.GET)
	public String callSolver(
			@PathVariable("idAnalysis") int idAnalysis,
			Model model) {


		return "redirect:/model/{modelId}/instance/{modelInstanceId}/analysis/{idAnalysis}";
	}	
	@RequestMapping(value = "/model/{modelId}/instance/{modelInstanceId}/analysis/{idAnalysis}/solution", method = RequestMethod.GET)
	public String populateSolution(
			@PathVariable("idAnalysis") int idAnalysis,
			Model model) {
        GetSolutionRequest request = objectFactory.createGetSolutionRequest();
        request.setIdAnalysis(idAnalysis);
        GetSolutionResponse response = modelClient.getSolutionRequest(request);
        model.addAttribute("entityValues", response.getEntityValues());
		return "analysis/solution";
	}
}
