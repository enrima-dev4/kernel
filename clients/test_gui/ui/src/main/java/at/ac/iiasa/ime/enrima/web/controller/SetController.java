package at.ac.iiasa.ime.enrima.web.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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
import at.ac.iiasa.ime.enrima.client.domain.GetSetMembersRequest;
import at.ac.iiasa.ime.enrima.client.domain.IteratorContainer;
import at.ac.iiasa.ime.enrima.client.domain.MemberDic;
import at.ac.iiasa.ime.enrima.client.domain.ModelSpec;
import at.ac.iiasa.ime.enrima.client.domain.ObjectFactory;
import at.ac.iiasa.ime.enrima.client.domain.RemoveSetMembersRequest;
import at.ac.iiasa.ime.enrima.client.domain.RemoveSetMembersResponse;
import at.ac.iiasa.ime.enrima.client.domain.ResponseStatus;
import at.ac.iiasa.ime.enrima.client.domain.SetMembers;
import at.ac.iiasa.ime.enrima.client.domain.SetSpec;
import at.ac.iiasa.ime.enrima.client.domain.SetSpecType;
import at.ac.iiasa.ime.enrima.client.domain.StoreMainSetRequest;
import at.ac.iiasa.ime.enrima.client.domain.StoreSetMembersRequest;
import at.ac.iiasa.ime.enrima.client.domain.StoreSetMembersResponse;
import at.ac.iiasa.ime.enrima.client.domain.TupleMembers;
import at.ac.iiasa.ime.enrima.client.ws.ModelClient;

@SessionAttributes({ "model","modelId","dataId", "symbolicModelSpecificationHelper" })
@Controller
@RequestMapping("/model/{modelId}/data/{dataId}")
public class SetController {

	@Autowired
	private ModelClient modelClient;
	public ObjectFactory objectFactory = new ObjectFactory();
	@ModelAttribute
	public void data(Model model) {
		 model.addAttribute("whereWeAre", "data");
	}

	@RequestMapping(value = "set/{setId}", method = RequestMethod.GET)
	public String populateSetData(
			@ModelAttribute("model") ModelSpec modelSpec,
			@PathVariable("dataId") int dataId,
			@PathVariable("setId") int setId, Model model) {
		SetSpec setSpec = SymbolicModelSpecificationHelper.getSetSpecById(
				modelSpec, setId);
		model.addAttribute("setSpec", setSpec);
		if (setSpec.getType().equals(SetSpecType.MAINSET)) {
			populateSetmembers(model,dataId,setSpec.getShortName(),null);
			return "data/setmembers";
		} else if (setSpec.getType().equals(SetSpecType.SUBSET)) {
			populateSetmembers(model,dataId,setSpec.getShortName(),null);
			return "data/setmembers";
		} else {

			return "redirect:/model/{modelId}/data/{dataId}/indexedsubSet/{setId}";

		}

	}

	
	@RequestMapping(value = "indexedsubSet/{setId}", method = RequestMethod.GET)
	public String populateIndexedSubSet(
			@ModelAttribute("model") ModelSpec modelSpec,
			@PathVariable("dataId") int dataId,
			@PathVariable("setId") int setId, Model model) {
		SetSpec setSpec = SymbolicModelSpecificationHelper.getSetSpecById(
				modelSpec, setId);
		model.addAttribute("setSpec", setSpec);
		IteratorContainer itrContainer = setSpec.getIteratorContainer();
		Map<String, List<MemberDic>> iteratorsMap = new LinkedHashMap<String, List<MemberDic>>();
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
		model.addAttribute("iteratorsMap", iteratorsMap);
		return "data/indexedSubSet";
	}

	@RequestMapping(value = "indexedsubSet/{setId}/members", method = RequestMethod.GET)
	public String populateIndexedSetMembers(
			@ModelAttribute("model") ModelSpec modelSpec,
			@RequestParam(value = "iterators", required = false) String[] iterators,
			@PathVariable("dataId") int dataId,
			@PathVariable("setId") int setId, Model model) {
		SetSpec setSpec = SymbolicModelSpecificationHelper.getSetSpecById(
				modelSpec, setId);
		model.addAttribute("setSpec", setSpec);
		populateSetmembers(model,dataId,setSpec.getShortName(),iterators);
		return "data/setmembers";

	}

	@RequestMapping(value = "set/{setId}/member/{code}/remove", method = RequestMethod.GET)
	public String removeSetMember(
			@ModelAttribute("model") ModelSpec modelSpec,
			@RequestParam(value = "iterators", required = false) String iterators,
			@PathVariable("dataId") int dataId,
			@PathVariable("setId") int setId,
			@PathVariable("code") String code, Model model) {

		SetSpec setSpec = SymbolicModelSpecificationHelper.getSetSpecById(
				modelSpec, setId);
		RemoveSetMembersRequest request = objectFactory
				.createRemoveSetMembersRequest();
		request.setIdModeldata(dataId);
		SetMembers setMembers = objectFactory.createSetMembers();
		setMembers.setSetShortName(setSpec.getShortName());
		TupleMembers tupleMembers = objectFactory.createTupleMembers();
		tupleMembers.setMembers(code);
		if (iterators != null) {
			tupleMembers.setTuple(iterators);
		}

		setMembers.getTupleMembers().add(tupleMembers);
		request.getSetMembers().add(setMembers);
		RemoveSetMembersResponse response =modelClient.removeSetMembers(request);
		if(response.getStatus().equals(ResponseStatus.OK))
		{
		   return returnURL(iterators);
		}
		else
		{
			model.addAttribute("message",response.getMsg());
			populateSetmembers(model,dataId,setSpec.getShortName(),stringToArray(iterators));
			return "data/setmembers";
		}

	}

