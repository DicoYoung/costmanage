package com.cm.pojo;

import com.cm.pojo.BasePojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 *
 * </p>
 *
 * @author Dico
 * @since 2022-05-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class CmProcess extends BasePojo {

    private static final long serialVersionUID = 1L;

    /**
     * job id
     */
    private String jobId;

    /**
     * working procedure
     */
    private Long procedureId;

    /**
     * equipment
     */
    private String equipment;

    /**
     * team
     */
    private String team;

    /**
     * raw material
     */
    private String rawMaterial;

    /**
     * raw material used number
     */
    private Long rawNumber;

    /**
     * product id
     */
    private Integer productId;

    /**
     * product number
     */
    private Long productNumber;

    /**
     * this process begin time
     */
    private String beginTime;

    /**
     * this process end time
     */
    private String endTime;

    /**
     * this process is or not end
     */
    private Integer isEnd;

    /**
     * this process is or not to next process
     */
    private Integer isNext;


}
