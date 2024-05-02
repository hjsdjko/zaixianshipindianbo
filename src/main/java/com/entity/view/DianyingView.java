package com.entity.view;

import com.entity.DianyingEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 视频
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 */
@TableName("dianying")
public class DianyingView extends DianyingEntity implements Serializable {
    private static final long serialVersionUID = 1L;
		/**
		* 视频类型名称的值
		*/
		private String leixValue;



	public DianyingView() {

	}

	public DianyingView(DianyingEntity dianyingEntity) {
		try {
			BeanUtils.copyProperties(this, dianyingEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



			/**
			* 获取： 视频类型名称的值
			*/
			public String getLeixValue() {
				return leixValue;
			}
			/**
			* 设置： 视频类型名称的值
			*/
			public void setLeixValue(String leixValue) {
				this.leixValue = leixValue;
			}





}
