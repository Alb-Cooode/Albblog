package com.albert.blog.service.serviceImpl;

import com.albert.blog.config.Cons;
import com.albert.blog.mapper.PictureMapper;
import com.albert.blog.pojo.Picture;
import com.albert.blog.service.PictureService;
import com.albert.blog.utils.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 相册数据访问实现类
 */
@Service
public class PictureServiceImpl implements PictureService {

    @Autowired
    private PictureMapper pictureMapper;

    @Autowired
    private Cons cons;

    @Override
    public List<Picture> listPicture() {
        return this.pictureMapper.getAllPicture();
    }

    @Override
    public Picture detail(int id) {
        return this.pictureMapper.getPictureById(id);
    }

    @Override
    public boolean append(Picture picture) {
        return this.pictureMapper.insert(picture) > 0 ? true : false;
    }

    @Override
    public boolean remove(int id) {
        return this.pictureMapper.delete(id) > 0 ? true : false;
    }

    @Override
    public boolean modify(Picture picture) {
        return this.pictureMapper.update(picture) > 0 ? true : false;
    }

    @Override
    public Pager<Picture> list(Picture condition, Integer page) {

        int size = this.pictureMapper.size(condition);
        List<Picture> datas = this.pictureMapper.getPictureByCondition(condition,(page - 1) * this.cons.getPage_size(), this.cons.getPage_size());

        return new Pager<Picture>(datas, size, page, this.cons.getPage_size());
    }

    @Override
    public boolean isExistByPictureAddress(String pictureAddress) {
        return this.pictureMapper.isExistByPictureAddress(pictureAddress) > 0 ? true : false;
    }
}
