package com.bean;

import java.util.List;

/**
 * @Description Page类是供分页模块的模型对象
 * @ClassName Page
 * @author   mlp52
 * @createTime  2022/3/15 13:20
 * @return
 * @Param <T>可以用具体的JavaBean
 */
public class Page<T> {

    public static final Integer SIZE = 4;

    //当前页码
    private Integer pageNum;

    //总页面数
    private Integer pageTotal;

    //当前页面显示的数据个数
    private Integer pageSize = SIZE;

    //总记录的个数
    private Integer pageTotalCount;

    //当前页数据对象的list集合
    private List<T> pageItems;

    //页面url
    private String url;


    public Page() {
    }

    public Page(Integer pageNum, Integer pageTotal, Integer pageSize, Integer pageTotalCount, List<T> pageItems, String url) {
        this.pageNum = pageNum;
        this.pageTotal = pageTotal;
        this.pageSize = pageSize;
        this.pageTotalCount = pageTotalCount;
        this.pageItems = pageItems;
        this.url = url;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageTotalCount() {
        return pageTotalCount;
    }

    public void setPageTotalCount(Integer pageTotalCount) {
        this.pageTotalCount = pageTotalCount;
    }

    public List<T> getPageItems() {
        return pageItems;
    }

    public void setPageItems(List<T> pageItems) {
        this.pageItems = pageItems;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageNum=" + pageNum +
                ", pageTotal=" + pageTotal +
                ", pageSize=" + pageSize +
                ", pageTotalCount=" + pageTotalCount +
                ", pageItems=" + pageItems +
                ", url='" + url + '\'' +
                '}';
    }
}
