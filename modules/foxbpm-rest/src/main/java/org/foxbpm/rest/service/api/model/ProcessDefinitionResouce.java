/**
 * Copyright 1996-2014 FoxBPM ORG.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * @author ych
 */
package org.foxbpm.rest.service.api.model;

import org.foxbpm.engine.ModelService;
import org.foxbpm.engine.ProcessEngine;
import org.foxbpm.engine.ProcessEngineManagement;
import org.foxbpm.engine.repository.ProcessDefinition;
import org.foxbpm.rest.common.api.AbstractRestResource;
import org.restlet.resource.Get;

/**
 * 
 * @author ych
 */
public class ProcessDefinitionResouce extends AbstractRestResource{

	@Get
	public ProcessDefinitionResponse getProcessDefinition(){
		String processDefinitionId = getAttribute("processDefinitionId");
		if(processDefinitionId == null){
			return null;
		}
		ProcessEngine engine = ProcessEngineManagement.getDefaultProcessEngine();
		ModelService modelService =engine.getModelService();
		ProcessDefinition processEntity = modelService.getProcessDefinition(processDefinitionId);
		if(processEntity == null){
			return null;
		}
		return new ProcessDefinitionResponse(processEntity);
	}
}