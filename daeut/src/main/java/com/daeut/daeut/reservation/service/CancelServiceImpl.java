package com.daeut.daeut.reservation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daeut.daeut.reservation.dto.Cancel;
import com.daeut.daeut.reservation.mapper.CancelMapper;

@Service
public class CancelServiceImpl implements CancelService{

    @Autowired
    private CancelMapper cancelMapper;

    @Override
    public List<Cancel> list() throws Exception {
        return cancelMapper.list();
    }

    @Override
    public Cancel select(int cancelNo) throws Exception {
        return cancelMapper.select(cancelNo);
    }

    @Override
    public Cancel selectByOrdersNo(String ordersNo) throws Exception {
        return cancelMapper.selectByOrdersNo(ordersNo);
    }

    @Override
    public int insert(Cancel cancel) throws Exception {
        return cancelMapper.insert(cancel);
    }

    @Override
    public int update(Cancel cancel) throws Exception {
        return cancelMapper.update(cancel);
    }

    @Override
    public int delete(int cancelNo) throws Exception {
        return cancelMapper.delete(cancelNo);
    }
    
}
