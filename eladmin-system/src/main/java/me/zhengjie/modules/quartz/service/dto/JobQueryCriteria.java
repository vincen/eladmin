package me.zhengjie.modules.quartz.service.dto;

import lombok.Data;
import me.zhengjie.annotation.Query;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class JobQueryCriteria {

    @Query(type = Query.Type.INNER_LIKE)
    private String jobName;

    @Query
    private Boolean isSuccess;

    @Query(type = Query.Type.BETWEEN)
    private List<LocalDateTime> createTime;
}
