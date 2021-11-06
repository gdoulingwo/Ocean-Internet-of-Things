package org.linkworld.ocean.service;


import com.baomidou.mybatisplus.extension.service.IService;
import org.linkworld.ocean.model.pojo.SysUser;

import java.util.List;

/**
 * <p>
 * 系统用户 服务类
 * </p>
 *
 * @author LiuXiangCheng
 * @since 2021-11-05
 */
public interface SysUserService extends IService<SysUser> {
    // 增加一个用户
    public Integer insertSysUser(SysUser sysUser);
    // 根据删除一个用户
    public Integer deleteSysUserById(Integer sysUserId);
    // 修改一个用户的信息
    public Integer updateSysUserById(SysUser sysUser);
    // 通过id查询一个用户
    public SysUser querySysUserById(Integer sysUserId);
    // 查询多个用户的信息
    public List<SysUser> querySysUserList();
    // 查询总用户个数
    public Integer queryAllSysUsersCount();
}
