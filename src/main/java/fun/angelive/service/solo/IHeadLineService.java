package fun.angelive.service.solo;

import fun.angelive.entity.bo.HeadLine;
import fun.angelive.entity.dto.Result;

import java.util.List;

/**
 * @Author angel
 * @Date 2020/6/29
 */
public interface IHeadLineService {
    Result<Boolean> addHeadLine(HeadLine headLine);
    Result<Boolean> removeHeadLine(int headLineId);
    Result<Boolean> modifyHeadLine(HeadLine headLine);
    Result<HeadLine> queryHeadLineById(int headLineId);
    Result<List<HeadLine>>queryHeadLine(HeadLine headLineCondition, int pageIndex, int pageSize);
}
