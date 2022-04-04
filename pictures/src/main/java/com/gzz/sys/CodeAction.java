package com.gzz.sys;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gzz.utils.Dict;
import com.gzz.utils.Json;
import com.gzz.utils.Result;

@RestController
public class CodeAction {
	@Autowired
	private CodeService service;

	@PostMapping("/queryDicts")
	public Result<List<Dict>> queryDicts() {
		return Result.success(Json.json());
	}

	@PostMapping("/queryCount")
	public Result<Integer> queryCount(String url) {
		return Result.success(service.queryCount(url));
	}

	@PostMapping("/downPictures")
	public Result<?> downPictures(@RequestBody DownParam param) {
		new Thread(() -> service.downPictures(param)).start();
		return Result.success();
	}

}
