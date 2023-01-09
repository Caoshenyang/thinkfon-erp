package com.yang.erp.controller;


import com.yang.erp.dto.DeptTreeDto;
import com.yang.erp.vo.CommonResponse;
import com.yang.erp.service.IDeptService;
import com.yang.erp.vo.DeptVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 部门表 前端控制器
 * </p>
 *
 * @author 曹申阳
 * @since 2023-01-07
 */
@Api(tags = "部门管理")
@RestController
@RequestMapping("/sys/dept")
@RequiredArgsConstructor
public class DeptController {

    private final IDeptService deptService;

    @ApiOperation("新增部门")
    @PostMapping("/save")
    public CommonResponse<String> saveDept(@Valid @RequestBody DeptVo deptVo) {
        deptService.saveDept(deptVo);
        return new CommonResponse<String>().success().setMessage("新增成功！");
    }

    @ApiOperation("更新部门")
    @PostMapping("/update")
    public CommonResponse<String> updateDept(@Valid @RequestBody DeptVo deptVo) {
        deptService.updateDept(deptVo);
        return new CommonResponse<String>().success().setMessage("更新成功！");
    }

    @ApiOperation("获取部门树")
    @PostMapping("/tree")
    public CommonResponse<List<DeptTreeDto>> getDeptTree() {
        List<DeptTreeDto> deptTreeDtoList = deptService.deptTree();
        return new CommonResponse<List<DeptTreeDto>>().success(deptTreeDtoList);
    }

}
