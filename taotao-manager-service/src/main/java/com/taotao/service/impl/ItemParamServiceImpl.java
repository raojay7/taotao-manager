package com.taotao.service.impl;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamExample;
import com.taotao.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 商品规格参数的模板
 * 如手机有电池容量，内存大小等东西，用于添加这样的模板
 * Created by 隽 on 2017/6/30.
 */
@Service
public class ItemParamServiceImpl implements ItemParamService
{
    @Autowired
    private TbItemParamMapper itemParamMapper;

    @Override
    public TaotaoResult getItemParamByCid(Long cid)
    {
        //根据cid查询规格参数模板
        TbItemParamExample example=new TbItemParamExample();
        TbItemParamExample.Criteria criteria = example.createCriteria();
        criteria.andItemCatIdEqualTo(cid);
        List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(example);
        //判断是否查询到结果
        if(list!=null&list.size()>0)
        {
            TbItemParam itemParam=list.get(0);
            return TaotaoResult.ok(itemParam);
        }
        return TaotaoResult.ok();
    }

    @Override
    public TaotaoResult insertItemParam(Long cid, String paramData)
    {
        TbItemParam itemParam=new TbItemParam();
        itemParam.setItemCatId(cid);
        itemParam.setParamData(paramData);
        Date date=new Date();
        itemParam.setCreated(date);
        itemParam.setUpdated(date);
        //插入记录
        itemParamMapper.insert(itemParam);
        return TaotaoResult.ok();
    }
}
