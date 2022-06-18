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
public class CmPlan extends BasePojo {

    private static final long serialVersionUID = 1L;

    /**
     * plan name
     */
    private String planName;

    /**
     * 0 is 劳务,1 is 产成品,2 is 半成品,3 is 物资
     */
    private Integer objectType;

    /**
     * product.. `s id,only in 12 13 14
     */
    private Long objectId;

    /**
     * plan price
     */
    private String planPrice;

    /**
     * bigin plan time
     */
    private String beginTime;

    /**
     * end plan time
     */
    private String endTime;

    /**
     * 0 is ban,1 is live
     */
    private Integer isActive;

    /**
     * 0 is not assess,1 is assessed
     */
    private Integer isAssess;


}
