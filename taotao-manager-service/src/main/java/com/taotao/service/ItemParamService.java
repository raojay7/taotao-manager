package com.taotao.service;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;

/**
 * 商品规格参数模板管理
 * Created by 隽 on 2017/6/30.
 */
public interface ItemParamService
{
    TaotaoResult getItemParamByCid(Long cid);
    TaotaoResult insertItemParam(Long cid,String paramData);

    /**
     * 根据当前页得到对应的easyUI分页对象
     * @param page 当前页
     * @param rows 每页多少行
     * @return
     */
    EasyUIDataGridResult getItemParamList(Integer page, Integer rows);

    TaotaoResult checkParam(Long cid);

    /**
     * 根据选择的id删除对应的参数模板
     * @param ids
     * @return
     */
    TaotaoResult deleteItemParamByIds(String[] ids);
}
