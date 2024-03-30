package com.example.user.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PagePara {
    @JsonProperty("当前页数，即需要查询的页码")
    @ApiModelProperty(value="当前页数，即需要查询的页码")
    private Long nowPage;

    @JsonProperty("每页数据条数")
    @ApiModelProperty(value="每页数据条数")
    private Long onePageCount;

    @JsonProperty("数据总条数")
    @ApiModelProperty(value="数据总条数")
    private Long dataCount;

    @JsonProperty("总页数，即数据总条数除以每页数据条数的结果")
    @ApiModelProperty(value="总页数，即数据总条数除以每页数据条数的结果")
    private Long pageCount;

    @JsonProperty("分页查询时需要从哪一条数据开始查询")
    @ApiModelProperty(value="分页查询时需要从哪一条数据开始查询")
    private Long startIndex;

    @JsonProperty("排序字段和排序方式")
    @ApiModelProperty(value="排序字段和排序方式")
    private String orderBy;

    public PagePara() {

    }
    public PagePara(long nowPage, long onePageCount, long dataCount, long pageCount) {
        this.nowPage = nowPage;
        this.onePageCount = onePageCount;
        this.dataCount = dataCount;
        this.pageCount = pageCount;
    }
}

