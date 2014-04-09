package org.foxbpm.kernel.event;

import java.util.List;

import org.foxbpm.kernel.process.KernelException;
import org.foxbpm.kernel.process.impl.KernelFlowElementsContainerImpl;
import org.foxbpm.kernel.runtime.InterpretableExecutionContext;

public abstract class AbstractKernelEvent implements KernelEvent {

	public boolean isAsync(InterpretableExecutionContext executionContext) {
		// TODO Auto-generated method stub
		return false;
	}

	public void execute(InterpretableExecutionContext executionContext) {
	
		KernelFlowElementsContainerImpl container = getContainer(executionContext);		
		List<KernelListener> kernelListeners = container.getKernelListeners(getEventName());
		
		int kernelListenerIndex = executionContext.getKernelListenerIndex();

		if (kernelListeners.size() > kernelListenerIndex) {
			executionContext.setEventName(getEventName());
			executionContext.setEventSource(container);
			KernelListener listener = kernelListeners.get(kernelListenerIndex);
			try {
				listener.notify(executionContext);
			} catch (RuntimeException e) {
				throw e;
			} catch (Exception e) {
				throw new KernelException("不能执行事件监听 : " + e.getMessage(), e);
			}
			executionContext.setKernelListenerIndex(kernelListenerIndex + 1);
			executionContext.fireEvent(this);

		} else {
			executionContext.setKernelListenerIndex(0);
			executionContext.setEventName(null);
			executionContext.setEventSource(null);

			eventNotificationsCompleted(executionContext);
		}

	}

	protected abstract KernelFlowElementsContainerImpl getContainer(InterpretableExecutionContext executionContext);

	protected abstract String getEventName();

	protected abstract void eventNotificationsCompleted(InterpretableExecutionContext executionContext);

}
