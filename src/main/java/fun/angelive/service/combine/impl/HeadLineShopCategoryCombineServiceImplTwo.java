package fun.angelive.service.combine.impl;

import fun.angelive.entity.bo.HeadLine;
import fun.angelive.entity.bo.ShopCategory;
import fun.angelive.entity.dto.MainPageInfoDTO;
import fun.angelive.entity.dto.Result;
import fun.angelive.service.combine.IHeadLineShopCategoryCombineService;
import fun.angelive.service.solo.IHeadLineService;
import fun.angelive.service.solo.IShopCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.simpleframework.core.annotation.Service;
import org.simpleframework.inject.annotation.Autowired;

import java.util.List;

/**
 * @Author angel
 * @Date 2020/6/29
 */
@Slf4j
@Service
public class HeadLineShopCategoryCombineServiceImplTwo implements IHeadLineShopCategoryCombineService {

    @Autowired
    private IHeadLineService iHeadLineService;

    @Autowired
    private IShopCategoryService iShopCategoryService;

    @Override
    public Result<MainPageInfoDTO> getMainPageInfo() {
        return null;
    }

}
