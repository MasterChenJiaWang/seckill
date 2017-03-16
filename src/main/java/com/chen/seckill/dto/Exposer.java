/**
 * 
 */
package com.chen.seckill.dto;

/**
 *<p>����: Exposer </p>
 *<p>��������¶��ɱ�ӿ�DTO </p>
 *<p>company:</p>
 * @����  �¼���
 * @ʱ��  2017��3��15�� ����1:58:23
 *@�汾 
 */
public class Exposer {

	private boolean exposed;//�Ƿ�����ɱ
	private String md5;// һ�ּ��ܴ�ʩ
	private long seckillId;
	//ϵͳ��ǰʱ��
	private long now;
	private long start;
	private long end;
	
	/**
	 * �Ƿ���md5
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
	 * �Ƿ���id
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
