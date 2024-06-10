package com.daeut.daeut.reservation.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.daeut.daeut.reservation.dto.Cancel;

@Mapper
public interface CancelMapper {
    public List<Cancel> list() throws Exception;
    public Cancel select(int cancelNo) throws Exception;
    public Cancel selectByOrdersNo(String ordersNo) throws Exception;
    public int insert(Cancel cancel) throws Exception;
    public int update(Cancel cancel) throws Exception;
    public int delete(int cancelNo) throws Exception;
}