	@RequestMapping(value = "set/{setId}/add", method = RequestMethod.GET)
	public String populateSetMemberForm(
			@ModelAttribute("model") ModelSpec modelSpec,
			@RequestParam(value = "iterators", required = false) String iterators,
			@PathVariable("dataId") int dataId,
			@PathVariable("setId") int setId, Model model) {
		SetSpec setSpec = SymbolicModelSpecificationHelper.getSetSpecById(
				modelSpec, setId);
		model.addAttribute("setSpec", setSpec);
		if (setSpec.getType().equals(SetSpecType.MAINSET)) {
			MemberDic member = objectFactory.createMemberDic();
			model.addAttribute("member", member);
			return "data/memberForm";
		} else if (setSpec.getType().equals(SetSpecType.SUBSET)) {
			int idParent = setSpec.getIdParent();
			SetSpec parentSet = SymbolicModelSpecificationHelper
					.getSetSpecById(modelSpec, idParent);
			model.addAttribute("parent", parentSet);
			populateSetmembers(model,dataId,parentSet.getShortName(),stringToArray(iterators));
			return "data/subSetMemberForm";
		} else {
			int idParent = setSpec.getIdParent();
			SetSpec parentSet = SymbolicModelSpecificationHelper
					.getSetSpecById(modelSpec, idParent);
			model.addAttribute("parent", parentSet);	
			populateSetmembers(model,dataId,parentSet.getShortName(),stringToArray(iterators));
			return "data/subSetMemberForm";
		}

	}

	@RequestMapping(value = "set/{setId}/add", method = RequestMethod.POST)
	public String saveMember(
			@ModelAttribute("model") ModelSpec modelSpec,
			@RequestParam(value = "iterators", required = false) String iterators,
			@RequestParam(value = "_cancel", required = false) String canel,
			@RequestParam(value = "_create", required = false) String create,
			@RequestParam(value = "_add", required = false) String add,
			@RequestParam(value = "codes", required = false) String[] codes,
			@PathVariable("dataId") int dataId,
			@PathVariable("setId") int setId,
			@ModelAttribute("member") MemberDic member, Model model) {
		if (canel != null) {
			return returnURL(iterators);
		}
		//main set
		if (create != null) {
			StoreMainSetRequest request = objectFactory.createStoreMainSetRequest();
			request.setIdModeldata(dataId);
			SetSpec setSpec = SymbolicModelSpecificationHelper.getSetSpecById(
					modelSpec, setId);
			request.setSetShortName(setSpec.getShortName());
			request.getMember().add(member);
			modelClient.saveMainset(request);
		} 
		if (add != null) {
			StoreSetMembersRequest request = objectFactory
					.createStoreSetMembersRequest();
			request.setIdModeldata(dataId);
			SetSpec setSpec = SymbolicModelSpecificationHelper.getSetSpecById(
					modelSpec, setId);
			SetMembers setMembers = objectFactory.createSetMembers();
			setMembers.setSetShortName(setSpec.getShortName());
			TupleMembers tupleMembers = objectFactory.createTupleMembers();
			if (codes == null) {
				return returnURL(iterators);
			}

			else {
				tupleMembers.setMembers(arraytoString(codes));
				if (iterators != null) {
				//	System.out.println("----------iterators:  " + iterators);
					tupleMembers.setTuple(iterators);
				}
			}
			setMembers.getTupleMembers().add(tupleMembers);
			request.getSetMembers().add(setMembers);
			StoreSetMembersResponse response=modelClient.saveSetMember(request);
			//System.out.println("----------:" +response.getStatus().name());
		}

		return returnURL(iterators);
	}

	private void populateSetmembers(Model model,int dataId,String shortName,String[] iterators)
	{
		GetSetMembersRequest request = objectFactory
				.createGetSetMembersRequest();
		request.setIdModelData(dataId);
		request.setSetShortName(shortName);
		if(iterators!=null)
		{
			for (String itr : iterators) {
				MemberDic memberDic = objectFactory.createMemberDic();
				memberDic.setCode(itr);
				request.getTupleMember().add(memberDic);
			}
			model.addAttribute("iterators", arraytoString(iterators));
		}
		
		List<MemberDic> members = modelClient.getSetMembers(request);
		model.addAttribute("members", members);
	}
	private String arraytoString(String[] array) {
		String s = "";
		int i = 0;
		for (String code : array) {
			if (i == 0)
				s = code;
			else
				s = s + "," + code;
			i++;
		}
		return s;
	}
	private String[] stringToArray(String str) {
		if(str==null)
			return null;
		return str.split(",");
		
	}

	private String returnURL(String iterators) {
		if (iterators == null) {
			return "redirect:/model/{modelId}/data/{dataId}/set/{setId}";
		} else {
			return "redirect:/model/{modelId}/data/{dataId}/indexedsubSet/{setId}/members?iterators="
					+ iterators;
		}
	}
}
