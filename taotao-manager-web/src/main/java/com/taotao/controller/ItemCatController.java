package com.taotao.controller;

import com.taotao.common.pojo.TreeNode;
import com.taotao.service.ItemCatService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 商品分类管理controller
 * Created by 隽 on 2017/6/23.
 */
@Controller
@RequestMapping("/item/cat")
public class ItemCatController
{
    @Resource
    private ItemCatService itemCatService;

    @RequestMapping("/list")
    @ResponseBody
    public List<TreeNode> getItemCatList(@RequestParam(value="id",defaultValue ="0") long parentId)
    {
        return itemCatService.getItemCatList(parentId);
    }


}
