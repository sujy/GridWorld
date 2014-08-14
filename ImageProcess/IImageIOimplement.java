import imagereader.IImageIO;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

class IImageIOimplement implements IImageIO {

    public class ReadImage {
        private byte bitmaphead[];
        private byte bitmapinfo[];
        private int[] canvas;
        private byte[] colorMatrix;
        private int width, height, bitCount, sizeImage;
        private static final int mathFactor = 0xff;

        public ReadImage(FileInputStream filestream) throws IOException {
            /*
             * BMP文件由4部分组成：位图文件头(bitmap-file header) 
             * 位图信息头(bitmap-information header) 
             * 信息头中 14字节 是文件头 40字节 是信息头 
             * 颜色表(color table) - 颜色点阵数据(bits data)
             */
            
            // 我们先把文件头读出来
            int bitMapHeadSize = 14;
            bitmaphead = new byte[bitMapHeadSize];
            filestream.read(bitmaphead, 0, bitMapHeadSize);
            // 再信息头读出来
            int bitMapHeadInfoSize = 40;
            bitmapinfo = new byte[bitMapHeadInfoSize];
            filestream.read(bitmapinfo, 0, bitMapHeadInfoSize);  
        }

        Image get() {
            return (Toolkit.getDefaultToolkit()
                    .createImage(new MemoryImageSource(width, height, canvas, 0,
                            width)));
        }
        void getImgInfo(){
            // 获取我们想要的数据            
            /*
             *  位图信息头共40字节： 
                名称  占用空间    内容  实际数据
                biSize  4字节     位图信息头的大小，为40    0x28(40)
   we need  --> biWidth     4字节     位图的宽度，单位是像素     2
   we need  --> biHeight    4字节     位图的高度，单位是像素     3
                biPlanes    2字节     固定值1    1
   we need  --> biBitCount  2字节     每个像素的位数
                1-黑白图，4-16色，8-256色，24-真彩色   0x18(24)
                biCompression   4字节     压缩方式，BI_RGB(0)为不压缩  0
   we need  --> biSizeImage     4字节     位图全部像素占用的字节数，BI_RGB时可设为0    0x1A
                biXPelsPerMeter     4字节     水平分辨率(像素/米)     0xB12(2834)
                biYPelsPerMeter     4字节     垂直分辨率(像素/米)     0xB12(2834)
                biClrUsed   4字节     位图使用的颜色数
                如果为0，则颜色数为2的biBitCount次方    0
                biClrImportant  4字节     重要的颜色数，0代表所有颜色都重要   0
             */
            width = move(((int) bitmapinfo[4] & mathFactor),0) | move(((int) bitmapinfo[5] & mathFactor),8)
                    | move(((int) bitmapinfo[6] & mathFactor),16)
                    | move(((int) bitmapinfo[7] & mathFactor),24);
            height = move(((int) bitmapinfo[8] & mathFactor),0) | move(((int) bitmapinfo[9] & mathFactor),8)
                    | move(((int) bitmapinfo[10] & mathFactor),16)
                    | move(((int) bitmapinfo[11] & mathFactor),24);
            bitCount = move(((int) bitmapinfo[14] & mathFactor),0) | move(((int) bitmapinfo[15] & mathFactor),8);
            sizeImage = move(((int) bitmapinfo[20] & mathFactor),0) | move(((int) bitmapinfo[21] & mathFactor),8)
                    | move(((int) bitmapinfo[22] & mathFactor),16)
                    | move(((int) bitmapinfo[23] & mathFactor),24);
        }
        void getColor(FileInputStream filestream) throws IOException {
            // 处理24-真彩色
            if (bitCount == 24) {
                // 计算空白空间
                // 由于像素使用的字节若不是4的倍数，则会自动扩大，由此产生空白。
                // 因此我们需要在一开始计算出空白的大小
                // sizeImage单位是字节，height 和 width单位是像素
                // 因为rgb，每个像素3个字节
                int nullblock = (sizeImage / height) - width * 3;

                // 我们需要把读进来的图像放到colorMatrix中
                // 进过处理去除空格之后在放入canvas
                canvas = new int[height * width];
                colorMatrix = new byte[sizeImage];
                filestream.read(colorMatrix, 0, sizeImage);
                // 开始读colorMatrix
                int index = 0;
                for (int j = 0; j < height; j++) {
                    for (int i = 0; i < width; i++) {
                        // 注意透明度 255 & mathFactor
                        canvas[width * (height - j - 1) + i] = move((255 & mathFactor),24)
                                | move(((int) colorMatrix[index + 2] & mathFactor),16)
                                | move(((int) colorMatrix[index + 1] & mathFactor),8)
                                | move((int) colorMatrix[index] & mathFactor,0);
                        index += 3;
                    }
                    // 避免空格
                    index += nullblock;
                }
            } else {
                throw new IllegalArgumentException("Not 24-bit bit map.");
            }
        }
        
        int move(int source, int bits) {
            if (bits == 0) {
                return source;
            } else {
                return (source << bits);
            }
        }
    }

    @Override
    public Image myRead(String filename) throws IOException {
        // 我们要读取到Image
        Image image = null;
        try {
            // 读取文件
            FileInputStream filestream = new FileInputStream(filename);
            ReadImage myimage = new ReadImage(filestream);
            myimage.getImgInfo();
            myimage.getColor(filestream);
            image = myimage.get();
            filestream.close();
            return image;
        }
        // IO读取异常，FileInputStream和FileOutStream必须使用异常处理
        catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public Image myWrite(Image image, String imagename) throws IOException {
        try {
            File imgfile = new File(imagename + ".bmp");
            BufferedImage bufferedImage = new BufferedImage(
                    image.getWidth(null), image.getHeight(null),
                    BufferedImage.TYPE_INT_RGB);
            Graphics canvas = bufferedImage.getGraphics();
            canvas.drawImage(image, 0, 0, null);
            canvas.dispose();
            ImageIO.write(bufferedImage, "bmp", imgfile);
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
        return image;
    }

}