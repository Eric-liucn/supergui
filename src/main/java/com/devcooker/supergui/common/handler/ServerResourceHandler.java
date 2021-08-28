package com.devcooker.supergui.common.handler;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.devcooker.supergui.common.data.SuperScreen;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.loading.FMLPaths;
import org.apache.commons.io.FilenameUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Optional;

public class ServerResourceHandler {

    public static ServerResourceHandler instance;
    private static Path dataPath;
    private static Path guis;
    private static Path textures;

    public ServerResourceHandler(){
        instance = this;
        DistExecutor.safeRunWhenOn(Dist.DEDICATED_SERVER, SetupDir::new);
    }

    public SuperScreen getScreen(final String key) throws IOException {
        Optional<Path> optionalPath = Files.walk(guis).filter(p -> FilenameUtils.removeExtension(p.getFileName().toString()).equals(key)).findFirst();
        if (optionalPath.isPresent()){
            Path filePath = optionalPath.get();
            return JSONObject.parseObject(new String(Files.readAllBytes(filePath)), SuperScreen.class);
        }else {
            throw new NullPointerException("Can't find the gui json file");
        }
    }

    public void saveScreenJson(SuperScreen screen) throws IOException {
        Path filePath = guis.resolve(screen.name + ".json");
        Files.write(filePath, JSONObject.toJSONBytes(screen, SerializerFeature.PrettyFormat), StandardOpenOption.CREATE);
    }


    private static class SetupDir implements DistExecutor.SafeRunnable{

        @Override
        public void run() {
            Path gamePath = FMLPaths.GAMEDIR.get();
            dataPath = gamePath.resolve("SuperGUI");
            guis = dataPath.resolve("gui");
            textures = dataPath.resolve("textures");
            try {
                Files.createDirectories(guis);
                Files.createDirectories(textures);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}
