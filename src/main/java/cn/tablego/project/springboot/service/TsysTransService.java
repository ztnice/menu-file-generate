package cn.tablego.project.springboot.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.tablego.project.springboot.model.TsysTrans;
import cn.tablego.project.springboot.model.condition.TsysTransCondition;
import cn.tablego.project.springboot.mapper.TsysTransMapper;

/**
 * 系统交易表Service接口实现
 *
 * @author bianj
 * @version 1.0.0 2021-12-07
 */
@Slf4j
@Service
@Transactional(readOnly = true)
public class TsysTransService extends ServiceImpl<TsysTransMapper, TsysTrans> {
    /**
     * 根据条件分页查询系统交易表列表
     *
     * @param condition 查询条件
     * @return 分页数据
     */
    public IPage<TsysTrans> findTsysTransPage(TsysTransCondition condition) {
        IPage<TsysTrans> page = condition.buildPage();
        return this.baseMapper.findTsysTransPage(page, condition);
    }

    /**
     * 根据主键ID查询系统交易表信息
     *
     * @param transCode 主键ID
     * @return 系统交易表信息
     */
    public TsysTrans getTsysTransById(String transCode) {
        return this.getById(transCode);
    }

    /**
     * 新增系统交易表信息
     *
     * @param tsysTrans 系统交易表信息
     * @return 是否成功
     */
    @Transactional(rollbackFor = Exception.class)
    public Boolean addTsysTrans(TsysTrans tsysTrans) {
        return this.save(tsysTrans);
    }

    /**
     * 修改系统交易表信息
     *
     * @param tsysTrans 系统交易表信息
     * @return 是否成功
     */
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateTsysTrans(TsysTrans tsysTrans) {
        return this.updateById(tsysTrans);
    }

    /**
     * 根据主键ID删除系统交易表
     *
     * @param transCode 主键ID
     * @return 是否成功
     */
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteTsysTransById(String transCode) {
        return this.removeById(transCode);
    }

    /**
     * 根据主键ID列表批量删除系统交易表
     *
     * @param idList 主键ID列表
     * @return 是否成功
     */
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteTsysTransByIds(List<String> idList) {
        return this.removeByIds(idList);
    }
}