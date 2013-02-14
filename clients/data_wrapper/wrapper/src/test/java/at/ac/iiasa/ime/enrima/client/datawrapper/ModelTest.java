package at.ac.iiasa.ime.enrima.client.datawrapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import at.ac.iiasa.ime.enrima.client.datawrapper.DataWrapperClient;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/datawrapper-applicationContext.xml")
public class ModelTest {

	 @Autowired private  DataWrapperClient dataWrapperClient;
	 @Test
	 public void getModel(){
		dataWrapperClient.getModelById(71);
	 }

}
