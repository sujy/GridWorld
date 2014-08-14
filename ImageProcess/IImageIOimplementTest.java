import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Before;
import org.junit.Test;

public class IImageIOimplementTest {

    private File srcfile1, srcfile2;
    private File blue1, blue2, red1, red2, green1, green2, gray1, gray2;
    private IImageProcessorimplement processor;
    private IImageIOimplement io;

    // change the img to bufferedImage
    public static BufferedImage tobuffBufferedImage(Image im) {
        BufferedImage bi = new BufferedImage(im.getWidth(null),
                im.getHeight(null), BufferedImage.TYPE_INT_RGB);
        return bi;
    }

    // set the the file url
    @Before
    public void setUp() throws Exception {
        srcfile1 = new File(
                "/home/passion/workspace/ImageProcessing/bmptest/1.bmp");
        srcfile2 = new File(
                "/home/passion/workspace/ImageProcessing/bmptest/2.bmp");
        blue1 = new File(
                "/home/passion/workspace/ImageProcessing/bmptest/goal/1_blue_goal.bmp");
        blue2 = new File(
                "/home/passion/workspace/ImageProcessing/bmptest/goal/2_blue_goal.bmp");
        red1 = new File(
                "/home/passion/workspace/ImageProcessing/bmptest/goal/1_red_goal.bmp");
        red2 = new File(
                "/home/passion/workspace/ImageProcessing/bmptest/goal/2_red_goal.bmp");
        green1 = new File(
                "/home/passion/workspace/ImageProcessing/bmptest/goal/1_green_goal.bmp");
        green2 = new File(
                "/home/passion/workspace/ImageProcessing/bmptest/goal/2_green_goal.bmp");
        gray1 = new File(
                "/home/passion/workspace/ImageProcessing/bmptest/goal/1_gray_goal.bmp");
        gray2 = new File(
                "/home/passion/workspace/ImageProcessing/bmptest/goal/2_gray_goal.bmp");

        // new a io and processor
        processor = new IImageProcessorimplement();
        io = new IImageIOimplement();
    }

    @Test
    // test if hte red color is right
    public void testRed() throws IOException {
        // bring the src file in
        Image my1 = io.myRead(srcfile1.toString());
        Image my2 = io.myRead(srcfile2.toString());
        // change color
        Image myimg1 = processor.showChanelR(my1);
        Image myimg2 = processor.showChanelR(my2);
        // read goal file
        Image goal1 = ImageIO.read(red1);
        Image goal2 = ImageIO.read(red2);
        // compare goal1
        assertEquals(myimg1.getWidth(null), goal1.getWidth(null));
        assertEquals(myimg1.getHeight(null), goal1.getHeight(null));
        assertArrayEquals(
                tobuffBufferedImage(myimg1).getRGB(0, 0, myimg1.getWidth(null),
                        myimg1.getHeight(null), null, 0, myimg1.getWidth(null)),
                tobuffBufferedImage(goal1).getRGB(0, 0, goal1.getWidth(null),
                        goal1.getHeight(null), null, 0, goal1.getWidth(null)));
        // compare goal2
        assertEquals(myimg2.getWidth(null), goal2.getWidth(null));
        assertEquals(myimg2.getHeight(null), goal2.getHeight(null));
        assertArrayEquals(
                tobuffBufferedImage(myimg2).getRGB(0, 0, myimg2.getWidth(null),
                        myimg2.getHeight(null), null, 0, myimg2.getWidth(null)),
                tobuffBufferedImage(goal2).getRGB(0, 0, goal2.getWidth(null),
                        goal2.getHeight(null), null, 0, goal2.getWidth(null)));
    }

