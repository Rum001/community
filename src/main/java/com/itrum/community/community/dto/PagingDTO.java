package com.itrum.community.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PagingDTO {
    private Integer pageNum; //当前页
    private Integer pageSize;//当前显示的条数
    private Integer totalPage;//当前的总页数
    private Long totalCount;//当前的总条数

    private List<Integer> pages = new ArrayList<>();//保存当前页的集合

    private Boolean showPrevious;//是否显示上一页
    private Boolean showNext;//是否显示下一页
    private Boolean showFirstPage;//是否显示第一页
    private Boolean showEndPage;//是否显示最后一页

    public PagingDTO(Integer pageNum, Integer pageSize, Long totalCount, Integer totalPage) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.totalPage = totalPage;
        this.totalCount = totalCount;
        pages.add(pageNum);
        for (int i = 1; i <= 3; i++) {
            if (pageNum - i > 0) {
                pages.add(0, pageNum - i);
            }
            if (pageNum + i <= totalPage) {
                pages.add(pageNum + i);
            }
        }
        if (pageNum == 1)
            showPrevious = false;
        else
            showPrevious = true;
        if (pageNum.equals(totalPage))
            showNext = false;
        else
            showNext = true;
        if (pages.contains(1))
            showFirstPage = false;
        else
            showFirstPage = true;
        if (pages.contains(totalPage))
            showEndPage = false;
        else
            showEndPage = true;
    }
}
