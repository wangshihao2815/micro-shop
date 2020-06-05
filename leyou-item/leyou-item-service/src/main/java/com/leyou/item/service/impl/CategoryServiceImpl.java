package com.leyou.item.service.impl;

import com.leyou.item.mapper.CategoryMapper;
import com.leyou.item.pojo.Category;
import com.leyou.item.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author wangshihao
 * @Date 2020/2/29
 * @Since 1.0.0
 */

@Service
@Transactional(rollbackFor = Exception.class)
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> queryCategoriesByPid(Long pid) {
        Category category = new Category();
        category.setParentId(pid);
        List<Category> categories = categoryMapper.select(category);
        return categories;
    }

    @Override
    public List<Category> queryByBrandId(Long id) {
        return categoryMapper.selectByBrandId(id);
    }

    @Override
    public List<String> selectNamesByIds(List<Long> ids) {
        List<Category> categories = categoryMapper.selectByIdList(ids);
        return categories.stream().map(category -> category.getName()).collect(Collectors.toList());
    }

    @Override
    public List<String> queryNamesByIds(List<Long> ids) {
        List<Category> categories = categoryMapper.selectByIdList(ids);
        List<String> categoryNames = categories.stream().map(category -> category.getName()).collect(Collectors.toList());
        return categoryNames;
    }

    @Override
    public List<Category> queryAllByCid3(Long id) {
        Category c3 = this.categoryMapper.selectByPrimaryKey(id);
        Category c2 = this.categoryMapper.selectByPrimaryKey(c3.getParentId());
        Category c1 = this.categoryMapper.selectByPrimaryKey(c2.getParentId());
        return Arrays.asList(c1, c2, c3);
    }

    @Override
    public void addCategory(Category category) {
        Long id = category.getParentId();
        Category category1 = new Category();
        category1.setId(id);
        Category cate = categoryMapper.selectOne(category1);
        cate.setIsParent(true);
        categoryMapper.updateByPrimaryKey(cate);
        categoryMapper.insertSelective(category);
    }

    @Override
    public void deleteCategory(Long id) {
        Category category = categoryMapper.selectByPrimaryKey(id);
        if (category.getIsParent()){
            Category category1 = new Category();
            category1.setParentId(id);
            categoryMapper.delete(category1);
            categoryMapper.deleteByPrimaryKey(id);
        }else {
            categoryMapper.deleteByPrimaryKey(id);
            Long parentId = category.getParentId();
            Category pacategory = categoryMapper.selectByPrimaryKey(parentId);
            Category category1 = new Category();
            category1.setParentId(parentId);
            List<Category> select = categoryMapper.select(category1);
            if (CollectionUtils.isEmpty(select)){
                pacategory.setIsParent(false);
                categoryMapper.updateByPrimaryKey(pacategory);
            }
        }
    }
}
