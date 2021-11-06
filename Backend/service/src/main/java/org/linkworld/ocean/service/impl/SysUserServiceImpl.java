package org.linkworld.ocean.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.linkworld.ocean.mapper.SysUserMapper;
import org.linkworld.ocean.model.pojo.SysUser;
import org.linkworld.ocean.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 系统用户 服务实现类
 * </p>
 *
 * @author LiuXiangCheng
 * @since 2021-11-05
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public Integer insertSysUser(SysUser sysUser) {
        return sysUserMapper.insert(sysUser);
    }

    @Override
    public Integer deleteSysUserById(Integer sysUserId) {
        return sysUserMapper.deleteById(sysUserId);
    }

    @Override
    public Integer updateSysUserById(SysUser sysUser) {
        return sysUserMapper.updateById(sysUser);
    }

    @Override
    public SysUser querySysUserById(Integer sysUserId) {
        return sysUserMapper.selectById(sysUserId);
    }

    @Override
    public List<SysUser> querySysUserList() {
        return sysUserMapper.selectList(null);
    }

    @Override
    public Integer queryAllSysUsersCount() {
        return sysUserMapper.selectCount(null);
    }
}
