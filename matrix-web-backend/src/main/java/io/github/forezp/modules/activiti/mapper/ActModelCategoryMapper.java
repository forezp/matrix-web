package io.github.forezp.modules.activiti.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.forezp.modules.activiti.entity.ActModelCategory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author forezp
 * @since 2019-08-16
 */
public interface ActModelCategoryMapper extends BaseMapper<ActModelCategory> {

    IPage<ActModelCategory> selectPagelist(Page page, @Param("categoryId") String categoryId,
                                           @Param("categoryName") String categoryName,
                                           @Param("pCategoryId")String pCategoryId);
}
