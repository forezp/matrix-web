package io.github.forezp.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.forezp.common.dto.PageResultsDTO;
import io.github.forezp.common.exception.AriesException;
import io.github.forezp.common.exception.ErrorCode;
import io.github.forezp.modules.system.entity.SysDict;
import io.github.forezp.modules.system.entity.SysDictType;
import io.github.forezp.modules.system.mapper.SysDictMapper;
import io.github.forezp.modules.system.mapper.SysDictTypeMapper;
import io.github.forezp.modules.system.service.SysDictService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 字典表 服务实现类
 * </p>
 *
 * @author forezp
 * @since 2019-08-13
 */
@Service
public class SysDictServiceImpl extends ServiceImpl<SysDictMapper, SysDict> implements SysDictService {

    @Autowired
    SysDictMapper sysDictMapper;

    @Override
    public Boolean addDict(SysDict sysDict) {

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("code_id", sysDict.getCodeId());
        SysDict sysDictDB = sysDictMapper.selectOne(queryWrapper);
        if (sysDictDB != null) {
            throw new AriesException(ErrorCode.INSERT_DATA_EXIST);
        }
        int result = sysDictMapper.insert(sysDict);
        return result == 1 ? true : false;
    }

    @Override
    public Boolean updateDict(Long id, SysDict sysDict) {
        sysDict.setId(id);
        int result = sysDictMapper.updateById(sysDict);
        return result == 1 ? true : false;
    }

    @Override
    public Boolean deleteDict(Long id) {
        int result = sysDictMapper.deleteById(id);
        return result == 1 ? true : false;
    }

    @Override
    public PageResultsDTO selectPage(int page, int pageSize, String codeId, String codeName, String typeId, String typeName) {
        Page<SysDict> sysDictPage = new Page<>(page, pageSize);
        IPage<SysDict> sysLogIPage = sysDictMapper.selectPage(sysDictPage, codeId, codeName, typeId, typeName);
        PageResultsDTO result = new PageResultsDTO(page, pageSize);
        result.setTotalCount(sysLogIPage.getTotal());
        result.setList(sysLogIPage.getRecords());
        result.setTotalPage((int) sysLogIPage.getTotal(), pageSize);
        return result;
    }
}
