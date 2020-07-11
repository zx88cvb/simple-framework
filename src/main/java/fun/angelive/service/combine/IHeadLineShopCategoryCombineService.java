package fun.angelive.service.combine;

import fun.angelive.entity.dto.MainPageInfoDTO;
import fun.angelive.entity.dto.Result;

/**
 * @Author angel
 * @Date 2020/6/29
 */
public interface IHeadLineShopCategoryCombineService {
    Result<MainPageInfoDTO> getMainPageInfo();
}
