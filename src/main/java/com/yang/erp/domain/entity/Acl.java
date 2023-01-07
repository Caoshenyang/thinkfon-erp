package com.yang.erp.domain.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 权限表
 * </p>
 *
 * @author 曹申阳
 * @since 2023-01-07
 */
@Getter
@Setter
@TableName("sys_acl")
@ApiModel(value = "Acl对象", description = "权限表")
public class Acl implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("权限ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("权限码")
    private String code;

    @ApiModelProperty("权限名称")
    private String name;

    @ApiModelProperty("权限所在的权限模块id")
    private Long aclModuleId;

    @ApiModelProperty("请求的url, 可以填正则表达式")
    private String url;

    @ApiModelProperty("类型【1 菜单 2 按钮 3 其他】")
    private Integer type;

    @ApiModelProperty("状态 【1 正常 0 冻结】")
    private Integer status;

    @ApiModelProperty("权限在当前模块下的顺序，由小到大")
    private Integer sort;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("创建人")
    private Long creator;

    @ApiModelProperty("操作人")
    private Long operator;

    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;


}
