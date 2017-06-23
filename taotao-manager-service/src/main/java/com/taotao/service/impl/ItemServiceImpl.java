package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TreeNode;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.pojo.TbItemExample;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService
{

    @Autowired
    private TbItemMapper itemMapper;
    @Autowired
    private TbItemCatMapper itemCatMapper;

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
