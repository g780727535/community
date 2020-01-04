package life.majiang.community.mapper;

import life.majiang.community.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

@Mapper  //指定这是一个数据库的mapper
@Repository
public interface UserMapper {
    @Insert("insert into user(name,account_id,token,gmt_create,gmt_modified,avatar_url) values(#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified},#{avatarurl})")
    void insert(User user);

    @Select("select * from user where token= #{token}")
   //    不是类的话需要加个注解
    User findByToken(@Param("token") String token);


    // creator == id
    @Select("select * from user where account_id= #{creator}")
    User findById(@Param("creator") Integer creator);


    @Select("select * from user where account_id= #{accountId}")
    User findByAccountId(@Param("accountId") String accountId);


    @Update("update user set name = #{name} ,token = #{token}, gmt_modified = #{gmtModified},avatar_url=#{avatarurl}  where id = #{id}")
    void update(User user);
}
