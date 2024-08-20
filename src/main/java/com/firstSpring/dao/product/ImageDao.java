package com.firstSpring.dao.product;

import com.firstSpring.domain.product.ImageDto;

public interface  ImageDao {
    ImageDto select(Integer mainImgNo);
}
