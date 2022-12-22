package PonyLand.PonyLand.service;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.stereotype.Component;

@Component
public class TomcatConfig implements WebServerFactoryCustomizer<TomcatServletWebServerFactory> {

    @Override
    public void customize(TomcatServletWebServerFactory factory) {
        //톰캣의 docBase 를 변경함
       // factory.setDocumentRoot(new File("C:\\Users\\이진혁\\AppData\\Local\\Temp\\tomcat-docbase.80.13049837589883602549"));
    }
}