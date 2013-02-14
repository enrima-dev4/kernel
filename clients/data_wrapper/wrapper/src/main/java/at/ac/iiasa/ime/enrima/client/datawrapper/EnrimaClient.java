package at.ac.iiasa.ime.enrima.client.datawrapper;

import java.io.IOException;

import org.springframework.oxm.XmlMappingException;
import org.springframework.ws.WebServiceMessageFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.xml.transform.StringResult;

import at.ac.iiasa.ime.enrima.client.domain.ObjectFactory;


public abstract class EnrimaClient extends WebServiceGatewaySupport {

	public ObjectFactory objectFactory = new ObjectFactory();
	
	public EnrimaClient(WebServiceMessageFactory messageFactory) {
		super(messageFactory);
	}

	public String toXMLString(Object o) {
		StringResult result = new StringResult();
		try {
			getMarshaller().marshal(o, result);
		} catch (XmlMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result.toString();
	}

}
