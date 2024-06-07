package com.retailStore.userservice.Config;

import feign.RequestLine;

public interface FeignUid {
    @RequestLine("GET /getUID")
    String getUID();

}
