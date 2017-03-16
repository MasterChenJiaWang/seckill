/**
 * 
 */
package com.chen.seckill.exception;

/**
 *<p>标题: RepeatKillException </p>
 *<p>描述：重复秒杀异常（运行期异常） </p>
 *<p>company:</p>
 * @作者  陈加望
 * @时间  2017年3月15日 下午2:11:53
 *@版本 
 */
public class RepeatKillException extends RuntimeException {

	public RepeatKillException(String message) {
		super(message);
	}

	public RepeatKillException(String message, Throwable cause) {
		super(message, cause);
	}
}
