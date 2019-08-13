package io.github.forezp.modules.task.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.forezp.common.dto.PageResultsDTO;
import io.github.forezp.common.exception.AriesException;
import io.github.forezp.common.util.BeanUtils;
import io.github.forezp.common.util.DateUtils;
import io.github.forezp.modules.task.entity.QrtzCronTriggers;
import io.github.forezp.modules.task.entity.QrtzTriggersInfo;
import io.github.forezp.modules.task.job.BaseJob;
import io.github.forezp.modules.task.mapper.QrtzCronTriggersMapper;
import io.github.forezp.modules.task.mapper.QrtzTriggersInfoMapper;
import io.github.forezp.modules.task.service.QrtzCronTriggersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.forezp.modules.task.vo.dto.QrtzCronTriggersDTO;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static io.github.forezp.common.constant.enums.TaskStatus.*;
import static io.github.forezp.common.exception.ErrorCode.ADD_TASK_FAIL;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author forezp
 * @since 2019-08-09
 */
@Service
@Slf4j
public class QrtzCronTriggersServiceImpl extends ServiceImpl<QrtzCronTriggersMapper, QrtzCronTriggers> implements QrtzCronTriggersService {

    /**
     * 加入Qulifier注解，通过名称注入bean
     */
    @Autowired
    @Qualifier("Scheduler")
    private Scheduler scheduler;

    @Autowired
    QrtzTriggersInfoMapper qrtzTriggersInfoMapper;

    @Autowired
    QrtzCronTriggersMapper qrtzCronTriggersMapper;

    @Override
    public void addTask(String taskName, String taskClassName, String taskGroupId, String cronExpression) throws Exception {

        // 启动调度器
        scheduler.start();
        //构建job信息
        JobDetail jobDetail = JobBuilder.newJob(getClass(taskClassName).getClass()).withIdentity(taskClassName, taskGroupId).build();

        //表达式调度构建器(即任务执行的时间)
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
        //按新的cronExpression表达式构建一个新的trigger
        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(taskClassName, taskGroupId).withSchedule(scheduleBuilder).build();
        try {
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            log.info("创建定时任务失败" + e);
            throw new AriesException(ADD_TASK_FAIL);
        }
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("trigger_name", taskClassName);
        queryWrapper.eq("group_id", taskGroupId);
        QrtzTriggersInfo qrtzTriggersInfoDb = qrtzTriggersInfoMapper.selectOne(queryWrapper);
        if (qrtzTriggersInfoDb == null) {
            QrtzTriggersInfo qrtzTriggersInfo = new QrtzTriggersInfo();
            qrtzTriggersInfo.setTriggerName(taskClassName);
            qrtzTriggersInfo.setTriggerSimpleName(taskName);
            qrtzTriggersInfo.setGroupId(taskGroupId);
            qrtzTriggersInfo.setStatus(CREATED.getStatus());
            qrtzTriggersInfoMapper.insert(qrtzTriggersInfo);
        } else {
            qrtzTriggersInfoDb.setTriggerName(taskClassName);
            qrtzTriggersInfoDb.setTriggerSimpleName(taskName);
            qrtzTriggersInfoDb.setGroupId(taskGroupId);
            qrtzTriggersInfoDb.setStatus(CREATED.getStatus());
            qrtzTriggersInfoMapper.updateById(qrtzTriggersInfoDb);
        }
    }

    @Override
    public void pauseTask(String taskClassName, String taskGroupId) throws Exception {
        scheduler.pauseJob(JobKey.jobKey(taskClassName, taskGroupId));

        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("trigger_name", taskClassName);
        updateWrapper.eq("group_id", taskGroupId);
        QrtzTriggersInfo qrtzTriggersInfo = new QrtzTriggersInfo();
        qrtzTriggersInfo.setTriggerName(taskClassName);
        qrtzTriggersInfo.setGroupId(taskGroupId);
        qrtzTriggersInfo.setStatus(PAUSED.getStatus());
        qrtzTriggersInfoMapper.update(qrtzTriggersInfo, updateWrapper);
    }

