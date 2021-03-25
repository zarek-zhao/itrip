package com.zarek.itrip.service.impl;

import com.zarek.itrip.dao.AreaDicDao;
import com.zarek.itrip.pojo.entity.ItripAreaDic;
import com.zarek.itrip.pojo.vo.ItripAreaDicVO;
import com.zarek.itrip.service.AreaDicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <b>酒店区域信息业务层接口实现类</b>
 * @author zarek
 * @version 1.0.0
 * @since 1.0.0
 */
@Service("areaDicService")
@Transactional
public class AreaDicServiceImpl implements AreaDicService {

    @Autowired
    private AreaDicDao areaDicDao;
    /**
     * <b>根据是否是国内外城市查询列表</b>
     * @param type
     * @return
     * @throws Exception
     */
    @Override
    public List<ItripAreaDicVO> getListByType(Integer type) throws Exception
    {
        // 创建查询对象
        ItripAreaDic query = new ItripAreaDic();
        query.setIsChina(type);
        // 根据查询对象查询列表
        List<ItripAreaDic> areaDicList = areaDicDao.findListByQuery(query);
        // 将实体对象切换为视图列表
        List<ItripAreaDicVO> areaDicVOList = new ArrayList<ItripAreaDicVO>();
        for (ItripAreaDic areaDic :
                areaDicList) {
            // 将实体对象数据移动到视图对象中
            ItripAreaDicVO areaDicVO = new ItripAreaDicVO();
            areaDicVO.setId(areaDic.getId());
            areaDicVO.setName(areaDic.getName());
            areaDicVOList.add(areaDicVO);
        }
        return areaDicVOList;
    }
}
