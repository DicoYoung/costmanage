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
 * @since 2022-05-12
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CmProcedureEnergy extends BasePojo {

    private static final long serialVersionUID = 1L;

    /**
     * id of the procedure
     */
    private Long procedureId;

    /**
     * id of energy
     */
    private Long energyType;

    /**
     * name of energy
     */
    private String energyName;

    /**
     * number of used energy
     */
    private Long energyNumber;

    /**
     * price of each energy
     */
    private Long energyPrice;

    /**
     * this all the cost
     */
    private Long allCost;

    /**
     * 0 is ban,1 is live
     */
    private Integer active;
}
