package com.taotao.service;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TreeNode;
import com.taotao.pojo.TbItem;

import java.util.List;

public interface ItemService {

	TbItem getItemById(long itemId);
	EasyUIDataGridResult getItemList(int page,int rows);
	List<TreeNode> getItemCatList(long parentId);
}
