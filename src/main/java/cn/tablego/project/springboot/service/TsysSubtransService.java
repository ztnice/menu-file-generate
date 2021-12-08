package cn.tablego.project.springboot.service;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.tablego.project.springboot.model.TsysSubtrans;
import cn.tablego.project.springboot.model.condition.TsysSubtransCondition;
import cn.tablego.project.springboot.mapper.TsysSubtransMapper;

/**
 * 系统子交易表Service接口实现
 *
 * @author bianj
 * @version 1.0.0 2021-12-07
 */
@Slf4j
@Service
@Transactional(readOnly = true)
public class TsysSubtransService extends ServiceImpl<TsysSubtransMapper, TsysSubtrans> {
    /**
     * 根据条件分页查询系统子交易表列表
     *
     * @param condition 查询条件
     * @return 分页数据
     */
    public IPage<TsysSubtrans> findTsysSubtransPage(TsysSubtransCondition condition) {
        IPage<TsysSubtrans> page = condition.buildPage();
        return this.baseMapper.findTsysSubtransPage(page, condition);
    }

    /**
     * 根据主键ID查询系统子交易表信息
     *
     * @param transCode 主键ID
     * @return 系统子交易表信息
     */
    public TsysSubtrans getTsysSubtransById(String transCode) {
        return this.getById(transCode);
    }


    public List<TsysSubtrans> listTsysSubtrans(String transCode) {
        QueryWrapper<TsysSubtrans> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("TRANS_CODE",transCode);
        return baseMapper.selectList(queryWrapper);
    }
    /**
     * 新增系统子交易表信息
     *
     * @param tsysSubtrans 系统子交易表信息
     * @return 是否成功
     */
    @Transactional(rollbackFor = Exception.class)
    public Boolean addTsysSubtrans(TsysSubtrans tsysSubtrans) {
        return this.save(tsysSubtrans);
    }

    /**
     * 修改系统子交易表信息
     *
     * @param tsysSubtrans 系统子交易表信息
     * @return 是否成功
     */
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateTsysSubtrans(TsysSubtrans tsysSubtrans) {
        return this.updateById(tsysSubtrans);
    }

    /**
     * 根据主键ID删除系统子交易表
     *
     * @param transCode 主键ID
     * @return 是否成功
     */
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteTsysSubtransById(String transCode) {
        return this.removeById(transCode);
    }

    /**
     * 根据主键ID列表批量删除系统子交易表
     *
     * @param idList 主键ID列表
     * @return 是否成功
     */
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteTsysSubtransByIds(List<String> idList) {
        return this.removeByIds(idList);
    }
}