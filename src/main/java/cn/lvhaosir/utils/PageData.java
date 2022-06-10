package cn.lvhaosir.utils;


import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PageData<T> implements Serializable {

    private static final long serialVersionUID = 4075259339793751406L;
    private long total;
    private List<T> list;

    public PageData(List<T> list, long total) {
        this.total = total;
        this.list = list;
    }

}
