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
public class CmShare extends BasePojo {

    private static final long serialVersionUID = 1L;

    /**
     * share ruler name
     */
    private String rulerName;

    /**
     * working procedure id
     */
    private Long procedureId;

    /**
     * share ruler factor
     */
    private Long rulerFactor;

    /**
     * share ruler describe
     */
    private String remarks;

    /**
     * 0 is ban,1 is live
     */
    private Integer isActive;


}
