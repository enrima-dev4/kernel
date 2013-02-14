package at.ac.iiasa.ime.enrima.client.ws;

import java.io.File;
import java.util.List;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import org.springframework.ws.WebServiceMessageFactory;
import org.springframework.xml.transform.StringResult;

import at.ac.iiasa.ime.enrima.client.datawrapper.EnrimaClient;
import at.ac.iiasa.ime.enrima.client.datawrapper.XMLFormatter;
import at.ac.iiasa.ime.enrima.client.domain.DefineAnalysisRequest;
import at.ac.iiasa.ime.enrima.client.domain.DefineAnalysisResponse;
import at.ac.iiasa.ime.enrima.client.domain.DefinePreferenceRequest;
import at.ac.iiasa.ime.enrima.client.domain.DefinePreferenceResponse;
import at.ac.iiasa.ime.enrima.client.domain.GetAnalysesRequest;
import at.ac.iiasa.ime.enrima.client.domain.GetAnalysesResponse;
import at.ac.iiasa.ime.enrima.client.domain.GetEntityValuesRequest;
import at.ac.iiasa.ime.enrima.client.domain.GetEntityValuesResponse;
import at.ac.iiasa.ime.enrima.client.domain.GetPreferenceRequest;
import at.ac.iiasa.ime.enrima.client.domain.GetPreferenceResponse;
import at.ac.iiasa.ime.enrima.client.domain.GetSMSRequest;
import at.ac.iiasa.ime.enrima.client.domain.GetSMSResponse;
import at.ac.iiasa.ime.enrima.client.domain.GetSetMembersRequest;
import at.ac.iiasa.ime.enrima.client.domain.GetSetMembersResponse;
import at.ac.iiasa.ime.enrima.client.domain.GetSolutionRequest;
import at.ac.iiasa.ime.enrima.client.domain.GetSolutionResponse;
import at.ac.iiasa.ime.enrima.client.domain.GetSolverStatusRequest;
import at.ac.iiasa.ime.enrima.client.domain.GetSolverStatusResponse;
import at.ac.iiasa.ime.enrima.client.domain.MemberDic;
import at.ac.iiasa.ime.enrima.client.domain.ModelSpec;
import at.ac.iiasa.ime.enrima.client.domain.RemoveEntityValuesRequest;
import at.ac.iiasa.ime.enrima.client.domain.RemoveEntityValuesResponse;
import at.ac.iiasa.ime.enrima.client.domain.RemoveSetMembersRequest;
import at.ac.iiasa.ime.enrima.client.domain.RemoveSetMembersResponse;
import at.ac.iiasa.ime.enrima.client.domain.StoreEntityValuesRequest;
import at.ac.iiasa.ime.enrima.client.domain.StoreEntityValuesResponse;
import at.ac.iiasa.ime.enrima.client.domain.StoreMainSetRequest;
import at.ac.iiasa.ime.enrima.client.domain.StoreMainSetResponse;
import at.ac.iiasa.ime.enrima.client.domain.StoreSetMembersRequest;
import at.ac.iiasa.ime.enrima.client.domain.StoreSetMembersResponse;
import at.ac.iiasa.ime.enrima.client.domain.StoreSolutionRequest;
import at.ac.iiasa.ime.enrima.client.domain.StoreSolutionResponse;
import at.ac.iiasa.ime.enrima.client.domain.UpdateSolverStatusRequest;
import at.ac.iiasa.ime.enrima.client.domain.UpdateSolverStatusResponse;


public class ModelClientImpl extends EnrimaClient implements ModelClient {

    public ModelClientImpl(WebServiceMessageFactory messageFactory) {
		super(messageFactory);
	}


	@Override
	public List<MemberDic> getSetMembers(
			GetSetMembersRequest request) {
		GetSetMembersResponse response = (GetSetMembersResponse)getWebServiceTemplate().marshalSendAndReceive(request);
		//logger.debug(toXMLString(response));
		return response.getMember();
	}

	@Override
	public RemoveSetMembersResponse removeSetMembers(
			RemoveSetMembersRequest request) {
		RemoveSetMembersResponse response = (RemoveSetMembersResponse)getWebServiceTemplate().marshalSendAndReceive(request);
		return response;
	}

	@Override
	public StoreSetMembersResponse saveSetMember(StoreSetMembersRequest request) {
		StoreSetMembersResponse response = (StoreSetMembersResponse)getWebServiceTemplate().marshalSendAndReceive(request);
		return response;
	}

	@Override
	public GetEntityValuesResponse getEntityValues(
			GetEntityValuesRequest request) {
		GetEntityValuesResponse response = (GetEntityValuesResponse)getWebServiceTemplate().marshalSendAndReceive(request);
		return response;
	}

	@Override
	public StoreEntityValuesResponse saveEntityValues(
			StoreEntityValuesRequest request) {
		StoreEntityValuesResponse response = (StoreEntityValuesResponse)getWebServiceTemplate().marshalSendAndReceive(request);
		return response;
	}

	@Override
	public RemoveEntityValuesResponse removeEntityValues(
			RemoveEntityValuesRequest request) {
		RemoveEntityValuesResponse response = (RemoveEntityValuesResponse)getWebServiceTemplate().marshalSendAndReceive(request);
		return response;
	}


	@Override
	public GetAnalysesResponse getAnalysesRequest(
			GetAnalysesRequest request) {
		GetAnalysesResponse response = (GetAnalysesResponse)getWebServiceTemplate().marshalSendAndReceive(request);
		return response;
	}

	@Override
	public DefineAnalysisResponse saveAnalysis(DefineAnalysisRequest request) {
		DefineAnalysisResponse response = (DefineAnalysisResponse)getWebServiceTemplate().marshalSendAndReceive(request);
		return response;
	}

	@Override
	public GetPreferenceResponse getPreferenceRequest(
			GetPreferenceRequest request) {
		GetPreferenceResponse response = (GetPreferenceResponse)getWebServiceTemplate().marshalSendAndReceive(request);
		return response;
	}

	@Override
	public GetSolutionResponse getSolutionRequest(GetSolutionRequest request) {
		GetSolutionResponse response = (GetSolutionResponse)getWebServiceTemplate().marshalSendAndReceive(request);
		return response;
	}

	@Override
	public StoreSolutionResponse storeSolutionRequest(
			StoreSolutionRequest request) {
		StoreSolutionResponse response = (StoreSolutionResponse)getWebServiceTemplate().marshalSendAndReceive(request);
		return response;
	}

	@Override
	public GetSolverStatusResponse getSolverStatus(
			GetSolverStatusRequest request) {
		GetSolverStatusResponse response = (GetSolverStatusResponse)getWebServiceTemplate().marshalSendAndReceive(request);
		return response;
	}

	@Override
	public UpdateSolverStatusResponse updateSolverStatus(
			UpdateSolverStatusRequest request) {
		UpdateSolverStatusResponse response = (UpdateSolverStatusResponse)getWebServiceTemplate().marshalSendAndReceive(request);
		return response;
	}

	@Override
	public DefinePreferenceResponse definePreferenceRequest(
			DefinePreferenceRequest request) {
		DefinePreferenceResponse response = (DefinePreferenceResponse)getWebServiceTemplate().marshalSendAndReceive(request);
		return response;
	}

	@Override
	public StoreMainSetResponse saveMainset(StoreMainSetRequest request) {
		StoreMainSetResponse response = (StoreMainSetResponse)getWebServiceTemplate().marshalSendAndReceive(request);
		return response;
	}

	

}




