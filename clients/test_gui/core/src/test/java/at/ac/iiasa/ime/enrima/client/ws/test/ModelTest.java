package at.ac.iiasa.ime.enrima.client.ws.test;



import java.io.File;
import java.io.IOException;

import javax.xml.transform.Source;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.oxm.XmlMappingException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.xml.transform.ResourceSource;
import org.springframework.xml.transform.StringResult;

import at.ac.iiasa.ime.enrima.client.datawrapper.DataWrapperClient;
import at.ac.iiasa.ime.enrima.client.datawrapper.SymbolicModelSpecificationHelper;
import at.ac.iiasa.ime.enrima.client.domain.GetSMSResponse;
import at.ac.iiasa.ime.enrima.client.domain.ModelSpec;
import at.ac.iiasa.ime.enrima.client.domain.SetSpec;
import at.ac.iiasa.ime.enrima.client.ws.ModelClientImpl;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/enrima-ws-client-applicationContext.xml")
public class ModelTest {

	 @Autowired private  DataWrapperClient dataWrapperClient;
	 @Autowired private  ModelClientImpl modelClient;
		private Logger logger = Logger.getLogger(this.getClass().getName());
	
	 @Test
	 public void getModel(){
		ModelSpec model = dataWrapperClient.getModelById(69);
		SetSpec setSpec = SymbolicModelSpecificationHelper.getSetSpecById(model, 365);
		
		logger.debug("--------- "+ setSpec.getId()+", name: " + setSpec.getShortName()+", type: "+setSpec.getType().name());
	 }
	
	 @Test
	 public void getXmlBasedModel()
	 {
	//	ApplicationContext appContext = new ClassPathXmlApplicationContext();
	//	Resource request= appContext.getResource("classpath:getModelRequest.xml");
	//	try {
	//		Source requestSource = new ResourceSource(request);
	 //       StringResult result = new StringResult();
	//        modelClient.getWebServiceTemplate().sendSourceAndReceiveToResult(requestSource, result);
	//        System.out.println(result.toString());
	       
	//        
	//	} catch (IOException e) {
	//		e.printStackTrace();
	//	}
	//
	 }

	 
	 @Test
	 public void generalWS()
	 {
	 //  File f = new File("C:/dev/gitrepos/enrima-gui/core/src/main/resources/getModelRequest.xml");
	//   String result =modelClient.consumeWS(f);
	//   System.out.println(result);
	 }

}
