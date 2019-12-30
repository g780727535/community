package life.majiang.community.dto;


//question表中的creator关联user中的account_id拿到user中的图片链接
// 因为question和user是数据库模型 不易改变 我们创建一个DTO用来传输


import life.majiang.community.model.User;
import lombok.Data;

@Data
public class QuestionDTO {

    private  Integer id;
    private  String title;
    private  String description;
    private String tag;
    private  Long gmtCreate;
    private  Long gmtModified;
    private  Integer creator;
    private Integer viewCount;
    private Integer commentCount;
    private Integer likeCount;

    //多出关联的
    private User user;
}
