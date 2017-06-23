package com.taotao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 展示jsp页面
 */
@Controller
public class PageController
{

    /**
     * 展示首页
     * <p>Title: showIndex</p>
     * <p>Description: </p>
     *
     * @return
     */
    @RequestMapping("/")
    public String showIndex()
    {
        return "index";
    }

    /**
     * 展示功能页面，可以根据页面的uri的名字跳到对应的页面
     * <p>Title: showPage</p>
     * <p>Description: </p>
     *
     * @param page
     * @return
     */
    @RequestMapping("/{page}")
    public String showPage(@PathVariable String page)
    {
        return page;
    }
}
