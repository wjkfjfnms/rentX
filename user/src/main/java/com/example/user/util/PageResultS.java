package com.example.user.util;

import com.example.user.vo.PagePara;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class PageResultS<T> {

//    @JsonProperty("存储查询出来的数据")
    private List<T> list;

    @JsonProperty("分页信息数据")
    private PagePara page;
}

