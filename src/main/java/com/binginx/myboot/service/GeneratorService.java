package com.binginx.myboot.service;


import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import com.binginx.myboot.mapper.GeneratorMapper;
import com.binginx.myboot.model.GenConfig;
import com.binginx.myboot.utils.GenCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedOutputStream;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class GeneratorService {
	@Autowired
	private GeneratorMapper generatorMapper;
	/**
	 * 生成代码
	 *
	 * @param genConfig 生成配置
	 * @return
	 */
	public void generatorCode(GenConfig genConfig) {
		BufferedOutputStream outputStream = null;
		//ZipOutputStream zip = new ZipOutputStream(outputStream);

		String tableNames = genConfig.getTableName();
		for (String tableName : StrUtil.split(tableNames, StrUtil.DASHED)) {
			//查询表信息
			Map<String, String> table = generatorMapper.queryTable(tableName);
			//查询列信息
			List<Map<String, String>> columns = generatorMapper.queryColumns(tableName);
			//生成代码
			GenCodeUtils.generatorCode(genConfig, table, columns, outputStream);
		}
		IoUtil.close(outputStream);
	}
}
