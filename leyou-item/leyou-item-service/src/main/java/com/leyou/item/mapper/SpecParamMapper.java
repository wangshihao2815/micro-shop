package com.leyou.item.mapper;

import com.leyou.item.pojo.SpecParam;
import tk.mybatis.mapper.additional.idlist.SelectByIdListMapper;
import tk.mybatis.mapper.common.Mapper;

/**
 * @Author wangshihao
 * @Date 2020/3/5
 * @Since 1.0.0
 */

public interface SpecParamMapper extends Mapper<SpecParam>, SelectByIdListMapper<SpecParam, Long> {

}
