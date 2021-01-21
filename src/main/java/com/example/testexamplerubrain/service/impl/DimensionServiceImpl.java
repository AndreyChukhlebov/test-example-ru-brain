package com.example.testexamplerubrain.service.impl;

import com.example.testexamplerubrain.model.Dimension;
import com.example.testexamplerubrain.model.ListDimensions;
import com.example.testexamplerubrain.model.OperationState;
import com.example.testexamplerubrain.service.DimensionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DimensionServiceImpl implements DimensionService {

    private final DimensionDao dementionDao;

    public DimensionServiceImpl(DimensionDao dementionDao) {
        this.dementionDao = dementionDao;
    }

    @Override
    public ListDimensions getDimensionsByUserId(final int userId) {
        if (userId < 0) {
            throw new RuntimeException("not valid param userId");
        }
        ListDimensions wraper = new ListDimensions();
        wraper.setList(dementionDao.getAllDementionsByUserId(userId));
        return wraper;
    }

    @Override
    @Transactional
    public OperationState createDimension(final Dimension demention) {
        OperationState operationState = new OperationState();
        if (demention.getHotWhaterInfo() < 0) {
            operationState.getErrorMessages().add("not valid HotWhaterInfo");
        }
        if (demention.getColdWaterInfo() < 0) {
            operationState.getErrorMessages().add("not valid ColdWhaterInfo");
        }
        if (demention.getGasInfo() < 0) {
            operationState.getErrorMessages().add("not valid GasWhaterInfo");
        }
        try {
            dementionDao.createDimension(demention);
        } catch (Exception e) {
            operationState.getErrorMessages().add("error save");
            System.out.println(e.getMessage());
        }
        if (operationState.getErrorMessages().isEmpty()) {
            operationState.setSuccess(true);
        }
        return operationState;
    }
}
