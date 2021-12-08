package cn.tablego.project.springboot.model.condition;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import cn.tablego.project.springboot.common.model.BaseCondition;

/**
 * 角色权限授权表查询条件
 *
 * @author bianj
 * @version 1.0.0 2021-12-07
 */
@Setter
@Getter
@Accessors(chain = true)
@ApiModel(description = "角色权限授权表查询条件")
public class TsysRoleRightCondition extends BaseCondition {
    /** 版本号 */
    private static final long serialVersionUID = -1808010871437595086L;

    /** 创建角色权限授权表实例对象 */
    public static TsysRoleRightCondition newInstance() {
        return new TsysRoleRightCondition();
    }
}