package com.leyou.search.service;

import com.leyou.common.pojo.PageResult;
import com.leyou.item.pojo.Spu;
import com.leyou.search.pojo.Goods;
import com.leyou.search.pojo.SearchRequest;

import java.io.IOException;

/**
 * @Author wangshihao
 * @Date 2020/3/12
 * @Since 1.0.0
 */

public interface SearchService {

    /**
     * 构建商品查询数据
     *
     * @param spu
     * @return
     */
    Goods buildGoods(Spu spu) throws Exception;

    /**
     * 搜索商品
     * @param searchRequest
     * @return
     */
    PageResult<Goods> searchGoods(SearchRequest searchRequest);

    /**
     * 创建或者更新索引信息
     * @param id
     */
    void createIndex(Long id) throws IOException;

    /**
     * 删除索引信息
     * @param id
     */
    void deleteIndex(Long id);
}
