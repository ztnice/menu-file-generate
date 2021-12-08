package cn.tablego.project.springboot.controller;

import cn.tablego.project.springboot.common.controller.BaseController;
import cn.tablego.project.springboot.common.model.Result;
import cn.tablego.project.springboot.common.util.Assert;
import cn.tablego.project.springboot.service.SqlGenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author haozt
 * @date 2021/12/7
 */
@RestController
@RequestMapping("/sql")
public class MenuSqlGenerateController extends BaseController {


    @Autowired
    private SqlGenService sqlGenService ;

    @GetMapping("/gen/{menuCodes}")
    public Result<Boolean> genSqlFile(@PathVariable String menuCodes) {
        Assert.isNotEmpty(menuCodes, "请选择需要生成的菜单编号！");

        Boolean bool = sqlGenService.genSqlFile(menuCodes);

        return Result.okOrFailed(bool);
    }


    @GetMapping("/genChildren/{menuCode}")
    public Result<Boolean> genChildrenSqlFile(@PathVariable String menuCode) {
        Assert.isNotEmpty(menuCode, "请选择需要生成的菜单编号！");

        Boolean bool = sqlGenService.genChildrenSqlFile(menuCode);
        return Result.okOrFailed(bool);
    }


    @GetMapping("/delMenu/{menuCodes}")
    public Result<Boolean> genDelMenuSqlFile(@PathVariable String menuCodes) {
        Assert.isNotEmpty(menuCodes, "请选择需要生成的菜单编号！");

        Boolean bool = sqlGenService.genDelSqlFile(menuCodes);
        return Result.okOrFailed(bool);
    }


    @GetMapping("/delChildrenMenu/{menuCode}")
    public Result<Boolean> genDelChildrenMenuSqlFile(@PathVariable String menuCode) {
        Assert.isNotEmpty(menuCode, "请选择需要生成的菜单编号！");
        Boolean bool = sqlGenService.genDelChildrenSqlFile(menuCode);
        return Result.okOrFailed(bool);
    }

}
