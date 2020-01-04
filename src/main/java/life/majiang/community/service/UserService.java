package life.majiang.community.service;


import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserService {
    @Autowired
    private UserMapper userMapper;


    public void createOrUpdate(User user) {
        User dbUser = userMapper.findByAccountId(user.getAccountId());
        if (dbUser==null){
            user.setGmtModified(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            //插入
            userMapper.insert(user);
        }else {

            dbUser.setGmtCreate(System.currentTimeMillis());
            dbUser.setAvatarurl(user.getAvatarurl());
            dbUser.setName(user.getName());
            dbUser.setToken(user.getToken());


            //更新
            userMapper.update(dbUser);

        }

    }
}
