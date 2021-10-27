package com.coconut.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.coconut.model.dto.BlogDTO;
import com.coconut.model.entity.Blog;
import com.coconut.model.vo.BlogVO;

public interface BlogService extends IService<Blog> {

    Blog edit(Integer userId, BlogDTO dto);

    Boolean delete(Integer userId, Integer blogId);

    BlogVO view(Integer blogId);

    IPage<BlogVO> list(int pageNum, int pageSize, String opt, String val);

    Integer countBlog(Integer userId);

}
