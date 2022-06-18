package com.cm.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class BasePojo {
    @TableId(type = IdType.AUTO)//表示数据库里主键自增，此注解告诉mybatis-plus不用设置主键，因为数据库已将其设置为自增长
    private Long id;
}
