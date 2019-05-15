package controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

@RestController
public class ImageEditorController {

    private Map<String, BufferedImage> images = new HashMap<>();
    private ImageEditorAlgorithms imageEditorAlgorithms = new ImageEditorAlgorithms();

    @RequestMapping(value = "/image", method = RequestMethod.POST)
    public Map<String, String> addImage(HttpServletRequest requestEntity) throws Exception {

        BufferedImage image = ImageIO.read(requestEntity.getInputStream());
        String uniqueID = UUID.randomUUID().toString();
        images.put(uniqueID, image);

        Map<String, String> JSONData = new HashMap<>();
        JSONData.put("id", uniqueID);
        JSONData.put("height",Integer.toString(image.getHeight()));
        JSONData.put("width", Integer.toString(image.getWidth()));
        return JSONData;
    }

    @RequestMapping(value = "/image/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteImage(@PathVariable("id") String id) {
        if (images.containsKey(id)) {
            images.remove(id);
            return new ResponseEntity<>("Item has been removed!",HttpStatus.OK);
        } else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID not found");
    }


    @RequestMapping(value = "/image/{id}/size", method = RequestMethod.GET)
    public Map<String, String> getImageData(@PathVariable("id") String id) {
        if (images.containsKey(id)) {
            Map<String, String> JSONData = new HashMap<>();
            BufferedImage image = images.get(id);

            JSONData.put("height",Integer.toString(image.getHeight()));
            JSONData.put("width", Integer.toString(image.getWidth()));
            return JSONData;
        } else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID not found");

    }
    @RequestMapping(value = "/image/{id}/display", method = RequestMethod.GET,produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] displayImage(@PathVariable("id") String id) throws IOException {
        if (images.containsKey(id)) {
            BufferedImage image =images.get(id);
            return displayImage(image);

        } else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID not found");
    }
    @RequestMapping(value = "/image/{id}/greyscale", method = RequestMethod.GET,produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getGreyscalledImage(@PathVariable("id") String id) throws IOException {
        if (images.containsKey(id)) {
            BufferedImage image =imageEditorAlgorithms.GreyScale(images.get(id));

            return displayImage(image);

        } else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID not found");
    }
    @RequestMapping(value = "/image/{id}/scale/{percent}",method = RequestMethod.GET,produces = MediaType.IMAGE_PNG_VALUE)
    public byte [] getScalledImage(@PathVariable String id, @PathVariable String percent) throws IOException {
      try {
          Double.parseDouble(percent);
          if (images.containsKey(id)) {
              BufferedImage image =imageEditorAlgorithms.BicubicInterpolation(images.get(id),percent);
              return displayImage(image);
          } else
              throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID not found");
      }
      catch (IllegalArgumentException e){
          throw new ResponseStatusException(HttpStatus.valueOf(400),"Incorrect 'percent' argument!");
      }

    }
 @RequestMapping(value = "/image/{id}/blur/{radius}",method = RequestMethod.GET,produces = MediaType.IMAGE_PNG_VALUE)
    public byte [] getBlurredImage(@PathVariable String id, @PathVariable String radius) throws IOException {
     try {
        int r= Integer.parseInt(radius);
         if(r%2==1){
             if (images.containsKey(id)) {
                 BufferedImage image =imageEditorAlgorithms.GaussFilter(images.get(id),Integer.parseInt(radius));

                 return displayImage(image);

             } else
                 throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID not found");
         }
         else
             throw new ResponseStatusException(HttpStatus.valueOf(400), "Gaussian kernel must be odd!");
     }
     catch (IllegalArgumentException e){
         throw new ResponseStatusException(HttpStatus.valueOf(400),"Incorrect 'radius' argument!");

     }

     }





    @RequestMapping(value = "/image/{id}/histogram",method = RequestMethod.GET)
    public HashMap<String,TreeMap<Integer,Double>> getHistogramData(@PathVariable String id) {
        if (images.containsKey(id)) {
            BufferedImage image =images.get(id);
           return imageEditorAlgorithms.getHistogram(image);
        } else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID not found");
    }


    @RequestMapping(value = "/image/{id}/crop/{start}/{stop}/{width}/{height}",method = RequestMethod.GET,produces = MediaType.IMAGE_PNG_VALUE)
    public byte [] getPartOfThePhoto(@PathVariable String id, @PathVariable String start, @PathVariable String stop, @PathVariable String width, @PathVariable String height) throws IOException {

        try {
            int w = Integer.parseInt(width);
            int h = Integer.parseInt(height);
            int sta = Integer.parseInt(start);
            int sto = Integer.parseInt(stop);
            if (images.containsKey(id)) {
                BufferedImage image = images.get(id);

                if (w>0&&w<=image.getWidth() && h>0 && h <= image.getHeight() && sta +w<=image.getWidth()&&sto+h<=image.getHeight()&&sta>=0&&sto>=0){
                    image = imageEditorAlgorithms.cropImage(image, Integer.parseInt(start), Integer.parseInt(stop), Integer.parseInt(width), Integer.parseInt(height));
                    return displayImage(image);
                }
                else{
                    throw new ResponseStatusException(HttpStatus.valueOf(400), "Incorrect arguments!!");
                }



            } else
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID not found");
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.valueOf(400), "Please enter the numbers!");

        }
    }

    public  byte[] displayImage(BufferedImage img) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(img, "png", outputStream);
        return outputStream.toByteArray();
    }
}


