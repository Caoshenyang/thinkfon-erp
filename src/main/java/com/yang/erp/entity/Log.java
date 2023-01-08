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
 *
 * </p>
 *
 * @author 曹申阳
 * @since 2023-01-07
 */
@Getter
@Setter
@TableName("sys_log")
@ApiModel(value = "Log对象")
public class Log implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("权限更新类型【1 部门 2 用户 3 权限模块 4 权限 5 角色 6 角色用户关系 7 角色权限关系】")
    private Integer type;

    @ApiModelProperty("基于type指定的对应对象ID，比如用户、角色、权限表的主键")
    private Long targetId;

    @ApiModelProperty("旧值")
    private String oldValue;

    @ApiModelProperty("新值")
    private String newValue;

    @ApiModelProperty("操作人")
    private Long operator;

    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty("当前是否复原过【0 未操作 1 已操作】")
    private Integer status;


}
