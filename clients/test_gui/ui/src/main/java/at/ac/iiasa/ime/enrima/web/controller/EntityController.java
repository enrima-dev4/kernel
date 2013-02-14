package at.ac.iiasa.ime.enrima.web.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import at.ac.iiasa.ime.enrima.client.datawrapper.SymbolicModelSpecificationHelper;
import at.ac.iiasa.ime.enrima.client.domain.EntitySpec;
import at.ac.iiasa.ime.enrima.client.domain.EntityValues;
import at.ac.iiasa.ime.enrima.client.domain.GetEntityValuesRequest;
import at.ac.iiasa.ime.enrima.client.domain.GetEntityValuesResponse;
import at.ac.iiasa.ime.enrima.client.domain.GetSetMembersRequest;
import at.ac.iiasa.ime.enrima.client.domain.IteratorContainer;
import at.ac.iiasa.ime.enrima.client.domain.MathType;
import at.ac.iiasa.ime.enrima.client.domain.MemberDic;
import at.ac.iiasa.ime.enrima.client.domain.ModelSpec;
import at.ac.iiasa.ime.enrima.client.domain.ObjectFactory;
import at.ac.iiasa.ime.enrima.client.domain.RemoveEntityValuesRequest;
import at.ac.iiasa.ime.enrima.client.domain.SetSpec;
import at.ac.iiasa.ime.enrima.client.domain.StoreEntityValuesRequest;
import at.ac.iiasa.ime.enrima.client.domain.TupleValue;
import at.ac.iiasa.ime.enrima.client.domain.TupleValues;
import at.ac.iiasa.ime.enrima.client.domain.Value;
import at.ac.iiasa.ime.enrima.client.ws.ModelClient;

@SessionAttributes({ "model", "symbolicModelSpecificationHelper" })
@Controller
@RequestMapping("/model/{modelId}/data/{dataId}/entity/{entityId}")
public class EntityController {

	@Autowired
	private ModelClient modelClient;
	public ObjectFactory objectFactory = new ObjectFactory();

