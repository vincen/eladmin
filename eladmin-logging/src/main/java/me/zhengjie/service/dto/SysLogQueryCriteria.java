package me.zhengjie.service.dto;

import lombok.Data;
import me.zhengjie.annotation.Query;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 日志查询类
 */
@Data
public class SysLogQueryCriteria {

    @Query(blurry = "username,description,address,requestIp,method,params")
    private String blurry;

    @Query
    private String username;

    @Query
    private String logType;

    @Query(type = Query.Type.BETWEEN)
    private List<LocalDateTime> createTime;
}
