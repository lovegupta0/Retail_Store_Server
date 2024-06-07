package com.retailStore.productService.Config;

import feign.Feign;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UIDGenerator implements IdentifierGenerator {
    @Value ("${UID.server.URL}")
    String url;
    @Override
    public Object generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) {
        UIDFeign feign= Feign.builder().target(UIDFeign.class,url);
        return feign.getUID();
    }
}
