package com.hwy.cache.controller;

import com.alibaba.fastjson.JSONObject;
import com.hwy.cache.Service.BasicDataService;
import com.hwy.cache.entity.ResultBean;
import com.hwy.cache.entity.TData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author hwy
 * @program cache
 * @date 2019/7/11 11:54
 */
@Api(value="dataService", description = "数据访问测试")
@RestController
@RequestMapping("/api")
public class TestController {


    @Autowired
    private BasicDataService basicDataService;

    @ApiOperation(value = "helloService")
    @ApiImplicitParams({
            })
    @RequestMapping("/cache")
    public String helloService() {
        int count = basicDataService.countAllData();

        return "hello";
    }

    @ApiOperation(value = "helloService")
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "id",
                    value = "数据Id",
                    dataType = "Integer",
                    required = true,
                    paramType = "query"
            )
    })
    @GetMapping("/getData")
    public ResultBean getData(@RequestParam("id") int id) {
        ResultBean resultBean = ResultBean.SUCCESS;
        resultBean.setData(basicDataService.getDataById(id));

        return resultBean;
    }

    @PostMapping("/saveData")
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "tData",
                    value = "数据实体",
                    dataType = "TData",
                    required = true,
                    paramType = "body"
            )
    })
    public ResultBean saveData(@RequestBody TData tData) {
        if(basicDataService.saveData(tData) == 1) {
            return ResultBean.SUCCESS;
        }else {
            return ResultBean.FAILURE;
        }
    }

    @PostMapping("/deleteData")
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "id",
                    value = "数据Id",
                    dataType = "Integer",
                    required = true,
                    paramType = "query"
            )
    })
    public ResultBean deleteData(@RequestParam("id") int id) {
        if(basicDataService.deleteData(id) == 1) {

            return ResultBean.SUCCESS;
        }else {

            return ResultBean.FAILURE;
        }
    }
}
