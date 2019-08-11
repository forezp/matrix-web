package io.github.forezp.modules.task.service;

import io.github.forezp.common.dto.PageResultsDTO;
import io.github.forezp.modules.task.entity.QrtzTriggersGroup;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author forezp
 * @since 2019-08-09
 */
public interface QrtzTriggersGroupService extends IService<QrtzTriggersGroup> {


    Boolean addTriggerGroup(String groupId,String groupName);

    Boolean updateTriigerGroup(Long id,String groupName);

    PageResultsDTO getTriggerGroupPage(int page, int pageSize, String groupId, String groupName);

}
