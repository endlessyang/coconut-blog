package com.coconut.mapper;

import com.coconut.model.entity.Tag;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface TagMapper extends BaseMapper<Tag> {

    List<String> getTagNamesByBlogId(@Param("blog_id") Integer blogId);

}
