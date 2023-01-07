package com.yang.erp.controller;


import com.yang.erp.domain.dto.DeptDto;
import com.yang.erp.domain.vo.CommonResponse;
import com.yang.erp.service.IDeptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
    public CommonResponse<String> saveDept(@Valid @RequestBody DeptDto deptDto) {
        deptService.saveDept(deptDto);
        return new CommonResponse<String>().success().setMessage("新增成功！");
    }



}