    @Test
    public void testGreen() throws IOException {
        // bring the src file in
        Image my1 = io.myRead(srcfile1.toString());
        Image my2 = io.myRead(srcfile2.toString());
        // change color
        Image myimg1 = processor.showChanelG(my1);
        Image myimg2 = processor.showChanelG(my2);
        // read goal file
        Image goal1 = ImageIO.read(green1);
        Image goal2 = ImageIO.read(green2);
        // compare goal1
        assertEquals(my1.getWidth(null), goal1.getWidth(null));
        assertEquals(my1.getHeight(null), goal1.getHeight(null));
        assertArrayEquals(
                tobuffBufferedImage(myimg1).getRGB(0, 0, myimg1.getWidth(null),
                        myimg1.getHeight(null), null, 0, myimg1.getWidth(null)),
                tobuffBufferedImage(goal1).getRGB(0, 0, goal1.getWidth(null),
                        goal1.getHeight(null), null, 0, goal1.getWidth(null)));
        // compare goal2
        assertEquals(myimg2.getWidth(null), goal2.getWidth(null));
        assertEquals(myimg2.getHeight(null), goal2.getHeight(null));
        assertArrayEquals(
                tobuffBufferedImage(myimg2).getRGB(0, 0, myimg2.getWidth(null),
                        myimg2.getHeight(null), null, 0, myimg2.getWidth(null)),
                tobuffBufferedImage(goal2).getRGB(0, 0, goal2.getWidth(null),
                        goal2.getHeight(null), null, 0, goal2.getWidth(null)));
    }

    @Test
    public void testBlue() throws IOException {
        // bring the src file in
        Image my1 = io.myRead(srcfile1.toString());
        Image my2 = io.myRead(srcfile2.toString());
        // change color
        Image myimg1 = processor.showChanelB(my1);
        Image myimg2 = processor.showChanelB(my2);
        // read goal file
        Image goal1 = ImageIO.read(blue1);
        Image goal2 = ImageIO.read(blue2);
        // compare goal1
        assertEquals(my1.getWidth(null), goal1.getWidth(null));
        assertEquals(my1.getHeight(null), goal1.getHeight(null));
        assertArrayEquals(
                tobuffBufferedImage(myimg1).getRGB(0, 0, myimg1.getWidth(null),
                        myimg1.getHeight(null), null, 0, myimg1.getWidth(null)),
                tobuffBufferedImage(goal1).getRGB(0, 0, goal1.getWidth(null),
                        goal1.getHeight(null), null, 0, goal1.getWidth(null)));
        // compare goal2
        assertEquals(myimg2.getWidth(null), goal2.getWidth(null));
        assertEquals(myimg2.getHeight(null), goal2.getHeight(null));
        assertArrayEquals(
                tobuffBufferedImage(myimg2).getRGB(0, 0, myimg2.getWidth(null),
                        myimg2.getHeight(null), null, 0, myimg2.getWidth(null)),
                tobuffBufferedImage(goal2).getRGB(0, 0, goal2.getWidth(null),
                        goal2.getHeight(null), null, 0, goal2.getWidth(null)));
    }

    @Test
    public void testGray() throws IOException {
        // bring the src file in
        Image my1 = io.myRead(srcfile1.toString());
        Image my2 = io.myRead(srcfile2.toString());
        // change color
        Image myimg1 = processor.showGray(my1);
        Image myimg2 = processor.showGray(my2);
        // read goal file
        Image goal1 = ImageIO.read(gray1);
        Image goal2 = ImageIO.read(gray2);
        // compare goal1
        assertEquals(my1.getWidth(null), goal1.getWidth(null));
        assertEquals(my1.getHeight(null), goal1.getHeight(null));
        assertArrayEquals(
                tobuffBufferedImage(myimg1).getRGB(0, 0, myimg1.getWidth(null),
                        myimg1.getHeight(null), null, 0, myimg1.getWidth(null)),
                tobuffBufferedImage(goal1).getRGB(0, 0, goal1.getWidth(null),
                        goal1.getHeight(null), null, 0, goal1.getWidth(null)));
        // compare goal2
        assertEquals(myimg2.getWidth(null), goal2.getWidth(null));
        assertEquals(myimg2.getHeight(null), goal2.getHeight(null));
        assertArrayEquals(
                tobuffBufferedImage(myimg2).getRGB(0, 0, myimg2.getWidth(null),
                        myimg2.getHeight(null), null, 0, myimg2.getWidth(null)),
                tobuffBufferedImage(goal2).getRGB(0, 0, goal2.getWidth(null),
                        goal2.getHeight(null), null, 0, goal2.getWidth(null)));
    }
}
