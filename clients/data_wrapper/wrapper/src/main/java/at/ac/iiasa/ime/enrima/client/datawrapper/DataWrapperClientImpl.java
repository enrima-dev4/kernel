package at.ac.iiasa.ime.enrima.client.datawrapper;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import org.apache.log4j.Logger;
import org.springframework.ws.WebServiceMessageFactory;
import org.springframework.xml.transform.StringResult;

import at.ac.iiasa.ime.enrima.client.datawrapper.csv.CSVReader;
import at.ac.iiasa.ime.enrima.client.domain.EntitySpec;
import at.ac.iiasa.ime.enrima.client.domain.EntityValues;
import at.ac.iiasa.ime.enrima.client.domain.GetSMSRequest;
import at.ac.iiasa.ime.enrima.client.domain.GetSMSResponse;
import at.ac.iiasa.ime.enrima.client.domain.MathType;
import at.ac.iiasa.ime.enrima.client.domain.MemberDic;
import at.ac.iiasa.ime.enrima.client.domain.ModelSpec;
import at.ac.iiasa.ime.enrima.client.domain.StoreEntityValuesRequest;
import at.ac.iiasa.ime.enrima.client.domain.StoreEntityValuesResponse;
import at.ac.iiasa.ime.enrima.client.domain.StoreMainSetRequest;
import at.ac.iiasa.ime.enrima.client.domain.StoreMainSetResponse;
import at.ac.iiasa.ime.enrima.client.domain.TupleValues;
import at.ac.iiasa.ime.enrima.client.domain.Value;


public class DataWrapperClientImpl extends EnrimaClient implements DataWrapperClient {

	private Logger logger = Logger.getLogger(this.getClass().getName());
	public DataWrapperClientImpl(WebServiceMessageFactory messageFactory) {
		super(messageFactory);
	}

	@Override
	public ModelSpec getModelById(int idModel) {
		GetSMSRequest request = objectFactory.createGetSMSRequest();
		request.setIdModelSpec(idModel);
		GetSMSResponse response = (GetSMSResponse) getWebServiceTemplate()
				.marshalSendAndReceive(request);
		return response.getModelSpec();
	}

	@Override
	public String consumeWS(File xmlFile) {
		Source requestSource = new StreamSource(xmlFile);
		StringResult result = new StringResult();
		getWebServiceTemplate().sendSourceAndReceiveToResult(requestSource,
				result);
		return XMLFormatter.format(result.toString());
	}

	@Override
	public String  uploadEntitiesValues(String filePath, int idModel,
			int idModelData) throws IOException {
		ModelSpec model = getModelById(idModel);
		StoreEntityValuesRequest request = objectFactory
				.createStoreEntityValuesRequest();
		request.setIdModelSpec(idModel);
		request.setIdModeldata(idModelData);
		List<EntitySpec> entities = new ArrayList<EntitySpec>();
		List<EntityValues> entitiesValuesList = new ArrayList<EntityValues>();
		int iteratorsNum = 0;
		String[] nextLine;
		CSVReader reader = new CSVReader(new FileReader(filePath));
		boolean isHeader = true;
		while ((nextLine = reader.readNext()) != null) {
			if (isHeader) {
				for (String item : nextLine) {
					EntitySpec entityspec = SymbolicModelSpecificationHelper
							.getEntitySpecByShortName(model, item);
					if (entityspec == null) {
						iteratorsNum++;
					} else {
						entities.add(entityspec);
						EntityValues evs = objectFactory.createEntityValues();
						evs.setEntityShortName(item);
						entitiesValuesList.add(evs);
					}
				}
			} else {
				List<TupleValues> tupleValuesList = getTupleValues(nextLine, iteratorsNum,
						entities);
				int k = 0;
				for (TupleValues tv : tupleValuesList) {
					entitiesValuesList.get(k).getTupleValues().add(tv);
					k++;
				}

			}
			isHeader=false;
		}
		reader.close();
		request.getEntityValues().addAll(entitiesValuesList);
		StoreEntityValuesResponse response = (StoreEntityValuesResponse) getWebServiceTemplate()
				.marshalSendAndReceive(request);
		logger.debug("request: " + XMLFormatter.format(toXMLString(request)));
		logger.debug("response: " + XMLFormatter.format(toXMLString(response)));
		return XMLFormatter.format(toXMLString(response));
	}

	private List<TupleValues> getTupleValues(String[] line, int iterators,
			List<EntitySpec> entities) {
		List<TupleValues> lst = new ArrayList<TupleValues>();
		int i = 0;
		int j = 0;
		String tuple = "";
		for (String item : line) {
			if (i < iterators) {
				tuple = (i == 0) ? tuple + item : tuple + "," + item;
			} else {
				TupleValues tupleValues = objectFactory.createTupleValues();
				tupleValues.setTuple(tuple);
				Value v = objectFactory.createValue();
				if (entities.get(j).getMathType().equals(MathType.INTEGER)) {
					v.setIntValue(Integer.parseInt(item));

				} else {
					v.setDoubleValue(Double.parseDouble(item));
				}
				tupleValues.setValue(v);
				lst.add(tupleValues);
				j++;
			}
			i++;
		}
		return lst;
	}

	@Override
	public String uploadMainSet(String filePath, int idModel, int idModelData)
			throws IOException {
            
		StoreMainSetRequest request = objectFactory.createStoreMainSetRequest();
		request.setIdModeldata(idModelData);
		
		String[] nextLine;
		CSVReader reader = new CSVReader(new FileReader(filePath));
		boolean isHeader = true;
		while ((nextLine = reader.readNext()) != null) {
			if (isHeader) {
				logger.debug("header: " + nextLine[0]);
				request.setSetShortName(nextLine[0]);
				
			} else {
				MemberDic dic = new MemberDic();
				dic.setCode(nextLine[0]);
				dic.setDescription(nextLine[1]);
				request.getMember().add(dic);
			}
			isHeader=false;
		}
		reader.close();
		StoreMainSetResponse response = (StoreMainSetResponse) getWebServiceTemplate()
				.marshalSendAndReceive(request);
		logger.debug("request: " + XMLFormatter.format(toXMLString(request)));
		logger.debug("response: " + XMLFormatter.format(toXMLString(response)));
		 return XMLFormatter.format(toXMLString(response));
	}
}
