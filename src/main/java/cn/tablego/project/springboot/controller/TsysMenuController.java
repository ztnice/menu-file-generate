package cn.tablego.project.springboot.controller;

import java.util.List;
import javax.validation.Valid;

import cn.hutool.core.util.StrUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import cn.hutool.core.collection.CollUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;

import cn.tablego.project.springboot.common.controller.BaseController;
import cn.tablego.project.springboot.common.model.Paging;
import cn.tablego.project.springboot.common.model.Result;
import cn.tablego.project.springboot.common.util.Assert;
import cn.tablego.project.springboot.model.TsysMenu;
import cn.tablego.project.springboot.model.condition.TsysMenuCondition;
import cn.tablego.project.springboot.service.TsysMenuService;

/**
 * 系统菜单表服务
 * 
 * @author bianj
 * @version 1.0.0 2021-12-07
 */
@Api(tags = "系统菜单表服务")
@RestController
@RequestMapping("/tsysMenu")
public class TsysMenuController extends BaseController {
    @Autowired
    private TsysMenuService tsysMenuService;

    @ApiOperation(value = "根据条件分页查询系统菜单表列表")
    @ApiImplicitParam(name = "condition", value = "系统菜单表查询条件", required = true, dataType = "TsysMenuCondition", paramType = "body")
    @PostMapping("/findTsysMenuPage")
    public Paging<TsysMenu> findTsysMenuPage(@RequestBody TsysMenuCondition condition) {
        IPage<TsysMenu> page = tsysMenuService.findTsysMenuPage(condition);
        return Paging.buildPaging(page);
    }

    @ApiOperation(value = "根据主键ID查询系统菜单表")
    @ApiImplicitParam(name = "menuCode", value = "主键ID", required = true)
    @GetMapping(value = "/getTsysMenuById/{menuCode}")
    public Result<TsysMenu> getTsysMenuById(@PathVariable String menuCode) {
        TsysMenu tsysMenu = tsysMenuService.getTsysMenuById(menuCode);
        return Result.ok(tsysMenu);
    }

    @ApiOperation(value = "新增系统菜单表")
    @ApiImplicitParam(name = "tsysMenu", value = "系统菜单表", required = true, dataType = "TsysMenu", paramType = "body")
    @PostMapping("/addTsysMenu")
    public Result<TsysMenu> addTsysMenu(@RequestBody @Valid TsysMenu tsysMenu) {
        Boolean bool = tsysMenuService.addTsysMenu(tsysMenu);
        if (bool) {
            return Result.ok(tsysMenu);
        }
        return Result.failed();
    }

    @ApiOperation(value = "修改系统菜单表")
    @ApiImplicitParam(name = "tsysMenu", value = "系统菜单表", required = true, dataType = "TsysMenu", paramType = "body")
    @PutMapping(value = "/updateTsysMenu")
    public Result<Boolean> updateTsysMenu(@RequestBody TsysMenu tsysMenu) {
        String menuCode = tsysMenu.getMenuCode();
        Assert.isNotBlank(menuCode, "请选择需要修改的数据！");

        Boolean bool = tsysMenuService.updateTsysMenu(tsysMenu);
        return Result.okOrFailed(bool);
    }

    @ApiOperation(value = "根据主键ID删除系统菜单表")
    @ApiImplicitParam(name = "menuCode", value = "主键ID", required = true)
    @DeleteMapping(value = "/deleteTsysMenuById/{id}")
    public Result<Boolean> deleteTsysMenuById(@PathVariable String menuCode) {
        Boolean bool = tsysMenuService.deleteTsysMenuById(menuCode);
        return Result.okOrFailed(bool);
    }

    @ApiOperation(value = "根据主键ID列表批量删除系统菜单表")
    @ApiImplicitParam(name = "idList", value = "主键ID列表", required = true, allowMultiple = true, paramType = "body")
    @DeleteMapping("/deleteTsysMenuByIds")
    public Result<Boolean> deleteTsysMenuByIds(@RequestBody List<String> idList) {
        Assert.isNotEmpty(idList, "请选择需要删除的数据！");
        Boolean bool = tsysMenuService.deleteTsysMenuByIds(idList);
        return Result.okOrFailed(bool);
    }
}