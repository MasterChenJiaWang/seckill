/**
 * 
 */
package com.chen.seckill.pojo;

import java.util.Date;

/**
 *<p>����: SuccessKilled </p>
 *<p>������ </p>
 *<p>company:</p>
 * @����  �¼���
 * @ʱ��  2017��3��15�� ����10:02:29
 *@�汾 
 */
public class SuccessKilled {

	private long seckillId;

	private long userPhone;

	private short state;

	private Date creteTime;

	// ���һ�ĸ�������
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
	 *2017��3��15������10:04:23
	 */
	@Override
	public String toString() {
		return "SuccessKilled [seckillId=" + seckillId + ", userPhone=" + userPhone + ", state=" + state
				+ ", creteTime=" + creteTime + ", seckill=" + seckill + "]";
	}
	
	
}
