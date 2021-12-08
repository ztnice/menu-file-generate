package cn.tablego.project.springboot.service;

import java.util.List;

import cn.tablego.project.springboot.model.TsysRoleRight;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.tablego.project.springboot.model.TsysUserRight;
import cn.tablego.project.springboot.model.condition.TsysUserRightCondition;
import cn.tablego.project.springboot.mapper.TsysUserRightMapper;

/**
 * 用户权限授权表Service接口实现
 *
 * @author bianj
 * @version 1.0.0 2021-12-07
 */
@Slf4j
@Service
@Transactional(readOnly = true)
public class TsysUserRightService extends ServiceImpl<TsysUserRightMapper, TsysUserRight> {
    /**
     * 根据条件分页查询用户权限授权表列表
     *
     * @param condition 查询条件
     * @return 分页数据
     */
    public IPage<TsysUserRight> findTsysUserRightPage(TsysUserRightCondition condition) {
        IPage<TsysUserRight> page = condition.buildPage();
        return this.baseMapper.findTsysUserRightPage(page, condition);
    }

    public List<TsysUserRight> listUserRights(String transCode) {
        QueryWrapper<TsysUserRight> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("TRANS_CODE",transCode);
        queryWrapper.eq("USER_ID","admin");
        return baseMapper.selectList(queryWrapper);
    }

    /**
     * 根据主键ID查询用户权限授权表信息
     *
     * @param transCode 主键ID
     * @return 用户权限授权表信息
     */
    public TsysUserRight getTsysUserRightById(String transCode) {
        return this.getById(transCode);
    }

    /**
     * 新增用户权限授权表信息
     *
     * @param tsysUserRight 用户权限授权表信息
     * @return 是否成功
     */
    @Transactional(rollbackFor = Exception.class)
    public Boolean addTsysUserRight(TsysUserRight tsysUserRight) {
        return this.save(tsysUserRight);
    }

    /**
     * 修改用户权限授权表信息
     *
     * @param tsysUserRight 用户权限授权表信息
     * @return 是否成功
     */
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateTsysUserRight(TsysUserRight tsysUserRight) {
        return this.updateById(tsysUserRight);
    }

    /**
     * 根据主键ID删除用户权限授权表
     *
     * @param transCode 主键ID
     * @return 是否成功
     */
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteTsysUserRightById(String transCode) {
        return this.removeById(transCode);
    }

    /**
     * 根据主键ID列表批量删除用户权限授权表
     *
     * @param idList 主键ID列表
     * @return 是否成功
     */
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteTsysUserRightByIds(List<String> idList) {
        return this.removeByIds(idList);
    }
}