package org.linkworld.ocean.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.linkworld.ocean.model.pojo.SysUser;

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
