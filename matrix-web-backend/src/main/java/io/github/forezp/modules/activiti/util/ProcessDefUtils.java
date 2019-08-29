package io.github.forezp.modules.activiti.util;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.bpmn.behavior.UserTaskActivityBehavior;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.el.FixedValue;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.task.TaskDefinition;
import org.apache.commons.lang3.reflect.FieldUtils;
import java.util.LinkedHashSet;
import java.util.Set;

@Slf4j
public abstract class ProcessDefUtils {
	
	public static ActivityImpl getActivity(ProcessEngine processEngine, String processDefId, String activityId) {
		ProcessDefinitionEntity pde = getProcessDefinition(processEngine, processDefId);
		return pde.findActivity(activityId);
	}

	public static ProcessDefinitionEntity getProcessDefinition(ProcessEngine processEngine, String processDefId) {
		return (ProcessDefinitionEntity) ((RepositoryServiceImpl) processEngine.getRepositoryService()).getDeployedProcessDefinition(processDefId);
	}

	public static void grantPermission(ActivityImpl activity, String assigneeExpression, String candidateGroupIdExpressions,
                                       String candidateUserIdExpressions) throws Exception {
		TaskDefinition taskDefinition = ((UserTaskActivityBehavior) activity.getActivityBehavior()).getTaskDefinition();
		taskDefinition.setAssigneeExpression(assigneeExpression == null ? null : new FixedValue(assigneeExpression));
		FieldUtils.writeField(taskDefinition, "candidateUserIdExpressions", ExpressionUtils.stringToExpressionSet(candidateUserIdExpressions), true);
		FieldUtils
				.writeField(taskDefinition, "candidateGroupIdExpressions", ExpressionUtils.stringToExpressionSet(candidateGroupIdExpressions), true);

		log.info(
				String.format("granting previledges for [%s, %s, %s] on [%s, %s]", assigneeExpression, candidateGroupIdExpressions,
						candidateUserIdExpressions, activity.getProcessDefinition().getKey(), activity.getProperty("name")));
	}

	/**
	 * 实现常见类型的expression的包装和转换
	 * 
	 * @author bluejoe2008@gmail.com
	 * 
	 */
	public static class ExpressionUtils {
		public static Expression stringToExpression(ProcessEngineConfigurationImpl conf, String expr) {
			return conf.getExpressionManager().createExpression(expr);
		}

		public static Expression stringToExpression(String expr) {
			return new FixedValue(expr);
		}

		public static Set<Expression> stringToExpressionSet(String exprs) {
			Set<Expression> set = new LinkedHashSet<Expression>();
			for (String expr : exprs.split(";")) {
				set.add(stringToExpression(expr));
			}

			return set;
		}
	}
}