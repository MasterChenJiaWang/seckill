/**
 * 
 */
package com.chen.seckill.exception;

/**
 *<p>标题: SeckillCloseException </p>
 *<p>描述：秒杀关闭异常 </p>
 *<p>company:</p>
 * @作者  陈加望
 * @时间  2017年3月15日 下午2:12:36
 *@版本 
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
