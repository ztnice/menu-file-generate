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
import cn.tablego.project.springboot.model.TsysRoleRight;
import cn.tablego.project.springboot.model.condition.TsysRoleRightCondition;
import cn.tablego.project.springboot.service.TsysRoleRightService;

/**
 * 角色权限授权表服务
 * 
 * @author bianj
 * @version 1.0.0 2021-12-07
 */
@Api(tags = "角色权限授权表服务")
@RestController
@RequestMapping("/tsysRoleRight")
public class TsysRoleRightController extends BaseController {
    @Autowired
    private TsysRoleRightService tsysRoleRightService;

    @ApiOperation(value = "根据条件分页查询角色权限授权表列表")
    @ApiImplicitParam(name = "condition", value = "角色权限授权表查询条件", required = true, dataType = "TsysRoleRightCondition", paramType = "body")
    @PostMapping("/findTsysRoleRightPage")
    public Paging<TsysRoleRight> findTsysRoleRightPage(@RequestBody TsysRoleRightCondition condition) {
        IPage<TsysRoleRight> page = tsysRoleRightService.findTsysRoleRightPage(condition);
        return Paging.buildPaging(page);
    }

    @ApiOperation(value = "根据主键ID查询角色权限授权表")
    @ApiImplicitParam(name = "transCode", value = "主键ID", required = true)
    @GetMapping(value = "/getTsysRoleRightById/{transCode}")
    public Result<TsysRoleRight> getTsysRoleRightById(@PathVariable String transCode) {
        TsysRoleRight tsysRoleRight = tsysRoleRightService.getTsysRoleRightById(transCode);
        return Result.ok(tsysRoleRight);
    }

    @ApiOperation(value = "新增角色权限授权表")
    @ApiImplicitParam(name = "tsysRoleRight", value = "角色权限授权表", required = true, dataType = "TsysRoleRight", paramType = "body")
    @PostMapping("/addTsysRoleRight")
    public Result<TsysRoleRight> addTsysRoleRight(@RequestBody @Valid TsysRoleRight tsysRoleRight) {
        Boolean bool = tsysRoleRightService.addTsysRoleRight(tsysRoleRight);
        if (bool) {
            return Result.ok(tsysRoleRight);
        }
        return Result.failed();
    }

    @ApiOperation(value = "修改角色权限授权表")
    @ApiImplicitParam(name = "tsysRoleRight", value = "角色权限授权表", required = true, dataType = "TsysRoleRight", paramType = "body")
    @PutMapping(value = "/updateTsysRoleRight")
    public Result<Boolean> updateTsysRoleRight(@RequestBody TsysRoleRight tsysRoleRight) {
        String transCode = tsysRoleRight.getTransCode();
        Assert.isNotBlank(transCode, "请选择需要修改的数据！");

        Boolean bool = tsysRoleRightService.updateTsysRoleRight(tsysRoleRight);
        return Result.okOrFailed(bool);
    }

    @ApiOperation(value = "根据主键ID删除角色权限授权表")
    @ApiImplicitParam(name = "transCode", value = "主键ID", required = true)
    @DeleteMapping(value = "/deleteTsysRoleRightById/{id}")
    public Result<Boolean> deleteTsysRoleRightById(@PathVariable String transCode) {
        Boolean bool = tsysRoleRightService.deleteTsysRoleRightById(transCode);
        return Result.okOrFailed(bool);
    }

    @ApiOperation(value = "根据主键ID列表批量删除角色权限授权表")
    @ApiImplicitParam(name = "idList", value = "主键ID列表", required = true, allowMultiple = true, paramType = "body")
    @DeleteMapping("/deleteTsysRoleRightByIds")
    public Result<Boolean> deleteTsysRoleRightByIds(@RequestBody List<String> idList) {
        Assert.isNotEmpty(idList, "请选择需要删除的数据！");
        Boolean bool = tsysRoleRightService.deleteTsysRoleRightByIds(idList);
        return Result.okOrFailed(bool);
    }
}