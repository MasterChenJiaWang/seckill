/**
 * 
 */
package com.chen.seckill.enums;

/**
 *<p>标题: SeckillStateEnum </p>
 *<p>描述： 使用枚举表述常量数据字典</p>
 *<p>company:</p>
 * @作者  陈加望
 * @时间  2017年3月15日 下午2:40:49
 *@版本 
 */
public enum SeckillStateEnum {

	SUCCESS(1, "秒杀成功"), 
	END(0, "秒杀结束"), 
	REPEAT_KILL(-1, "重发秒杀"), 
	INNER_ERROR(-2, "系统异常"), 
	DATA_REWRITE(-3, "数据篡改");

	private int state;

	private String stateInfo;

	private SeckillStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	public int getState() {
		return state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public static SeckillStateEnum stateOf(int index) {
		for (SeckillStateEnum state : values()) {
			if (state.getState() == index) {
				return state;
			}
		}
		return null;
	}
}
