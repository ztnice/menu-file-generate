package cn.tablego.project.springboot.mapper;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.tablego.project.springboot.model.condition.TsysTransCondition;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.tablego.project.springboot.model.TsysTrans;

/**
 * 系统交易表Mapper接口
 * 
 * @author bianj
 * @version 1.0.0 2021-12-07
 */
public interface TsysTransMapper extends BaseMapper<TsysTrans> {
    /**
     * 根据条件分页查询系统交易表列表
     * 
     * @param page      分页参数
     * @param condition 查询条件
     * @return 分页数据
     */
    IPage<TsysTrans> findTsysTransPage(IPage<TsysTrans> page, @Param("condition") TsysTransCondition condition);

}