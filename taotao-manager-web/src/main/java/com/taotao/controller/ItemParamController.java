package com.taotao.controller;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.service.ItemParamService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 商品规格参数管理模板的controller
 * Created by 隽 on 2017/6/30.
 */
@Controller
@RequestMapping("/item/param")
public class ItemParamController
{
    @Resource
    private ItemParamService itemParamService;

    @RequestMapping("/query/itemcatid/{cid}")
    @ResponseBody
    public TaotaoResult checkItemParam(@PathVariable Long cid)
    {
        return itemParamService.checkParam(cid);
    }

    @RequestMapping("/save/{cid}")
    @ResponseBody
    public TaotaoResult insertItemParam(@PathVariable Long cid, String paramData)
    {
        return itemParamService.insertItemParam(cid, paramData);
    }

    @RequestMapping("/list")
    @ResponseBody
    public EasyUIDataGridResult getItemList(Integer page, Integer rows)
    {
        return itemParamService.getItemParamList(page, rows);
    }

    @RequestMapping("/cid/{cid}")
    @ResponseBody
    public TaotaoResult getItemParamByCid(@PathVariable Long cid) {
        TaotaoResult result = itemParamService.getItemParamByCid(cid);
        return result;
    }


}
