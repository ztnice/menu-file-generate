package cn.tablego.project.springboot.service;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.tablego.project.springboot.model.TsysRoleRight;
import cn.tablego.project.springboot.model.condition.TsysRoleRightCondition;
import cn.tablego.project.springboot.mapper.TsysRoleRightMapper;

/**
 * 角色权限授权表Service接口实现
 *
 * @author bianj
 * @version 1.0.0 2021-12-07
 */
@Slf4j
@Service
@Transactional(readOnly = true)
public class TsysRoleRightService extends ServiceImpl<TsysRoleRightMapper, TsysRoleRight> {
    /**
     * 根据条件分页查询角色权限授权表列表
     *
     * @param condition 查询条件
     * @return 分页数据
     */
    public IPage<TsysRoleRight> findTsysRoleRightPage(TsysRoleRightCondition condition) {
        IPage<TsysRoleRight> page = condition.buildPage();
        return this.baseMapper.findTsysRoleRightPage(page, condition);
    }


    public List<TsysRoleRight> listRoleRights(String transCode) {
        QueryWrapper<TsysRoleRight> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("TRANS_CODE",transCode);
        queryWrapper.eq("ROLE_CODE","admin");
        return baseMapper.selectList(queryWrapper);
    }
    /**
     * 根据主键ID查询角色权限授权表信息
     *
     * @param transCode 主键ID
     * @return 角色权限授权表信息
     */
    public TsysRoleRight getTsysRoleRightById(String transCode) {
        return this.getById(transCode);
    }

    /**
     * 新增角色权限授权表信息
     *
     * @param tsysRoleRight 角色权限授权表信息
     * @return 是否成功
     */
    @Transactional(rollbackFor = Exception.class)
    public Boolean addTsysRoleRight(TsysRoleRight tsysRoleRight) {
        return this.save(tsysRoleRight);
    }

    /**
     * 修改角色权限授权表信息
     *
     * @param tsysRoleRight 角色权限授权表信息
     * @return 是否成功
     */
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateTsysRoleRight(TsysRoleRight tsysRoleRight) {
        return this.updateById(tsysRoleRight);
    }

    /**
     * 根据主键ID删除角色权限授权表
     *
     * @param transCode 主键ID
     * @return 是否成功
     */
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteTsysRoleRightById(String transCode) {
        return this.removeById(transCode);
    }

    /**
     * 根据主键ID列表批量删除角色权限授权表
     *
     * @param idList 主键ID列表
     * @return 是否成功
     */
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteTsysRoleRightByIds(List<String> idList) {
        return this.removeByIds(idList);
    }
}