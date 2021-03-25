package com.zarek.itrip.dao;

import com.zarek.itrip.pojo.entity.ItripLabelDic;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <b>系统字典信息数据持久层接口</b>
 * @author zarek
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository
public interface LabelDicDao {

    /**
     * <b>按照查询条件查询信息列表</b>
     * @param query
     * @return
     * @throws Exception
     */
    public List<ItripLabelDic> findListByQuery(ItripLabelDic query) throws Exception;
}