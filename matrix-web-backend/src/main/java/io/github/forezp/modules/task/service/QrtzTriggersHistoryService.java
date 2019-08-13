package io.github.forezp.modules.task.service;

import io.github.forezp.common.dto.PageResultsDTO;
import io.github.forezp.modules.task.entity.QrtzTriggersHistory;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author forezp
 * @since 2019-08-09
 */
public interface QrtzTriggersHistoryService extends IService<QrtzTriggersHistory> {

    PageResultsDTO getTriggersPage(int page, int pageSize, String groupId, String triggerName);
}
