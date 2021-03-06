package com.cg.utils;

import com.cg.exception.DataInputException;
import com.cg.model.ImageProduct;
import com.cg.model.Product;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class UploadUtils {

    public static final String IMAGE_UPLOAD_FOLDER = "product_images";
    public static final String VIDEO_UPLOAD_FOLDER = "product_videos";

    public Map buildImageUploadParams(ImageProduct imageProduct) {
        if (imageProduct == null || imageProduct.getId() == null) {
            throw new DataInputException("Không thể upload hình ảnh của sản phẩm chưa được lưu");
        }

        String publicId = String.format("%s/%s", IMAGE_UPLOAD_FOLDER, imageProduct.getId());

        return ObjectUtils.asMap(
                "public_id", publicId,
                "overwrite", true,
                "resource_type", "image"
        );
    }

    public Map buildImageDestroyParams(Product product, String publicId) {
        if (product == null || product.getIdProduct() == null)
            throw new DataInputException("Không thể destroy hình ảnh của sản phẩm không xác định");

        return ObjectUtils.asMap(
                "public_id", publicId,
                "overwrite", true,
                "resource_type", "image"
        );
    }

    public Map buildVideoUploadParams(ImageProduct imageProduct) {
        if (imageProduct == null || imageProduct.getId() == null)
            throw new DataInputException("Không thể upload video của sản phẩm chưa được lưu");

        String publicId = String.format("%s/%s", VIDEO_UPLOAD_FOLDER, imageProduct.getId());

        return ObjectUtils.asMap(
                "public_id", publicId,
                "overwrite", true,
                "resource_type", "video"
        );
    }

    public Map buildVideoDestroyParams(Product product, String publicId) {
        if (product == null || product.getIdProduct() == null)
            throw new DataInputException("Không thể destroy video của sản phẩm không xác định");

        return ObjectUtils.asMap(
                "public_id", publicId,
                "overwrite", true,
                "resource_type", "video"
        );
    }
}
