package com.devcooker.supergui.common.data;

import com.alibaba.fastjson.annotation.JSONField;


public class SuperScreen {

    @JSONField(name = "GUI名字")
    public String name;

    @JSONField(name = "绘制位置X")
    public int x = 0;

    @JSONField(name = "绘制位置Y")
    public int y = 0;

    @JSONField(name = "材质位置U")
    public int u;

    @JSONField(name = "材质位置V")
    public int v;

    @JSONField(name = "材质名称")
    public String texture;

    public SuperScreen(String name, int x, int y, int u, int v, String texture){
        this.name = name;
        this.x = x;
        this.y = y;
        this.u = u;
        this.v = v;
        this.texture = texture;
    }

}
