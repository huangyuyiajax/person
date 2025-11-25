package com.app.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @description: TODO
 * @author huangyuyi
 * @date 2023/4/21 10:49
 * @version 1.0
 */

@Data
public class Report {

    private Integer id;

    private String name;

    private String code;

    private BigDecimal outtotal;

    private BigDecimal win;

    private BigDecimal intotal;

    List<OrderDetil> list;

    List<OrderDetil> list2;

}
