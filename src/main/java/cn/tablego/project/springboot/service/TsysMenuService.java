package cn.tablego.project.springboot.service;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.tablego.project.springboot.model.TsysMenu;
import cn.tablego.project.springboot.model.condition.TsysMenuCondition;
import cn.tablego.project.springboot.mapper.TsysMenuMapper;

/**
 * 系统菜单表Service接口实现
 *
 * @author bianj
 * @version 1.0.0 2021-12-07
 */
@Slf4j
@Service
@Transactional(readOnly = true)
public class TsysMenuService extends ServiceImpl<TsysMenuMapper, TsysMenu> {
    /**
     * 根据条件分页查询系统菜单表列表
     *
     * @param condition 查询条件
     * @return 分页数据
     */
    public IPage<TsysMenu> findTsysMenuPage(TsysMenuCondition condition) {
        IPage<TsysMenu> page = condition.buildPage();
        return this.baseMapper.findTsysMenuPage(page, condition);
    }


    public List<TsysMenu> findTsysMenu(QueryWrapper<TsysMenu> queryWrapper){

        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 根据主键ID查询系统菜单表信息
     *
     * @param menuCode 主键ID
     * @return 系统菜单表信息
     */
    public TsysMenu getTsysMenuById(String menuCode) {
        return this.getById(menuCode);
    }

    /**
     * 新增系统菜单表信息
     *
     * @param tsysMenu 系统菜单表信息
     * @return 是否成功
     */
    @Transactional(rollbackFor = Exception.class)
    public Boolean addTsysMenu(TsysMenu tsysMenu) {
        return this.save(tsysMenu);
    }

    /**
     * 修改系统菜单表信息
     *
     * @param tsysMenu 系统菜单表信息
     * @return 是否成功
     */
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateTsysMenu(TsysMenu tsysMenu) {
        return this.updateById(tsysMenu);
    }

    /**
     * 根据主键ID删除系统菜单表
     *
     * @param menuCode 主键ID
     * @return 是否成功
     */
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteTsysMenuById(String menuCode) {
        return this.removeById(menuCode);
    }

    /**
     * 根据主键ID列表批量删除系统菜单表
     *
     * @param idList 主键ID列表
     * @return 是否成功
     */
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteTsysMenuByIds(List<String> idList) {
        return this.removeByIds(idList);
    }
}