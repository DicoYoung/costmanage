package com.cm.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.cm.pojo.BasePojo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author Dico
 * @since 2022-04-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CmUser extends BasePojo {

    private static final long serialVersionUID = 1L;

    /**
     * the login name
     */
    private String loginName;

    /**
     * real name
     */
    private String trueName;

    /**
     * login password(encoded)
     */
    private String password;

    /**
     * phone number
     */
    private String phone;

    /**
     * email address
     */
    private String email;

    /**
     * 1 is active,0 is banned
     */
    private Integer active;

    /**
     * user avatar
     */
    private String icon;

    /**
     * user age
     */
    private Integer age;

    /**
     * user gender( 1 for man,0 for woman)
     */
    private Integer gender;

    /**
     * user home address
     */
    private String address;

    /**
     * user type
     * 0 for 超管;1 for 财务;2 for 车间;3 for 核算;4 for 厂务;5 for 分析
     */
    private Integer type;

    /**
     * password(not encoded)
     */
    @TableField(exist = false)//this is not DB Table column
    private String rawPassword;

}
