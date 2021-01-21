package com.example.testexamplerubrain.service;

import com.example.testexamplerubrain.model.Dimension;
import com.example.testexamplerubrain.model.ListDimensions;
import com.example.testexamplerubrain.model.OperationState;

//TODO doc
public interface DimensionService {

    ListDimensions getDimensionsByUserId(int userId);

    OperationState createDimension(Dimension demention);
}
