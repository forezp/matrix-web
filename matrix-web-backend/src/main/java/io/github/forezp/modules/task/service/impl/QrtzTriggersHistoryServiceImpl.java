package io.github.forezp.modules.task.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.forezp.common.dto.PageResultsDTO;
import io.github.forezp.common.util.BeanUtils;
import io.github.forezp.common.util.DateUtils;
import io.github.forezp.modules.task.entity.QrtzCronTriggers;
import io.github.forezp.modules.task.entity.QrtzTriggersHistory;
import io.github.forezp.modules.task.mapper.QrtzTriggersHistoryMapper;
import io.github.forezp.modules.task.service.QrtzTriggersHistoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.forezp.modules.task.vo.dto.QrtzCronTriggersDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author forezp
 * @since 2019-08-09
 */
@Service
public class QrtzTriggersHistoryServiceImpl extends ServiceImpl<QrtzTriggersHistoryMapper, QrtzTriggersHistory> implements QrtzTriggersHistoryService {

    @Autowired
    QrtzTriggersHistoryMapper qrtzTriggersHistoryMapper;

    @Override
    public PageResultsDTO getTriggersPage(int page, int pageSize, String groupId, String triggerName) {
        Page<QrtzTriggersHistory> sysLogPage = new Page<>(page, pageSize);
        IPage<QrtzTriggersHistory> sysUserIPage = qrtzTriggersHistoryMapper.getQrtzCronTriggersPagelist(sysLogPage, groupId, triggerName);
        PageResultsDTO result = new PageResultsDTO(page, pageSize);
        result.setTotalCount(sysUserIPage.getTotal());
        result.setTotalPage((int) sysUserIPage.getTotal(), pageSize);
        List<QrtzTriggersHistory> records = sysUserIPage.getRecords();
        result.setList(records);
        return result;
    }

}
