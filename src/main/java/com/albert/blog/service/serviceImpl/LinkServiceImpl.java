package com.albert.blog.service.serviceImpl;

import com.albert.blog.config.Cons;
import com.albert.blog.mapper.LinkMapper;
import com.albert.blog.pojo.Link;
import com.albert.blog.service.LinkService;
import com.albert.blog.utils.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 友链业务逻辑接口
 */
@Service
public class LinkServiceImpl implements LinkService {

    @Autowired
    private LinkMapper linkMapper;

    @Autowired
    private Cons cons;

    @Override
    public List<Link> listLink() {
        return this.linkMapper.getAllLink();
    }

    @Override
    public Link detail(int id) {
        return this.linkMapper.getLinkById(id);
    }

    @Override
    public boolean append(Link link) {
        return this.linkMapper.insert(link) > 0 ? true : false;
    }

    @Override
    public boolean modify(Link link) {
        return this.linkMapper.update(link) > 0 ? true : false;
    }

    @Override
    public boolean remove(int id) {
        return this.linkMapper.delete(id) > 0 ? true : false;
    }

    @Override
    public boolean isExistByBlogAddress(String blogAddress) {
        return this.linkMapper.isExistByBlogAddress(blogAddress) > 0 ? true : false;
    }

    @Override
    public Pager<Link> list(Link condition, Integer page) {

        int size = this.linkMapper.size(condition);
        List<Link> datas = this.linkMapper.getLinkByCondition(condition, (page - 1) * this.cons.getPage_size(), this.cons.getPage_size());

        return new Pager<Link>(datas, size, page, this.cons.getPage_size());
    }
}
