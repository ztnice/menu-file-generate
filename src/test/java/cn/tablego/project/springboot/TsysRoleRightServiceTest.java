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
import cn.tablego.project.springboot.model.TsysRoleRight;
import cn.tablego.project.springboot.model.condition.TsysRoleRightCondition;
import cn.tablego.project.springboot.service.TsysRoleRightService;

/**
 * 角色权限授权表Service接口测试
 *
* @author bianj
* @version 1.0.0 2021-12-07
 */
@SpringBootTest
public class TsysRoleRightServiceTest {
    @Autowired
    private TsysRoleRightService tsysRoleRightService;

    /** 测试根据参数分页查询角色权限授权表列表 */
    @Test
    public void testFindTsysRoleRightPage() {
        Instant begin = Instant.now();

        TsysRoleRightCondition condition = TsysRoleRightCondition.newInstance();

        IPage<TsysRoleRight> page = tsysRoleRightService.findTsysRoleRightPage(condition);
        Console.log("Records: {}", page.getRecords());

        Instant end = Instant.now();
        Console.log("代码执行消耗时间: {} 毫秒", Duration.between(begin, end).toMillis());
    }

    /** 测试根据角色权限授权表ID查询角色权限授权表 */
    @Test
    public void testGetTsysRoleRightById() {
        Instant begin = Instant.now();

        TsysRoleRight tsysRoleRight = tsysRoleRightService.getTsysRoleRightById("20584639185787575947931294387667");
        Console.log("TsysRoleRight: {}", tsysRoleRight);

        Instant end = Instant.now();
        Console.log("代码执行消耗时间: {} 毫秒", Duration.between(begin, end).toMillis());
    }

    /** 测试新增角色权限授权表 */
    @Test
    public void testAddTsysRoleRight() {
        Instant begin = Instant.now();

        TsysRoleRight tsysRoleRight = TsysRoleRight.newInstance();

        boolean bool = tsysRoleRightService.addTsysRoleRight(tsysRoleRight);
        if (bool) {
            Console.log("新增角色权限授权表成功");
        } else {
            Console.log("新增角色权限授权表失败");
        }

        Instant end = Instant.now();
        Console.log("代码执行消耗时间: {} 毫秒", Duration.between(begin, end).toMillis());
    }

    /** 测试修改角色权限授权表 */
    @Test
    public void testUpdateTsysRoleRight() {
        Instant begin = Instant.now();

        TsysRoleRight tsysRoleRight = tsysRoleRightService.getTsysRoleRightById("20584639185787575947931294387667");
        if (tsysRoleRight == null) {
            Console.log("角色权限授权表不存在");
            return;
        }
        boolean bool = tsysRoleRightService.updateTsysRoleRight(tsysRoleRight);
        if (bool) {
            Console.log("修改角色权限授权表成功");
        } else {
            Console.log("修改角色权限授权表失败");
        }

        Instant end = Instant.now();
        Console.log("代码执行消耗时间: {} 毫秒", Duration.between(begin, end).toMillis());
    }

    /** 测试根据主键ID删除角色权限授权表 */
    @Test
    public void testDeleteTsysRoleRightById() {
        Instant begin = Instant.now();

        boolean bool = tsysRoleRightService.deleteTsysRoleRightById("20584639185787575947931294387667");
        if (bool) {
            Console.log("删除角色权限授权表成功");
        } else {
            Console.log("删除角色权限授权表失败");
        }

        Instant end = Instant.now();
        Console.log("代码执行消耗时间: {} 毫秒", Duration.between(begin, end).toMillis());
    }

    /** 测试根据主键ID集合批量删除角色权限授权表 */
    @Test
    public void testDeleteTsysRoleRightByIds() {
        Instant begin = Instant.now();

        List<String> idList = new ArrayList<>();

        boolean bool = tsysRoleRightService.deleteTsysRoleRightByIds(idList);
        if (bool) {
            Console.log("批量删除角色权限授权表成功");
        } else {
            Console.log("批量删除角色权限授权表失败");
        }

        Instant end = Instant.now();
        Console.log("代码执行消耗时间: {} 毫秒", Duration.between(begin, end).toMillis());
    }
}