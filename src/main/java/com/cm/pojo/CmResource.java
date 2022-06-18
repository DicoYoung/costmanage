package com.cm.pojo;

import com.cm.pojo.BasePojo;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

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
public class CmResource extends BasePojo {

    private static final long serialVersionUID = 1L;

    /**
     * resouce name
     */
    private String name;

    /**
     * 0 front ; 1 back
     */
    private Integer type;

    /**
     * front url
     */
    private String frontUrl;

    /**
     * back url
     */
    private String backUrl;

    /**
     * father resource id,top is 0
     */
    private Long parentId;

    /**
     * 0 is not have next,1 is have next
     */
    private Integer haschildren;

    /**
     * active is or not
     */
    private Integer active;

    /**
     * 定义子集
     */
    @TableField(exist = false)
    private List<CmResource> children;

}
