package com.albert.blog.utils;

import java.util.List;

/**
 * 分页类
 * 
 * @author Albert
 *
 * @param <T> 泛型
 */
public class Pager<T> {

	/**
	 * 分页类的带参数构造方法
	 * 
	 * @param datas 本页数据
	 * @param size  总记录数
	 * @param page  页码
	 */
	public Pager(List<T> datas, int size, int page, int page_size) {
		super();
		this.page = page;
		this.size = size;
		this.datas = datas;
		this.page_size = page_size;

		this.setAttribute();
		this.setPage_array();
	}

	/**
	 * 页容量
	 */
	private int page_size;

	/**
	 * 页码
	 */
	private int page;

	/**
	 * 是否有上一页
	 */
	private boolean has_pre;

	/**
	 * 上一页
	 */
	private int pre;

	/**
	 * 下一页
	 */
	private int next;

	/**
	 * 是否有下一页
	 */
	private boolean has_next;

	/**
	 * 首页
	 */
	private int first;

	/**
	 * 末页
	 */
	private int last;

	/**
	 * 总页数
	 */
	private int pages;

	/**
	 * 总记录数
	 */
	private int size;

	/**
	 * 本页的数据
	 */
	private List<T> datas;

	/**
	 * 页码数组
	 */
	private int[] page_array;

	/**
	 * 设置需要计算的属性的值
	 */
	private void setAttribute() {

		if (this.size != 0)
			this.pages = this.size % this.page_size == 0 ? this.size / this.page_size : this.size / this.page_size + 1;
		else
			this.pages = 1;

		if (this.page < 1)
			this.page = 1;

		if (this.page > this.pages)
			this.page = this.pages;

		if (this.page > 1)
			this.pre = this.page - 1;
		else
			this.pre = 1;

		if (this.page < this.pages)
			this.next = this.page + 1;
		else
			this.next = this.pages;

		this.first = 1;
		this.last = this.pages;

		if (this.page <= this.first)
			this.has_pre = false;
		else
			this.has_pre = true;

		if (this.page >= this.pages)
			this.has_next = false;
		else
			this.has_next = true;
	}

	/**
	 * 设置页码数组
	 */
	private void setPage_array() {
		
		int start = 1;

		if (this.pages >= 5) {

			this.page_array = new int[5];		

			if (this.page > (start + this.page_array.length - 1) / 2 + 1)
				start = this.page - 2;

			while (true) {
				if (this.pages - start <= 3)
					start--;
				else
					break;
			}
		}else
			this.page_array=new int[this.pages];

		for (int i = 0; i < page_array.length; i++) {
			this.page_array[i] = start;
			start++;
		}

	}

	/**
	 * 获取页容量
	 * 
	 * @return 页容量
	 */
	public int getPage_size() {
		return this.page_size;
	}

	/**
	 * 获取页码
	 * 
	 * @return 页码
	 */
	public int getPage() {
		return page;
	}

	/**
	 * 获取上一页
	 * 
	 * @return 上一页
	 */
	public int getPre() {
		return pre;
	}

	/**
	 * 获取下一页
	 * 
	 * @return 下一页
	 */
	public int getNext() {
		return next;
	}

	/**
	 * 获取首页
	 * 
	 * @return 首页
	 */
	public int getFirst() {
		return first;
	}

	/**
	 * 获取末页
	 * 
	 * @return 末页
	 */
	public int getLast() {
		return last;
	}

	/**
	 * 获取总页数
	 * 
	 * @return 总页数
	 */
	public int getPages() {
		return pages;
	}

	/**
	 * 获取总记录数
	 * 
	 * @return 总记录数
	 */
	public int getSize() {
		return size;
	}

	/**
	 * 获取本页数据
	 * 
	 * @return 本页数据
	 */
	public List<T> getDatas() {
		return datas;
	}

	/**
	 * 判断是否有上一页
	 * 
	 * @return
	 */
	public boolean isHas_pre() {
		return has_pre;
	}

	/**
	 * 判断是否有下一页
	 * 
	 * @return
	 */
	public boolean isHas_next() {
		return has_next;
	}

	/**
	 * 获取页码数组
	 * 
	 * @return
	 */
	public int[] getPage_array() {
		return page_array;
	}
}
