package com.soccergao;

import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Source;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfig {
//	public static CloseableHttpClient acceptsUntrustedCertsHttpClient()
//			throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
//		HttpClientBuilder b = HttpClientBuilder.create();
//
//		SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
//			public boolean isTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
//				return true;
//			}
//		}).build();
//		b.setSSLContext(sslContext);
//
//		HostnameVerifier hostnameVerifier = NoopHostnameVerifier.INSTANCE;
//
//		SSLConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(sslContext, hostnameVerifier);
//		Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
//				.register("http", PlainConnectionSocketFactory.getSocketFactory()).register("https", sslSocketFactory)
//				.build();
//
//		PoolingHttpClientConnectionManager connMgr = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
//		connMgr.setMaxTotal(200);
//		connMgr.setDefaultMaxPerRoute(100);
//		b.setConnectionManager(connMgr);
//
//		CloseableHttpClient client = b.build();
//
//		return client;
//	}

	@Bean
	public RestTemplate restTemplate() {
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
		messageConverters.add(new ByteArrayHttpMessageConverter());
		messageConverters.add(new StringHttpMessageConverter());
		messageConverters.add(new ResourceHttpMessageConverter());
		messageConverters.add(new SourceHttpMessageConverter<Source>());
		messageConverters.add(new AllEncompassingFormHttpMessageConverter());
		messageConverters.add(new MappingJackson2HttpMessageConverter());
		
		final RestTemplate restTemplate = new RestTemplate(messageConverters);
		
		final HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
		final HttpClient httpClient = HttpClientBuilder.create()
		        .setRedirectStrategy(new LaxRedirectStrategy())
		        .build();
		factory.setHttpClient(httpClient);
		 
		return restTemplate;
	}

}
