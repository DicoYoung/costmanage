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
public class CmAccount extends BasePojo {

    private static final long serialVersionUID = 1L;

    /**
     * stove id,and is job id
     */
    private String jobId;

    /**
     * working procedure id
     */
    private Long procedureId;

    /**
     * product id
     */
    private Integer productId;

    /**
     * raw material cost
     */
    private Long rawCost;

    /**
     * energy cost
     */
    private Long energyCost;

    /**
     * procedure cost
     */
    private Long procedureCost;

    /**
     * share cost
     */
    private Long shareCost;

    /**
     * cumulative cost
     */
    private Long cumulativeCost;

    /**
     * finish time
     */
    private String finishTime;

    /**
     * 0 is not eva,1 is eva
     */
    private Integer isEva;

}
