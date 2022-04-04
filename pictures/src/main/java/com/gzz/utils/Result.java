package com.gzz.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @类说明 【反回结果】包装
 * @author 高振中
 * @date 2021-02-01 20:20:20
 **/
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {

	static final int OK = 200;
	static final int ERROR = 500;

	static final String SUCCESS = "SUCCESS";
	static final String FAILURE = "FAILURE";

	private Integer code;
	private String msg;
	private T data;

	public Result(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	/**
	 * @说明 【状态码500-默认失败消息】
	 **/
	public static <T> Result<T> error() {
		return new Result<>(ERROR, FAILURE);
	}

	/**
	 * @说明 【状态码500-自定义失败消息】
	 **/
	public static <T> Result<T> error(String msg) {
		return new Result<>(ERROR, msg);
	}

	/**
	 * @说明 【自定义编码-自定义失败消息】
	 **/
	public static <T> Result<T> error(Integer code, String msg) {
		return new Result<>(code, msg);
	}

	/**
	 * @说明 【自定义编码-自定义失败消息-有数据】
	 **/
	public static <T> Result<T> error(Integer code, String msg, T data) {
		return new Result<>(code, msg, data);
	}

	/**
	 * @说明 【成功-默认消息-没数据】
	 **/
	public static <T> Result<T> success() {
		return new Result<>(OK, SUCCESS);
	}

	/**
	 * @说明 【成功-默认消息-带数据】
	 **/
	public static <T> Result<T> success(T data) {
		return new Result<>(OK, SUCCESS, data);
	}

	/**
	 * @说明 【成功-自定议消息-带数据】
	 **/
	public static <T> Result<T> success(T data, String msg) {
		return new Result<>(OK, msg, data);
	}

}
