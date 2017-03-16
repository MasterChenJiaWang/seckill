/**
 * 
 */
package com.chen.seckill.enums;

/**
 *<p>����: SeckillStateEnum </p>
 *<p>������ ʹ��ö�ٱ������������ֵ�</p>
 *<p>company:</p>
 * @����  �¼���
 * @ʱ��  2017��3��15�� ����2:40:49
 *@�汾 
 */
public enum SeckillStateEnum {

	SUCCESS(1, "��ɱ�ɹ�"), 
	END(0, "��ɱ����"), 
	REPEAT_KILL(-1, "�ط���ɱ"), 
	INNER_ERROR(-2, "ϵͳ�쳣"), 
	DATA_REWRITE(-3, "���ݴ۸�");

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
