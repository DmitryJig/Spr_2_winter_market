package com.spring.wintermarket.core.endpoints;

import com.spring.wintermarket.core.converters.ProductConverter;
import com.spring.wintermarket.core.services.ProductService;
import com.spring.wintermarket.core.soap.productsws.*;
import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Endpoint
@RequiredArgsConstructor
public class ProductEndpoint {

    private static final String NAMESPACE_URI = "http://www.jd.com/spring/ws/productsWs";
    private final ProductService productService;
    private final ProductConverter productConverter;

    /*
        Пример запроса: POST http://localhost:8189/winter/ws

        Header -> Content-Type: text/xml

        <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:f="http://www.jd.com/spring/ws/productsWs">
            <soapenv:Header/>
            <soapenv:Body>
                <f:getProductByNameRequest>
                    <f:title>Bread</f:title>
                </f:getProductByNameRequest>
            </soapenv:Body>
        </soapenv:Envelope>
     */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductByNameRequest")
    @ResponsePayload
    @Transactional // одновременно запрашивается категория товара из бд
    public GetProductByNameResponse getProductByName(@RequestPayload GetProductByNameRequest request) {
        GetProductByNameResponse response = new GetProductByNameResponse();
        response.setProductWs(productConverter.entityToSoap(productService.findByTitle(request.getTitle())));
        return response;
    }

    /*
        Пример запроса: POST http://localhost:8189/winter/ws

        Header -> Content-Type: text/xml

        <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:f="http://www.jd.com/spring/ws/productsWs">
            <soapenv:Header/>
            <soapenv:Body>
                <f:getAllProductsRequest/>
            </soapenv:Body>
        </soapenv:Envelope>
     */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllProductsRequest")
    @ResponsePayload
    @Transactional
    public GetAllProductsResponse getAllProducts(@RequestPayload GetAllProductsRequest request){
        GetAllProductsResponse response = new GetAllProductsResponse();
        List<ProductWs> productWsList = productService.findAll().stream().map(productConverter::entityToSoap).collect(Collectors.toList());
        productWsList.forEach(response.getProductsWs()::add);
        return response;
    }
}
