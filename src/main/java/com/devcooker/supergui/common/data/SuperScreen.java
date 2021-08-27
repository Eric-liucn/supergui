package com.devcooker.supergui.common.data;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;


public class SuperScreen {

    @JSONField(name = "GUI名字")
    private String name;

    @JSONField(name = "X")
    private int x = 0;

    @JSONField(name = "Y")
    private int y = 0;

    @JSONField(name = "u")
    private int u;

    @JSONField(name = "v")
    private int v;

    @JSONField(name = "材质名称")
    private String texture;

    public SuperScreen(String name, int x, int y, int u, int v, String texture){
        this.name = name;
        this.x = x;
        this.y = y;
        this.u = u;
        this.v = v;
        this.texture = texture;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void save(Path path) throws IOException {
        Path filePath = path.resolve(this.name + ".json");
        Files.write(filePath, JSONObject.toJSONBytes(this, SerializerFeature.PrettyFormat), StandardOpenOption.CREATE);
    }
}
