package me.zhengjie.service.mapstruct;

import me.zhengjie.base.BaseMapper;
import me.zhengjie.domain.SysLog;
import me.zhengjie.service.dto.SysLogSmallDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LogSmallMapper extends BaseMapper<SysLogSmallDto, SysLog> {

}
