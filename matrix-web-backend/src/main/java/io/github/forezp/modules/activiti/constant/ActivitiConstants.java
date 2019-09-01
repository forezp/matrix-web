package io.github.forezp.modules.activiti.constant;

/**
 * @author: xufei.
 * @createTime: 2017/8/23.
 */
public class ActivitiConstants {
    /**
     * 任务状态
     */
    public static final String TASK_STATUS_TODO = "todo";
    public static final String TASK_STATUS_CLAIM = "claim";
    public static final String TASK_STATUS_FINISH = "finish";

    /**
     * 审批结果
     */
    public static final String TASK_AUDIT_DISAGREE = "0";
    public static final String TASK_AUDIT_AGREE = "1";
    public static final String TASK_AUDIT_BACK = "2";

    /**
     * 流程节点类型
     */
    public static final String ACTIVITI_TYPE_START_EVENT = "startEvent";
    public static final String ACTIVITI_TYPE_END_EVENT = "endEvent";
    public static final String ACTIVITI_TYPE_USER_TASK = "userTask";

    /**
     * 通用审批单变量名称
     */
    public static final String APPROVE_RESULT = "approved";
    public static final String APPROVE_SUGGESTION = "suggestion";

    /**
     * 流程节点状态
     */
    public static final String STATE_DONE = "已执行";
    public static final String STATE_DOING = "待执行";
    public static final String STATE_TODO = "未执行";

    /**
     * 流程状态
     */
    public static final String PROCESS_INSTANCE_ING = "审批中";
    public static final String PROCESS_INSTANCE_DONE = "已结束";

    /**
     * 流程线上的判断
     */
    public static final String CONDITION_TEXT_PASSED = "${pass==1}";
    public static final String CONDITION_TEXT_REFUSE = "${pass==0}";
    public static final String CONDITION_TEXT_BACK = "${pass==2}";

    public static final String APPROVED_PASSED="1";//通过
    public static final String APPROVED_REJECT="0";//拒绝
    public static final String[] APPROVED_PASSED_TEXT={"同意","通过","批准","提交"};
    public static final String[] APPROVED_REJECT_TEXT={"拒绝","不同意","不批准","终止"};
}