	@ModelAttribute
	public void data(Model model) {
		  model.addAttribute("whereWeAre", "data");
	}
	@RequestMapping(method = RequestMethod.GET)
	public String populateEntityData(
			@ModelAttribute("model") ModelSpec modelSpec,
			@PathVariable("modelId") int modelId,
			@PathVariable("dataId") int dataId,
			@PathVariable("entityId") int entityId, Model model) {
		EntitySpec entitySpec = SymbolicModelSpecificationHelper.getEntitySpecById(modelSpec, entityId);
		model.addAttribute("entitySpec", entitySpec);
		GetEntityValuesRequest request =objectFactory.createGetEntityValuesRequest();
		request.setIdModelSpec(modelId);
		request.setIdModelData(dataId);
		request.setEntityShortName(entitySpec.getShortName());
		GetEntityValuesResponse response= modelClient.getEntityValues(request);
		List<TupleValue> tupleValues = response.getTupleValue();
		model.addAttribute("tupleValueList", tupleValues);
		return "data/entityValues";
	}

	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String populateEntityValueForm(
			@ModelAttribute("model") ModelSpec modelSpec,
			@PathVariable("dataId") int dataId,
			@PathVariable("entityId") int entityId, Model model) {
		EntitySpec entitySpec = SymbolicModelSpecificationHelper.getEntitySpecById(modelSpec, entityId);
		model.addAttribute("entitySpec", entitySpec);
		
		IteratorContainer itrContainer = entitySpec.getIteratorContainer();
		Map<String, List<MemberDic>> iteratorsMap = new LinkedHashMap<String, List<MemberDic>>();
		if(itrContainer!=null)
		{
			for (int idSet : itrContainer.getIdSetSpec()) {
				SetSpec idx = SymbolicModelSpecificationHelper.getSetSpecById(
						modelSpec, idSet);
				GetSetMembersRequest request = objectFactory
						.createGetSetMembersRequest();
				request.setIdModelData(dataId);
				request.setSetShortName(idx.getShortName());
				List<MemberDic> members = modelClient.getSetMembers(request);
				iteratorsMap.put("" + idSet, members);
			}
		}
		
		model.addAttribute("iteratorsMap", iteratorsMap);
		return "data/entityValueForm";
	}
	
	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public String populateEntityValueEditForm(
			@ModelAttribute("model") ModelSpec modelSpec,
			@RequestParam(value = "tuple", required = false) String tuple,
			@RequestParam(value = "entityValue", required = false) String entityValue,
			@PathVariable("entityId") int entityId, Model model) {
		EntitySpec entitySpec = SymbolicModelSpecificationHelper.getEntitySpecById(modelSpec, entityId);
		model.addAttribute("entitySpec", entitySpec);
		model.addAttribute("entityValue", entityValue);
		model.addAttribute("tuple", tuple);
	
		return "data/entityValueEditForm";
	}
	@RequestMapping(value = "save", method = RequestMethod.GET)
	public String saveEntityValue(
			@ModelAttribute("model") ModelSpec modelSpec,
			@RequestParam(value = "tuple", required = false) String tuple,
			@RequestParam(value = "_cancel", required = false) String canel,
			@RequestParam(value = "_save", required = false) String save,
			@RequestParam(value = "entityValue", required = false) String entityValue,
			@PathVariable("modelId") int modelId,
			@PathVariable("dataId") int dataId,
			@PathVariable("entityId") int entityId, Model model) {
		if (canel != null) {
			return "redirect:/model/{modelId}/data/{dataId}/entity/{entityId}";
		}
		EntitySpec entitySpec = SymbolicModelSpecificationHelper.getEntitySpecById(modelSpec, entityId);
		StoreEntityValuesRequest request = objectFactory.createStoreEntityValuesRequest();
		request.setIdModelSpec(modelId);
		request.setIdModeldata(dataId);
		EntityValues entityValues = objectFactory.createEntityValues();
		entityValues.setEntityShortName(entitySpec.getShortName());
		TupleValues tupleValues = objectFactory.createTupleValues();
		if(tuple!=null)
		{
			tupleValues.setTuple(tuple);
		}
		
		Value v = objectFactory.createValue();
		if(entitySpec.getMathType().equals(MathType.INTEGER))
		{
			v.setIntValue(Integer.parseInt(entityValue));
		}
		else
		{
			v.setDoubleValue(Double.parseDouble(entityValue));
		}
		tupleValues.setValue(v);
		entityValues.getTupleValues().add(tupleValues);
		request.getEntityValues().add(entityValues);
		modelClient.saveEntityValues(request);
		
		return "redirect:/model/{modelId}/data/{dataId}/entity/{entityId}";
	}
	
	
	@RequestMapping(value = "remove", method = RequestMethod.GET)
	public String removeEntityValue(
			@ModelAttribute("model") ModelSpec modelSpec,
			@RequestParam(value = "tuple", required = false) String tuple,
			@PathVariable("modelId") int modelId,
			@PathVariable("dataId") int dataId,
			@PathVariable("entityId") int entityId, Model model) {
	
		EntitySpec entitySpec = SymbolicModelSpecificationHelper.getEntitySpecById(modelSpec, entityId);
		RemoveEntityValuesRequest request = objectFactory.createRemoveEntityValuesRequest();
		request.setIdModelSpec(modelId);
		request.setIdModeldata(dataId);
		EntityValues entityValues = objectFactory.createEntityValues();
		entityValues.setEntityShortName(entitySpec.getShortName());
		TupleValues tupleValues = objectFactory.createTupleValues();
		if(tuple!=null)
		{
			tupleValues.setTuple(tuple);
		}
		
		entityValues.getTupleValues().add(tupleValues);
		request.getEntityValues().add(entityValues);
		modelClient.removeEntityValues(request);
		return "redirect:/model/{modelId}/data/{dataId}/entity/{entityId}";
	}

	
}
