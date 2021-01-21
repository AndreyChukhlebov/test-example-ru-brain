package com.example.testexamplerubrain;

import com.example.testexamplerubrain.model.Dimension;
import com.example.testexamplerubrain.model.ListDimensions;
import com.example.testexamplerubrain.model.OperationState;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DimensionRestControllerIntegrationTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    int randomServerPort;

    @Test
    public void createTest() throws URISyntaxException {
        final String baseUrl = "http://localhost:" + randomServerPort + "/dimension";
        URI uri = new URI(baseUrl);
        Dimension dimension = new Dimension();
        dimension.setUserId(3);
        dimension.setGasInfo(1);
        dimension.setColdWaterInfo(11);
        dimension.setHotWhaterInfo(111);
        restTemplate.postForEntity(uri, dimension, OperationState.class);
        ResponseEntity<ListDimensions> response = restTemplate.getForEntity(uri + "/3", ListDimensions.class);
        Assert.assertEquals("http state not OK", HttpStatus.OK, response.getStatusCode());
        ListDimensions listDimensions = response.getBody();
        Assert.assertNotNull("the body must not be empty", listDimensions);
        List<Dimension> dimensions = listDimensions.getList();
        Assert.assertNotNull("list dimensions must not be null", dimensions);
        Dimension selectedDimension = dimensions.get(0);
        assertEqualsDimension(dimension, selectedDimension);
    }

    @Test
    public void getAllTest() throws URISyntaxException {
        final String baseUrl = "http://localhost:" + randomServerPort + "/dimension";
        URI uri = new URI(baseUrl);

        Dimension dem1 = new Dimension();
        dem1.setUserId(2);
        dem1.setGasInfo(1);
        dem1.setColdWaterInfo(11);
        dem1.setHotWhaterInfo(111);

        restTemplate.postForEntity(uri, dem1, OperationState.class);

        Dimension dem2 = new Dimension();
        dem2.setUserId(2);
        dem2.setGasInfo(2);
        dem2.setColdWaterInfo(22);
        dem2.setHotWhaterInfo(222);

        restTemplate.postForEntity(uri, dem2, OperationState.class);

        Dimension dem3 = new Dimension();
        dem3.setUserId(2);
        dem3.setGasInfo(3);
        dem3.setColdWaterInfo(33);
        dem3.setHotWhaterInfo(333);

        restTemplate.postForEntity(uri, dem3, OperationState.class);

        ResponseEntity<ListDimensions> response = restTemplate.getForEntity(uri + "/2", ListDimensions.class);
        Assert.assertEquals("http state not OK", HttpStatus.OK, response.getStatusCode());
        ListDimensions listDimensions = response.getBody();
        Assert.assertNotNull("the body must not be empty", listDimensions);
        List<Dimension> dimensions = listDimensions.getList();
        Assert.assertNotNull("list dimensions must not be null", dimensions);
        Assert.assertEquals("dimensions list must be 3", dimensions.size(), 3);

        //ordering test
        assertEqualsDimension(dimensions.get(0), dem1);
        assertEqualsDimension(dimensions.get(1), dem2);
        assertEqualsDimension(dimensions.get(2), dem3);
    }

    @Test
    public void validationTest() throws URISyntaxException {
        final String baseUrl = "http://localhost:" + randomServerPort + "/dimension";
        URI uri = new URI(baseUrl);
        Dimension dem1 = new Dimension();
        dem1.setUserId(1);
        dem1.setGasInfo(-11);
        dem1.setColdWaterInfo(-11);
        dem1.setHotWhaterInfo(-111);

        ResponseEntity<OperationState> response = restTemplate.postForEntity(uri, dem1, OperationState.class);
        OperationState state = response.getBody();

        Assert.assertEquals("the state must not be false", state.isSuccess(), false);
        Assert.assertTrue("verification HotWhaterInfo must be present", state.getErrorMessages().contains("not valid HotWhaterInfo"));
        Assert.assertTrue("verification ColdWhaterInfo must be present", state.getErrorMessages().contains("not valid ColdWhaterInfo"));
        Assert.assertTrue("verification GasWhaterInfo must be present ", state.getErrorMessages().contains("not valid GasWhaterInfo"));


        dem1.setUserId(1);
        dem1.setGasInfo(1);
        dem1.setColdWaterInfo(111);
        dem1.setHotWhaterInfo(1111);
        response = restTemplate.postForEntity(uri, dem1, OperationState.class);
        state = response.getBody();
        Assert.assertEquals("the state must not be false", state.isSuccess(), true);
        Assert.assertTrue("checks must be empty", state.getErrorMessages().isEmpty());
    }

    private static void assertEqualsDimension(Dimension expected, Dimension actual) {
        Assert.assertEquals("userId non valid", expected.getUserId(), actual.getUserId());
        Assert.assertEquals("gasInfo non valid", expected.getGasInfo(), actual.getGasInfo());
        Assert.assertEquals("coldWaterInfo non valid", expected.getColdWaterInfo(), actual.getColdWaterInfo());
        Assert.assertEquals("hotWhaterInfo non valid", expected.getHotWhaterInfo(), actual.getHotWhaterInfo());
    }
}
