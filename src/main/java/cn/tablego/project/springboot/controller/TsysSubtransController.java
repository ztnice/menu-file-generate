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
import cn.tablego.project.springboot.model.TsysSubtrans;
import cn.tablego.project.springboot.model.condition.TsysSubtransCondition;
import cn.tablego.project.springboot.service.TsysSubtransService;

/**
 * 系统子交易表服务
 * 
 * @author bianj
 * @version 1.0.0 2021-12-07
 */
@Api(tags = "系统子交易表服务")
@RestController
@RequestMapping("/tsysSubtrans")
public class TsysSubtransController extends BaseController {
    @Autowired
    private TsysSubtransService tsysSubtransService;

    @ApiOperation(value = "根据条件分页查询系统子交易表列表")
    @ApiImplicitParam(name = "condition", value = "系统子交易表查询条件", required = true, dataType = "TsysSubtransCondition", paramType = "body")
    @PostMapping("/findTsysSubtransPage")
    public Paging<TsysSubtrans> findTsysSubtransPage(@RequestBody TsysSubtransCondition condition) {
        IPage<TsysSubtrans> page = tsysSubtransService.findTsysSubtransPage(condition);
        return Paging.buildPaging(page);
    }

    @ApiOperation(value = "根据主键ID查询系统子交易表")
    @ApiImplicitParam(name = "transCode", value = "主键ID", required = true)
    @GetMapping(value = "/getTsysSubtransById/{transCode}")
    public Result<TsysSubtrans> getTsysSubtransById(@PathVariable String transCode) {
        TsysSubtrans tsysSubtrans = tsysSubtransService.getTsysSubtransById(transCode);
        return Result.ok(tsysSubtrans);
    }

    @ApiOperation(value = "新增系统子交易表")
    @ApiImplicitParam(name = "tsysSubtrans", value = "系统子交易表", required = true, dataType = "TsysSubtrans", paramType = "body")
    @PostMapping("/addTsysSubtrans")
    public Result<TsysSubtrans> addTsysSubtrans(@RequestBody @Valid TsysSubtrans tsysSubtrans) {
        Boolean bool = tsysSubtransService.addTsysSubtrans(tsysSubtrans);
        if (bool) {
            return Result.ok(tsysSubtrans);
        }
        return Result.failed();
    }

    @ApiOperation(value = "修改系统子交易表")
    @ApiImplicitParam(name = "tsysSubtrans", value = "系统子交易表", required = true, dataType = "TsysSubtrans", paramType = "body")
    @PutMapping(value = "/updateTsysSubtrans")
    public Result<Boolean> updateTsysSubtrans(@RequestBody TsysSubtrans tsysSubtrans) {
        String transCode = tsysSubtrans.getTransCode();
        Assert.isNotBlank(transCode, "请选择需要修改的数据！");

        Boolean bool = tsysSubtransService.updateTsysSubtrans(tsysSubtrans);
        return Result.okOrFailed(bool);
    }

    @ApiOperation(value = "根据主键ID删除系统子交易表")
    @ApiImplicitParam(name = "transCode", value = "主键ID", required = true)
    @DeleteMapping(value = "/deleteTsysSubtransById/{id}")
    public Result<Boolean> deleteTsysSubtransById(@PathVariable String transCode) {
        Boolean bool = tsysSubtransService.deleteTsysSubtransById(transCode);
        return Result.okOrFailed(bool);
    }

    @ApiOperation(value = "根据主键ID列表批量删除系统子交易表")
    @ApiImplicitParam(name = "idList", value = "主键ID列表", required = true, allowMultiple = true, paramType = "body")
    @DeleteMapping("/deleteTsysSubtransByIds")
    public Result<Boolean> deleteTsysSubtransByIds(@RequestBody List<String> idList) {
        Assert.isNotEmpty(idList, "请选择需要删除的数据！");
        Boolean bool = tsysSubtransService.deleteTsysSubtransByIds(idList);
        return Result.okOrFailed(bool);
    }
}