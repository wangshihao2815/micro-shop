package com.leyou.item.controller;

import com.leyou.item.pojo.Category;
import com.leyou.item.service.CategoryService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author wangshihao
 * @Date 2020/2/29
 * @Since 1.0.0
 */

@Controller
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 根据parentId查询子类目
     *
     * @param pid
     * @return
     */
    @GetMapping("/list")
    public ResponseEntity<List<Category>> queryCategoriesByPid(@RequestParam(name = "pid", defaultValue = "0") Long pid) {
        List<Category> categoryList = categoryService.queryCategoriesByPid(pid);

        //没有数据返回404
        if (CollectionUtils.isEmpty(categoryList)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(categoryList);
    }

    /**
     * 根据品牌id查询分类(修改品牌时回显所属分类)
     *
     * @return
     */
    @GetMapping("bid/{id}")
    public ResponseEntity<List<Category>> queryByBrandId(@PathVariable("id") Long id) {
        List<Category> categories = categoryService.queryByBrandId(id);
        if (CollectionUtils.isEmpty(categories)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(categories);
    }

    /**
     * 根据id查询分类的名称(集合)
     *
     * @param ids
     * @return
     */
    @GetMapping("/names")
    public ResponseEntity<List<String>> queryNamesByIds(@RequestParam("ids") List<Long> ids) {
        List<String> categoryNames = categoryService.queryNamesByIds(ids);
        if (CollectionUtils.isEmpty(categoryNames)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(categoryNames);
    }

    /**
     * 根据3级分类id，查询1~3级的分类
     *
     * @param id
     * @return
     */
    @GetMapping("all/level")
    public ResponseEntity<List<Category>> queryAllByCid3(@RequestParam("id") Long id) {
        List<Category> list = this.categoryService.queryAllByCid3(id);
        if (CollectionUtils.isEmpty(list)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(list);
    }
    /*
       新增分类
     */
    @PostMapping("add")
    public ResponseEntity<Void> addCategory(@RequestBody Category category){
         categoryService.addCategory(category);
         return ResponseEntity.noContent().build();
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable("id") Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
