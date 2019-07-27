package io.github.forezp.modules.system.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.forezp.modules.system.entity.SysLog;
import io.github.forezp.modules.system.entity.SysLoginLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author forezp
 * @since 2019-07-27
 */
@Mapper
public interface SysLoginLogMapper extends BaseMapper<SysLoginLog> {

    IPage<SysLoginLog> selectPageSysLog(Page page,
                                   @Param("userId") String userId,
                                   @Param("beginTime") String beginTime,
                                   @Param("endTime") String endTime);

}
