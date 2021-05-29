package com.albert.blog.service.serviceImpl;

import com.albert.blog.config.Cons;
import com.albert.blog.mapper.CategoryMapper;
import com.albert.blog.pojo.Category;
import com.albert.blog.service.CategoryService;
import com.albert.blog.utils.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 分类业务逻辑实现类
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private Cons cons;

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> listCategory() {
        return this.categoryMapper.getAllCategoty();
    }

    @Override
    public Category detail(int id) {
        return this.categoryMapper.getCategoryById(id);
    }

    @Override
    public boolean append(Category category) {
        return this.categoryMapper.insert(category) > 0 ? true : false;
    }

    @Override
    public boolean modify(Category category) {
        return this.categoryMapper.update(category) > 0 ? true : false;
    }

    @Override
    public boolean remove(int id) {
        return this.categoryMapper.delete(id) > 0 ? true : false;
    }

    @Override
    public boolean isExistByCatName(String catName) {
        return this.categoryMapper.isExistByCatName(catName) > 0 ? true : false;
    }

    @Override
    public boolean isUsedByBlog(int id) {
        return this.categoryMapper.isUsedByBlog(id) > 0 ? true : false;
    }

    @Override
    public Pager<Category> list(Category condition, Integer page) {

        int size = this.categoryMapper.size(condition);
        List<Category> datas = this.categoryMapper.getCategoryByCondition(condition,(page - 1) * this.cons.getPage_size(),this.cons.getPage_size());

        return new Pager<Category>(datas,size,page,this.cons.getPage_size());
    }
}
