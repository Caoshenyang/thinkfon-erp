package com.yang.erp.entity;

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
 * 权限模块表
 * </p>
 *
 * @author 曹申阳
 * @since 2023-01-07
 */
@Getter
@Setter
@TableName("sys_acl_module")
@ApiModel(value = "AclModule对象", description = "权限模块表")
public class AclModule implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("权限模块名称")
    private String name;

    @ApiModelProperty("上级权限模块id")
    private Long parentId;

    @ApiModelProperty("权限模块层级")
    private Integer level;

    @ApiModelProperty("权限模块在当前层级下的顺序，由小到大")
    private Integer sort;

    @ApiModelProperty("状态 【0 冻结 1 正常】")
    private Integer status;

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
