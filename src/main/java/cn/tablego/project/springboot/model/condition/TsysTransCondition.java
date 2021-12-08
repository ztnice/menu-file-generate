package cn.tablego.project.springboot.model.condition;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import cn.tablego.project.springboot.common.model.BaseCondition;

/**
 * 系统交易表查询条件
 *
 * @author bianj
 * @version 1.0.0 2021-12-07
 */
@Setter
@Getter
@Accessors(chain = true)
@ApiModel(description = "系统交易表查询条件")
public class TsysTransCondition extends BaseCondition {
    /** 版本号 */
    private static final long serialVersionUID = -5247044948367965236L;

    /** 创建系统交易表实例对象 */
    public static TsysTransCondition newInstance() {
        return new TsysTransCondition();
    }
}