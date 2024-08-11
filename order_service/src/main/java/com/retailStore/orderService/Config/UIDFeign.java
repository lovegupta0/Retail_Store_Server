package com.retailStore.orderService.Config;

import feign.RequestLine;

public interface UIDFeign {
    @RequestLine("GET /getUID")
    String getUID();

}
