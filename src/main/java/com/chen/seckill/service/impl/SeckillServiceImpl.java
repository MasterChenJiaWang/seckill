/**
 * 
 */
package com.chen.seckill.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import com.chen.seckill.dao.ISeckillDao;
import com.chen.seckill.dao.ISuccessKilledDao;
import com.chen.seckill.dao.cache.RedisDao;
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
 *<p>����: SeckillServiceImpl </p>
 *<p>������ ҵ��ӿڣ�վ��"ʹ����"�Ƕ���ƽӿ�
 * �������棺�����������ȣ��������������ͣ�return ����/�쳣��</p>
 *<p>company:</p>
 * @����  �¼���
 * @ʱ��  2017��3��15�� ����11:15:24
 *@�汾 
 */

@Service
public class SeckillServiceImpl  implements ISeckillService{

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ISeckillDao seckillDao;
	
	@Autowired
	private ISuccessKilledDao successKilledDao;
	
	 @Autowired
	  private RedisDao redisDao;
	
	// md5��ֵ�ַ��������ڻ���MD5
	private final String slat = "skdfjksjdf7787%^%^%^FSKJFK*(&&%^%&^8DF8^%^^*7hFJDHFJ";
	
	private String getMD5(long seckillId){
		
		String base=seckillId+"/"+slat;
		String md5=DigestUtils.md5DigestAsHex(base.getBytes());
		return md5;
	}
	/* 
	 *��ѯ������ɱ��¼
	 *2017��3��15������2:14:20
	 */
	@Override
	public List<Seckill> getSeckillList() {
	
		return seckillDao.queryAll(0,4);
		
	}

	/* 
	 *��ѯ������ɱ��¼
	 *2017��3��15������2:14:20
	 */
	@Override
	public Seckill getById(long seckillId) {
		return seckillDao.queryById(seckillId);
	}

	/* 
	 *��ɱ���� : ���������ɱ�ӿڵ�ַ
	 * �������ϵͳʱ�����ɱʱ��
	 *2017��3��15������2:14:20
	 */
	@Override
	public Exposer exportSeckillUrl(long seckillId) {
	
		//先从缓存中查看是否有该数据
		Seckill seckill = redisDao.getSeckill(seckillId);
		if(seckill==null){
			//说明缓存中没有数据,需要从数据库中获取
			seckill= seckillDao.queryById(seckillId);
			if(seckill==null){
				return new Exposer(false, seckillId);
			}else{
				//把数据存入缓存，以便下次快速获取
				redisDao.putSeckill(seckill);
			}
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
	 *ִ����ɱ����
	 *  ʹ��ע��������񷽷����ŵ㣺 
	 * 1.�����ŶӴ��һ��Լ������ȷ��ע���񷽷��ı�̷��
	 * 2.��֤���񷽷���ִ��ʱ�価���̣ܶ���Ҫ�����������������RPC/HTTP������߰��뵽���񷽷��ⲿ
	 * 3.�������еķ�������Ҫ������ֻ��һ���޸Ĳ�����ֻ����������Ҫ�������
	 *2017��3��15������2:14:20
	 */
	@Override
	@Transactional
	public SeckillExecution executeSeckill(long seckillId, long userPhone, String md5)
			throws SeckillException, RepeatKillException, SeckillCloseException {
	
		if (md5 == null || !md5.equals(getMD5(seckillId))){
			throw new SeckillException("��ɱû�п���");
		}
		Date nowTime = new Date();
		
		try {
			int insertCount= successKilledDao.insertSuccessKilled(seckillId, userPhone);
			if(insertCount<=0){
				
				throw new RepeatKillException("重复秒杀");
			}else{
				 //减库存,热点商品竞争
                int updateCount=seckillDao.reduceNumber(seckillId,nowTime);
                if (updateCount<=0)
                {
                    //没有更新库存记录，说明秒杀结束 rollback
                    throw new SeckillCloseException("seckill is closed");
                }else {
                    //秒杀成功,得到成功插入的明细记录,并返回成功秒杀的信息 commit
                    SuccessKilled successKilled=successKilledDao.queryByIdWithSeckill(seckillId,userPhone);
                    return new SeckillExecution(seckillId, SeckillStateEnum.SUCCESS,successKilled);
                }
			}
//			//  ��¼������Ϊ
//			int reduceNumber = seckillDao.reduceNumber(userPhone, nowTime);
//			
//			if(reduceNumber<=0){
//				//û�и��¼�¼ �ظ���ɱ
//				throw new SeckillCloseException("��ɱ�Ѿ�����");
//			}else{
//				// ����棬�ȵ���Ʒ����
//				int insertCount = successKilledDao.insertSuccessKilled(seckillId, userPhone);
//				
//				if(insertCount<=0){
//					// û�и��µ���¼ rollback
//					throw new RepeatKillException("��ɱ��ͻ");
//				}else{
//					//��ɱ�ɹ�
//					SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(seckillId, userPhone);
//					return new SeckillExecution(seckillId, SeckillStateEnum.SUCCESS, successKilled);
//				}
//			}
		} catch (SeckillCloseException e1)
        {
            throw e1;
        }catch (RepeatKillException e2)
        {
            throw e2;
        }catch (Exception e)
        {
            logger.error(e.getMessage(),e);
            //所以编译期异常转化为运行期异常
            throw new SeckillException("seckill inner error :"+e.getMessage());
        }
	}

	/* 
	 * ִ����ɱ����by�洢����
	 *2017��3��15������2:14:20
	 */
	@Override
	public SeckillExecution executeSeckillProcedure(long seckillId, long userPhone, String md5){

		if (md5 == null || !md5.equals(getMD5(seckillId))){
            throw new SeckillException(SeckillStateEnum.DATA_REWRITE.getStateInfo());
        }

        Date killTime = new Date();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("seckillId", seckillId);
        map.put("phone", userPhone);
        map.put("killTime", killTime);
        map.put("result", null);
        //执行存储过程 result被赋值
        try {
            seckillDao.killByProcedure(map);
            //获取result
            int result = MapUtils.getInteger(map, "result", -2);
            if (result == 1) {
                SuccessKilled sk = successKilledDao.queryByIdWithSeckill(seckillId, userPhone);
                return new SeckillExecution(seckillId, SeckillStateEnum.SUCCESS, sk);
            } else {
                return new SeckillExecution(seckillId, SeckillStateEnum.stateOf(result));
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
            return new SeckillExecution(seckillId, SeckillStateEnum.INNER_ERROR);
        }
		
	}



	
}
