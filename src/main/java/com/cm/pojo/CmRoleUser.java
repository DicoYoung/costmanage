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
public class CmRoleUser extends BasePojo {

    private static final long serialVersionUID = 1L;

    /**
     * role id
     */
    private Long roleId;

    /**
     * user id
     */
    private Long userId;


}
