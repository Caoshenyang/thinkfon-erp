package com.yang.erp.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 部门VO对象
 * </p>
 *
 * @author 曹申阳
 * @since 2023-01-07 16:19:05
 */
@Data
@ApiModel(value = "Dept对象")
public class DeptVo {

    @ApiModelProperty("部门ID")
    private Long id;

    @NotBlank(message = "部门名称不可以为空")
    @Length(max = 15,min = 2,message = "部门名称需要在 2-15 个字之间")
    @ApiModelProperty("部门名称")
    private String name;

    @NotNull(message = "排序不可为空")
    @ApiModelProperty("排序")
    private Integer sort;

    @ApiModelProperty("父节点ID")
    private Long parentId;

    @Length(max = 150,message = "备注的长度需要在 150 字以内")
    @ApiModelProperty("备注")
    private String remark;
}
