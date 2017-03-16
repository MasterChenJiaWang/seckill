/**
 * 
 */
package com.chen.seckill.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import com.chen.seckill.dao.ISeckillDao;
import com.chen.seckill.dao.ISuccessKilledDao;
import com.chen.seckill.dto.Exposer;
import com.chen.seckill.dto.SeckillExecution;
import com.chen.seckill.enums.SeckillStateEnum;
import com.chen.seckill.exception.RepeatKillException;
import com.chen.seckill.exception.SeckillCloseException;
import com.chen.seckill.exception.SeckillException;
import com.chen.seckill.pojo.Seckill;
import com.chen.seckill.pojo.SuccessKilled;
import com.chen.seckill.service.ISeckillService;


/**
 *<p>标题: SeckillServiceImpl </p>
 *<p>描述： 业务接口：站在"使用者"角度设计接口
 * 三个方面：方法定义粒度，参数，返回类型（return 类型/异常）</p>
 *<p>company:</p>
 * @作者  陈加望
 * @时间  2017年3月15日 上午11:15:24
 *@版本 
 */

@Service

public class SeckillServiceImpl  implements ISeckillService{

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Resource
	private ISeckillDao seckillDao;
	
	@Resource
	private ISuccessKilledDao successKilledDao;
	
	// md5盐值字符串，用于混淆MD5
	private final String slat = "skdfjksjdf7787%^%^%^FSKJFK*(&&%^%&^8DF8^%^^*7hFJDHFJ";
	
	private String getMD5(long seckillId){
		
		String base=seckillId+"/"+slat;
		String md5=DigestUtils.md5DigestAsHex(base.getBytes());
		return md5;
	}
	/* 
	 *查询所有秒杀纪录
	 *2017年3月15日下午2:14:20
	 */
	@Override
	public List<Seckill> getSeckillList() {
	
		return seckillDao.queryAll(0,4);
		
	}

	/* 
	 *查询单个秒杀纪录
	 *2017年3月15日下午2:14:20
	 */
	@Override
	public Seckill getById(long seckillId) {
		return seckillDao.queryById(seckillId);
	}

	/* 
	 *秒杀开启 : 是输出是秒杀接口地址
	 * 否则输出系统时间和秒杀时间
	 *2017年3月15日下午2:14:20
	 */
	@Override
	public Exposer exportSeckillUrl(long seckillId) {
	
		Seckill seckill = seckillDao.queryById(seckillId);
		if(seckill==null){
			return new Exposer(false, seckillId);
		}
		Date startTime = seckill.getStartTime();
		Date endTime = seckill.getEndTime();
		Date nowTime = new Date();
		
		if(nowTime.getTime()<startTime.getTime() || nowTime.getTime()>endTime.getTime()){
			
			return new Exposer(false, seckillId, nowTime.getTime(), startTime.getTime(), endTime.getTime());
		}
		String md5=null;
		return new Exposer(true, md5, seckillId);
	}

	/* 
	 *执行秒杀操作
	 *  使用注解控制事务方法的优点： 
	 * 1.开发团队达成一致约定，明确标注事务方法的编程风格
	 * 2.保证事务方法的执行时间尽可能短，不要穿插其他网络操作，RPC/HTTP请求或者剥离到事务方法外部
	 * 3.不是所有的方法都需要事务，如只有一条修改操作，只读操作不需要事务控制
	 *2017年3月15日下午2:14:20
	 */
	@Override
	@Transactional
	public SeckillExecution executeSeckill(long seckillId, long userPhone, String md5)
			throws SeckillException, RepeatKillException, SeckillCloseException {
	
		if(md5==null || !md5.equals(getMD5(seckillId))){
			throw new SeckillException("秒杀没有开启");
		}
		Date nowTime = new Date();
		
		try {
			//  记录购买行为
			int reduceNumber = seckillDao.reduceNumber(userPhone, nowTime);
			
			if(reduceNumber<=0){
				//没有跟新纪录 重复秒杀
				throw new SeckillCloseException("秒杀已经结束");
			}else{
				// 减库存，热点商品竞争
				int insertCount = successKilledDao.insertSuccessKilled(seckillId, userPhone);
				
				if(insertCount<=0){
					// 没有更新到记录 rollback
					throw new RepeatKillException("秒杀冲突");
				}else{
					//秒杀成功
					SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(seckillId, userPhone);
					return new SeckillExecution(seckillId, SeckillStateEnum.SUCCESS, successKilled);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			// 所有编译期异常转换为运行期异常
			throw new SeckillException("seckill inner error:" + e.getMessage());
		}
	}

	/* 
	 * 执行秒杀操作by存储过程
	 *2017年3月15日下午2:14:20
	 */
	@Override
	public SeckillExecution executeSeckillProcedure(long seckillId, long userPhone, String md5)
			throws SeckillException, RepeatKillException, SeckillCloseException {
		// TODO Auto-generated method stub
		return null;
	}



	
}
