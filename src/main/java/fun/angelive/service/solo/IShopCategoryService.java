package fun.angelive.service.solo;

import fun.angelive.entity.bo.ShopCategory;
import fun.angelive.entity.dto.Result;

import java.util.List;

/**
 * @Author angel
 * @Date 2020/6/29
 */
public interface IShopCategoryService {
    Result<Boolean> addShopCategory(ShopCategory shopCategory);
    Result<Boolean> removeShopCategory(int shopCategoryId);
    Result<Boolean> modifyShopCategory(ShopCategory shopCategory);
    Result<ShopCategory> queryShopCategoryById(int shopCategoryId);
    Result<List<ShopCategory>> queryShopCategory(ShopCategory shopCategoryCondition, int pageIndex, int pageSize);
}
