package com.julong.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.julong.dao.entity.CityInfo;
import com.julong.service.CityService;

@Controller
public class CityAction {

	@Autowired
	private CityService cityServiceImpl;
	
	@RequestMapping("/citys")
	public ResponseEntity<Object> getCityList(){
		Pageable pageable = new PageRequest(1, 10);
		try {
			Page<CityInfo> list = this.cityServiceImpl.getAll(pageable);
			System.out.println(1/0);
			return new ResponseEntity<Object>(list,HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//异常返回
			return new ResponseEntity<Object>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping("/city/{cityId}")
	public ResponseEntity<Object> getCity(@PathVariable("cityId") int cityId){
		try {
			CityInfo cityInfo = this.cityServiceImpl.getByCityId(cityId);
			return new ResponseEntity<Object>(cityInfo,HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//异常返回
			return new ResponseEntity<Object>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
}
