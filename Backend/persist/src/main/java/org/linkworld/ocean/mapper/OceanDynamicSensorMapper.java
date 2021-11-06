package org.linkworld.ocean.mapper;



import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.linkworld.ocean.model.pojo.OceanDynamicSensor;


/**
 * <p>
 * 动态的传感器，例如船舶中的GPS Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2021-11-04
 */
@Mapper
public interface OceanDynamicSensorMapper extends BaseMapper<OceanDynamicSensor> {

}
