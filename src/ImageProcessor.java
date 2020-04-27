import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class ImageProcessor {
   static int processPixel(int p, String type) {

      // unpack values from the pixel
      // process pixel based on type
      // pack values back into the pixel

      if (type == "grayscale"){

         int a = (p>>24) &0xff;
         int r = (p>>16) &0xff;
         int g = (p>>8) & 0xff;
         int b = p&0xff;

         int avg = (r+g+b)/3;
         p = (a<<24) | (avg<<16) | (avg<<8) | avg;

      }
      else if (type == "sepia"){

         int a = (p>>24) &0xff;
         int r = (p>>16) &0xff;
         int g = (p>>8) & 0xff;
         int b = p&0xff;

         /*
         calculate new RGB using the formula

         new red = 0.393*red+ 0.769*green + 0.189*blue
         new green = 0.349*red+ 0.686*green+ 0.168*blue
         new blue = 0.272*red+ 0.534*green+ 0.131*blue
          */
         int NewRed = (int)(0.393*r + 0.769*g + 0.189*b);
         int NewGreen = (int)(0.349*r + 0.686*g + 0.168*b);
         int NewBlue = (int)(0.272*r + 0.534*g + 0.131*b);

         if(NewRed > 255){
            r = 255;
         }else{
            r = NewRed;
         }

         if(NewGreen > 255){
            g = 255;
         }else{
            g = NewGreen;
         }

         if(NewBlue > 255){
            b = 255;
         }else{
            b = NewBlue;
         }
         p = (a<<24) | (r<<16) | (g<<8) | b;



      }
      else if (type == "negative"){

         int a = (p>>24) &0xff;
         int r = (p>>16) &0xff;
         int g = (p>>8) & 0xff;
         int b = p&0xff;

         r = 255 - r;
         g = 255 - g;
         b = 255 - b;

         p = (a<<24) | (r<<16) | (g<<8) | b;
      }
      else {
         System.out.println("Invalid Type");
         System.exit(0);
      }
      
      return p;
   }
   
   static void processImage(String inFilename, String outFilename, String type) {
      BufferedImage img = null;
      File f = null;
      
      // load image
      try {
         f = new File(inFilename);
         img = ImageIO.read(f);
         System.out.println("loaded an image");
      }
      catch(IOException e) {
         System.out.println(e);
      }
      
      // get image width and height
      int width = img.getWidth();
      int height = img.getHeight();
      
      // process all pixels in the image
      for(int x = 0; x < width; x++) {
         for(int y = 0; y < height; y++) {
            int p = img.getRGB(x,y);
            p = processPixel(p, type);
            img.setRGB(x, y, p);
         }
      }
      
      // write image to file
      try {
         f = new File(outFilename);
         ImageIO.write(img, "jpg", f);
      }
      catch(IOException e) {
         System.out.println(e);
      }
   }

   public static void main(String args[]) {
      processImage("/Users/dev/Documents/GitHub/Image-Processor/out/production/Image-Processor/resources/images/Taj.jpg", "/Users/dev/Documents/GitHub/Image-Processor/out/production/Image-Processor/resources/images/Taj_negative.jpg", "negative");
      processImage("/Users/dev/Documents/GitHub/Image-Processor/out/production/Image-Processor/resources/images/Taj.jpg", "/Users/dev/Documents/GitHub/Image-Processor/out/production/Image-Processor/resources/images/Taj_sepia.jpg", "sepia");
      processImage("/Users/dev/Documents/GitHub/Image-Processor/out/production/Image-Processor/resources/images/Taj.jpg", "/Users/dev/Documents/GitHub/Image-Processor/out/production/Image-Processor/resources/images/Taj_grayscale.jpg", "grayscale");
   }
}
