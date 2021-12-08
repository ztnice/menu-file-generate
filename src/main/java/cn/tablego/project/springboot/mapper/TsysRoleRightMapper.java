package cn.tablego.project.springboot.mapper;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.tablego.project.springboot.model.condition.TsysRoleRightCondition;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.tablego.project.springboot.model.TsysRoleRight;

/**
 * 角色权限授权表Mapper接口
 * 
 * @author bianj
 * @version 1.0.0 2021-12-07
 */
public interface TsysRoleRightMapper extends BaseMapper<TsysRoleRight> {
    /**
     * 根据条件分页查询角色权限授权表列表
     * 
     * @param page      分页参数
     * @param condition 查询条件
     * @return 分页数据
     */
    IPage<TsysRoleRight> findTsysRoleRightPage(IPage<TsysRoleRight> page, @Param("condition") TsysRoleRightCondition condition);

}