package com.coconut.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.coconut.model.entity.Follow;

public interface FollowService extends IService<Follow> {

    Boolean isFollow(Integer parentId, Integer followerId);

    String XORFollow(Integer parentId, Integer followerId);

    Integer countFollower(Integer userId);

}
