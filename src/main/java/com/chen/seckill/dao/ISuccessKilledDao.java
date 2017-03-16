/**
 * 
 */
package com.chen.seckill.dao;

import org.apache.ibatis.annotations.Param;

import com.chen.seckill.pojo.SuccessKilled;

/**
 *<p>标题: ISuccessKilledDao </p>
 *<p>描述： 秒杀成功dao</p>
 *<p>company:</p>
 * @作者  陈加望
 * @时间  2017年3月15日 上午10:09:12
 *@版本 
 */
public interface ISuccessKilledDao {

	/**
	 * 插入购买明细，可过滤重复
	 * 
	 * 插入的行数
	 *@时间 2017年3月15日 上午10:09:59
	 */
	int insertSuccessKilled(@Param("seckillId")long  seckillId,@Param("userPhone")long userPhone);
	
	/**
	 * 根据id 查询SeccessKilled 并携带秒杀产品
	 * 
	 *@时间 2017年3月15日 上午10:11:10
	 */
	SuccessKilled queryByIdWithSeckill(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);
}
