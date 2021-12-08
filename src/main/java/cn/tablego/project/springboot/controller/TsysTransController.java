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
import cn.tablego.project.springboot.model.TsysTrans;
import cn.tablego.project.springboot.model.condition.TsysTransCondition;
import cn.tablego.project.springboot.service.TsysTransService;

/**
 * 系统交易表服务
 * 
 * @author bianj
 * @version 1.0.0 2021-12-07
 */
@Api(tags = "系统交易表服务")
@RestController
@RequestMapping("/tsysTrans")
public class TsysTransController extends BaseController {
    @Autowired
    private TsysTransService tsysTransService;

    @ApiOperation(value = "根据条件分页查询系统交易表列表")
    @ApiImplicitParam(name = "condition", value = "系统交易表查询条件", required = true, dataType = "TsysTransCondition", paramType = "body")
    @PostMapping("/findTsysTransPage")
    public Paging<TsysTrans> findTsysTransPage(@RequestBody TsysTransCondition condition) {
        IPage<TsysTrans> page = tsysTransService.findTsysTransPage(condition);
        return Paging.buildPaging(page);
    }

    @ApiOperation(value = "根据主键ID查询系统交易表")
    @ApiImplicitParam(name = "transCode", value = "主键ID", required = true)
    @GetMapping(value = "/getTsysTransById/{transCode}")
    public Result<TsysTrans> getTsysTransById(@PathVariable String transCode) {
        TsysTrans tsysTrans = tsysTransService.getTsysTransById(transCode);
        return Result.ok(tsysTrans);
    }

    @ApiOperation(value = "新增系统交易表")
    @ApiImplicitParam(name = "tsysTrans", value = "系统交易表", required = true, dataType = "TsysTrans", paramType = "body")
    @PostMapping("/addTsysTrans")
    public Result<TsysTrans> addTsysTrans(@RequestBody @Valid TsysTrans tsysTrans) {
        Boolean bool = tsysTransService.addTsysTrans(tsysTrans);
        if (bool) {
            return Result.ok(tsysTrans);
        }
        return Result.failed();
    }

    @ApiOperation(value = "修改系统交易表")
    @ApiImplicitParam(name = "tsysTrans", value = "系统交易表", required = true, dataType = "TsysTrans", paramType = "body")
    @PutMapping(value = "/updateTsysTrans")
    public Result<Boolean> updateTsysTrans(@RequestBody TsysTrans tsysTrans) {
        String transCode = tsysTrans.getTransCode();
        Assert.isNotBlank(transCode, "请选择需要修改的数据！");

        Boolean bool = tsysTransService.updateTsysTrans(tsysTrans);
        return Result.okOrFailed(bool);
    }

    @ApiOperation(value = "根据主键ID删除系统交易表")
    @ApiImplicitParam(name = "transCode", value = "主键ID", required = true)
    @DeleteMapping(value = "/deleteTsysTransById/{id}")
    public Result<Boolean> deleteTsysTransById(@PathVariable String transCode) {
        Boolean bool = tsysTransService.deleteTsysTransById(transCode);
        return Result.okOrFailed(bool);
    }

    @ApiOperation(value = "根据主键ID列表批量删除系统交易表")
    @ApiImplicitParam(name = "idList", value = "主键ID列表", required = true, allowMultiple = true, paramType = "body")
    @DeleteMapping("/deleteTsysTransByIds")
    public Result<Boolean> deleteTsysTransByIds(@RequestBody List<String> idList) {
        Assert.isNotEmpty(idList, "请选择需要删除的数据！");
        Boolean bool = tsysTransService.deleteTsysTransByIds(idList);
        return Result.okOrFailed(bool);
    }
}