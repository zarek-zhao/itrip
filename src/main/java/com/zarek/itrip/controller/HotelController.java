package com.zarek.itrip.controller;

import com.zarek.itrip.pojo.dto.Dto;
import com.zarek.itrip.pojo.entity.ItripLabelDic;
import com.zarek.itrip.pojo.vo.ItripAreaDicVO;
import com.zarek.itrip.pojo.vo.ItripLabelDicVO;
import com.zarek.itrip.service.AreaDicService;
import com.zarek.itrip.service.LabelDicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <b>爱旅行-酒店功能控制层</b>
 * @author zarek
 * @date 2021/3/24 19:32
 */

@RestController("hotelController")
@RequestMapping("/biz/api/hotel")
public class HotelController {
    @Autowired
    private AreaDicService areaDicService;
    @Autowired
    private LabelDicService labelDicService;
    /**
     * <b>查询国内、国外的热门城市(1.国内 2.国外)</b>
     * @param type
     * @return
     * @throws Exception
     */
    @GetMapping("/queryhotcity/{type}")
    public Dto<Object> queryhotcity(@PathVariable("type") Integer type) throws Exception{

        //  创建返回对象
        Dto<Object> dto = new Dto<Object>();
        // 查询热门城市集合
        List<ItripAreaDicVO> list = areaDicService.getListByType(type);
        dto.setSuccess("true");
        dto.setData(list);
        return dto;
    }

    /**
     * <b>查询酒店特色</b>
     * @return
     * @throws Exception
     */
    @GetMapping("/queryhotelfeature")
    public Dto<Object> queryHotelFeature() throws Exception{
        // 创建查询对象
        ItripLabelDic query = new ItripLabelDic();
        // 酒店特色储存的上级主键为 16
        query.setParentId(16L);
        // 进行查询
        List<ItripLabelDicVO> labelDicVOList = labelDicService.getListByQuery(query);

        Dto<Object> dto = new Dto<Object>();
        dto.setSuccess("ture");
        dto.setData(labelDicVOList);

        return dto;
    }

}










