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
 *<p>����: ISeckillDao </p>
 *<p>������ ��ɱ���DAO�ӿ�</p>
 *<p>company:</p>
 * @����  �¼���
 * @ʱ��  2017��3��15�� ����10:05:48
 *@�汾 
 */
public interface ISeckillDao {

	/**
	 * �����
	 * @return ���Ӱ����������>1����ʾ���µļ�¼����
	 *@ʱ�� 2017��3��15�� ����10:07:09
	 */
	int reduceNumber(@Param("seckillId") long seckillId,@Param("killTime") Date killTime);
	
	/**
	 * ����id��ѯ��ɱ����
	 * 
	 *@ʱ�� 2017��3��15�� ����10:07:04
	 */
	Seckill queryById(long seckillId);
	
	/**
	 * ����ƫ������ѯ�װ��͹���Ʒ�б�
	 * 
	 *@ʱ�� 2017��3��15�� ����10:08:29
	 */
	List<Seckill> queryAll(@Param("offset") int offset,@Param("limit") int limit);
	
	/**
	 * ʹ�ô洢����ִ����ɱ
	 * 
	 * @param paramMap
	 */
	void killByProcedure(Map<String, Object> paramMap);
}