    @Override
    public void resumeTask(String taskClassName, String taskGroupId) throws Exception {
        scheduler.resumeJob(JobKey.jobKey(taskClassName, taskGroupId));

        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("trigger_name", taskClassName);
        updateWrapper.eq("group_id", taskGroupId);
        QrtzTriggersInfo qrtzTriggersInfo = new QrtzTriggersInfo();
        qrtzTriggersInfo.setTriggerName(taskClassName);
        qrtzTriggersInfo.setGroupId(taskGroupId);
        qrtzTriggersInfo.setStatus(RESUME.getStatus());
        qrtzTriggersInfoMapper.update(qrtzTriggersInfo, updateWrapper);
    }

    @Override
    public void updateTask(String taskName, String taskClassName, String taskGroupId, String cronExpression) throws Exception {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(taskClassName, taskGroupId);
            // 表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);

            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

            // 按新的cronExpression表达式重新构建trigger
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

            // 按新的trigger重新设置job执行
            scheduler.rescheduleJob(triggerKey, trigger);
        } catch (SchedulerException e) {
            log.info("更新定时任务失败" + e);
            throw new Exception("更新定时任务失败");
        }
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("trigger_name", taskClassName);
        updateWrapper.eq("group_id", taskGroupId);
        QrtzTriggersInfo qrtzTriggersInfo = new QrtzTriggersInfo();
        qrtzTriggersInfo.setTriggerSimpleName(taskName);
        qrtzTriggersInfo.setTriggerName(taskClassName);
        qrtzTriggersInfo.setGroupId(taskGroupId);
        qrtzTriggersInfo.setStatus(UPDATED.getStatus());
        qrtzTriggersInfoMapper.update(qrtzTriggersInfo, updateWrapper);
    }

    @Override
    public void deletTask(String taskClassName, String taskGroupId) throws Exception {
        scheduler.pauseTrigger(TriggerKey.triggerKey(taskClassName, taskGroupId));
        scheduler.unscheduleJob(TriggerKey.triggerKey(taskClassName, taskGroupId));
        scheduler.deleteJob(JobKey.jobKey(taskClassName, taskGroupId));

        QueryWrapper updateWrapper = new QueryWrapper();
        updateWrapper.eq("trigger_name", taskClassName);
        updateWrapper.eq("group_id", taskGroupId);
        QrtzTriggersInfo qrtzTriggersInfo = new QrtzTriggersInfo();
        qrtzTriggersInfo.setTriggerName(taskClassName);
        qrtzTriggersInfo.setGroupId(taskGroupId);
        qrtzTriggersInfo.setStatus(DELETED.getStatus());
        qrtzTriggersInfoMapper.update(qrtzTriggersInfo, updateWrapper);
    }

    @Override
    public PageResultsDTO getTriggersPage(int page, int pageSize, String groupId, String triggerName) {
        Page<QrtzCronTriggers> sysLogPage = new Page<>(page, pageSize);
        IPage<QrtzCronTriggers> sysUserIPage = qrtzCronTriggersMapper.getQrtzCronTriggersPagelist(sysLogPage, groupId, triggerName);
        PageResultsDTO result = new PageResultsDTO(page, pageSize);
        result.setTotalCount(sysUserIPage.getTotal());
        result.setTotalPage((int) sysUserIPage.getTotal(), pageSize);
        List<QrtzCronTriggers> records = sysUserIPage.getRecords();
        List<QrtzCronTriggersDTO> qrtzCronTriggersDTOList=new ArrayList<>();
        for (QrtzCronTriggers qrtzCronTriggers:records){
            QrtzCronTriggersDTO dto=new QrtzCronTriggersDTO();
            BeanUtils.copy(qrtzCronTriggers,dto);
            dto.setNextFireTimeStr(DateUtils.convert(new Date(dto.getNextFireTime())));
            dto.setPrevFireTimeStr(DateUtils.convert(new Date(dto.getPrevFireTime())));
            qrtzCronTriggersDTOList.add(dto);
        }
        result.setList(qrtzCronTriggersDTOList);
        return result;
    }

    private BaseJob getClass(String classname) throws Exception {
        Class<?> class1 = Class.forName(classname);
        return (BaseJob) class1.newInstance();
    }
}
