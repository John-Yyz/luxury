package com.luxury.service;

import com.luxury.model.User_info;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 描述：
 *
 * @author yuyz
 * @version 1.0
 * @date 2021/11/23 3:05
 */
@Service
public interface User_infoServiceI {
    public List<User_info> selectList();
    public int addUser(User_info user);
    public int editUser(User_info user);
    public int delUser(String id);
}
