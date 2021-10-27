package com.coconut.service;

import com.coconut.model.entity.Tag;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

public interface TagService extends IService<Tag> {

    Integer add(String tagName);

    List<String> getTagNamesByBlogId(Integer blogId);

}
