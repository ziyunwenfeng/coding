package com.example.feignconsume;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value="service001",fallback=FeignFallBack.class)
public interface FeignInterface {
	@RequestMapping(value="/hello",method=RequestMethod.GET)
	public String getService();
	@RequestMapping(value="/hello1",method=RequestMethod.GET)
	public String getInsert(@RequestParam("name") String name);
	@RequestMapping(value="/hello3",method=RequestMethod.POST)
	public String insert(@RequestBody User u);
}
