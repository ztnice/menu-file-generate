package cn.tablego.project.springboot.mapper;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.tablego.project.springboot.model.condition.TsysUserRightCondition;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.tablego.project.springboot.model.TsysUserRight;

/**
 * 用户权限授权表Mapper接口
 * 
 * @author bianj
 * @version 1.0.0 2021-12-07
 */
public interface TsysUserRightMapper extends BaseMapper<TsysUserRight> {
    /**
     * 根据条件分页查询用户权限授权表列表
     * 
     * @param page      分页参数
     * @param condition 查询条件
     * @return 分页数据
     */
    IPage<TsysUserRight> findTsysUserRightPage(IPage<TsysUserRight> page, @Param("condition") TsysUserRightCondition condition);

}