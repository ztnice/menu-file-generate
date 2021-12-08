package cn.tablego.project.springboot.mapper;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.tablego.project.springboot.model.condition.TsysMenuCondition;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.tablego.project.springboot.model.TsysMenu;

/**
 * 系统菜单表Mapper接口
 * 
 * @author bianj
 * @version 1.0.0 2021-12-07
 */
public interface TsysMenuMapper extends BaseMapper<TsysMenu> {
    /**
     * 根据条件分页查询系统菜单表列表
     * 
     * @param page      分页参数
     * @param condition 查询条件
     * @return 分页数据
     */
    IPage<TsysMenu> findTsysMenuPage(IPage<TsysMenu> page, @Param("condition") TsysMenuCondition condition);

}