use image::codecs::gif;
use image::codecs::gif::{GifDecoder, GifEncoder};
use image::{imageops, AnimationDecoder, Frame, ImageError, ImageResult};
use std::fs::File;
use std::io::BufReader;

pub struct ResizeResult {
    pub resized: bool,
    pub error_type: Option<ResizeError>,
}

pub enum ResizeError {
    Decoding,
    Parameter,
    Limits,
    Unsupported,
    IoError,
}

pub fn resize(input_path: &str, output_path: &str, width: u32, height: u32) -> ResizeResult {
    let result = resize0(input_path, output_path, width, height);
    match result {
        Ok(resized) => {
            ResizeResult {
                resized,
                error_type: None,
            }
        }
        Err(e) => {
            ResizeResult {
                resized: false,
                error_type: Some(match e {
                    ImageError::Decoding(_) => {
                        ResizeError::Decoding
                    }
                    ImageError::Encoding(_) => {
                        panic!("Encoding error")
                    }
                    ImageError::Parameter(_) => {
                        ResizeError::Parameter
                    }
                    ImageError::Limits(_) => {
                        ResizeError::Limits
                    }
                    ImageError::Unsupported(_) => {
                        ResizeError::Unsupported
                    }
                    ImageError::IoError(_) => {
                        ResizeError::IoError
                    }
                }),
            }
        }
    }
}

fn should_resize(width: u32, height: u32, img_width: u32, img_height: u32) -> bool {
    img_width > width || img_height > height
}

fn resize0(input_path: &str, output_path: &str, width: u32, height: u32) -> ImageResult<bool> {
    // TODO: webp
    if input_path.ends_with(".gif") {
        return resize_gif(&input_path, &output_path, width, height);
    }
    let img = image::open(input_path)?;
    if should_resize(width, height, img.width(), img.height()) {
        let thumb = img.thumbnail(width, height);
        thumb.save(output_path)?;
        Ok(true)
    } else {
        Ok(false)
    }
}

fn resize_gif(input_path: &str, output_path: &str, width: u32, height: u32) -> ImageResult<bool> {
    match File::open(input_path) {
        Ok(f) => {
            let raw_frames = GifDecoder::new(BufReader::new(f))?.into_frames();
            if let Ok(frames) = raw_frames.collect_frames() {
                if frames.is_empty() {
                    return Ok(false);
                }
                let first_frame = frames.first().unwrap().buffer();
                if !should_resize(width, height, first_frame.width(), first_frame.height()) {
                    return Ok(false);
                }
                let resized_frames = frames.iter().map(|frame| Frame::new(imageops::thumbnail(&frame.buffer().clone(), width, height)));
                match File::create(output_path) {
                    Ok(gif_out) => {
                        let mut encoder = GifEncoder::new(gif_out);
                        encoder.set_repeat(gif::Repeat::Infinite)?;
                        encoder.encode_frames(resized_frames)?;
                        Ok(true)
                    }
                    Err(e) => {
                        Err(ImageError::IoError(e))
                    }
                }
            } else {
                Ok(false)
            }
        }
        Err(e) => {
            Err(ImageError::IoError(e))
        }
    }
}