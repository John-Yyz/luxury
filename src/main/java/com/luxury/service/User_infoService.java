package com.luxury.service;

import com.luxury.mapper.User_infoMapper;
import com.luxury.model.User_info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class User_infoService implements User_infoMapper {

    @Autowired
    private  User_infoMapper uMapper;

    @Override
    public List<User_info> selectList() {
        return uMapper.selectList();
    }

    @Override
    public int addUser(User_info user) {
        return uMapper.addUser(user);
    }

    @Override
    public int editUser(User_info user) {
        return uMapper.editUser(user);
    }

    @Override
    public int delUser(String id) {
        return uMapper.delUser(id);
    }
}
