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
 * @since 2022-05-02
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CmRole extends BasePojo {

    private static final long serialVersionUID = 1L;

    /**
     * role name
     */
    private String name;

    /**
     * is or not active
     */
    private Integer active;


}
