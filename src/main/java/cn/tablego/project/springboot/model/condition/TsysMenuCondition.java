package cn.tablego.project.springboot.model.condition;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import cn.tablego.project.springboot.common.model.BaseCondition;

/**
 * 系统菜单表查询条件
 *
 * @author bianj
 * @version 1.0.0 2021-12-07
 */
@Setter
@Getter
@Accessors(chain = true)
@ApiModel(description = "系统菜单表查询条件")
public class TsysMenuCondition extends BaseCondition {
    /** 版本号 */
    private static final long serialVersionUID = -7157415547042524648L;

    /** 创建系统菜单表实例对象 */
    public static TsysMenuCondition newInstance() {
        return new TsysMenuCondition();
    }
}