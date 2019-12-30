package life.majiang.community.dto;


//import life.majiang.community.model.Question;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PaginationDTO {
    private  List<QuestionDTO> questions;
    private  boolean hasPrevious;
    private  boolean hasFirstPage;
    private boolean showNext;
    private  boolean showEnPage;

    private  Integer totalPage;
    //当前页面
    private  Integer page;

    // 总的页面数
    private  List<Integer> pages = new ArrayList<>();

    public void setPagination(Integer totalCount, Integer page, Integer size) {



        // 页面总数
        if (totalCount%size ==0){
            totalPage = totalCount/size;
        }else {

            totalPage = totalCount/size + 1;
        }

        if (page<1){
            page=1;
        }
        if (page>totalPage){
            page = totalPage;
        }

        this.page = page;

        pages.add(page);
        // 向前展示三个 向后展示三个页码
        for (int i = 1;i<=3;i++){
            if (page-i>0){
                pages.add(0,page-i);
            }
            if (page +i <= totalPage){
                pages.add(page+i);
            }
        }

        //是否展现上一页
        if (page==1){
            hasPrevious = false;
        }else {
            hasPrevious = true;
        }

        //是否展现下一页
        if (page == totalPage){
            showNext = false;
        }else{
            showNext = true;
        }

        //是否展现第一页
        if (pages.contains(1)){
            hasFirstPage = false;
        }else {
            hasFirstPage = true;
        }

        //是否展现最后一页
        if(pages.contains(totalPage)){
            showEnPage = false;
        }else {
            showEnPage = true;
        }
    }
}
