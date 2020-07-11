package fun.angelive.entity.dto;

import fun.angelive.entity.bo.HeadLine;
import fun.angelive.entity.bo.ShopCategory;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author angel
 * @Date 2020/6/29
 */
@Data
public class MainPageInfoDTO implements Serializable {

    private List<HeadLine> headLineList;
    private List<ShopCategory> shopCategoryList;
}
