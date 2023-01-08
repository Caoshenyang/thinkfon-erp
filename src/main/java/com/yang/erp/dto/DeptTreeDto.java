package com.yang.erp.dto;

import com.google.common.collect.Lists;
import com.yang.erp.entity.Dept;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * <p>
 * dept dto
 * </p>
 *
 * @author 曹申阳
 * @since 2023-01-07 17:07:23
 */
@Data
public class DeptTreeDto extends Dept {

    private List<DeptTreeDto> children = Lists.newArrayList();

    public static DeptTreeDto adapt(Dept dept){
        DeptTreeDto deptTreeDto = new DeptTreeDto();
        BeanUtils.copyProperties(dept,deptTreeDto);
        return deptTreeDto;
    }

}
