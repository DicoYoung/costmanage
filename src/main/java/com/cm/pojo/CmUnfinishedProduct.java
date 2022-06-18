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
public class CmUnfinishedProduct extends BasePojo {

    private static final long serialVersionUID = 1L;

    /**
     * un-product id
     */
    private Long unfinishedProductId;

    /**
     * un-product number
     */
    private String unfinishedProductNumber;

    /**
     * begin time
     */
    private String beginTime;

    /**
     * id of the belong procedure
     */
    private Long procedureId;

    /**
     * describe
     */
    private String remarks;

    /**
     * 0 is ban,1 is live
     */
    private Integer isActive;


}
