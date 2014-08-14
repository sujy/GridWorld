import imagereader.IImageProcessor;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.FilteredImageSource;
import java.awt.image.RGBImageFilter;

public class IImageProcessorimplement implements IImageProcessor {

    private static final int green = 0xff00ff00;
    private static final int red = 0xffff0000;
    private static final int blue = 0xff0000ff;

    // Green Filter
    class GreenFilter extends RGBImageFilter {
        public GreenFilter() {
            canFilterIndexColorModel = true;
        }

        public int filterRGB(int x, int y, int rgb) {
            return (rgb & green);
        }
    }

    // Blue Filter
    class BlueFilter extends RGBImageFilter {
        public BlueFilter() {
            canFilterIndexColorModel = true;
        }

        public int filterRGB(int x, int y, int rgb) {
            return (rgb & blue);
        }
    }

    // Red Filter
    class RedFilter extends RGBImageFilter {
        public RedFilter() {
            canFilterIndexColorModel = true;
        }

        public int filterRGB(int x, int y, int rgb) {
            return (rgb & red);
        }
    }

    // Gray Filter
    class GrayFilter extends RGBImageFilter {
        public GrayFilter() {
            canFilterIndexColorModel = true;
        }

        public int filterRGB(int x, int y, int rgb) {
            int gray = (int) (((rgb & 0x00ff0000) >> 16) * 0.299
                    + ((rgb & 0x0000ff00) >> 8) * 0.587 + (rgb & 0x000000ff) * 0.114);
            return (rgb & 0xff000000) + (gray << 16) + (gray << 8) + gray;
        }
    }

    // show chanel blue by called filter
    @Override
    public Image showChanelB(Image image) {
        BlueFilter filter = new BlueFilter();
        Toolkit kit = Toolkit.getDefaultToolkit();
        Image newimg = kit.createImage(new FilteredImageSource(image
                .getSource(), filter));
        return newimg;
    }

    // show chanel green by called filter
    @Override
    public Image showChanelG(Image image) {
        GreenFilter filter = new GreenFilter();
        Toolkit kit = Toolkit.getDefaultToolkit();
        Image newimg = kit.createImage(new FilteredImageSource(image
                .getSource(), filter));
        return newimg;
    }

    // show chanel red by called filter
    @Override
    public Image showChanelR(Image image) {
        RedFilter filter = new RedFilter();
        Toolkit kit = Toolkit.getDefaultToolkit();
        Image newimg = kit.createImage(new FilteredImageSource(image
                .getSource(), filter));
        return newimg;
    }

    // show chanel gray by bufferedimage
    /* we also can use 
     * BufferedImage bufferedImage = new BufferedImage(image.getWidth(null),
     * image.getHeight(null), BufferedImage.TYPE_BYTE_GRAY)
     * canvas.drawImage(image, 0, 0, null)
     * canvas.dispose()
     */
    @Override
    public Image showGray(Image image) {

        GrayFilter filter = new GrayFilter();
        Toolkit kit = Toolkit.getDefaultToolkit();
        Image newimg = kit.createImage(new FilteredImageSource(image
                .getSource(), filter));
        return newimg;

    }
}