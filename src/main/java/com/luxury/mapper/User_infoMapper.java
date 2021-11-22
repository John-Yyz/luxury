package com.luxury.mapper;

import com.luxury.model.User_info;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface User_infoMapper {

    // 查询列表
    public List<User_info> selectList();

    //添加
    public  int addUser(User_info user);

    //修改
    public  int editUser(User_info user);

    //删除
    public  int delUser(String id);
}
