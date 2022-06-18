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
 * @since 2022-04-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class CmFile extends BasePojo {

    private static final long serialVersionUID = 1L;

    /**
     * file md5,the file only code
     */
    private String md5;

    /**
     * file size
     */
    private Long size;

    /**
     * xxx of the file.xxx
     */
    private String suffix;

    /**
     * file address
     */
    private String url;

    /**
     * file update time
     */
    private String time;


}
