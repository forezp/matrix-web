package io.github.forezp.modules.activiti.cmd;

import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.pvm.runtime.AtomicOperation;

import java.util.Map;

public class JumpTaskCmd implements Command<Void> {
	
	private TaskEntity taskEntity;
	private ActivityImpl targetActivity;
	protected Map<String, Object> variables;

	public JumpTaskCmd(TaskEntity taskEntity, ActivityImpl targetActivity, Map<String, Object> variables) {
		this.taskEntity = taskEntity;
		this.targetActivity = targetActivity;
		this.variables = variables;
	}

	@Override
	public Void execute(CommandContext commandContext) {
		
		if (taskEntity != null) {

			if (variables != null) {
				if (taskEntity.getExecutionId() != null) {
					taskEntity.setExecutionVariables(variables);
				} else {
					taskEntity.setVariables(variables);
				}
			}

//			// 完成活动历史
//			Context.getCommandContext().getHistoryManager()
//				.recordActivityEnd((ExecutionEntity) taskEntity.getExecution());
			
			// 完成待办任务
			Context.getCommandContext().getTaskEntityManager().deleteTask(taskEntity,
					TaskEntity.DELETE_REASON_COMPLETED, false);	// DELETE_REASON_DELETED  DELETE_REASON_COMPLETED
			
			// 跳转任务
			ExecutionEntity execution = taskEntity.getExecution();
			execution.setActivity(targetActivity);
			execution.performOperation(AtomicOperation.ACTIVITY_START);
			
		}

		return null;
	}
}