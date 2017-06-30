package com.taotao.service;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;

public interface ItemService
{

    /**
     * 根据商品id查询对应的商品
     * @param itemId 商品id
     * @return 商品对象
     */
    TbItem getItemById(long itemId);

    /**
     * 用于easyui的分页查询
     * @param page 当前页数
     * @param rows 每页显示多少行
     * @return 封装的easyui分页对象；、
     * 其核心熟悉有：总记录数total、满足分页查询条件返回的list数据
     */
    EasyUIDataGridResult getItemList(int page, int rows);

    /**
     * 新增商品
     * @param item 有了具体数据的商品对象
     * @param itemDesc 商品的描述
     * @param itemParams 商品的规格参数
     * @return
     */
    TaotaoResult addItem(TbItem item, TbItemDesc itemDesc, String itemParams);
}
