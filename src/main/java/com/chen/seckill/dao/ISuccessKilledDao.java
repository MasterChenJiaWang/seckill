/**
 * 
 */
package com.chen.seckill.dao;

import org.apache.ibatis.annotations.Param;

import com.chen.seckill.pojo.SuccessKilled;

/**
 *<p>����: ISuccessKilledDao </p>
 *<p>������ ��ɱ�ɹ�dao</p>
 *<p>company:</p>
 * @����  �¼���
 * @ʱ��  2017��3��15�� ����10:09:12
 *@�汾 
 */
public interface ISuccessKilledDao {

	/**
	 * ���빺����ϸ���ɹ����ظ�
	 * 
	 * ���������
	 *@ʱ�� 2017��3��15�� ����10:09:59
	 */
	int insertSuccessKilled(@Param("seckillId")long  seckillId,@Param("userPhone")long userPhone);
	
	/**
	 * ����id ��ѯSeccessKilled ��Я����ɱ��Ʒ
	 * 
	 *@ʱ�� 2017��3��15�� ����10:11:10
	 */
	SuccessKilled queryByIdWithSeckill(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);
}
