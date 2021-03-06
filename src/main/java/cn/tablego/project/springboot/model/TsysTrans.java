package cn.tablego.project.springboot.model;

import javax.validation.constraints.NotBlank;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import cn.tablego.project.springboot.common.model.OverrideBeanMethods;

/**
 * 系统交易表(TSYS_TRANS)
 *
 * @author bianj
 * @version 1.0.0 2021-12-07
 */
@Setter
@Getter
@Accessors(chain = true)
@ApiModel(description = "系统交易表")
@TableName("TSYS_TRANS")
public class TsysTrans extends OverrideBeanMethods {
    /** 版本号 */
    private static final long serialVersionUID = -5247044948367965236L;

    /** 创建系统交易表实例对象 */
    public static TsysTrans newInstance() {
        return new TsysTrans();
    }

    /* This code was generated by TableGo tools, mark 1 begin. */

    @ApiModelProperty(value = "交易编号", position = 1)
    @JsonProperty(index = 1)
    @TableId
    private String transCode;

    @ApiModelProperty(value = "交易名称", position = 2)
    @JsonProperty(index = 2)
    @NotBlank(message = "交易名称不能为空！")
    private String transName;

    @ApiModelProperty(value = "分类编号", position = 3)
    @JsonProperty(index = 3)
    private String kindCode;

    @ApiModelProperty(value = "模块编号", position = 4)
    @JsonProperty(index = 4)
    private String modelCode;

    @ApiModelProperty(value = "备注", position = 5)
    @JsonProperty(index = 5)
    private String remark;

    @ApiModelProperty(value = "扩展字段1", position = 6)
    @JsonProperty(index = 6)
    @TableField(value = "EXT_FIELD_1")
    private String extField1;

    @ApiModelProperty(value = "扩展字段2", position = 7)
    @JsonProperty(index = 7)
    @TableField(value = "EXT_FIELD_2")
    private String extField2;

    @ApiModelProperty(value = "扩展字段3", position = 8)
    @JsonProperty(index = 8)
    @TableField(value = "EXT_FIELD_3")
    private String extField3;

    /* This code was generated by TableGo tools, mark 1 end. */
}