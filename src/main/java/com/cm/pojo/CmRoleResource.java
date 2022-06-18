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
 * @since 2022-05-02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class CmRoleResource extends BasePojo {

    private static final long serialVersionUID = 1L;

    /**
     * the id of role
     */
    private Long roleId;

    /**
     * the id of resource
     */
    private Long resourceId;


}
