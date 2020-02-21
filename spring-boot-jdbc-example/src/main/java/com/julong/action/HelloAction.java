package com.julong.action;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class HelloAction {

	private static final Logger logger = LoggerFactory.getLogger(HelloAction.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	
	@RequestMapping("")
	@ResponseBody
	public String sayHello(){
		
		logger.debug("debug");
		logger.error("error");
		logger.info("info");
		logger.warn("warn");
		
		List<Object> list = this.jdbcTemplate.query("select * from temp ",new RowMapper<Object>(){

			@Override
			public List<Object> mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				List<Object> dataList = new ArrayList<Object>();
				while (rs.next()) {
					Object obj = rs.getObject(1);
					logger.info("info:{}",obj);
					dataList.add(obj);
				}
				return dataList;
			}
		});
		try {
			logger.info("info:{}",new ObjectMapper().writeValueAsString(list));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "hello world!";
	}
}
