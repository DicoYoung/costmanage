package com.cm.mapper;

import com.cm.pojo.CmResource;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author Dico
 * @since 2022-05-02
 */
public interface CmResourceMapper extends BaseMapper<CmResource> {
    List<CmResource> getByUserId(Long userId);
}
