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
 *<p>����: SuccessKilledTest </p>
 *<p>������ </p>
 *<p>company:</p>
 * @����  �¼���
 * @ʱ��  2017��3��15�� ����1:40:47
 *@�汾 
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
