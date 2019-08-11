package io.github.forezp.modules.task.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.forezp.modules.system.entity.SysUser;
import io.github.forezp.modules.task.entity.QrtzTriggersGroup;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author forezp
 * @since 2019-08-09
 */
@Mapper
public interface QrtzTriggersGroupMapper extends BaseMapper<QrtzTriggersGroup> {


    IPage<QrtzTriggersGroup> getTriggerGroupPagelist(Page page, @Param("groupId") String groupId,
                               @Param("groupName") String groupName);

}
