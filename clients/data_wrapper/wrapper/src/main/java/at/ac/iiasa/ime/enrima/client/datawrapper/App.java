package at.ac.iiasa.ime.enrima.client.datawrapper;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext(
				"classpath:/datawrapper-applicationContext.xml");
		try {
			// Get command line arguments
			if (args.length != 4)
				throw new Exception(
						"USAGE: java -jar enrimaDataWrapper.jar mainset|entity csvFilePath modelId dataId");
			DataWrapperClient client = ac.getBean("dataWrapperClient",
					DataWrapperClient.class);
			String type = args[0];
			String csvFilePath = args[1];
			int idModel = Integer.parseInt(args[2]);
			int idModelData = Integer.parseInt(args[3]);
			if (type.equalsIgnoreCase("mainset")) {
			    client.uploadMainSet(csvFilePath, idModel,
						idModelData);
			} else if (type.equalsIgnoreCase("entity")) {
				 client.uploadEntitiesValues(csvFilePath,
						idModel, idModelData);

			} else {
				throw new Exception("the argument '" + args[0]
						+ "' is unknown, it should be either mainset or entity");
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ac.close();
		}
	}
}
