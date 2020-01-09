package life.majiang.community.service;


import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.User;
import life.majiang.community.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class UserService {

    @Autowired
    private UserMapper userMapper;


    public void createOrUpdate(User user) {
//        User dbUser = userMapper.findByAccountId(user.getAccountId());
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andAccountIdEqualTo(user.getAccountId());

        List<User> users = userMapper.selectByExample(userExample);
        if (users.size()==0){
            user.setGmtModified(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            //插入
            userMapper.insert(user);
        }else {

            User dbUser = users.get(0);



            //更新

            User updateUser = new User();
            updateUser.setGmtCreate(System.currentTimeMillis());
            updateUser.setAvatarUrl(user.getAvatarUrl());
            updateUser.setName(user.getName());
            updateUser.setToken(user.getToken());


            UserExample example = new UserExample();
            example.createCriteria()
                    .andIdEqualTo(dbUser.getId());
            userMapper.updateByExampleSelective(updateUser,example);


        }

    }
}
