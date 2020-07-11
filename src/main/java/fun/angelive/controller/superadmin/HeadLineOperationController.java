package fun.angelive.controller.superadmin;

import fun.angelive.entity.bo.HeadLine;
import fun.angelive.entity.dto.Result;
import fun.angelive.service.solo.IHeadLineService;
import org.simpleframework.core.annotation.Controller;
import org.simpleframework.inject.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author angel
 * @Date 2020/6/29
 */
@Controller
public class HeadLineOperationController {

    @Autowired
    private IHeadLineService iHeadLineService;

    public void removeHeadLine(){
        System.out.println("删除HeadLine");
    }

    public Result<Boolean> modifyHeadLine(HttpServletRequest req, HttpServletResponse resp){
        //TODO:参数校验以及请求参数转化
        return iHeadLineService.modifyHeadLine(new HeadLine());
    }

    public Result<HeadLine> queryHeadLineById(HttpServletRequest req, HttpServletResponse resp){
        //TODO:参数校验以及请求参数转化
        return iHeadLineService.queryHeadLineById(1);
    }

    public Result<List<HeadLine>>queryHeadLine(){
        return iHeadLineService.queryHeadLine(null, 1, 100);
    }
}
