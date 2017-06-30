package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.ExceptionUtil;
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
        TbItemParamExample example = new TbItemParamExample();
        TbItemParamExample.Criteria criteria = example.createCriteria();
        criteria.andItemCatIdEqualTo(cid);
        List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(example);
        //判断是否查询到结果
        if (list != null && !list.isEmpty())
        {
            return TaotaoResult.ok(list.get(0));
        }
        return TaotaoResult.build(400, "此分类未定义规格模板");
    }

    @Override
    public TaotaoResult insertItemParam(Long cid, String paramData)
    {
        TbItemParam itemParam = new TbItemParam();
        itemParam.setItemCatId(cid);
        itemParam.setParamData(paramData);
        Date date = new Date();
        itemParam.setCreated(date);
        itemParam.setUpdated(date);
        //插入记录
        itemParamMapper.insert(itemParam);
        return TaotaoResult.ok();
    }

    @Override
    public EasyUIDataGridResult getItemParamList(Integer page, Integer rows)
    {
        //分页处理
        PageHelper.startPage(page, rows);
        //执行查询
        TbItemParamExample example = new TbItemParamExample();
        List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(example);
        //取分页信息
        PageInfo<TbItemParam> pageInfo = new PageInfo<TbItemParam>(list);
        //返回处理结果
        EasyUIDataGridResult result = new EasyUIDataGridResult(pageInfo.getTotal(), list);
        return result;
    }

    @Override
    public TaotaoResult checkParam(Long cid)
    {
        try
        {
            TbItemParamExample example = new TbItemParamExample();
            TbItemParamExample.Criteria criteria = example.createCriteria();
            criteria.andItemCatIdEqualTo(cid);
            List<TbItemParam> list = itemParamMapper.selectByExample(example);
            // 判断是否查询到结果
            if (null == list || list.isEmpty())
            {
                return TaotaoResult.ok();
            }
            return TaotaoResult.ok(list.get(0));
        }
        catch (Exception e)
        {
            return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
    }



}
