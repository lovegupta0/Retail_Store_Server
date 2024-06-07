package com.retailStore.productService.Config;

import feign.RequestLine;

public interface UIDFeign {
    @RequestLine("GET /getUID")
    String getUID();

}
