package org.linkworld.ocean.dao;

import org.apache.ibatis.annotations.Mapper;
import org.linkworld.ocean.pojo.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 系统用户 Mapper 接口
 * </p>
 *
 * @author LiuXiangCheng
 * @since 2021-11-05
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

}
