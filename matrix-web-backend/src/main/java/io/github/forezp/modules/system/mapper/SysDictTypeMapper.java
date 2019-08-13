package io.github.forezp.modules.system.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.forezp.modules.system.entity.SysDictType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.forezp.modules.system.entity.SysLog;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author forezp
 * @since 2019-08-13
 */
public interface SysDictTypeMapper extends BaseMapper<SysDictType> {
    IPage<SysDictType> selectPageSysLog(Page page, @Param("typeId") String typeId,
                                   @Param("typeName") String typeName);
}
