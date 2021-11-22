package com.luxury.controller;

import com.luxury.model.User_info;
import com.luxury.service.User_infoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Api(tags = "用户信息管理")
@RestController
@RequestMapping("/user")
public class User_infoController {

    @Resource
    private User_infoService userService;

    @ApiOperation("测试接口")
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @ApiOperation("增加用户")
    @PostMapping("/addUser")
    public int addUser( @RequestBody User_info user){
        user.setCreate_time(new Date());
        user.setId(UUID.randomUUID().toString());
        return userService.addUser(user);
    }

    @ApiOperation("删除用户")
    @PostMapping("/delUser")
    public int delUser( @RequestBody String  id){
        return userService.delUser(id);
    }

    @ApiOperation("修改用户")
    @PostMapping("/editUser")
    public int editUser( @RequestBody User_info  user){
        user.setCreate_time(new Date());
        return userService.editUser(user);
    }

    @ApiOperation("查询列表")
    @GetMapping("/selectList")
    public List<User_info> selectList() {
        return userService.selectList();
    }



}
