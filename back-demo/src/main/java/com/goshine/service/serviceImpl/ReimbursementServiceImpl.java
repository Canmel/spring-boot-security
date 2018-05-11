package com.goshine.service.serviceImpl;

import com.goshine.core.base.DemoBaseMapper;
import com.goshine.mapper.ReimbursementMapper;
import com.goshine.mapper.WorkFlowMapper;
import com.goshine.service.ReimbursementService;
import com.goshine.service.WorkFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReimbursementServiceImpl extends BaseServiceImpl implements ReimbursementService {

    @Autowired
    private ReimbursementMapper mapper;

    @Override
    public DemoBaseMapper getMapper() {
        return mapper;
    }
}
