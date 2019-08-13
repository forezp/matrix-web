package io.github.forezp.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.forezp.common.dto.PageResultsDTO;
import io.github.forezp.common.exception.AriesException;
import io.github.forezp.common.exception.ErrorCode;
import io.github.forezp.modules.system.entity.SysDictType;
import io.github.forezp.modules.system.mapper.SysDictTypeMapper;
import io.github.forezp.modules.system.service.SysDictTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author forezp
 * @since 2019-08-13
 */
@Service
public class SysDictTypeServiceImpl extends ServiceImpl<SysDictTypeMapper, SysDictType> implements SysDictTypeService {

    @Autowired
    SysDictTypeMapper sysDictTypeMapper;

    @Override
    public Boolean addDictType(String typeId, String typeName, String typeDescribe, String remarks) {
        SysDictType sysDictType = new SysDictType();
        sysDictType.setRemarks(remarks);
        sysDictType.setTypeDescribe(typeDescribe);
        sysDictType.setTypeId(typeId);
        sysDictType.setTypeName(typeName);

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("type_id", typeId);
        SysDictType sysDictTypeDB = sysDictTypeMapper.selectOne(queryWrapper);
        if (sysDictTypeDB != null) {
            throw new AriesException(ErrorCode.INSERT_DATA_EXIST);
        }
        int result = sysDictTypeMapper.insert(sysDictType);
        return result == 1 ? true : false;
    }

    @Override
    public Boolean editDictType(Long id, String typeName, String typeDescribe, String remarks) {
        SysDictType sysDictType = new SysDictType();
        sysDictType.setId(id);
        sysDictType.setRemarks(remarks);
        sysDictType.setTypeDescribe(typeDescribe);
        sysDictType.setTypeName(typeName);
        int result = sysDictTypeMapper.updateById(sysDictType);
        return result == 1 ? true : false;
    }

    @Override
    public Boolean deleteDictType(Long id) {
        int result = sysDictTypeMapper.deleteById(id);
        return result == 1 ? true : false;
    }

    @Override
    public PageResultsDTO selectPageSysLog(int page, int pageSize, String typeId, String typeName) {
        Page<SysDictType> sysLogPage = new Page<>(page, pageSize);
        IPage<SysDictType> sysLogIPage = sysDictTypeMapper.selectPageSysLog(sysLogPage, typeId, typeName);
        PageResultsDTO result = new PageResultsDTO(page, pageSize);
        result.setTotalCount(sysLogIPage.getTotal());
        result.setList(sysLogIPage.getRecords());
        result.setTotalPage((int) sysLogIPage.getTotal(),pageSize);
        return result;
    }
}
