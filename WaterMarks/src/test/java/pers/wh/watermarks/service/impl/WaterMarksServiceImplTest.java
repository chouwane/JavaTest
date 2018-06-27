package pers.wh.watermarks.service.impl;

import org.junit.Test;

import java.io.IOException;

public class WaterMarksServiceImplTest {

    @Test
    public void testMarkImage() throws IOException {

        String wateImageName = new WaterMarksServiceImpl().markImage("E:\\WebstormProjects\\瀑布流布局\\瀑布流布局.png", "水印文字");
        System.out.println(wateImageName);

    }

    @Test
    public void testMarkImages() throws IOException {

        String wateImageName = new WaterMarksServiceImpl().markImages("E:\\WebstormProjects\\瀑布流布局\\瀑布流布局.png", "水印文字");
        System.out.println(wateImageName);

    }

}
