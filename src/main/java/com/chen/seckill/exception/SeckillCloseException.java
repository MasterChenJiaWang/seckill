/**
 * 
 */
package com.chen.seckill.exception;

/**
 *<p>����: SeckillCloseException </p>
 *<p>��������ɱ�ر��쳣 </p>
 *<p>company:</p>
 * @����  �¼���
 * @ʱ��  2017��3��15�� ����2:12:36
 *@�汾 
 */
public class SeckillCloseException  extends SeckillException{

	/**
	 * @param message
	 */
	public SeckillCloseException(String message) {
		super(message);
	}
	
	public SeckillCloseException(String message, Throwable cause) {
		super(message, cause);
	}

}
