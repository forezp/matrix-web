package io.github.forezp.modules.task.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.forezp.modules.task.entity.QrtzCronTriggers;
import io.github.forezp.modules.task.entity.QrtzTriggersHistory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author forezp
 * @since 2019-08-09
 */
public interface QrtzTriggersHistoryMapper extends BaseMapper<QrtzTriggersHistory> {

    IPage<QrtzTriggersHistory> getQrtzCronTriggersPagelist(Page page, @Param("groupId") String groupId,
                                                        @Param("triggerName") String triggerName);
}
