/**
 * 
 */
package com.chen.seckill;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.CORBA.LongHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.chen.seckill.dao.ISuccessKilledDao;
import com.chen.seckill.pojo.SuccessKilled;

/**
 *<p>标题: SuccessKilledTest </p>
 *<p>描述： </p>
 *<p>company:</p>
 * @作者  陈加望
 * @时间  2017年3月15日 下午1:40:47
 *@版本 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SuccessKilledTest {

	@Resource
	private ISuccessKilledDao successKilled;
	
	@Test
	public void  testInsertSuccessKilled(){
		
	}
	
	public void testQueryByIdWithSeckill(){
		
	}
	
	
	
}
