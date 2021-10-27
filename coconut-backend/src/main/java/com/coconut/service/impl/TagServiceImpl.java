package com.coconut.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.coconut.model.entity.Tag;
import com.coconut.mapper.TagMapper;
import com.coconut.service.TagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    @Override
    public Integer add(String tagName) {
        Tag tag = baseMapper.selectOne(new QueryWrapper<Tag>().eq("name", tagName));
        if(tag == null) {
            tag = Tag.builder().name(tagName).build();
            baseMapper.insert(tag);
        }
        return tag.getId();
    }

    @Override
    public List<String> getTagNamesByBlogId(Integer blogId) {
        return baseMapper.getTagNamesByBlogId(blogId);
    }

}
