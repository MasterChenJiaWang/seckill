/**
 * 
 */
package com.chen.seckill.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.chen.seckill.pojo.Seckill;

/**
 *<p>标题: ISeckillDao </p>
 *<p>描述： 秒杀库存DAO接口</p>
 *<p>company:</p>
 * @作者  陈加望
 * @时间  2017年3月15日 上午10:05:48
 *@版本 
 */
public interface ISeckillDao {

	/**
	 * 减库存
	 * @return 如果影响行数等于>1，表示更新的记录行数
	 *@时间 2017年3月15日 上午10:07:09
	 */
	int reduceNumber(@Param("seckillId") long seckillId,@Param("killTime") Date killTime);
	
	/**
	 * 根据id查询秒杀对象
	 * 
	 *@时间 2017年3月15日 上午10:07:04
	 */
	Seckill queryById(long seckillId);
	
	/**
	 * 根据偏移量查询米啊送哈商品列表
	 * 
	 *@时间 2017年3月15日 上午10:08:29
	 */
	List<Seckill> queryAll(@Param("offset") int offset,@Param("limit") int limit);
	
	/**
	 * 使用存储过程执行秒杀
	 * 
	 * @param paramMap
	 */
	void killByProcedure(Map<String, Object> paramMap);
}
