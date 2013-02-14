package at.ac.iiasa.ime.enrima.client.datawrapper;

import java.io.IOException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import at.ac.iiasa.ime.enrima.client.datawrapper.DataWrapperClient;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/datawrapper-applicationContext.xml")
public class CSVTest {

	@Autowired private  DataWrapperClient dataWrapperClient;
	String path = System.getProperty("user.dir");
	 @Test
	 public void storeMainSetICSVTest(){
		 /*	 
		 try {
			 dataWrapperClient.uploadMainSet(path +"/src/main/resources/mainset_I.csv", 71, 23);

		} catch (IOException e) {
			e.printStackTrace();
		}
		
	 }
	 @Test
	 public void storeMainSetKCSVTest(){
		 try {
			 dataWrapperClient.uploadMainSet(path +"/src/main/resources/mainset_K.csv", 71, 23);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	 }
	 @Test
	 public void storeMainSetTCSVTest(){
		 try {
			 dataWrapperClient.uploadMainSet(path +"/src/main/resources/mainset_T.csv", 71, 23);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	 }
	
	 @Test
	 public void storeEntityValuesCSVTest(){
		 try {
			  dataWrapperClient.uploadEntitiesValues(path +"/src/main/resources/demand.csv", 71, 23);
		} catch (IOException e) {
			e.printStackTrace();
		}
		 */
	 }
	


}
