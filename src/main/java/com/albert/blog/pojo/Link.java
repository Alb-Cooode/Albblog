package com.albert.blog.pojo;

/**
 * 友链实体类
 */
public class Link {

    private int id;

    private String blogAddress;

    private String blogName;

    private String imageAddress;

    private String createTime;

    @Override
    public String toString() {
        return "Link{" +
                "id=" + id +
                ", blogAddress='" + blogAddress + '\'' +
                ", blogName='" + blogName + '\'' +
                ", imageAddress='" + imageAddress + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBlogAddress() {
        return blogAddress;
    }

    public void setBlogAddress(String blogAddress) {
        this.blogAddress = blogAddress;
    }

    public String getBlogName() {
        return blogName;
    }

    public void setBlogName(String blogName) {
        this.blogName = blogName;
    }

    public String getImageAddress() {
        return imageAddress;
    }

    public void setImageAddress(String imageAddress) {
        this.imageAddress = imageAddress;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
