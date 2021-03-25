package com.zarek.itrip.service;

import com.zarek.itrip.pojo.vo.ItripAreaDicVO;

import java.util.List;

/**
 * @author zhao
 * @date 2021/3/24 19:46
 */

public interface AreaDicService {

    /**
     * <b>根据是否是国内外城市查询列表</b>
     * @param type
     * @return
     * @throws Exception
     */
    List<ItripAreaDicVO> getListByType(Integer type) throws Exception;
}
