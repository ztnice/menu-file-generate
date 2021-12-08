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
import cn.tablego.project.springboot.model.TsysSubtrans;
import cn.tablego.project.springboot.model.condition.TsysSubtransCondition;
import cn.tablego.project.springboot.service.TsysSubtransService;

/**
 * 系统子交易表Service接口测试
 *
* @author bianj
* @version 1.0.0 2021-12-07
 */
@SpringBootTest
public class TsysSubtransServiceTest {
    @Autowired
    private TsysSubtransService tsysSubtransService;

    /** 测试根据参数分页查询系统子交易表列表 */
    @Test
    public void testFindTsysSubtransPage() {
        Instant begin = Instant.now();

        TsysSubtransCondition condition = TsysSubtransCondition.newInstance();

        IPage<TsysSubtrans> page = tsysSubtransService.findTsysSubtransPage(condition);
        Console.log("Records: {}", page.getRecords());

        Instant end = Instant.now();
        Console.log("代码执行消耗时间: {} 毫秒", Duration.between(begin, end).toMillis());
    }

    /** 测试根据系统子交易表ID查询系统子交易表 */
    @Test
    public void testGetTsysSubtransById() {
        Instant begin = Instant.now();

        TsysSubtrans tsysSubtrans = tsysSubtransService.getTsysSubtransById("36833884571656084005855980463566");
        Console.log("TsysSubtrans: {}", tsysSubtrans);

        Instant end = Instant.now();
        Console.log("代码执行消耗时间: {} 毫秒", Duration.between(begin, end).toMillis());
    }

    /** 测试新增系统子交易表 */
    @Test
    public void testAddTsysSubtrans() {
        Instant begin = Instant.now();

        TsysSubtrans tsysSubtrans = TsysSubtrans.newInstance();

        boolean bool = tsysSubtransService.addTsysSubtrans(tsysSubtrans);
        if (bool) {
            Console.log("新增系统子交易表成功");
        } else {
            Console.log("新增系统子交易表失败");
        }

        Instant end = Instant.now();
        Console.log("代码执行消耗时间: {} 毫秒", Duration.between(begin, end).toMillis());
    }

    /** 测试修改系统子交易表 */
    @Test
    public void testUpdateTsysSubtrans() {
        Instant begin = Instant.now();

        TsysSubtrans tsysSubtrans = tsysSubtransService.getTsysSubtransById("36833884571656084005855980463566");
        if (tsysSubtrans == null) {
            Console.log("系统子交易表不存在");
            return;
        }
        boolean bool = tsysSubtransService.updateTsysSubtrans(tsysSubtrans);
        if (bool) {
            Console.log("修改系统子交易表成功");
        } else {
            Console.log("修改系统子交易表失败");
        }

        Instant end = Instant.now();
        Console.log("代码执行消耗时间: {} 毫秒", Duration.between(begin, end).toMillis());
    }

    /** 测试根据主键ID删除系统子交易表 */
    @Test
    public void testDeleteTsysSubtransById() {
        Instant begin = Instant.now();

        boolean bool = tsysSubtransService.deleteTsysSubtransById("36833884571656084005855980463566");
        if (bool) {
            Console.log("删除系统子交易表成功");
        } else {
            Console.log("删除系统子交易表失败");
        }

        Instant end = Instant.now();
        Console.log("代码执行消耗时间: {} 毫秒", Duration.between(begin, end).toMillis());
    }

    /** 测试根据主键ID集合批量删除系统子交易表 */
    @Test
    public void testDeleteTsysSubtransByIds() {
        Instant begin = Instant.now();

        List<String> idList = new ArrayList<>();

        boolean bool = tsysSubtransService.deleteTsysSubtransByIds(idList);
        if (bool) {
            Console.log("批量删除系统子交易表成功");
        } else {
            Console.log("批量删除系统子交易表失败");
        }

        Instant end = Instant.now();
        Console.log("代码执行消耗时间: {} 毫秒", Duration.between(begin, end).toMillis());
    }
}