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
import cn.tablego.project.springboot.model.TsysUserRight;
import cn.tablego.project.springboot.model.condition.TsysUserRightCondition;
import cn.tablego.project.springboot.service.TsysUserRightService;

/**
 * 用户权限授权表服务
 * 
 * @author bianj
 * @version 1.0.0 2021-12-07
 */
@Api(tags = "用户权限授权表服务")
@RestController
@RequestMapping("/tsysUserRight")
public class TsysUserRightController extends BaseController {
    @Autowired
    private TsysUserRightService tsysUserRightService;

    @ApiOperation(value = "根据条件分页查询用户权限授权表列表")
    @ApiImplicitParam(name = "condition", value = "用户权限授权表查询条件", required = true, dataType = "TsysUserRightCondition", paramType = "body")
    @PostMapping("/findTsysUserRightPage")
    public Paging<TsysUserRight> findTsysUserRightPage(@RequestBody TsysUserRightCondition condition) {
        IPage<TsysUserRight> page = tsysUserRightService.findTsysUserRightPage(condition);
        return Paging.buildPaging(page);
    }

    @ApiOperation(value = "根据主键ID查询用户权限授权表")
    @ApiImplicitParam(name = "transCode", value = "主键ID", required = true)
    @GetMapping(value = "/getTsysUserRightById/{transCode}")
    public Result<TsysUserRight> getTsysUserRightById(@PathVariable String transCode) {
        TsysUserRight tsysUserRight = tsysUserRightService.getTsysUserRightById(transCode);
        return Result.ok(tsysUserRight);
    }

    @ApiOperation(value = "新增用户权限授权表")
    @ApiImplicitParam(name = "tsysUserRight", value = "用户权限授权表", required = true, dataType = "TsysUserRight", paramType = "body")
    @PostMapping("/addTsysUserRight")
    public Result<TsysUserRight> addTsysUserRight(@RequestBody @Valid TsysUserRight tsysUserRight) {
        Boolean bool = tsysUserRightService.addTsysUserRight(tsysUserRight);
        if (bool) {
            return Result.ok(tsysUserRight);
        }
        return Result.failed();
    }

    @ApiOperation(value = "修改用户权限授权表")
    @ApiImplicitParam(name = "tsysUserRight", value = "用户权限授权表", required = true, dataType = "TsysUserRight", paramType = "body")
    @PutMapping(value = "/updateTsysUserRight")
    public Result<Boolean> updateTsysUserRight(@RequestBody TsysUserRight tsysUserRight) {
        String transCode = tsysUserRight.getTransCode();
        Assert.isNotBlank(transCode, "请选择需要修改的数据！");

        Boolean bool = tsysUserRightService.updateTsysUserRight(tsysUserRight);
        return Result.okOrFailed(bool);
    }

    @ApiOperation(value = "根据主键ID删除用户权限授权表")
    @ApiImplicitParam(name = "transCode", value = "主键ID", required = true)
    @DeleteMapping(value = "/deleteTsysUserRightById/{id}")
    public Result<Boolean> deleteTsysUserRightById(@PathVariable String transCode) {
        Boolean bool = tsysUserRightService.deleteTsysUserRightById(transCode);
        return Result.okOrFailed(bool);
    }

    @ApiOperation(value = "根据主键ID列表批量删除用户权限授权表")
    @ApiImplicitParam(name = "idList", value = "主键ID列表", required = true, allowMultiple = true, paramType = "body")
    @DeleteMapping("/deleteTsysUserRightByIds")
    public Result<Boolean> deleteTsysUserRightByIds(@RequestBody List<String> idList) {
        Assert.isNotEmpty(idList, "请选择需要删除的数据！");
        Boolean bool = tsysUserRightService.deleteTsysUserRightByIds(idList);
        return Result.okOrFailed(bool);
    }
}