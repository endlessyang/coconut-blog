package com.coconut.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coconut.mapper.FollowMapper;
import com.coconut.model.entity.Follow;
import com.coconut.service.FollowService;
import org.springframework.stereotype.Service;

@Service
public class FollowServiceImpl extends ServiceImpl<FollowMapper, Follow> implements FollowService {

    @Override
    public Boolean isFollow(Integer parentId, Integer followerId) {
        return count(new QueryWrapper<Follow>().eq("parent_id", parentId).eq("follower_id", followerId)) == 1;
    }

    @Override
    public String XORFollow(Integer parentId, Integer followerId) {
        if(isFollow(parentId, followerId)) {
            remove(new QueryWrapper<Follow>().eq("parent_id", parentId).eq("follower_id", followerId));
            return "Unfollow Successfully";
        }
        save(new Follow(parentId, followerId));
        return "Follow successfully";
    }

    @Override
    public Integer countFollower(Integer userId) {
        return count(new QueryWrapper<Follow>().eq("parent_id", userId));
    }

}
