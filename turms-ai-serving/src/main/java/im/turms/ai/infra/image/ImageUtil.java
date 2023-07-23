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
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.MemoryCacheImageInputStream;

import ai.djl.modality.cv.Image;
import ai.djl.modality.cv.ImageFactory;
import ai.djl.modality.cv.output.BoundingBox;
import ai.djl.modality.cv.output.DetectedObjects;
import ai.djl.modality.cv.output.Rectangle;
import ai.djl.modality.cv.util.NDImageUtils;
import ai.djl.ndarray.NDArray;
import ai.djl.ndarray.NDManager;
import io.netty.util.concurrent.FastThreadLocal;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;

import im.turms.server.common.infra.io.FileUtil;

/**
 * @author James Chen
 */
public final class ImageUtil {

    private static final Color BACKGROUND = new Color(0, 0, 0, 192);
    private static final Path IMAGE_DIR = Path.of("image");
    private static final AtomicLong TEMP_IMAGE_FILE_ID = new AtomicLong(0);
    private static final Canvas CANVAS = new Canvas();
    private static final ImageReadParam PNG_IMAGE_READER_DEFAULT_READ_PARAM =
            ImageIO.getImageReadersBySuffix("png")
                    .next()
                    .getDefaultReadParam();
    private static final FastThreadLocal<ImageReader> PNG_IMAGE_READER = new FastThreadLocal<>() {
        @Override
        protected ImageReader initialValue() {
            return ImageIO.getImageReadersBySuffix("png")
                    .next();
        }
    };

    private ImageUtil() {
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

    public static Mat drawBoundingBoxes(Mat image, DetectedObjects detections, Font font) {
        List<DetectedObjects.DetectedObject> list = detections.items();
        if (list.isEmpty()) {
            return image;
        }
        int imageWidth = image.width();
        int imageHeight = image.height();
        for (DetectedObjects.DetectedObject result : list) {
            String className = result.getClassName();
            BoundingBox box = result.getBoundingBox();

            Rectangle rectangle = box.getBounds();
            int x = (int) (rectangle.getX() * imageWidth);
            int y = (int) (rectangle.getY() * imageHeight);

            int targetHeight = (int) (imageHeight * rectangle.getHeight());
            int fontSize = 1;
            Font previousFont = new Font(font.getFontName(), font.getStyle(), fontSize);
            font = previousFont;
            FontMetrics metrics = CANVAS.getFontMetrics(font);
            while (metrics.getHeight() < targetHeight) {
                fontSize++;
                previousFont = font;
                font = new Font(font.getFontName(), font.getStyle(), fontSize);
                metrics = CANVAS.getFontMetrics(font);
            }

            int textRectWidth = metrics.stringWidth(className) + 4 * 2;
            int textRectHeight = metrics.getHeight() + metrics.getDescent();
            Mat submat = image.submat(y, y + textRectHeight, x, x + textRectWidth);

            BufferedImage subimage = mat2Image(submat);

            Graphics2D g = subimage.createGraphics();

            g.setColor(BACKGROUND);
            g.fillRect(0, 0, textRectWidth, textRectHeight);

            g.setFont(previousFont);
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setColor(Color.WHITE);
            int ascent = metrics.getAscent();
            g.drawString(className, 0, ascent);
            g.dispose();

            DataBufferByte dataBuffer = (DataBufferByte) subimage.getRaster()
                    .getDataBuffer();
            byte[] data = dataBuffer.getData();
            submat.put(0, 0, data);
        }
        return image;
    }

    private static BufferedImage mat2Image(Mat mat) {
        MatOfByte mob = new MatOfByte();
        Imgcodecs.imencode(".png", mat, mob);
        byte[] byteArray = mob.toArray();
        ImageReader reader = PNG_IMAGE_READER.get();
        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArray);
                MemoryCacheImageInputStream memoryCacheImageInputStream =
                        new MemoryCacheImageInputStream(byteArrayInputStream)) {
            reader.setInput(memoryCacheImageInputStream);
            // Don't use "ImageIO.read(in)" to read image since it is inefficient.
            return reader.read(0, PNG_IMAGE_READER_DEFAULT_READ_PARAM);
        } catch (IOException e) {
            throw new RuntimeException("Failed to convert an image from Mat to BufferedImage", e);
        } finally {
            reader.dispose();
        }
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

    public static Path writeTempImageFile(Mat mat) {
        Path tempFile = FileUtil.createTempFile(IMAGE_DIR,
                TEMP_IMAGE_FILE_ID.getAndIncrement()
                        + ".png");
        Path path = tempFile.toAbsolutePath()
                .normalize();
        try {
            Imgcodecs.imwrite(path.toString(), mat);
        } catch (Exception e) {
            try {
                Files.deleteIfExists(path);
            } catch (IOException ex) {
                e.addSuppressed(ex);
            }
            throw e;
        }
        return path;
    }

}