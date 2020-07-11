package fun.angelive.controller.frontend;

import fun.angelive.entity.dto.MainPageInfoDTO;
import fun.angelive.entity.dto.Result;
import fun.angelive.service.combine.IHeadLineShopCategoryCombineService;
import lombok.Getter;
import org.simpleframework.core.annotation.Controller;
import org.simpleframework.inject.annotation.Autowired;

/**
 * @Author angel
 * @Date 2020/6/29
 */
@Controller
@Getter
public class MainPageController {
    @Autowired("HeadLineShopCategoryCombineServiceImpl")
    private IHeadLineShopCategoryCombineService iHeadLineShopCategoryCombineService;

    public Result<MainPageInfoDTO> getMainPageInfo() {
        return iHeadLineShopCategoryCombineService.getMainPageInfo();
    }
}
