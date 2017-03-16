/**
 * 
 */
package com.chen.seckill.service;

import java.util.List;

import com.chen.seckill.dto.Exposer;
import com.chen.seckill.dto.SeckillExecution;
import com.chen.seckill.exception.RepeatKillException;
import com.chen.seckill.exception.SeckillCloseException;
import com.chen.seckill.exception.SeckillException;
import com.chen.seckill.pojo.Seckill;

/**
 *<p>标题: ISeckillService </p>
 *<p>描述： </p>
 *<p>company:</p>
 * @作者  陈加望
 * @时间  2017年3月15日 上午11:14:02
 *@版本 
 */
public interface ISeckillService {

	/**
	 * 查询所有秒杀纪录
	 * 
	 *@时间 2017年3月15日 下午1:55:16
	 */
	List<Seckill> getSeckillList();
	
	/**
	 * 查询单个秒杀纪录
	 * 
	 *@时间 2017年3月15日 下午1:55:37
	 */
	Seckill getById(long seckillId);
	
	
	/**
	 * 秒杀开启是输出是秒杀接口地址
	 * 否则输出系统时间和秒杀时间
	 * 
	 *@时间 2017年3月15日 下午1:56:25
	 */
	Exposer exportSeckillUrl(long seckillId);
	
	/**
	 * 执行秒杀操作
	 *@时间 2017年3月15日 下午2:06:43
	 */
	SeckillExecution executeSeckill(long seckillId, long userPhone, String md5)
			throws SeckillException, RepeatKillException, SeckillCloseException;
	
	
	/**
	 * 执行秒杀操作by存储过程
	 * 
	 *@时间 2017年3月15日 下午2:06:56
	 */
	SeckillExecution executeSeckillProcedure(long seckillId, long userPhone, String md5)
			throws SeckillException, RepeatKillException, SeckillCloseException;
 }
