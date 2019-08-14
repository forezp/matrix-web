package io.github.forezp.modules.system.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.forezp.modules.system.entity.SysDict;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.forezp.modules.system.entity.SysDictType;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 字典表 Mapper 接口
 * </p>
 *
 * @author forezp
 * @since 2019-08-13
 */
public interface SysDictMapper extends BaseMapper<SysDict> {
    IPage<SysDict> selectPage(Page page, @Param("codeId")String codeId,
                              @Param("codeName") String codeName,
                              @Param("typeId") String typeId,
                              @Param("typeName") String typeName);
}
