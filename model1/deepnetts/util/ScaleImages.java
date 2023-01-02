/**
 *  DeepNetts is pure Java Deep Learning Library with support for Backpropagation
 *  based learning and image recognition.
 *
 *  Copyright (C) 2017  Zoran Sevarac <sevarac@gmail.com>
 *
 *  This file is part of DeepNetts.
 *
 *  DeepNetts is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <https://www.gnu.org/licenses/>.package deepnetts.core;
 */

package deepnetts.util;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Zoran Sevarac <zoran.sevarac@deepnetts.com>
 */
public class ScaleImages {

    String sourcePath = "D:\\datasets\\sourcePath\\"; 
    String targetPath = "D:\\datasets\\targetPath\\";
    int targetWidth=96,
        targetHeight=96;

    String imageIndexFileFile =""; 
    String labelsFile =""; 

    public void run() {
        try {
            HashMap<File, BufferedImage> images = ImageUtils.loadFileImageMapFromDirectory(new File(sourcePath));

            int i = 0;
            for(File file : images.keySet()) {
                BufferedImage scaledImage = ImageUtils.scaleAndCenter(images.get(file), targetWidth, targetHeight, 0, Color.WHITE);
                String fileName = file.getName();
                String imgType = fileName.substring(fileName.lastIndexOf(".")+1);

                ImageIO.write(scaledImage, imgType, new File(targetPath + ""+file.getName()));
                i++;
            }

            ImageUtils.createIndexFile(images, targetPath + "imageIndex.txt", true);

        } catch (IOException ex) {
            Logger.getLogger(ScaleImages.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public static void main(String[] args) {
        ScaleImages demo = new ScaleImages();
        demo.run();
    }

}