package pers.wh.util;

import java.io.Closeable;
import java.io.IOException;

/**
 * 释放资源
 */
public class CloseUtil {

    public static void close(Closeable closeable){
        if(closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(AutoCloseable closeable){
        if(closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
