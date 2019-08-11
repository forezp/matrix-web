package io.github.forezp.modules.task.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.forezp.common.dto.PageResultsDTO;
import io.github.forezp.common.exception.AriesException;
import io.github.forezp.common.exception.ErrorCode;
import io.github.forezp.modules.task.entity.QrtzTriggersGroup;
import io.github.forezp.modules.task.mapper.QrtzTriggersGroupMapper;
import io.github.forezp.modules.task.service.QrtzTriggersGroupService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author forezp
 * @since 2019-08-09
 */
@Service
public class QrtzTriggersGroupServiceImpl extends ServiceImpl<QrtzTriggersGroupMapper, QrtzTriggersGroup> implements QrtzTriggersGroupService {

    @Autowired
    QrtzTriggersGroupMapper qrtzTriggersGroupMapper;

    @Override
    public Boolean addTriggerGroup(String groupId, String groupName) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("group_id", groupId);
        QrtzTriggersGroup qrtzTriggersGroupDB = qrtzTriggersGroupMapper.selectOne(queryWrapper);
        if (qrtzTriggersGroupDB != null) {
            throw new AriesException(ErrorCode.INSERT_DATA_EXIST);
        }
        QrtzTriggersGroup qrtzTriggersGroup = new QrtzTriggersGroup();
        qrtzTriggersGroup.setGroupId(groupId);
        qrtzTriggersGroup.setGroupName(groupName);
        int result = qrtzTriggersGroupMapper.insert(qrtzTriggersGroup);

        return result == 1 ? true : false;
    }

    @Override
    public Boolean updateTriigerGroup(Long id, String groupName) {
        QrtzTriggersGroup qrtzTriggersGroup = new QrtzTriggersGroup();
        qrtzTriggersGroup.setId(id);
        qrtzTriggersGroup.setGroupName(groupName);
        int result = qrtzTriggersGroupMapper.updateById(qrtzTriggersGroup);
        return result == 1 ? true : false;
    }

    @Override
    public PageResultsDTO getTriggerGroupPage(int page, int pageSize, String groupId, String groupName) {
        Page<QrtzTriggersGroup> sysLogPage = new Page<>(page, pageSize);
        IPage<QrtzTriggersGroup> sysUserIPage = qrtzTriggersGroupMapper.getTriggerGroupPagelist(sysLogPage, groupId, groupName);
        PageResultsDTO result = new PageResultsDTO(page, pageSize);
        result.setTotalCount(sysUserIPage.getTotal());
        result.setTotalPage((int) sysUserIPage.getTotal(), pageSize);
        List<QrtzTriggersGroup> records = sysUserIPage.getRecords();
        result.setList(records);
        return result;
    }


}
