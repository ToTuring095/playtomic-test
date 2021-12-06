package com.playtomic.tests.wallet.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;


@Configuration
public class DozerConfig {
	
	@Bean
	public DozerBeanMapper dozerBeanMapper(ResourcePatternResolver resourcePatternResolver) throws IOException {
	    Resource[] resources = resourcePatternResolver.getResources("classpath*:dozer/*mapping.xml");
	    if(resources == null || resources.length == 0)
	    	return new DozerBeanMapper();
	    List<String> mappingFiles = new ArrayList<>();
	    for(Resource resource : resources)
	    	mappingFiles.add("dozer/"+resource.getFilename());
	    return new DozerBeanMapper(mappingFiles);
	 }
}
