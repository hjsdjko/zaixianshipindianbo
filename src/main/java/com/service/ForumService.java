package com.service;

import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.ForumEntity;
import java.util.Map;

/**
 * 论坛 服务类
 * @author 
 * @since 2021-04-05
 */
public interface ForumService extends IService<ForumEntity> {

    /**
    * @param params 查询参数
    * @return 带分页的查询出来的数据
    */
     PageUtils queryPage(Map<String, Object> params);
}