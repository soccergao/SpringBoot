package com.soccergao.rest.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.soccergao.rest.RestPageImpl;
import com.soccergao.rest.service.RestService;


@Service
public class RestServiceImpl implements RestService {
	private final static Logger logger = LoggerFactory.getLogger(RestServiceImpl.class); 
	
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public <T> T get(String url, Class<T> responseType, Map<String, Object> uriVariables) {
		logger.info("Get: " + url + ", responseTyep:" + responseType + ", paramter: " + uriVariables);
		return uriVariables.isEmpty() ? restTemplate.getForObject(url, responseType)
				: restTemplate.getForObject(url, responseType, uriVariables);
	}
	
	@Override
	public <T> T get(String url, Class<T> responseType, Object...uriVariables) {
		logger.info("Get: " + url + ", responseTyep:" + responseType + ", paramter: " + uriVariables);
		return restTemplate.getForObject(url, responseType, uriVariables);
	}

	@Override
	public <T> Page<T> search(String url, Class<T> responseType, Object...uriVariables) {
		ParameterizedTypeReference<RestPageImpl<T>> typeRef = new ParameterizedTypeReference<RestPageImpl<T>>() {
		};
		return restTemplate.exchange(url, HttpMethod.GET, null, typeRef, uriVariables).getBody();
	}
	
	@Override
	public <T, V> Page<T> search(String url, Class<T> responseType, Map<String, V> uriVariables) {
		ParameterizedTypeReference<RestPageImpl<T>> typeRef = new ParameterizedTypeReference<RestPageImpl<T>>() {
		};
		return restTemplate.exchange(url, HttpMethod.GET, null, typeRef, uriVariables).getBody();
	}

	@Override
	public <T> T post(String url, Object request, Class<T> responseType, Object...uriVariables) {
		ResponseEntity<T> resp = restTemplate.postForEntity(url, request, responseType, uriVariables);
		return resp.getBody();
	}
}
