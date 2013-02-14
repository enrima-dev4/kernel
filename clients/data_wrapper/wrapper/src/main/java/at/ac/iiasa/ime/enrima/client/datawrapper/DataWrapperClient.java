package at.ac.iiasa.ime.enrima.client.datawrapper;

import java.io.File;
import java.io.IOException;
import at.ac.iiasa.ime.enrima.client.domain.ModelSpec;

public interface DataWrapperClient {
    public ModelSpec getModelById(int idModel);
    public String consumeWS(File xmlFile);
    public String uploadEntitiesValues(String filePath, int idModel,int idModelData)throws IOException;
    public String uploadMainSet(String filePath, int idModel,int idModelData)throws IOException;
}




