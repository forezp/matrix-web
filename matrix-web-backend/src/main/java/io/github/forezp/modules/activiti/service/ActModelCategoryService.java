package io.github.forezp.modules.activiti.service;

import io.github.forezp.common.dto.PageResultsDTO;
import io.github.forezp.modules.activiti.entity.ActModelCategory;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author forezp
 * @since 2019-08-16
 */
public interface ActModelCategoryService extends IService<ActModelCategory> {

    Boolean addModelCategory(String categoryId,String categoryName,String pCategoryId);

    Boolean updateModelCategory(Long id,String categoryName);

    PageResultsDTO selectPagelist(int page, int pageSize, String categoryId, String categoryName,String pCategoryId);
}
