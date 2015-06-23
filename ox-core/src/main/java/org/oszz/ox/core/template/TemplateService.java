package org.oszz.ox.core.template;

import java.util.List;
import java.util.Map;

public class TemplateService implements ITemplateService {
	
	private ITemplateDataClassHolder tempDataCalssHolder;
	
	
	public TemplateService(ITemplateDataClassHolder tempDataCalssHolder){
		this.tempDataCalssHolder = tempDataCalssHolder;
	}

	@Override
	public boolean create() {
		return true;
	}

	@Override
	public boolean init() {
		tempDataCalssHolder.init();
		return true;
	}

	@Override
	public boolean start() {
		List<Class<? extends ITemplateData>> allTempDataClasses = tempDataCalssHolder.getAllTemplateClass();
		for(Class<? extends ITemplateData> tempDataClazz : allTempDataClasses){
			
		}
		
		return true;
	}

	@Override
	public boolean restart() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean stop() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <T extends ITemplateData> T get(int id, Class<T> clazz) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends ITemplateData> Map<Integer, T> getAll(Class<T> clazz) {
		// TODO Auto-generated method stub
		return null;
	}

}
