package com.zarek.itrip.dao;

import com.zarek.itrip.pojo.entity.ItripAreaDic;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zarek
 * @date 2021/3/24 19:38
 */
@Repository
public interface AreaDicDao {

    /**
     * <b>按照查询条件查询信息列表</b>
     * @param itripAreaDic
     * @return
     * @throws Exception
     */
    public List<ItripAreaDic> findListByQuery(ItripAreaDic itripAreaDic) throws Exception;

}
