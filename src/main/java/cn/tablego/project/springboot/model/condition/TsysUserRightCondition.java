package cn.tablego.project.springboot.model.condition;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import cn.tablego.project.springboot.common.model.BaseCondition;

/**
 * 用户权限授权表查询条件
 *
 * @author bianj
 * @version 1.0.0 2021-12-07
 */
@Setter
@Getter
@Accessors(chain = true)
@ApiModel(description = "用户权限授权表查询条件")
public class TsysUserRightCondition extends BaseCondition {
    /** 版本号 */
    private static final long serialVersionUID = 3270163722184852664L;

    /** 创建用户权限授权表实例对象 */
    public static TsysUserRightCondition newInstance() {
        return new TsysUserRightCondition();
    }
}