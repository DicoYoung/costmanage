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
 * @since 2022-05-06
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CmCostItem extends BasePojo {

    private static final long serialVersionUID = 1L;

    /**
     * cost item name
     */
    private String name;

    /**
     * cost item type level,0 is top
     */
    private Integer type;

    /**
     * father item id
     */
    private Long parentId;

    /**
     * 0 is not have next,1 is have next
     */
    private Integer hasChild;

    /**
     * describe item
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
    private List<CmCostItem> children;
}
