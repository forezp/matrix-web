import io.github.forezp.modules.system.entity.SysOrg;
import io.github.forezp.modules.system.vo.dto.SysOrgDTO;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

/**
 * @author 王文渊
 * @description: 测试类
 * @date 2022/6/22/022 15:43
 */
public class MyTest {

    @Test
    public void testCopyProperties(){
        SysOrg sysOrg = new SysOrg();
        SysOrgDTO sysOrgDTO = new SysOrgDTO();
        // 链式编程
        sysOrg.setLevel("2").setPid("2").setRemarks("测试测试");
        System.out.println("sysOrg = " + sysOrg);

        sysOrgDTO.setDepth(1);
        System.out.println("sysOrgDTO = " + sysOrgDTO);

        // 把sysOrg的属性值拷贝给sysOrgDTO
        BeanUtils.copyProperties(sysOrg, sysOrgDTO);
        System.out.println("Copy后 sysOrgDTO = " + sysOrgDTO);

    }
}
