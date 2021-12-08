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
import cn.tablego.project.springboot.model.TsysMenu;
import cn.tablego.project.springboot.model.condition.TsysMenuCondition;
import cn.tablego.project.springboot.service.TsysMenuService;

/**
 * 系统菜单表Service接口测试
 *
* @author bianj
* @version 1.0.0 2021-12-07
 */
@SpringBootTest
public class TsysMenuServiceTest {
    @Autowired
    private TsysMenuService tsysMenuService;

    /** 测试根据参数分页查询系统菜单表列表 */
    @Test
    public void testFindTsysMenuPage() {
        Instant begin = Instant.now();

        TsysMenuCondition condition = TsysMenuCondition.newInstance();

        IPage<TsysMenu> page = tsysMenuService.findTsysMenuPage(condition);
        Console.log("Records: {}", page.getRecords());

        Instant end = Instant.now();
        Console.log("代码执行消耗时间: {} 毫秒", Duration.between(begin, end).toMillis());
    }

    /** 测试根据系统菜单表ID查询系统菜单表 */
    @Test
    public void testGetTsysMenuById() {
        Instant begin = Instant.now();

        TsysMenu tsysMenu = tsysMenuService.getTsysMenuById("07483164418401403266858100493024");
        Console.log("TsysMenu: {}", tsysMenu);

        Instant end = Instant.now();
        Console.log("代码执行消耗时间: {} 毫秒", Duration.between(begin, end).toMillis());
    }

    /** 测试新增系统菜单表 */
    @Test
    public void testAddTsysMenu() {
        Instant begin = Instant.now();

        TsysMenu tsysMenu = TsysMenu.newInstance();

        boolean bool = tsysMenuService.addTsysMenu(tsysMenu);
        if (bool) {
            Console.log("新增系统菜单表成功");
        } else {
            Console.log("新增系统菜单表失败");
        }

        Instant end = Instant.now();
        Console.log("代码执行消耗时间: {} 毫秒", Duration.between(begin, end).toMillis());
    }

    /** 测试修改系统菜单表 */
    @Test
    public void testUpdateTsysMenu() {
        Instant begin = Instant.now();

        TsysMenu tsysMenu = tsysMenuService.getTsysMenuById("07483164418401403266858100493024");
        if (tsysMenu == null) {
            Console.log("系统菜单表不存在");
            return;
        }
        boolean bool = tsysMenuService.updateTsysMenu(tsysMenu);
        if (bool) {
            Console.log("修改系统菜单表成功");
        } else {
            Console.log("修改系统菜单表失败");
        }

        Instant end = Instant.now();
        Console.log("代码执行消耗时间: {} 毫秒", Duration.between(begin, end).toMillis());
    }

    /** 测试根据主键ID删除系统菜单表 */
    @Test
    public void testDeleteTsysMenuById() {
        Instant begin = Instant.now();

        boolean bool = tsysMenuService.deleteTsysMenuById("07483164418401403266858100493024");
        if (bool) {
            Console.log("删除系统菜单表成功");
        } else {
            Console.log("删除系统菜单表失败");
        }

        Instant end = Instant.now();
        Console.log("代码执行消耗时间: {} 毫秒", Duration.between(begin, end).toMillis());
    }

    /** 测试根据主键ID集合批量删除系统菜单表 */
    @Test
    public void testDeleteTsysMenuByIds() {
        Instant begin = Instant.now();

        List<String> idList = new ArrayList<>();

        boolean bool = tsysMenuService.deleteTsysMenuByIds(idList);
        if (bool) {
            Console.log("批量删除系统菜单表成功");
        } else {
            Console.log("批量删除系统菜单表失败");
        }

        Instant end = Instant.now();
        Console.log("代码执行消耗时间: {} 毫秒", Duration.between(begin, end).toMillis());
    }
}