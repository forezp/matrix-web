package io.github.forezp.modules.system.service;

import io.github.forezp.common.dto.PageResultsDTO;
import io.github.forezp.modules.system.entity.SysDict;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 字典表 服务类
 * </p>
 *
 * @author forezp
 * @since 2019-08-13
 */
public interface SysDictService extends IService<SysDict> {

    Boolean addDict(SysDict sysDict);

    Boolean updateDict(Long id, SysDict sysDict);

    Boolean deleteDict(Long id);

    PageResultsDTO selectPage(int page, int pageSize, String codeId, String codeName, String typeId, String typeName);
}
