/**
 * 
 */
package com.chen.seckill.dto;

/**
 *<p>标题: Exposer </p>
 *<p>描述：暴露秒杀接口DTO </p>
 *<p>company:</p>
 * @作者  陈加望
 * @时间  2017年3月15日 下午1:58:23
 *@版本 
 */
public class Exposer {

	private boolean exposed;//是否开启秒杀
	private String md5;// 一种加密措施
	private long seckillId;
	//系统当前时间
	private long now;
	private long start;
	private long end;
	
	/**
	 * 是否开启md5
	 * @param exposed
	 * @param md5
	 * @param seckillId
	 */
	public Exposer(boolean exposed, String md5, long seckillId) {
		this.exposed = exposed;
		this.md5 = md5;
		this.seckillId = seckillId;
	}

	/**
	 * 
	 * @param exposed
	 * @param seckillId
	 * @param now
	 * @param start
	 * @param end
	 */
	public Exposer(boolean exposed, long seckillId, long now, long start, long end) {
		this.exposed = exposed;
		this.seckillId = seckillId;
		this.now = now;
		this.start = start;
		this.end = end;
	}

	/**
	 * 是否开启id
	 * @param exposed
	 * @param seckillId
	 */
	public Exposer(boolean exposed, long seckillId) {
		this.exposed = exposed;
		this.seckillId = seckillId;
	}

	/**
	 * @return the exposed
	 */
	public boolean isExposed() {
		return exposed;
	}

	/**
	 * @param exposed the exposed to set
	 */
	public void setExposed(boolean exposed) {
		this.exposed = exposed;
	}

	/**
	 * @return the md5
	 */
	public String getMd5() {
		return md5;
	}

	/**
	 * @param md5 the md5 to set
	 */
	public void setMd5(String md5) {
		this.md5 = md5;
	}

	/**
	 * @return the seckillId
	 */
	public long getSeckillId() {
		return seckillId;
	}

	/**
	 * @param seckillId the seckillId to set
	 */
	public void setSeckillId(long seckillId) {
		this.seckillId = seckillId;
	}

	/**
	 * @return the now
	 */
	public long getNow() {
		return now;
	}

	/**
	 * @param now the now to set
	 */
	public void setNow(long now) {
		this.now = now;
	}

	/**
	 * @return the start
	 */
	public long getStart() {
		return start;
	}

	/**
	 * @param start the start to set
	 */
	public void setStart(long start) {
		this.start = start;
	}

	/**
	 * @return the end
	 */
	public long getEnd() {
		return end;
	}

	/**
	 * @param end the end to set
	 */
	public void setEnd(long end) {
		this.end = end;
	}
	
}
