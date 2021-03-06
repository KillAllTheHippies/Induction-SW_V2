package view;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.opencv.core.*;
import org.opencv.videoio.VideoCapture;   // VideoCapture


public class JPanelOpenCV extends JPanel{

    private BufferedImage image;


    public BufferedImage capturePhoto() {

        JPanelOpenCV t = new JPanelOpenCV();
        VideoCapture camera = new VideoCapture(0);

        Mat frame = new Mat();
        camera.read(frame);

        BufferedImage img = null;
        if (!camera.isOpened()) {
            System.out.println("Error");
        } else {
            while (true) {

                if (camera.read(frame)) {

                    img = t.MatToBufferedImage(frame);

                    //t.showImage(img, "Original Image", 0, 0);

                    //t.showImage(t.grayscale(img), "Processed Image", 40, 60);

                    //t.showImage(t.loadImage("ImageName"), "Image loaded", 0, 0);

                    t.saveImage(img);
                    System.out.println("image saved");

                    break;
                }
            }
        }
        camera.release();
        return img;
    }

//    public static void main (String args[]) throws InterruptedException{
//        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
//
//        JPanelOpenCV t = new JPanelOpenCV();
//        VideoCapture camera = new VideoCapture(0);
//
//        Mat frame = new Mat();
//        camera.read(frame);
//
//        if(!camera.isOpened()){
//            System.out.println("Error");
//        }
//        else {
//            while(true){
//
//                if (camera.read(frame)){
//
//                    BufferedImage image = t.MatToBufferedImage(frame);
//
//                    //t.showImage(image, "Original Image", 0, 0);
//
//                    //t.showImage(t.grayscale(image), "Processed Image", 40, 60);
//
//                    //t.showImage(t.loadImage("ImageName"), "Image loaded", 0, 0);
//
//                    t.saveImage(image);
//                    System.out.println("image saved");
//
//                    break;
//                }
//            }
//        }
//        camera.release();
//    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(image, 0, 0, this);
    }

    public JPanelOpenCV() {
    }

    public JPanelOpenCV(BufferedImage img) {
        this.image = img;
    }

    //Show image on new frame
    public void showImage(BufferedImage img, String text, int x, int y) {
        JFrame frame0 = new JFrame();
        frame0.getContentPane().add(new JPanelOpenCV(img));
        // frame0.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame0.setTitle(text);
        frame0.setSize(img.getWidth(), img.getHeight() + 30);
        frame0.setLocation(x, y);
        frame0.setVisible(true);
    }

    //Load an image
    public BufferedImage loadImage(String file) {
        BufferedImage img;

        try {
            File input = new File(file);
            img = ImageIO.read(input);

            return img;
        } catch (Exception e) {
            System.out.println("erro");
        }

        return null;
    }

    //Save an image
    public void saveImage(BufferedImage img) {
        try {
            File outputfile = new File("new.png");
            ImageIO.write(img, "png", outputfile);
        } catch (Exception e) {
            System.out.println("error");
        }
    }

    //Grayscale filter
    public BufferedImage grayscale(BufferedImage img) {
        for (int i = 0; i < img.getHeight(); i++) {
            for (int j = 0; j < img.getWidth(); j++) {
                Color c = new Color(img.getRGB(j, i));

                int red = (int) (c.getRed() * 0.299);
                int green = (int) (c.getGreen() * 0.587);
                int blue = (int) (c.getBlue() * 0.114);

                Color newColor =
                        new Color(
                                red + green + blue,
                                red + green + blue,
                                red + green + blue);

                img.setRGB(j, i, newColor.getRGB());
            }
        }

        return img;
    }

    public BufferedImage MatToBufferedImage(Mat frame) {
        //Mat() to BufferedImage
        int type = 0;
        if (frame.channels() == 1) {
            type = BufferedImage.TYPE_BYTE_GRAY;
        } else if (frame.channels() == 3) {
            type = BufferedImage.TYPE_3BYTE_BGR;
        }
        BufferedImage image = new BufferedImage(frame.width(), frame.height(), type);
        WritableRaster raster = image.getRaster();
        DataBufferByte dataBuffer = (DataBufferByte) raster.getDataBuffer();
        byte[] data = dataBuffer.getData();
        frame.get(0, 0, data);

        return image;
    }

    public BufferedImage getImage() {
        return image;
    }
}
