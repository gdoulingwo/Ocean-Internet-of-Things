package org.linkworld.ocean.service;


import com.baomidou.mybatisplus.extension.service.IService;
import io.swagger.models.auth.In;
import org.linkworld.ocean.model.pojo.SysRole;
import org.linkworld.ocean.model.pojo.SysUser;

import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author LiuXiangCheng
 * @since 2021-11-05
 */
public interface SysRoleService extends IService<SysRole> {
    // 增加一个角色
    public Integer insertSysRole(SysRole sysRole);
    // 根据id删除一个角色
    public Integer deleteSysRoleById(int sysRoleId);
    // 修改一个角色的信息
    public Integer updateSysRoleById(SysRole sysRole);
    // 通过id查询一个角色
    public SysRole selectSysRoleById(int sysRoleId);
    // 查询多个角色的信息
    public List<SysRole> selectSysRoleList();
    // 查询总角色个数
    public Integer selectSysRolesCount();
}
