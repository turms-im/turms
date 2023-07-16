/*
 * Copyright (C) 2019 The Turms Project
 * https://github.com/turms-im/turms
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package im.turms.ai.infra.image;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import javax.imageio.ImageIO;

import ai.djl.modality.cv.Image;
import ai.djl.modality.cv.ImageFactory;
import ai.djl.modality.cv.output.BoundingBox;
import ai.djl.modality.cv.output.DetectedObjects;
import ai.djl.modality.cv.output.Rectangle;
import ai.djl.modality.cv.util.NDImageUtils;
import ai.djl.ndarray.NDArray;
import ai.djl.ndarray.NDManager;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;

import im.turms.server.common.infra.lang.StringUtil;

/**
 * @author James Chen
 */
public final class ImageUtils {

    private static final Color BACKGROUND = new Color(0, 0, 0, 128);
    private static final Font FONT = new Font("Noto Sans", Font.PLAIN, 16);

    private ImageUtils() {
    }

    public static Image rotate90(Image image) {
        try (NDManager manager = NDManager.newBaseManager()) {
            NDArray rotated = NDImageUtils.rotate90(image.toNDArray(manager), 1);
            return ImageFactory.getInstance()
                    .fromNDArray(rotated);
        }
    }

    public static Image getSubImage(Image image, BoundingBox box) {
        int width = image.getWidth();
        int height = image.getHeight();
        Rectangle rect = box.getBounds();
        return image.getSubImage((int) (rect.getX() * width),
                (int) (rect.getY() * height),
                (int) (rect.getWidth() * width),
                (int) (rect.getHeight() * height));
    }

    public static BoundingBox extendBox(BoundingBox box) {
        Rectangle rect = box.getBounds();
        double x = rect.getX();
        double y = rect.getY();
        double width = rect.getWidth();
        double height = rect.getHeight();

        double centerX = x + width / 2;
        double centerY = y + height / 2;
        if (width > height) {
            width += height * 2.0;
            height *= 3.0;
        } else {
            height += width * 2.0;
            width *= 3.0;
        }
        double newX = centerX - width / 2 < 0
                ? 0
                : centerX - width / 2;
        double newY = centerY - height / 2 < 0
                ? 0
                : centerY - height / 2;
        double newWidth = newX + width > 1
                ? 1 - newX
                : width;
        double newHeight = newY + height > 1
                ? 1 - newY
                : height;
        return new Rectangle(newX, newY, newWidth, newHeight);
    }

    public static Mat drawBoundingBoxes(Mat mat, DetectedObjects detections) {
        BufferedImage image = mat2Image(mat);
        Graphics2D g = (Graphics2D) image.getGraphics();
        int stroke = 2;
        g.setStroke(new BasicStroke(stroke));
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int imageWidth = image.getWidth();
        int imageHeight = image.getHeight();

        List<DetectedObjects.DetectedObject> detectedObjects = detections.items();
        for (DetectedObjects.DetectedObject detectedObject : detectedObjects) {
            String className = detectedObject.getClassName();
            if (StringUtil.isBlank(className)) {
                continue;
            }
            BoundingBox box = detectedObject.getBoundingBox();
            Rectangle rectangle = box.getBounds();
            int x = (int) (rectangle.getX() * imageWidth);
            int y = (int) (rectangle.getY() * imageHeight);
            g.setPaint(BACKGROUND);
            g.drawRect(x,
                    y,
                    (int) (rectangle.getWidth() * imageWidth),
                    (int) (rectangle.getHeight() * imageHeight));
            drawText(g, className, x, y, stroke, 4);
        }
        g.dispose();
        return imageToMat(image);
    }

    private static void drawText(Graphics2D g, String text, int x, int y, int stroke, int padding) {
        FontMetrics metrics = g.getFontMetrics();
        x += stroke / 2;
        y += stroke / 2;
        int width = metrics.stringWidth(text) + padding * 2 - stroke / 2;
        int height = metrics.getHeight() + metrics.getDescent();
        int ascent = metrics.getAscent();
        java.awt.Rectangle background = new java.awt.Rectangle(x, y, width, height);
        g.fill(background);
        g.setPaint(Color.WHITE);
        g.setFont(FONT);
        g.drawString(text, x + padding, y + ascent);
    }

    private static BufferedImage mat2Image(Mat mat) {
        MatOfByte mob = new MatOfByte();
        Imgcodecs.imencode(".png", mat, mob);
        byte[] byteArray = mob.toArray();
        BufferedImage bufImage;
        InputStream in = new ByteArrayInputStream(byteArray);
        try {
            bufImage = ImageIO.read(in);
        } catch (IOException e) {
            throw new RuntimeException("Failed to convert an image from Mat to BufferedImage", e);
        }
        return bufImage;
    }

    private static Mat imageToMat(BufferedImage image) {
        if (image.getType() != BufferedImage.TYPE_3BYTE_BGR) {
            BufferedImage output = new BufferedImage(
                    image.getWidth(),
                    image.getHeight(),
                    BufferedImage.TYPE_3BYTE_BGR);
            Graphics2D g = output.createGraphics();
            try {
                g.setComposite(AlphaComposite.Src);
                g.drawImage(image, 0, 0, null);
            } finally {
                g.dispose();
            }
        }
        byte[] pixels = ((DataBufferByte) image.getRaster()
                .getDataBuffer()).getData();
        Mat mat = Mat.eye(image.getHeight(), image.getWidth(), CvType.CV_8UC3);
        mat.put(0, 0, pixels);
        return mat;
    }

}