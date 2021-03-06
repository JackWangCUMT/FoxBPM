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
package org.foxbpm.engine.impl.cmd;

import java.io.Serializable;

import org.foxbpm.engine.impl.Context;
import org.foxbpm.engine.impl.entity.ProcessInstanceEntity;
import org.foxbpm.engine.impl.entity.TaskEntity;
import org.foxbpm.engine.impl.interceptor.Command;
import org.foxbpm.engine.impl.interceptor.CommandContext;
import org.foxbpm.engine.impl.util.ClockUtil;
import org.foxbpm.engine.impl.util.ExceptionUtil;

public abstract class NeedsActiveTaskCmd<T> implements Command<T>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected String taskId;
	public NeedsActiveTaskCmd(String taskId) {
		this.taskId = taskId;
	}

	public T execute(CommandContext commandContext) {
		if (taskId == null) {
			throw ExceptionUtil.getException("10601201");
		}
		TaskEntity task = Context.getCommandContext().getTaskManager().findTaskById(taskId);
		if (task == null) {
			throw ExceptionUtil.getException("10602201");
		}
		if(task.hasEnded()){
			throw ExceptionUtil.getException("10603203");
		}
		if (task.isSuspended()) {
			throw ExceptionUtil.getException("10603202");
		}
		//增加流程修改时间
		ProcessInstanceEntity processInstance = task.getProcessInstance();
		if(processInstance != null){
			processInstance.setUpdateTime(ClockUtil.getCurrentTime());
		}
		
		return execute(commandContext, task);
	}

	/** 子类需要实现这个方法 */
	protected abstract T execute(CommandContext commandContext, TaskEntity task);

}
