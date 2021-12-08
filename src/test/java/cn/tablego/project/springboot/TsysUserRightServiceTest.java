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
import cn.tablego.project.springboot.model.TsysUserRight;
import cn.tablego.project.springboot.model.condition.TsysUserRightCondition;
import cn.tablego.project.springboot.service.TsysUserRightService;

/**
 * 用户权限授权表Service接口测试
 *
* @author bianj
* @version 1.0.0 2021-12-07
 */
@SpringBootTest
public class TsysUserRightServiceTest {
    @Autowired
    private TsysUserRightService tsysUserRightService;

    /** 测试根据参数分页查询用户权限授权表列表 */
    @Test
    public void testFindTsysUserRightPage() {
        Instant begin = Instant.now();

        TsysUserRightCondition condition = TsysUserRightCondition.newInstance();

        IPage<TsysUserRight> page = tsysUserRightService.findTsysUserRightPage(condition);
        Console.log("Records: {}", page.getRecords());

        Instant end = Instant.now();
        Console.log("代码执行消耗时间: {} 毫秒", Duration.between(begin, end).toMillis());
    }

    /** 测试根据用户权限授权表ID查询用户权限授权表 */
    @Test
    public void testGetTsysUserRightById() {
        Instant begin = Instant.now();

        TsysUserRight tsysUserRight = tsysUserRightService.getTsysUserRightById("38035164420899682304393171018555");
        Console.log("TsysUserRight: {}", tsysUserRight);

        Instant end = Instant.now();
        Console.log("代码执行消耗时间: {} 毫秒", Duration.between(begin, end).toMillis());
    }

    /** 测试新增用户权限授权表 */
    @Test
    public void testAddTsysUserRight() {
        Instant begin = Instant.now();

        TsysUserRight tsysUserRight = TsysUserRight.newInstance();

        boolean bool = tsysUserRightService.addTsysUserRight(tsysUserRight);
        if (bool) {
            Console.log("新增用户权限授权表成功");
        } else {
            Console.log("新增用户权限授权表失败");
        }

        Instant end = Instant.now();
        Console.log("代码执行消耗时间: {} 毫秒", Duration.between(begin, end).toMillis());
    }

    /** 测试修改用户权限授权表 */
    @Test
    public void testUpdateTsysUserRight() {
        Instant begin = Instant.now();

        TsysUserRight tsysUserRight = tsysUserRightService.getTsysUserRightById("38035164420899682304393171018555");
        if (tsysUserRight == null) {
            Console.log("用户权限授权表不存在");
            return;
        }
        boolean bool = tsysUserRightService.updateTsysUserRight(tsysUserRight);
        if (bool) {
            Console.log("修改用户权限授权表成功");
        } else {
            Console.log("修改用户权限授权表失败");
        }

        Instant end = Instant.now();
        Console.log("代码执行消耗时间: {} 毫秒", Duration.between(begin, end).toMillis());
    }

    /** 测试根据主键ID删除用户权限授权表 */
    @Test
    public void testDeleteTsysUserRightById() {
        Instant begin = Instant.now();

        boolean bool = tsysUserRightService.deleteTsysUserRightById("38035164420899682304393171018555");
        if (bool) {
            Console.log("删除用户权限授权表成功");
        } else {
            Console.log("删除用户权限授权表失败");
        }

        Instant end = Instant.now();
        Console.log("代码执行消耗时间: {} 毫秒", Duration.between(begin, end).toMillis());
    }

    /** 测试根据主键ID集合批量删除用户权限授权表 */
    @Test
    public void testDeleteTsysUserRightByIds() {
        Instant begin = Instant.now();

        List<String> idList = new ArrayList<>();

        boolean bool = tsysUserRightService.deleteTsysUserRightByIds(idList);
        if (bool) {
            Console.log("批量删除用户权限授权表成功");
        } else {
            Console.log("批量删除用户权限授权表失败");
        }

        Instant end = Instant.now();
        Console.log("代码执行消耗时间: {} 毫秒", Duration.between(begin, end).toMillis());
    }
}