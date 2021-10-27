package com.coconut.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coconut.mapper.BlogMapper;
import com.coconut.model.dto.BlogDTO;
import com.coconut.model.entity.Blog;
import com.coconut.model.entity.BlogTag;
import com.coconut.model.vo.BlogVO;
import com.coconut.service.BlogService;
import com.coconut.service.BlogTagService;
import com.coconut.service.TagService;
import com.coconut.service.UserService;
import com.coconut.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

    @Autowired
    UserService userService;

    @Autowired
    TagService tagService;

    @Autowired
    BlogTagService blogTagService;

    @Autowired
    RedisUtil redisUtil;

    @Override
    public Blog edit(Integer userId, BlogDTO dto) {
        Blog blog = new Blog();
        if(dto.getBlogId() != null) {
            blog = baseMapper.selectById(dto.getBlogId());
            if(!userId.equals(blog.getUserId())) return null;
            blogTagService.remove(new QueryWrapper<BlogTag>().eq("blog_id", dto.getBlogId()));
        } else {
            blog.setUserId(userId);
        }
        blog.setTitle(dto.getTitle());
        blog.setContent(dto.getContent());
        this.saveOrUpdate(blog);
        for(String tagName : dto.getTagNames()) {
            Integer tagId = tagService.add(tagName);
            blogTagService.save(BlogTag.builder().blogId(blog.getId()).tagId(tagId).build());
        }
        return blog;
    }

    @Override
    public Boolean delete(Integer userId, Integer blogId) {
        Blog blog = baseMapper.selectById(blogId);
        if(!userId.equals(blog.getUserId())) return false;
        blogTagService.remove(new QueryWrapper<BlogTag>().eq("blog_id", blogId));
        baseMapper.deleteById(blogId);
        return true;
    }

    @Override
    public BlogVO view(Integer blogId) {
        Blog blog = baseMapper.selectById(blogId);
        Integer incr = redisUtil.incrView(String.valueOf(blogId));
        return new BlogVO(blogId, blog.getTitle(), blog.getContent(), blog.getUserId(), userService.getById(blog.getUserId()).getUsername(),
                blog.getView() + incr, tagService.getTagNamesByBlogId(blogId), blog.getCreateTime());
    }

    @Override
    public IPage<BlogVO> list(int pageNum, int pageSize, String opt, String val) {
        QueryWrapper<Blog> wrapper = new QueryWrapper<Blog>().orderByDesc("create_time");
        if(opt.equals("userId")) wrapper.eq("user_id", val);
        else if(opt.equals("title")) wrapper.like("title", val);
        IPage<Blog> blogIPage = page(new Page(pageNum, pageSize), wrapper);
        IPage<BlogVO> blogVOIPage = new Page<>(blogIPage.getCurrent(), blogIPage.getSize(), blogIPage.getTotal());
        List<BlogVO> records = new ArrayList<>();
        for(Blog blog : blogIPage.getRecords()) {
            records.add(new BlogVO(blog.getId(), blog.getTitle(), null, blog.getUserId(), userService.getById(blog.getUserId()).getUsername(),
                    blog.getView() + redisUtil.getView(String.valueOf(blog.getId())), tagService.getTagNamesByBlogId(blog.getId()), blog.getCreateTime()));
        }
        blogVOIPage.setRecords(records);
        return blogVOIPage;
    }

    @Override
    public Integer countBlog(Integer userId) {
        return count(new QueryWrapper<Blog>().eq("user_id", userId));
    }

}
