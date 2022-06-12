package me.zhengjie.config;

import io.undertow.server.DefaultByteBufferPool;
import io.undertow.websockets.jsr.WebSocketDeploymentInfo;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.stereotype.Component;

// 解决 undertow warn
// UT026010: Buffer pool was not set on WebSocketDeploymentInfo, the default pool will be used
@Component
public class CustomerUndertowFactory implements WebServerFactoryCustomizer<UndertowServletWebServerFactory> {

    @Override
    public void customize(UndertowServletWebServerFactory factory) {
        factory.addDeploymentInfoCustomizers(deploymentInfo -> deploymentInfo.addServletContextAttribute("io.undertow.websockets.jsr.WebSocketDeploymentInfo", new WebSocketDeploymentInfo().setBuffers(new DefaultByteBufferPool(false, 1024))));
    }
}
