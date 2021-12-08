package cn.tablego.project.springboot.mapper;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.tablego.project.springboot.model.condition.TsysSubtransCondition;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.tablego.project.springboot.model.TsysSubtrans;

/**
 * 系统子交易表Mapper接口
 * 
 * @author bianj
 * @version 1.0.0 2021-12-07
 */
public interface TsysSubtransMapper extends BaseMapper<TsysSubtrans> {
    /**
     * 根据条件分页查询系统子交易表列表
     * 
     * @param page      分页参数
     * @param condition 查询条件
     * @return 分页数据
     */
    IPage<TsysSubtrans> findTsysSubtransPage(IPage<TsysSubtrans> page, @Param("condition") TsysSubtransCondition condition);

}