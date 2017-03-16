/**
 * 
 */
package com.chen.seckill.exception;

/**
 *<p>标题: SeckillException </p>
 *<p>描述： 秒杀相关业务异常 </p>
 *<p>company:</p>
 * @作者  陈加望
 * @时间  2017年3月15日 下午2:10:40
 *@版本 
 */
public class SeckillException  extends RuntimeException{

	public SeckillException(String message) {
		super(message);
	}

	public SeckillException(String message, Throwable cause) {
		super(message, cause);
	}
}
