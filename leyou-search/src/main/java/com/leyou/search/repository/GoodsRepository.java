package com.leyou.search.repository;

import com.leyou.search.pojo.Goods;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @Author wangshihao
 * @Date 2020/3/12
 * @Since 1.0.0
 */

public interface GoodsRepository extends ElasticsearchRepository<Goods,Long> {

}
