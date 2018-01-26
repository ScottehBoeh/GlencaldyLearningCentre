package com.scottehboeh.glc.utils;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by 1503257 on 08/12/2017.
 * <p>
 * GuiUtils
 * <p>
 * This utility class functions as a toolbox for different useful UI-related
 * methods and techniques.
 */
public class GuiUtils {

    /**
     * Get Scaled Image - Convert an image into a new Scaled Version
     *
     * @param srcImg - Given Original Image
     * @param w      - Given Width
     * @param h      - Given Height
     * @return - Returned Scaled Image (Image)
     */
    private Image getScaledImage(Image srcImg, int w, int h) {
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();

        return resizedImg;
    }

}
