package com.zarek.itrip.service.impl;

import com.zarek.itrip.dao.LabelDicDao;
import com.zarek.itrip.pojo.entity.ItripLabelDic;
import com.zarek.itrip.pojo.vo.ItripLabelDicVO;
import com.zarek.itrip.service.LabelDicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhao
 * @date 2021/3/25 14:31
 */
@Service("labelDicService")
@Transactional
public class LabelDicServiceImpl implements LabelDicService {
    @Autowired
    private LabelDicDao labelDicDao;

    /**
     * <b>根据查询获得信息列表</b>
     *
     * @param query
     * @return
     * @throws Exception
     */
    @Override
    public List<ItripLabelDicVO> getListByQuery(ItripLabelDic query) throws Exception
    {
        List<ItripLabelDicVO> labelDicVOList = new ArrayList<ItripLabelDicVO>();
        // 通过持久层查询结果
        List<ItripLabelDic> labelDicList = labelDicDao.findListByQuery(query);
        if(labelDicList != null){
            // 将实体列表转换为视图列表
            for (ItripLabelDic labelDic :
                    labelDicList) {
                ItripLabelDicVO itripLabelDicVO = new ItripLabelDicVO();
                itripLabelDicVO.setId(labelDic.getId());
                itripLabelDicVO.setName(labelDic.getName());
                itripLabelDicVO.setDescription(labelDic.getDescription());
                itripLabelDicVO.setPic(labelDic.getPic());
                labelDicVOList.add(itripLabelDicVO);
            }
        }

        return labelDicVOList;
    }
}
