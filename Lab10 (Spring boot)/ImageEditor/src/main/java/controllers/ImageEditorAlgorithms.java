package controllers;

import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.TreeMap;

public class ImageEditorAlgorithms {


    public BufferedImage copyImage(BufferedImage img){
        BufferedImage b = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
        Graphics g = b.getGraphics();
        g.drawImage(img, 0, 0, null);
        g.dispose();
        return b;
    }


    public BufferedImage GreyScale(BufferedImage img){
            BufferedImage image = copyImage(img);
            int width = image.getWidth();
            int height = image.getHeight();

            for(int i=0; i<height; i++) {

                for(int j=0; j<width; j++) {

                    Color c = new Color(image.getRGB(j, i));
                    int red = (int)(c.getRed() * 0.299);
                    int green = (int)(c.getGreen() * 0.587);
                    int blue = (int)(c.getBlue() *0.114);
                    Color newColor = new Color(red+green+blue,

                            red+green+blue,red+green+blue);

                    image.setRGB(j,i,newColor.getRGB());
                }
            }
            return image;
    }

    public static Mat getMat(BufferedImage image) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", byteArrayOutputStream);
        byteArrayOutputStream.flush();
        return Imgcodecs.imdecode(new MatOfByte(byteArrayOutputStream.toByteArray()), Imgcodecs.CV_LOAD_IMAGE_UNCHANGED);
    }
    public static BufferedImage getImage(Mat matrix) throws IOException {
        MatOfByte mob = new MatOfByte();
        Imgcodecs.imencode(".jpg", matrix, mob);
        return ImageIO.read(new ByteArrayInputStream(mob.toArray()));
    }

    public BufferedImage GaussFilter(BufferedImage img,int radius) throws IOException {

            BufferedImage image =copyImage(img);
            nu.pattern.OpenCV.loadShared();
            Mat sourceMat = getMat(image);
            Mat destination = new Mat(sourceMat.rows(), sourceMat.cols(), sourceMat.type());
            Imgproc.GaussianBlur(sourceMat, destination, new Size(radius, radius), 0);
            return getImage(destination);


        }


    public BufferedImage BicubicInterpolation(BufferedImage img,String perc){

        BufferedImage image =copyImage(img);
        double percent = Double.parseDouble(perc);
        int width = image.getWidth();
        int height = image.getHeight();
        int w2 = (int) (width * percent);
        int h2 = (int) (height * percent);

        BufferedImage afterInterpolation = new BufferedImage(w2, h2, BufferedImage.TYPE_INT_RGB);

        Graphics2D bg = afterInterpolation.createGraphics();
        bg.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);

        bg.scale(percent, percent);

        bg.drawImage(image, 0, 0, null);
        bg.dispose();


        return afterInterpolation;
    }
    public BufferedImage cropImage(BufferedImage img, int start, int stop, int width, int height) {
        BufferedImage image = img.getSubimage(start, stop, width, height);
        return image;
    }

    public HashMap<String, TreeMap<Integer,Double>> getHistogram(BufferedImage img){
        HashMap<String,TreeMap<Integer,Double>> histogram = new HashMap<>();
        int height = img.getHeight();
        int width = img.getWidth();
        Raster raster = img.getRaster();
        double hw =img.getHeight()*img.getWidth();

        TreeMap<Integer, Double> map = new TreeMap<>();
        TreeMap<Integer,Double> map1 = new TreeMap<>();
        TreeMap<Integer,Double> map2 = new TreeMap<>();

        double[][] bins = new double[3][256];
        for(int i=0; i < width ; i++) {
            for(int j=0; j < height ; j++) {
                bins[0][raster.getSample(i, j, 0)]++;
                bins[1][raster.getSample(i, j, 1)]++;
                bins[2][raster.getSample(i, j, 2)]++;


            }
        }
        for(int j=0;j<256;j++){
            map.put(j,bins[0][j]/(hw));
            map1.put(j,bins[1][j]/(hw));
            map2.put(j,bins[2][j]/(hw));

        }

        histogram.put("R", map);
        histogram.put("G",map1);
        histogram.put("B",map2);
        return histogram;
    }
}
