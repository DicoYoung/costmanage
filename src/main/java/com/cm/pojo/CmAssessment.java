package com.cm.pojo;

import com.cm.pojo.BasePojo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author Dico
 * @since 2022-05-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CmAssessment extends BasePojo {

    private static final long serialVersionUID = 1L;

    /**
     * plan id
     */
    private Long planId;

    /**
     * plan price
     */
    private String planPrice;

    /**
     * actual cost money
     */
    private String actualCost;

    /**
     * the difference between plan and actual
     */
    private String difference;

    /**
     * 0 to 5 stars
     */
    private Integer evaluate;

    /**
     * evaluate time
     */
    private String evaluateTime;

    /**
     * evaluate user
     */
    private String evaluateUser;

    /**
     * 0 is ban,1 is live
     */
    private Integer isActive;


}
