package com.nickless.blog.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * creat by nickless
 *
 * @Date 2020/1/14 19:03
 */
@Data
public class PaginationDto {
    private List<QuestionDto> questions;
    private boolean showPrevious; //向前
    private boolean showFirstPage; // 页数一
    private boolean showNext;  //下一页
    private boolean showEndPage; //最后一页
    private Integer currentPage; // 当前页
    private List<Integer> pages = new ArrayList<>();
    private Integer totalPage;

    public void setPagination(Integer totalcount, Integer page, Integer size) {


        if (totalcount % size == 0) {
            totalPage = totalcount / size;
        } else {
            totalPage = totalcount / size + 1;
        }

        this.currentPage = page;
        pages.add(currentPage);
        for (int i = 1; i <= 3; i++) {
            if (currentPage - i > 0) {
                pages.add(0, currentPage - 1);
            }
            if (currentPage + i <= totalPage) {
                pages.add(currentPage + i);
            }
        }

        // 是否展示前一页
        if (currentPage == 1) {
            showPrevious = false;
        } else {
            showPrevious = true;
        }
        // 是否展示下一页
        if (currentPage == totalPage) {
            showNext = false;
        } else {
            showNext = true;
        }
        // 是否展示第一页
        if (pages.contains(1)) {
            showFirstPage = false;
        } else {
            showFirstPage = true;
        }
        // 是否展示最后一页
        if (pages.contains(totalPage)) {
            showEndPage = false;
        } else {
            showEndPage = true;
        }
    }
}
