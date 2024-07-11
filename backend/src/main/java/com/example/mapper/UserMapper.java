package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    User selectUser(User user);

    User findByUserName(String username);

    int updateLoginAt(Long uid, LocalDateTime update_at);
}

