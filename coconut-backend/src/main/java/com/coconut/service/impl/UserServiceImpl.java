package com.coconut.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.coconut.util.JwtUtil;
import com.coconut.model.dto.LoginDTO;
import com.coconut.model.dto.RegisterDTO;
import com.coconut.model.entity.User;
import com.coconut.mapper.UserMapper;
import com.coconut.model.vo.UserVO;
import com.coconut.service.BlogService;
import com.coconut.service.FollowService;
import com.coconut.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.ObjectUtils;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    BlogService blogService;

    @Autowired
    FollowService followService;

    @Override
    public User register(RegisterDTO dto) {
        Integer count = baseMapper.selectCount(new QueryWrapper<User>().eq("username", dto.getUsername()));
        if(count == 1) return null;
        User user = User.builder()
                .username(dto.getUsername())
                .password(DigestUtils.md5DigestAsHex(dto.getPassword().getBytes()))
                .email(dto.getEmail())
                .build();
        baseMapper.insert(user);
        return user;
    }

    @Override
    public String login(LoginDTO dto) {
        User user = baseMapper.selectOne(new QueryWrapper<User>().eq("username", dto.getUsername()));
        if(ObjectUtils.isEmpty(user) || !user.getPassword().equals(DigestUtils.md5DigestAsHex(dto.getPassword().getBytes()))) return null;
        return user.getId() + " " + JwtUtil.getJwt(user.getId());
    }

    @Override
    public UserVO getUserVO(Integer userId) {
        return new UserVO(getById(userId).getUsername(), blogService.countBlog(userId), followService.countFollower(userId));
    }

}
