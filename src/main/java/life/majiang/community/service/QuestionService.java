package life.majiang.community.service;


import life.majiang.community.dto.PaginationDTO;
import life.majiang.community.dto.QuestionDTO;
import life.majiang.community.exception.CustomizeErrorCode;
import life.majiang.community.exception.CustomizeException;
import life.majiang.community.mapper.QuestionMapper;
import life.majiang.community.mapper.UserMapper;

import life.majiang.community.model.Question;
import life.majiang.community.model.QuestionExample;
import life.majiang.community.model.User;
import life.majiang.community.model.UserExample;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("questionService")
public class QuestionService {
    @Autowired
    private  QuestionMapper  questionMapper;
    @Autowired
    private UserMapper userMapper;
    public PaginationDTO list(Integer page, Integer size) {
        Integer totalPage;
        PaginationDTO paginationDTO = new PaginationDTO();

        Integer totalCount = Integer.valueOf((int) questionMapper.countByExample(new QuestionExample()));
        System.out.println("totalCount"+totalCount);
        if (totalCount%size ==0){
            totalPage = totalCount/size;
        }else {
            totalPage = totalCount/size +1 ;
        }

        if (page<1){
            page =1;
        }
        if (page>totalPage){
            page = totalPage;
        }

        paginationDTO.setPagination(totalPage,page);
        Integer offset = size*(page -1);




        // 每页列表
//        List<Question> questions = questionMapper.list(offset,size);
        List<Question> questions = questionMapper.selectByExampleWithRowbounds(new QuestionExample(), new RowBounds(offset, size));


        List<QuestionDTO> questionDTOList = new ArrayList<>();

        for (Question question : questions) {
//            userMapper.se
//            String id1 = String.valueOf(question.getCreator());
            Integer id1 = question.getCreator();

            UserExample userExample = new UserExample();
            userExample.createCriteria()
                    .andAccountIdEqualTo(id1);

            List<User> users = userMapper.selectByExample(userExample);
//            if ()
//            User user = userMapper.selectByPrimaryKey(question.getCreator());
//            System.out.println("这是取值的第一个user"+users.get(0));
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);

            questionDTO.setUser(users.get(0));

            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);


        return  paginationDTO;
    }

    public PaginationDTO list(Integer userId, Integer page, Integer size) {

        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalPage;
//        Integer totalCount = questionMapper.countByUserId(userId);

        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria()
                .andCreatorEqualTo(userId);
        Integer totalCount = Integer.valueOf((int)questionMapper.countByExample(questionExample));

        if (totalCount%size ==0){
            totalPage = totalCount/size;
        }else {
            totalPage = totalCount/size +1 ;
        }

        if (page<1){
            page =1;
        }
        if (page>totalPage){
            page = totalPage;
        }

        paginationDTO.setPagination(totalPage,page);

        Integer offset = size*(page -1);
        // 每页列表
//        List<Question> questions = questionMapper.listByUserId(userId,offset,size);

        QuestionExample questionExample1 = new QuestionExample();
        questionExample1.createCriteria()
                .andCreatorEqualTo(userId);
        List<Question> questions = questionMapper.selectByExampleWithRowbounds(questionExample1, new RowBounds(offset, size));

        List<QuestionDTO> questionDTOList = new ArrayList<>();

        for (Question question : questions) {

//            String id2 = String.valueOf(question.getCreator());
            Integer id2 = question.getCreator();
            UserExample example = new UserExample();
            example.createCriteria()
                    .andAccountIdEqualTo(id2);
            List<User> users = userMapper.selectByExample(example);
//            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);

            questionDTO.setUser(users.get(0));

            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);


        return  paginationDTO;


    }

    public QuestionDTO getById(Integer id) {
//        Question question =questionMapper.getById(id);
        Question question =questionMapper.selectByPrimaryKey(id);
        if (question==null){
            throw  new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question,questionDTO);


//        String id3 = String.valueOf(question.getCreator());
        Integer id3 = question.getCreator();
        UserExample example = new UserExample();
        example.createCriteria()
                .andAccountIdEqualTo(id3);
        List<User> users = userMapper.selectByExample(example);
//        User user = userMapper.selectByPrimaryKey(question.getCreator());
        questionDTO.setUser(users.get(0));
        System.out.println(users.get(0));
        System.out.println(users.get(0).getAccountId());
        System.out.println(question.getCreator());
        return  questionDTO;

    }

    public void createOrUpdate(Question question) {
        if (question.getId()==null){
            //创建
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
//            questionMapper.create(question);
            questionMapper.insert(question);
        }else {
            //更新
//            question.setGmtModified(question.getGmtCreate());
//            questionMapper.updateQuestion(question);


            Question updateQuestion = new Question();
            updateQuestion.setGmtModified(System.currentTimeMillis());
            updateQuestion.setTitle(question.getTitle());
            updateQuestion.setDescription(question.getDescription());
            updateQuestion.setTag(question.getTag());
            QuestionExample example = new QuestionExample();
            example.createCriteria()
                    .andIdEqualTo(question.getId());


            int update = questionMapper.updateByExampleSelective(updateQuestion, example);

            if (update!=1){
                throw  new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
        }

    }
}
