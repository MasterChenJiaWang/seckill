/**
 * 
 */
package com.chen.seckill.exception;

/**
 *<p>����: SeckillException </p>
 *<p>������ ��ɱ���ҵ���쳣 </p>
 *<p>company:</p>
 * @����  �¼���
 * @ʱ��  2017��3��15�� ����2:10:40
 *@�汾 
 */
public class SeckillException  extends RuntimeException{

	public SeckillException(String message) {
		super(message);
	}

	public SeckillException(String message, Throwable cause) {
		super(message, cause);
	}
}
