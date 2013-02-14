package at.ac.iiasa.ime.enrima.client.ws;


import java.util.List;

import at.ac.iiasa.ime.enrima.client.domain.DefineAnalysisRequest;
import at.ac.iiasa.ime.enrima.client.domain.DefineAnalysisResponse;
import at.ac.iiasa.ime.enrima.client.domain.DefinePreferenceRequest;
import at.ac.iiasa.ime.enrima.client.domain.DefinePreferenceResponse;
import at.ac.iiasa.ime.enrima.client.domain.GetEntityValuesRequest;
import at.ac.iiasa.ime.enrima.client.domain.GetEntityValuesResponse;
import at.ac.iiasa.ime.enrima.client.domain.GetPreferenceRequest;
import at.ac.iiasa.ime.enrima.client.domain.GetPreferenceResponse;
import at.ac.iiasa.ime.enrima.client.domain.GetSetMembersRequest;
import at.ac.iiasa.ime.enrima.client.domain.GetSolutionRequest;
import at.ac.iiasa.ime.enrima.client.domain.GetSolutionResponse;
import at.ac.iiasa.ime.enrima.client.domain.GetSolverStatusRequest;
import at.ac.iiasa.ime.enrima.client.domain.GetSolverStatusResponse;
import at.ac.iiasa.ime.enrima.client.domain.MemberDic;
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
import at.ac.iiasa.ime.enrima.client.domain.GetAnalysesResponse;
import at.ac.iiasa.ime.enrima.client.domain.GetAnalysesRequest;
import at.ac.iiasa.ime.enrima.client.domain.StoreSolutionRequest;
import at.ac.iiasa.ime.enrima.client.domain.StoreSolutionResponse;
import at.ac.iiasa.ime.enrima.client.domain.UpdateSolverStatusRequest;
import at.ac.iiasa.ime.enrima.client.domain.UpdateSolverStatusResponse;
public interface ModelClient {

    public List<MemberDic> getSetMembers(GetSetMembersRequest getSetMembersRequest);
    public RemoveSetMembersResponse removeSetMembers(
			RemoveSetMembersRequest removeSetMembersRequest);
    public StoreSetMembersResponse saveSetMember(StoreSetMembersRequest request);
    public StoreMainSetResponse saveMainset(StoreMainSetRequest request);
    
    public GetEntityValuesResponse getEntityValues(
			GetEntityValuesRequest request);
    public StoreEntityValuesResponse saveEntityValues(
			StoreEntityValuesRequest request);
    public RemoveEntityValuesResponse removeEntityValues(
			RemoveEntityValuesRequest request);
  
    public DefineAnalysisResponse saveAnalysis(DefineAnalysisRequest request);
    public GetAnalysesResponse getAnalysesRequest(GetAnalysesRequest request);
  
    public DefinePreferenceResponse definePreferenceRequest(DefinePreferenceRequest request);
    public GetPreferenceResponse getPreferenceRequest(GetPreferenceRequest request);
    public GetSolutionResponse getSolutionRequest(GetSolutionRequest request);
    public StoreSolutionResponse storeSolutionRequest(StoreSolutionRequest request);
    
    public GetSolverStatusResponse getSolverStatus(GetSolverStatusRequest request);
    public UpdateSolverStatusResponse updateSolverStatus(UpdateSolverStatusRequest request);


}




