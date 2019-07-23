package io.github.forezp.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.forezp.modules.system.entity.SysMenu;
import io.github.forezp.modules.system.mapper.SysMenuMapper;
import io.github.forezp.modules.system.service.SysMenuService;

import io.github.forezp.modules.system.vo.dto.SysMenuDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 * 菜单 服务实现类
 * </p>
 *
 * @author forezp
 * @since 2019-07-17
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Override
    public List<SysMenuDTO> generateMenuTree(List<SysMenu> menus) {
        if(menus==null||menus.size()==0){
            return null;
        }
        List<SysMenuDTO> result = new ArrayList<>();
        Map<String, SysMenuDTO> menuMap = new HashMap<>();
        for (SysMenu sysMenu : menus) {
            SysMenuDTO sysMenuDTO=new SysMenuDTO();
            BeanUtils.copyProperties(sysMenu,sysMenuDTO);
            menuMap.put(sysMenu.getCode(), sysMenuDTO);
        }
        Set<Map.Entry<String,SysMenuDTO>> set=menuMap.entrySet();
        for (Map.Entry<String,SysMenuDTO> entry:set){
            SysMenuDTO sysMenu= entry.getValue();
            if (sysMenu.getPcode().equals("0")){
                result.add(sysMenu);
            }else {
                SysMenuDTO parentMenu=menuMap.get(sysMenu.getPcode());
                if(parentMenu==null){
                    continue;
                }
                if(parentMenu.getChildren()==null){
                    List<SysMenuDTO> childrens=new ArrayList<>();
                    parentMenu.setChildren(childrens);
                }
                parentMenu.getChildren().add(sysMenu);
            }
        }
        sortTree(result);
        return result;
    }

    private List<SysMenuDTO> sortTree(List<SysMenuDTO> list){

        Collections.sort(list, new Comparator<SysMenuDTO>() {
            @Override
            public int compare(SysMenuDTO o1, SysMenuDTO o2) {
                return o1.getNum()-o2.getNum();
            }
        });
        for (SysMenuDTO sysMenuDTO:list){
            List<SysMenuDTO> children =sysMenuDTO.getChildren();
            if(children!=null&&children.size()>0){
                sortTree(children);
            }
        }

        return list;

    }
}
