package fun.angelive.entity.bo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 头部链接
 * @Author angel
 * @Date 2020/6/29
 */
@Data
public class HeadLine implements Serializable {
    private Long lineId;
    private String lineName;
    private  String lineLink;
    private String lineImg;
    private Integer priority;
    private Integer enableStatus;
    private Date createTime;
    private Date lastEditTime;
}
