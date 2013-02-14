package at.ac.iiasa.ime.enrima.client.datawrapper;

import java.util.ArrayList;
import java.util.List;

import at.ac.iiasa.ime.enrima.client.domain.EntityRole;
import at.ac.iiasa.ime.enrima.client.domain.EntitySpec;
import at.ac.iiasa.ime.enrima.client.domain.IteratorContainer;
import at.ac.iiasa.ime.enrima.client.domain.MemberDic;
import at.ac.iiasa.ime.enrima.client.domain.ModelSpec;
import at.ac.iiasa.ime.enrima.client.domain.SetSpec;

public class SymbolicModelSpecificationHelper {
	

	public static SetSpec getSetSpecByShortName(ModelSpec sms, String shortName) {
		for (SetSpec s : sms.getSetSpec()) {
			if (s.getShortName().equals(shortName))
				return s;
		}
		return null;
	}

	public static SetSpec getSetSpecById(ModelSpec sms, int id) {
		for (SetSpec s : sms.getSetSpec()) {
			if (s.getId() == id)
				return s;
		}
		return null;
	}
	
	
	
	public static EntitySpec getEntitySpecByShortName(ModelSpec sms,String shortName) {

		for (EntitySpec e : sms.getEntitySpec()) {
			if (e.getShortName().equals(shortName))
				return e;
		}
		return null;
	}

	public static EntitySpec getEntitySpecById(ModelSpec sms, int id) {
		for (EntitySpec e : sms.getEntitySpec()) {
			if (e.getId() == id)
				return e;
		}
		return null;
	}
	
	public static List<EntitySpec> getEntitySpecsCanbeUsedForPreference(ModelSpec sms) {
		List<EntitySpec> entities = new ArrayList<EntitySpec>();
		for (EntitySpec e : sms.getEntitySpec()) {
			if(e.getIteratorContainer()==null&&e.getRole().equals(EntityRole.A_VARIABLE))
			{
				entities.add(e);
			}
		}
		return entities;
	}

	public static String printIterators(ModelSpec sms, IteratorContainer itrContainer)
	{

		if(itrContainer==null)
			return " ";
		String idx="["; 
		boolean initItr=true;
		for(int idSetSpec:itrContainer.getIdSetSpec())
		{
			SetSpec setSpec = SymbolicModelSpecificationHelper.getSetSpecById(sms, idSetSpec);
			
			if(initItr)
			    {
				   idx +=setSpec.getShortName();
				   initItr=false;
				}
			else{
				idx += ","+ setSpec.getShortName();
			}
		}
		idx += "]";
		return idx;
	}
	
	public static String printAsTuple(List<MemberDic> members)
	{

		if(members==null||members.isEmpty())
			return " ";
		String tuple=""; 
		boolean initItr=true;
		for(MemberDic mem:members)
		{
			
			
			if(initItr)
			    {
				   tuple +=mem.getCode();
				   initItr=false;
				}
			else{
				tuple += ","+ mem.getCode();
			}
		}
		return tuple;
	}
	


}
