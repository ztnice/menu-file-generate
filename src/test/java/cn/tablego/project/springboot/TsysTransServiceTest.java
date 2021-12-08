package cn.tablego.project.springboot;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.baomidou.mybatisplus.core.metadata.IPage;

import cn.hutool.core.lang.Console;
import cn.tablego.project.springboot.model.TsysTrans;
import cn.tablego.project.springboot.model.condition.TsysTransCondition;
import cn.tablego.project.springboot.service.TsysTransService;

/**
 * 系统交易表Service接口测试
 *
* @author bianj
* @version 1.0.0 2021-12-07
 */
@SpringBootTest
public class TsysTransServiceTest {
    @Autowired
    private TsysTransService tsysTransService;

    /** 测试根据参数分页查询系统交易表列表 */
    @Test
    public void testFindTsysTransPage() {
        Instant begin = Instant.now();

        TsysTransCondition condition = TsysTransCondition.newInstance();

        IPage<TsysTrans> page = tsysTransService.findTsysTransPage(condition);
        Console.log("Records: {}", page.getRecords());

        Instant end = Instant.now();
        Console.log("代码执行消耗时间: {} 毫秒", Duration.between(begin, end).toMillis());
    }

    /** 测试根据系统交易表ID查询系统交易表 */
    @Test
    public void testGetTsysTransById() {
        Instant begin = Instant.now();

        TsysTrans tsysTrans = tsysTransService.getTsysTransById("18142085541495554490106070549789");
        Console.log("TsysTrans: {}", tsysTrans);

        Instant end = Instant.now();
        Console.log("代码执行消耗时间: {} 毫秒", Duration.between(begin, end).toMillis());
    }

    /** 测试新增系统交易表 */
    @Test
    public void testAddTsysTrans() {
        Instant begin = Instant.now();

        TsysTrans tsysTrans = TsysTrans.newInstance();

        boolean bool = tsysTransService.addTsysTrans(tsysTrans);
        if (bool) {
            Console.log("新增系统交易表成功");
        } else {
            Console.log("新增系统交易表失败");
        }

        Instant end = Instant.now();
        Console.log("代码执行消耗时间: {} 毫秒", Duration.between(begin, end).toMillis());
    }

    /** 测试修改系统交易表 */
    @Test
    public void testUpdateTsysTrans() {
        Instant begin = Instant.now();

        TsysTrans tsysTrans = tsysTransService.getTsysTransById("18142085541495554490106070549789");
        if (tsysTrans == null) {
            Console.log("系统交易表不存在");
            return;
        }
        boolean bool = tsysTransService.updateTsysTrans(tsysTrans);
        if (bool) {
            Console.log("修改系统交易表成功");
        } else {
            Console.log("修改系统交易表失败");
        }

        Instant end = Instant.now();
        Console.log("代码执行消耗时间: {} 毫秒", Duration.between(begin, end).toMillis());
    }

    /** 测试根据主键ID删除系统交易表 */
    @Test
    public void testDeleteTsysTransById() {
        Instant begin = Instant.now();

        boolean bool = tsysTransService.deleteTsysTransById("18142085541495554490106070549789");
        if (bool) {
            Console.log("删除系统交易表成功");
        } else {
            Console.log("删除系统交易表失败");
        }

        Instant end = Instant.now();
        Console.log("代码执行消耗时间: {} 毫秒", Duration.between(begin, end).toMillis());
    }

    /** 测试根据主键ID集合批量删除系统交易表 */
    @Test
    public void testDeleteTsysTransByIds() {
        Instant begin = Instant.now();

        List<String> idList = new ArrayList<>();

        boolean bool = tsysTransService.deleteTsysTransByIds(idList);
        if (bool) {
            Console.log("批量删除系统交易表成功");
        } else {
            Console.log("批量删除系统交易表失败");
        }

        Instant end = Instant.now();
        Console.log("代码执行消耗时间: {} 毫秒", Duration.between(begin, end).toMillis());
    }
}