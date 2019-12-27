//package life.majiang.community;
//
//import life.majiang.community.mapper.UserMapper;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//
//@SpringBootApplication
//public class SampleMybatisApplication implements CommandLineRunner {
//
//    private UserMapper userMapper;
//
//    public SampleMybatisApplication(UserMapper userMapper) {
//        this.userMapper = userMapper;
//    }
//
//    public static void main(String[] args) {
//        SpringApplication.run(SampleMybatisApplication.class, args);
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        System.out.println(this.userMapper.insert(user));
//    }
//}
