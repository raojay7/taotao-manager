package com.taotao.service.impl;

import com.taotao.common.pojo.TreeNode;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 隽 on 2017/6/23.
 */
public class ItemCatServiceImpl implements ItemCatService
{

    @Autowired
    private TbItemCatMapper itemCatMapper;

    /**
     * 根据parentid查询分类列表
     * @param parentId
     * @return
     */
    @Override
    public List<TreeNode> getItemCatList(long parentId)
    {
        TbItemCatExample example=new TbItemCatExample();
        TbItemCatExample.Criteria criteria = example.createCriteria();
        //设置查询条件
        criteria.andParentIdEqualTo(parentId);
        //执行查询
        List<TbItemCat> list = itemCatMapper.selectByExample(example);
        //转换为treenode列表
        List<TreeNode> result=new ArrayList<>();
        for(TbItemCat itemCat:list)
        {
            TreeNode node=new TreeNode(itemCat.getId(),itemCat.getName(),
                (itemCat.getIsParent())?"closed":"open");
            result.add(node);
        }
        return result;
    }
}
