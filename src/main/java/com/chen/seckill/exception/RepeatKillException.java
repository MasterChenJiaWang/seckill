/**
 * 
 */
package com.chen.seckill.exception;

/**
 *<p>����: RepeatKillException </p>
 *<p>�������ظ���ɱ�쳣���������쳣�� </p>
 *<p>company:</p>
 * @����  �¼���
 * @ʱ��  2017��3��15�� ����2:11:53
 *@�汾 
 */
public class RepeatKillException extends RuntimeException {

	public RepeatKillException(String message) {
		super(message);
	}

	public RepeatKillException(String message, Throwable cause) {
		super(message, cause);
	}
}
