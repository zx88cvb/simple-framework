package fun.angelive.service.solo.impl;

import fun.angelive.entity.bo.ShopCategory;
import fun.angelive.entity.dto.Result;
import fun.angelive.service.solo.IShopCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.simpleframework.core.annotation.Service;

import java.util.List;

/**
 * @Author angel
 * @Date 2020/6/29
 */
@Slf4j
@Service
public class ShopCategoryServiceImpl implements IShopCategoryService {
    @Override
    public Result<Boolean> addShopCategory(ShopCategory shopCategory) {
        return null;
    }

    @Override
    public Result<Boolean> removeShopCategory(int shopCategoryId) {
        return null;
    }

    @Override
    public Result<Boolean> modifyShopCategory(ShopCategory shopCategory) {
        return null;
    }

    @Override
    public Result<ShopCategory> queryShopCategoryById(int shopCategoryId) {
        return null;
    }

    @Override
    public Result<List<ShopCategory>> queryShopCategory(ShopCategory shopCategoryCondition, int pageIndex, int pageSize) {
        return null;
    }
}
