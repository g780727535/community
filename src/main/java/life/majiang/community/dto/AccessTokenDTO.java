package life.majiang.community.dto;


import lombok.Data;

@Data
public class AccessTokenDTO {
    //需要参数过多 我们尽量封装成对象
    private  String  client_id;
    private  String  client_secret;
    private  String  code;
    private  String  redirect_uri;
    private  String  state;

}
