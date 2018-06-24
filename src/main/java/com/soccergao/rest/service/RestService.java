package com.soccergao.rest.service;

import java.util.Map;

import org.springframework.data.domain.Page;

public interface RestService {
	<T> T get(String url, Class<T> responseType, Map<String, Object> uriVariables);
	
	<T> T get(String url, Class<T> responseType, Object...uriVariables);
	
	<T, V> Page<T> search(String url, Class<T> responseType, Map<String, V> uriVariables);
	
	<T> Page<T> search(String url, Class<T> responseType, Object...uriVariables);
	
	<T> T post(String url, Object requset, Class<T> responseType, Object...uriVariables);
}
