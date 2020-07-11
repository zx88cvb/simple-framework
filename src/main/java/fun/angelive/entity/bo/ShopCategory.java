package fun.angelive.entity.bo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author angel
 * @Date 2020/6/29
 */
@Data
public class ShopCategory implements Serializable {

    private Long shopCategoryId;
    private String shopCategoryName;
    private String shopCategoryDesc;
    private String shopCategoryImg;
    private Integer priority;
    private Date createTime;
    private Date lastEditTime;
    private ShopCategory parent;
}
