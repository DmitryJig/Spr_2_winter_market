//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2023.01.09 at 11:20:18 PM YEKT 
//


package com.spring.wintermarket.core.soap.productsws;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.jd.spring.ws.productsws package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.jd.spring.ws.productsws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetProductByNameRequest }
     * 
     */
    public GetProductByNameRequest createGetProductByNameRequest() {
        return new GetProductByNameRequest();
    }

    /**
     * Create an instance of {@link GetAllProductsRequest }
     * 
     */
    public GetAllProductsRequest createGetAllProductsRequest() {
        return new GetAllProductsRequest();
    }

    /**
     * Create an instance of {@link GetProductByNameResponse }
     * 
     */
    public GetProductByNameResponse createGetProductByNameResponse() {
        return new GetProductByNameResponse();
    }

    /**
     * Create an instance of {@link ProductWs }
     * 
     */
    public ProductWs createProductWs() {
        return new ProductWs();
    }

    /**
     * Create an instance of {@link GetAllProductsResponse }
     * 
     */
    public GetAllProductsResponse createGetAllProductsResponse() {
        return new GetAllProductsResponse();
    }

}
