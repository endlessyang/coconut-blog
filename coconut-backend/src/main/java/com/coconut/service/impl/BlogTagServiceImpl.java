package com.coconut.service.impl;

import com.coconut.model.entity.BlogTag;
import com.coconut.mapper.BlogTagMapper;
import com.coconut.service.BlogTagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class BlogTagServiceImpl extends ServiceImpl<BlogTagMapper, BlogTag> implements BlogTagService {

}
