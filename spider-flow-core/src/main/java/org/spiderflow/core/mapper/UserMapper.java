package org.spiderflow.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.spiderflow.core.model.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Select("select id, username, password from sp_user where username = #{username}")
    User selectOneByUsername(@Param("username") String username);
}
