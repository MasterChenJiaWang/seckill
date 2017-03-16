/**
 * 
 */
package com.chen.seckill.pojo;

import java.util.Date;

/**
 *<p>标题: SuccessKilled </p>
 *<p>描述： </p>
 *<p>company:</p>
 * @作者  陈加望
 * @时间  2017年3月15日 上午10:02:29
 *@版本 
 */
public class SuccessKilled {

	private long seckillId;

	private long userPhone;

	private short state;

	private Date creteTime;

	// 多对一的复合属性
	private Seckill seckill;

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
	 * @return the userPhone
	 */
	public long getUserPhone() {
		return userPhone;
	}

	/**
	 * @param userPhone the userPhone to set
	 */
	public void setUserPhone(long userPhone) {
		this.userPhone = userPhone;
	}

	/**
	 * @return the state
	 */
	public short getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(short state) {
		this.state = state;
	}

	/**
	 * @return the creteTime
	 */
	public Date getCreteTime() {
		return creteTime;
	}

	/**
	 * @param creteTime the creteTime to set
	 */
	public void setCreteTime(Date creteTime) {
		this.creteTime = creteTime;
	}

	/**
	 * @return the seckill
	 */
	public Seckill getSeckill() {
		return seckill;
	}

	/**
	 * @param seckill the seckill to set
	 */
	public void setSeckill(Seckill seckill) {
		this.seckill = seckill;
	}

	/* 
	 *
	 *2017年3月15日上午10:04:23
	 */
	@Override
	public String toString() {
		return "SuccessKilled [seckillId=" + seckillId + ", userPhone=" + userPhone + ", state=" + state
				+ ", creteTime=" + creteTime + ", seckill=" + seckill + "]";
	}
	
	
}
