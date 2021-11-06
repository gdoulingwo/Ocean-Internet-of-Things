package org.linkworld.ocean.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.linkworld.ocean.mapper.SysRoleMapper;
import org.linkworld.ocean.model.pojo.SysRole;
import org.linkworld.ocean.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author LiuXiangCheng
 * @since 2021-11-05
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {
    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public Integer insertSysRole(SysRole sysRole) {
        return sysRoleMapper.insert(sysRole);
    }

    @Override
    public Integer deleteSysRoleById(int sysRoleId) {
        return sysRoleMapper.deleteById(sysRoleId);
    }

    @Override
    public Integer updateSysRoleById(SysRole sysRole) {
        return sysRoleMapper.updateById(sysRole);
    }

    @Override
    public SysRole selectSysRoleById(int sysRoleId) {
        return sysRoleMapper.selectById(sysRoleId);
    }

    @Override
    public List<SysRole> selectSysRoleList() {
        return sysRoleMapper.selectList(null);
    }

    @Override
    public Integer selectSysRolesCount() {
        return sysRoleMapper.selectCount(null);
    }
}
