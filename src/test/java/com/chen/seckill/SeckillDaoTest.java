/**
 * 
 */
package com.chen.seckill;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.chen.seckill.dao.ISeckillDao;
import com.chen.seckill.pojo.Seckill;

/**
 *<p>标题: SeckillDaoTest </p>
 *<p>描述： </p>
 *<p>company:</p>
 * @作者  陈加望
 * @时间  2017年3月15日 上午11:20:22
 *@版本 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SeckillDaoTest {

	@Resource
	private ISeckillDao seckillDao;
	
	@Test
	public  void testQueryById()throws Exception{
		
		long id=1000;
		Seckill seckill = seckillDao.queryById(id);
		System.out.println(seckill.getName());
		System.out.println(seckill);
	}
	
	@Test
	public void testReduceNumber() throws Exception{
		
		Date killTime = new Date();
		int updateCount = seckillDao.reduceNumber(1000L, killTime);
		System.out.println("updateCount=" + updateCount);
	}
	
	@Test
	public void testQueryAll() throws Exception{
		List<Seckill> list = seckillDao.queryAll(0, 100);
		for(Seckill seckill:list){
			System.out.println(seckill);
		}
		
	}
}
