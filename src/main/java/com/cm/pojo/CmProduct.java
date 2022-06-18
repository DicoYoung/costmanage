package com.cm.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.cm.pojo.BasePojo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author Dico
 * @since 2022-05-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CmProduct extends BasePojo {

    private static final long serialVersionUID = 1L;

    /**
     * product name
     */
    private String name;

    /**
     * 0 is main product,1 is sub product
     */
    private Integer type;

    /**
     * product measure unit
     */
    private String unit;

    /**
     * product price of each unit
     */
    private String price;

    /**
     * father item id
     */
    private Long parentId;

    /**
     * 0 is not have next,1 is have next
     */
    private Integer hasChild;

    /**
     * describe product
     */
    private String remarks;

    /**
     * 0 is ban ,1 is live
     */
    private Integer active;

    /**
     * 定义子集
     */
    @TableField(exist = false)
    private List<CmProduct> children;
}
