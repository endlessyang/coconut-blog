package com.coconut.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.coconut.model.dto.LoginDTO;
import com.coconut.model.dto.RegisterDTO;
import com.coconut.model.entity.User;
import com.coconut.model.vo.UserVO;

public interface UserService extends IService<User> {

    User register(RegisterDTO dto);

    String login(LoginDTO dto);

    UserVO getUserVO(Integer userId);

}
