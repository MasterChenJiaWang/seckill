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
 *<p>����: ISeckillService </p>
 *<p>������ </p>
 *<p>company:</p>
 * @����  �¼���
 * @ʱ��  2017��3��15�� ����11:14:02
 *@�汾 
 */
public interface ISeckillService {

	/**
	 * ��ѯ������ɱ��¼
	 * 
	 *@ʱ�� 2017��3��15�� ����1:55:16
	 */
	List<Seckill> getSeckillList();
	
	/**
	 * ��ѯ������ɱ��¼
	 * 
	 *@ʱ�� 2017��3��15�� ����1:55:37
	 */
	Seckill getById(long seckillId);
	
	
	/**
	 * ��ɱ�������������ɱ�ӿڵ�ַ
	 * �������ϵͳʱ�����ɱʱ��
	 * 
	 *@ʱ�� 2017��3��15�� ����1:56:25
	 */
	Exposer exportSeckillUrl(long seckillId);
	
	/**
	 * ִ����ɱ����
	 *@ʱ�� 2017��3��15�� ����2:06:43
	 */
	SeckillExecution executeSeckill(long seckillId, long userPhone, String md5)
			throws SeckillException, RepeatKillException, SeckillCloseException;
	
	
	/**
	 * ִ����ɱ����by�洢����
	 * 
	 *@ʱ�� 2017��3��15�� ����2:06:56
	 */
	SeckillExecution executeSeckillProcedure(long seckillId, long userPhone, String md5);
 }
