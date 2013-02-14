package at.ac.iiasa.ime.jira;

import java.net.URL;
import java.rmi.RemoteException;
import javax.xml.rpc.ServiceException;

import at.ac.iiasa.ime.jira.soap.JiraSoapService;
import at.ac.iiasa.ime.jira.soap.JiraSoapServiceService;
import at.ac.iiasa.ime.jira.soap.JiraSoapServiceServiceLocator;



public class SOAPSession
{
    private JiraSoapServiceService jiraSoapServiceLocator;
    private JiraSoapService jiraSoapService;
    private String token;

    public SOAPSession(URL webServicePort)
    {
        jiraSoapServiceLocator = new JiraSoapServiceServiceLocator();
        try
        {
            if (webServicePort == null)
            {
                jiraSoapService = jiraSoapServiceLocator.getJirasoapserviceV2();
            }
            else
            {
                jiraSoapService = jiraSoapServiceLocator.getJirasoapserviceV2(webServicePort);
            }
        }
        catch (ServiceException e)
        {
            throw new RuntimeException("ServiceException during SOAPClient contruction", e);
        }
    }

    public SOAPSession()
    {
        this(null);
    }

    public void connect(String userName, String password) throws RemoteException
    {
        token = getJiraSoapService().login(userName, password);
        
    }

    public void disConnect(String userName, String password) throws RemoteException
    {
       getJiraSoapService().logout(token);
        
    }
    
    public String getAuthenticationToken()
    {
        return token;
    }

    public JiraSoapService getJiraSoapService()
    {
        return jiraSoapService;
    }

    public JiraSoapServiceService getJiraSoapServiceLocator()
    {
        return jiraSoapServiceLocator;
    }
}
