/**
 * 
 */
package com.chen.seckill.dto;

/**
 *<p>标题: SeckillResult </p>
 *<p>描述：封装json结果 </p>
 *<p>company:</p>
 * @作者  陈加望
 * @时间  2017年3月15日 下午2:09:28
 *@版本 
 */
public class SeckillResult<T> {

	private boolean success;

	private T data;

	private String error;

	public SeckillResult(boolean success, String error) {
		this.success = success;
		this.error = error;
	}

	public SeckillResult(boolean success, T data) {
		this.success = success;
		this.data = data;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	@Override
	public String toString() {
		return "SeckillResult [success=" + success + ", data=" + data + ", error=" + error + "]";
	}
}
