package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.IDUtils;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemExample;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService
{

    @Autowired
    private TbItemMapper itemMapper;


    @Autowired
    private TbItemDescMapper itemDescMapper;


    @Autowired
    private TbItemParamItemMapper itemParamItemMapper;

    @Override
    public TbItem getItemById(long itemId)
    {

        TbItem item = itemMapper.selectByPrimaryKey(itemId);

        return item;
    }

    @Override
    public EasyUIDataGridResult getItemList(int page, int rows)
    {
        //分页处理
        PageHelper.startPage(page,rows);
        //执行查询
        TbItemExample example=new TbItemExample();
        List<TbItem> list = itemMapper.selectByExample(example);
        //取分页信息
        PageInfo<TbItem> pageInfo=new PageInfo<TbItem>(list);
        //返回处理结果
        EasyUIDataGridResult result=new EasyUIDataGridResult(pageInfo.getTotal(),list);
        return result;
    }

    @Override
    public TaotaoResult addItem(TbItem item, TbItemDesc itemDesc, String itemParams)
    {
        //1设置必要属性(id,状态，时间)并保存商品
        //生成商品id
        //可以使用redis的自增长key，在没有redis之前使用时间+随机数策略生成
        Long itemId = IDUtils.genItemId();
        item.setId(itemId);
        // '商品状态，1-正常，2-下架，3-删除'
        item.setStatus((byte)1);
        // 创建时间和更新时间
        Date date = new Date();
        item.setCreated(date);
        item.setUpdated(date);
        //保存
        itemMapper.insert(item);

        //2设置必要属性保存商品描述
        itemDesc.setItemId(itemId);
        itemDesc.setCreated(date);
        itemDesc.setUpdated(date);
        itemDescMapper.insert(itemDesc);
        //3设置商品规格参数
        TbItemParamItem itemParamItem = new TbItemParamItem();
        itemParamItem.setItemId(itemId);
        itemParamItem.setParamData(itemParams);
        itemParamItem.setCreated(date);
        itemParamItem.setUpdated(date);
        itemParamItemMapper.insert(itemParamItem);
        return TaotaoResult.ok();
    }

}
