package pers.wh.watermarks.service;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public interface WaterMarksService {

    int FONT_SIZE = 100;
    float ALPHA = 0.3F;
    Color FONT_COLOR = Color.RED;

    /**
     * 给图片添加单个文字水印
     * @param srcImageFile
     * @param descDir
     * @param markText
     * @param x
     * @param y
     * @return
     * @throws IOException
     */
    String markImage(File srcImageFile,String descDir, String markText, int x, int y) throws IOException;

    /**
     * 给图片添加多个文字水印（水印文字内容都是相同的）
     * @param srcImageFile
     * @param descDir
     * @param markText
     * @return
     * @throws IOException
     */
    String markImages(File srcImageFile,String descDir, String markText) throws IOException;

}
