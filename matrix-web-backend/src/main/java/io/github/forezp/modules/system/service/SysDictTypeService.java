package io.github.forezp.modules.system.service;

import io.github.forezp.common.dto.PageResultsDTO;
import io.github.forezp.modules.system.entity.SysDictType;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author forezp
 * @since 2019-08-13
 */
public interface SysDictTypeService extends IService<SysDictType> {

    Boolean addDictType(String typeId, String typeName, String typeDescribe, String remarks);

    Boolean editDictType(Long id, String typeName, String typeDescribe, String remarks);

    Boolean deleteDictType(Long id);

    PageResultsDTO selectPageSysLog(int page, int pageSize, String typeId, String typeName);

}
