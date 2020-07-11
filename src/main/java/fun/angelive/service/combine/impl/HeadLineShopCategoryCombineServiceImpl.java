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
public class HeadLineShopCategoryCombineServiceImpl implements IHeadLineShopCategoryCombineService {

    @Autowired
    private IHeadLineService iHeadLineService;

    @Autowired
    private IShopCategoryService iShopCategoryService;

    @Override
    public Result<MainPageInfoDTO> getMainPageInfo() {
        //1.获取头条列表
        HeadLine headLineCondition = new HeadLine();
        headLineCondition.setEnableStatus(1);
        Result<List<HeadLine>> HeadLineResult = iHeadLineService.queryHeadLine(headLineCondition, 1, 4);
        //2.获取店铺类别列表
        ShopCategory shopCategoryCondition = new ShopCategory();
        Result<List<ShopCategory>> shopCategoryResult =iShopCategoryService.queryShopCategory(shopCategoryCondition, 1, 100);
        //3.合并两者并返回
        Result<MainPageInfoDTO> result = mergeMainPageInfoResult(HeadLineResult, shopCategoryResult);
        return result;
    }

    private Result<MainPageInfoDTO> mergeMainPageInfoResult(Result<List<HeadLine>> headLineResult, Result<List<ShopCategory>> shopCategoryResult) {
        return  null;
    }
}
