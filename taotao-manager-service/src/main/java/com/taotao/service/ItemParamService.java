package com.taotao.service;

import com.taotao.common.pojo.TaotaoResult;

/**
 * 商品规格参数模板管理
 * Created by 隽 on 2017/6/30.
 */
public interface ItemParamService
{
    TaotaoResult getItemParamByCid(Long cid);
    TaotaoResult insertItemParam(Long cid,String paramData);
}
