/**
 * 
 */
package com.chen.seckill.dto;

import com.chen.seckill.enums.SeckillStateEnum;
import com.chen.seckill.pojo.SuccessKilled;

/**
 *<p>标题: SeckillExecution </p>
 *<p>描述： 封装秒杀执行后结果</p>
 *<p>company:</p>
 * @作者  陈加望
 * @时间  2017年3月15日 下午2:07:35
 *@版本 
 */
public class SeckillExecution {

	private long seckillId;

	// 秒杀执行结果状态
	private int state;
	// 状态标识
	private String stateInfo;
	// 秒杀成功对象
	private SuccessKilled successKilled;
	
	public SeckillExecution(long seckillId, SeckillStateEnum stateEnum, SuccessKilled successKilled) {
		this.seckillId = seckillId;
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.successKilled = successKilled;
	}

	public SeckillExecution(long seckillId, SeckillStateEnum stateEnum) {
		this.seckillId = seckillId;
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}

	/**
	 * @param seckillId
	 * @param state
	 * @param stateInfo
	 * @param successKilled
	 */
//	public SeckillExecution(long seckillId, int state, String stateInfo, SuccessKilled successKilled) {
//		super();
//		this.seckillId = seckillId;
//		this.state = state;
//		this.stateInfo = stateInfo;
//		this.successKilled = successKilled;
//	}
//	
//	
//	
//	/**
// * @param seckillId
// * @param state
// * @param stateInfo
// */
//public SeckillExecution(long seckillId, int state, String stateInfo) {
//	super();
//	this.seckillId = seckillId;
//	this.state = state;
//	this.stateInfo = stateInfo;
//}



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
	 * @return the state
	 */
	public int getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(int state) {
		this.state = state;
	}

	/**
	 * @return the stateInfo
	 */
	public String getStateInfo() {
		return stateInfo;
	}

	/**
	 * @param stateInfo the stateInfo to set
	 */
	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}

	/**
	 * @return the successKilled
	 */
	public SuccessKilled getSuccessKilled() {
		return successKilled;
	}

	/**
	 * @param successKilled the successKilled to set
	 */
	public void setSuccessKilled(SuccessKilled successKilled) {
		this.successKilled = successKilled;
	}
	
	
	
}
