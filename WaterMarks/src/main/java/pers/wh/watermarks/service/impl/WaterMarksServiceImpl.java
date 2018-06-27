package pers.wh.watermarks.service.impl;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import pers.wh.util.CloseUtil;
import pers.wh.watermarks.service.WaterMarksService;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class WaterMarksServiceImpl implements WaterMarksService {

    public String markImage(String srcImageFilePath, String markText ) throws IOException {
        File srcImageFile = new File(srcImageFilePath);
        String descDir = srcImageFile.getParent();
        return this.markImage(srcImageFile, descDir, markText,  0, 0);
    }

    public String markImages(String srcImageFilePath, String markText ) throws IOException {
        File srcImageFile = new File(srcImageFilePath);
        String descDir = srcImageFile.getParent();
        return this.markImages(srcImageFile, descDir, markText);
    }

    @Override
    public String markImage(File srcImageFile, String descDir, String markText, int x, int y) throws IOException {

        //原图片文件名
        String srcImageName = srcImageFile.getName();
        //水印图片名
        String waterImageName = "water_"+srcImageName;

        //读取原图片文件
        Image srcImage = ImageIO.read(srcImageFile);
        //原图片宽度
        int width  = srcImage.getWidth(null);
        //原推按高度
        int heigjht = srcImage.getHeight(null);

        //准备水印图片
        BufferedImage bufferedImage = new BufferedImage(width, heigjht, BufferedImage.TYPE_INT_RGB);
        //获取画图工具
        Graphics2D graphics2D = bufferedImage.createGraphics();
        //绘制原图片
        graphics2D.drawImage(srcImage, 0,0, null);

        graphics2D.setFont(new Font("微软雅黑", Font.BOLD, FONT_SIZE));
        graphics2D.setColor(FONT_COLOR);

        //水印文字的宽度
        int w1 = getTextWidth(markText, FONT_SIZE);
        //水印文字的高度
        int h1 = FONT_SIZE;

        int widthDiff = width - w1;
        int heightDiff = heigjht - h1;

        //水印文字的横坐标不能比宽度之差大，否则就不能显示全水印文字
        if(x > widthDiff){
            x = widthDiff;
        }
        //水印文字的纵坐标不能比宽度之差大，否则就不能显示全水印文字
        if(y > heightDiff){
            y = heightDiff;
        }

        //设置文字水印的透明度
        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, ALPHA));


        //绘制水印文字
        graphics2D.drawString(markText, x, y+FONT_SIZE);
        //释放绘图资源
        graphics2D.dispose();

        //将内存里水印图片保存到磁盘里去
        FileOutputStream fos = null;
        try{

            fos = new FileOutputStream(descDir + File.separator + waterImageName);
            JPEGImageEncoder jpegImageEncoder = JPEGCodec.createJPEGEncoder(fos);
            jpegImageEncoder.encode(bufferedImage);

        }catch (Exception e){

        }finally {
            CloseUtil.close(fos);
        }


        return waterImageName;
    }

    @Override
    public String markImages(File srcImageFile, String descDir, String markText) throws IOException {

        //原图片文件名
        String srcImageName = srcImageFile.getName();
        //水印图片名
        String waterImageName = "water_"+srcImageName;

        //读取原图片文件
        Image srcImage = ImageIO.read(srcImageFile);
        //原图片宽度
        int width  = srcImage.getWidth(null);
        //原推按高度
        int height = srcImage.getHeight(null);

        //准备水印图片
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        //获取画图工具
        Graphics2D graphics2D = bufferedImage.createGraphics();
        //绘制原图片
        graphics2D.drawImage(srcImage, 0,0, null);

        graphics2D.setFont(new Font("微软雅黑", Font.BOLD, FONT_SIZE));
        graphics2D.setColor(FONT_COLOR);

        //水印文字的宽度
        int w1 = getTextWidth(markText, FONT_SIZE);
        //水印文字的高度
        int h1 = FONT_SIZE;

        //设置文字水印的透明度
        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, ALPHA));

        //水印文字旋转30度，以图片中心为旋转的中心
        graphics2D.rotate(Math.toRadians(30), bufferedImage.getWidth()/2, bufferedImage.getHeight()/2);


        //文字水印之间的间隔
        int distance = FONT_SIZE;
        //文字水印绘图部分扩展1.5倍
        double expand = 1.5;
        for(int i=-width/2; i<width*expand; i+=w1+distance){

            for(int j=-height/2; j<height*expand; j+=h1+distance){
                //绘制水印文字
                graphics2D.drawString(markText, i, j);
            }

        }

        //释放绘图资源
        graphics2D.dispose();

        //将内存里水印图片保存到磁盘里去
        FileOutputStream fos = null;
        try{

            fos = new FileOutputStream(descDir + File.separator + waterImageName);
            JPEGImageEncoder jpegImageEncoder = JPEGCodec.createJPEGEncoder(fos);
            jpegImageEncoder.encode(bufferedImage);

        }catch (Exception e){

        }finally {
            CloseUtil.close(fos);
        }


        return waterImageName;
    }

    /**
     * 获取水印文本的宽度
     *      我们设置的水印字字体大小是120个像素
     *      一个中文字的宽度是120
     *      一个英文字宽度是60
     * @param text
     * @return
     */
    private int getTextWidth(String text, int fontSize){
        //先获取有多少个字
        int lenth = text.length();

        char[] chars = text.toCharArray();
        for(char c : chars){
            String s = String.valueOf(c);
            if(s.getBytes().length > 1){
                //中文字大小加一个
                lenth ++;
            }
        }

        //前面计算的是多少个英文字符（一个中文按两个英文计算）
        //由于两个英文字符的宽度之和就是设定的字体大小， 一个中文字宽度就是设定的字体大小
        //所以这里计算出有多少个中文字
        lenth = lenth%2==0 ? lenth/2 : (lenth/2 + 1);
        return lenth * fontSize;
    }

}
