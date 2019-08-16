package io.github.forezp.modules.activiti.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.forezp.common.dto.PageResultsDTO;
import io.github.forezp.common.exception.AriesException;
import io.github.forezp.common.exception.ErrorCode;
import io.github.forezp.modules.activiti.entity.ActModelCategory;
import io.github.forezp.modules.activiti.mapper.ActModelCategoryMapper;
import io.github.forezp.modules.activiti.service.ActModelCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.forezp.modules.task.entity.QrtzTriggersGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author forezp
 * @since 2019-08-16
 */
@Service
public class ActModelCategoryServiceImpl extends ServiceImpl<ActModelCategoryMapper, ActModelCategory> implements ActModelCategoryService {

    @Autowired
    ActModelCategoryMapper actModelCategoryMapper;


    @Override
    public Boolean addModelCategory(String categoryId, String categoryName, String pCategoryId) {

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("category_id", categoryId);
        ActModelCategory actModelCategoryDB = actModelCategoryMapper.selectOne(queryWrapper);
        if (actModelCategoryDB != null) {
            throw new AriesException(ErrorCode.INSERT_DATA_EXIST);
        }
        ActModelCategory actModelCategory = new ActModelCategory();
        actModelCategory.setCategoryId(categoryId);
        actModelCategory.setCategoryName(categoryName);
        actModelCategory.setPCategoryId(pCategoryId);
        int result = actModelCategoryMapper.insert(actModelCategory);

        return result == 1 ? true : false;

    }

    @Override
    public Boolean updateModelCategory(Long id, String categoryName) {
        ActModelCategory actModelCategory = new ActModelCategory();
        actModelCategory.setId(id);
        actModelCategory.setCategoryName(categoryName);
        int result = actModelCategoryMapper.updateById(actModelCategory);
        return result == 1 ? true : false;

    }

    @Override
    public PageResultsDTO selectPagelist(int page, int pageSize, String categoryId, String categoryName, String pCategoryId) {
        Page<ActModelCategory> sysLogPage = new Page<>(page, pageSize);
        IPage<ActModelCategory> sysUserIPage = actModelCategoryMapper.selectPagelist(sysLogPage, categoryId, categoryName,pCategoryId);
        PageResultsDTO result = new PageResultsDTO(page, pageSize);
        result.setTotalCount(sysUserIPage.getTotal());
        result.setTotalPage((int) sysUserIPage.getTotal(), pageSize);
        List<ActModelCategory> records = sysUserIPage.getRecords();
        result.setList(records);
        return result;

    }
}
