package com.taotao.service.impl;

import com.taotao.common.utils.JsonUtils;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.pojo.TbItemParamItemExample;
import com.taotao.service.ItemParamItemService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * 取规格参数信息并显示在jsp页面上（要自己拼接html）
 * Created by 隽 on 2017/6/30.
 */
public class ItemParamItemServiceImpl implements ItemParamItemService
{

    @Autowired
    private TbItemParamItemMapper itemParamItemMapper;

    @Override
    public String getItemParemById(long itemId)
    {

        //创建查询条件
        TbItemParamItemExample example = new TbItemParamItemExample();
        TbItemParamItemExample.Criteria criteria = example.createCriteria();
        criteria.andItemIdEqualTo(itemId);
        List<TbItemParamItem> list = itemParamItemMapper.selectByExampleWithBLOBs(example);
        //1如果没有取到规格参数返回空串。
        if (null == list || list.isEmpty())
        {
            return "";
        }
        //2取到规格参数
        TbItemParamItem itemParamItem = list.get(0);
        String paramData = itemParamItem.getParamData();
        //把规格参数信息转换成java对象
        List<Map> paramList = JsonUtils.jsonToList(paramData, Map.class);
        //根据list生成html
        StringBuffer sb = new StringBuffer();
        sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"1\" class=\"Ptable\">\n");
        sb.append("     <tbody>\n");
        for (Map param : paramList)
        {
            sb.append("          <tr>\n");
            sb.append("               <th class=\"tdTitle\" colspan=\"2\">" + param.get("group") + "</th>\n");
            sb.append("          </tr>\n");
            //取规格项
            List<Map> object = (List<Map>)param.get("params");
            for (Map map : object)
            {
                sb.append("          <tr>\n");
                sb.append("               <td class=\"tdTitle\">" + map.get("k") + "</td>\n");
                sb.append("               <td>" + map.get("v") + "</td>\n");
                sb.append("          </tr>\n");
            }
        }
        sb.append("     </tbody>\n");
        sb.append("</table>");
        return sb.toString();
    }
    
}
