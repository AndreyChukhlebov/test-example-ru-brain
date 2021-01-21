package com.example.testexamplerubrain.service.impl;

import com.example.testexamplerubrain.model.Dimension;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Component
public class DimensionDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private static final String CREATE_DIEMENTIONS_QUERY = "INSERT INTO diementions (id,\n" +
                                                           "                         users_id,\n" +
                                                           "                         gas_info,\n" +
                                                           "                         cold_water_info,\n" +
                                                           "                         hot_water_info,\n" +
                                                           "                         create_data)\n" +
                                                           "VALUES (diementions_sequence.nextval,\n" +
                                                           "        :USERS_ID,\n" +
                                                           "        :GAS_INFO,\n" +
                                                           "        :COLD_WATER_INFO,\n" +
                                                           "        :HOT_WATER_INFO,\n" +
                                                           "        SYSDATE())";

    private static final String SELECT_DIEMENTIONS_QUERY = "SELECT * FROM diementions WHERE users_id = :USER_ID ORDER BY create_data";

    public DimensionDao(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public void createDimension(Dimension dimension) {
        namedParameterJdbcTemplate.update(CREATE_DIEMENTIONS_QUERY, Map.of(
                "USERS_ID", dimension.getUserId(),
                "GAS_INFO", dimension.getGasInfo(),
                "COLD_WATER_INFO", dimension.getColdWaterInfo(),
                "HOT_WATER_INFO", dimension.getHotWhaterInfo()
        ));
    }

    public List<Dimension> getAllDementionsByUserId(int userId) {
        return namedParameterJdbcTemplate.query(
                SELECT_DIEMENTIONS_QUERY,
                Collections.singletonMap("USER_ID", userId),
                (resultSet, i) -> {
                    Dimension dimension = new Dimension();
                    dimension.setId(resultSet.getInt("ID"));
                    dimension.setUserId(resultSet.getInt("USERS_ID"));
                    dimension.setGasInfo(resultSet.getInt("GAS_INFO"));
                    dimension.setColdWaterInfo(resultSet.getInt("COLD_WATER_INFO"));
                    dimension.setHotWhaterInfo(resultSet.getInt("HOT_WATER_INFO"));
                    return dimension;
                }
        );
    }
}
