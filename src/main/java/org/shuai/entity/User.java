package org.shuai.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Yangs
 */
@Data
public class User {
    
    private Integer age;

    private String chineseName;

    private String englishName;

    private Boolean isStudent;

    private BigDecimal money;

    private Double arrearsMoney;

    private List<Address> addresses;
}
