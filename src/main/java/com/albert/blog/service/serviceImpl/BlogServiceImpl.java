package com.albert.blog.service.serviceImpl;

import com.albert.blog.config.Cons;
import com.albert.blog.mapper.*;
import com.albert.blog.pojo.Blog;
import com.albert.blog.pojo.BlogQuery;
import com.albert.blog.pojo.Category;
import com.albert.blog.pojo.User;
import com.albert.blog.service.BlogService;
import com.albert.blog.utils.MarkdownUtils;
import com.albert.blog.utils.Pager;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 博客业务逻辑实现类
 */
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private Cons cons;

    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MessageMapper messageMapper;

    @Override
    public boolean append(Blog blog) {
        return this.blogMapper.insert(blog) > 0 ? true : false;
    }

    @Override
    public boolean remove(int id) {
        return this.blogMapper.delete(id) > 0 ? true : false;
    }

    @Override
    public boolean modify(Blog blog) {
        return this.blogMapper.update(blog) > 0 ? true : false;
    }

    @Override
    public Blog detail(int id) {
        Blog blog = this.blogMapper.getBlogById(id);
        Category category = this.categoryMapper.getCategoryById(blog.getCategoryId());
        blog.setCategory(category);
        return blog;
    }

    @Override
    public Blog detailWithMarkdown(int id) throws NotFoundException {

        Blog blog = this.blogMapper.getBlogById(id);

        if (blog == null)
            throw new NotFoundException("该博客不存在！");

        // 浏览数 + 1
        blog.setId(id);
        blog.setUser(this.userMapper.getUserById(blog.getUserId()));
        blog.setCategory(this.categoryMapper.getCategoryById(blog.getCategoryId()));
        blog.setViewsNum(blog.getViewsNum() + 1);

        this.blogMapper.update(blog);

        // 为了防止改变了原博客的内容，我们选择操作新的对象
        Blog newBlog = new Blog();
        BeanUtils.copyProperties(blog,newBlog);
        String content = newBlog.getContent();
        newBlog.setContent(MarkdownUtils.markdownToHtmlExtensions(content));

        return newBlog;
    }

    @Override
    public Pager<Blog> listByText(String text, int page) {

        int size = this.blogMapper.sizeByText(text);
        List<Blog> datas = this.blogMapper.searchBlog(text,(page - 1) * this.cons.getPage_size(), this.cons.getPage_size());

        return new Pager<Blog>(datas,size,page,this.cons.getPage_size());
    }

    @Override
    public Pager<Blog> list(int page) {

        int size = this.blogMapper.size();
        List<Blog> datas = this.blogMapper.listBlogs((page - 1) * this.cons.getPage_size(), this.cons.getPage_size());

        return new Pager<Blog>(datas,size,page,this.cons.getPage_size());
    }

    @Override
    public List<Blog> listAll() {
        return this.blogMapper.listAll();
    }

    @Override
    public Pager<BlogQuery> list(BlogQuery condition, int page) {

        int size = this.blogMapper.sizeByCondition(condition);
        List<BlogQuery> datas = this.blogMapper.getBlogQueryByCondition(condition,(page - 1) * this.cons.getPage_size(), this.cons.getPage_size());

        return new Pager<BlogQuery>(datas,size,page,this.cons.getPage_size());
    }

    @Override
    public Pager<Blog> listByCondition(Blog condition, int page) {

        int size = this.blogMapper.sizeByBlogCondition(condition);
        List<Blog> datas = this.blogMapper.listBlogsByCondition(condition,(page - 1) * this.cons.getPage_size(), this.cons.getPage_size());

        return new Pager<Blog>(datas,size,page,this.cons.getPage_size());
    }

    @Override
    public int sizeByCategoryId(Blog condition) {
        condition.setPublished(true);
        return this.blogMapper.sizeByBlogCondition(condition);
    }

    @Override
    public List<Blog> listByRecommend() {
        return this.blogMapper.listByRecommend();
    }

    @Override
    public int sumOfBlog() {
        return this.blogMapper.sumOfBlogs();
    }

    @Override
    public int sumOfComment() {
        return this.commentMapper.sumOfComment();
    }

    @Override
    public int sumOfViews() {
        return this.blogMapper.sumOfViews();
    }

    @Override
    public int sumOfMessages() {
        return this.messageMapper.sumOfMessage();
    }
}
