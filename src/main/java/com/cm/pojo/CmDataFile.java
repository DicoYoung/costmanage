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
 * @since 2022-05-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class CmDataFile extends BasePojo {

    private static final long serialVersionUID = 1L;

    /**
     * datafile name
     */
    private String name;

    /**
     * file back name
     */
    private String suffix;

    /**
     * file url
     */
    private String url;

    /**
     * file size k
     */
    private Long size;

    /**
     * file only one number
     */
    private String md5;

    /**
     * file last update time
     */
    private String time;


}
