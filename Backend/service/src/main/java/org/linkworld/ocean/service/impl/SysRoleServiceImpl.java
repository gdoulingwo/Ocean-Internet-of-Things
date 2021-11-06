package org.linkworld.ocean.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.linkworld.ocean.mapper.SysRoleMapper;
import org.linkworld.ocean.model.pojo.SysRole;
import org.linkworld.ocean.service.SysRoleService;
import org.springframework.stereotype.Service;

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

    public void hello() {
        System.out.println("hello");
    }

}
