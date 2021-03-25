package com.zarek.itrip.service;

import com.zarek.itrip.pojo.entity.ItripLabelDic;
import com.zarek.itrip.pojo.vo.ItripLabelDicVO;

import java.util.List;

/**
 * <b>爱旅行-系统字典信息业务层接口</b>
 * @author zarek
 * @version 1.0.0
 * @since 1.0.0
 */
public interface LabelDicService {
    /**
     * <b>根据查询获得信息列表</b>
     * @param query
     * @return
     * @throws Exception
     */
    List<ItripLabelDicVO> getListByQuery(ItripLabelDic query) throws Exception;
}
