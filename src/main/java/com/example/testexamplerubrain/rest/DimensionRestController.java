package com.example.testexamplerubrain.rest;


import com.example.testexamplerubrain.model.Dimension;
import com.example.testexamplerubrain.model.ListDimensions;
import com.example.testexamplerubrain.model.OperationState;
import com.example.testexamplerubrain.service.DimensionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("dimension")
//TODO doc swagger
public class DimensionRestController {

    private final DimensionService dimensionService;

    public DimensionRestController(DimensionService dimensionService) {
        this.dimensionService = dimensionService;
    }

    @GetMapping("/{userId}")
    private ListDimensions getDimensionByUser(@PathVariable int userId) {
        return dimensionService.getDimensionsByUserId(userId);
    }

    @PostMapping()
    private OperationState createDemention(@RequestBody Dimension demention) {
       return dimensionService.createDimension(demention);
    }
}
