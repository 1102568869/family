package tech.washmore.family.utils;


import org.apache.commons.collections4.CollectionUtils;
import tech.washmore.family.model.Bill;
import tech.washmore.family.model.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Washmore
 * @version V1.0
 * @summary TODO
 * @Copyright (c) 2018, Lianjia Group All Rights Reserved.
 * @since 2018/1/15
 */

public class PageUtil {

    public static Page fillPage(List list, int pageSize, int pageNo) {
        Page<Bill> page = new Page<>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setTotalCount(CollectionUtils.size(list));
        page.setList(CollectionUtils.isEmpty(list) ? new ArrayList<>() : list.subList(pageSize * (pageNo - 1), pageSize * pageNo > page.getTotalCount() ? page.getTotalCount() : pageSize * pageNo));
        return page;
    }


}
