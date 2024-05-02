package com.entity.view;

import com.entity.DianyingCollectionEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 视频收藏
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 */
@TableName("dianying_collection")
public class DianyingCollectionView extends DianyingCollectionEntity implements Serializable {
    private static final long serialVersionUID = 1L;



		//级联表 dianying
			/**
			* 视频名称
			*/
			private String yonghuName;
			/**
			* 视频名称
			*/
			private String dianyingName;
			/**
			* 视频主演
			*/
			private String dianyingZhuyan;
			/**
			* 视频海报
			*/
			private String dianyingPhoto;
			/**
			* 视频
			*/
			private String dianyingVideo;
			/**
			* 票数
			*/
			private Integer voting;
			/**
			* 视频类型名称
			*/
			private Integer leixTypes;
				/**
				* 视频类型名称的值
				*/
				private String leixValue;
			/**
			* 项目具体内容
			*/
			private String dianyingContent;

	public DianyingCollectionView() {

	}

	public DianyingCollectionView(DianyingCollectionEntity dianyingCollectionEntity) {
		try {
			BeanUtils.copyProperties(this, dianyingCollectionEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getYonghuName() {
		return yonghuName;
	}

	public void setYonghuName(String yonghuName) {
		this.yonghuName = yonghuName;
	}


//级联表的get和set dianying
					/**
					* 获取： 视频名称
					*/
					public String getDianyingName() {
						return dianyingName;
					}
					/**
					* 设置： 视频名称
					*/
					public void setDianyingName(String dianyingName) {
						this.dianyingName = dianyingName;
					}
					/**
					* 获取： 视频主演
					*/
					public String getDianyingZhuyan() {
						return dianyingZhuyan;
					}
					/**
					* 设置： 视频主演
					*/
					public void setDianyingZhuyan(String dianyingZhuyan) {
						this.dianyingZhuyan = dianyingZhuyan;
					}
					/**
					* 获取： 视频海报
					*/
					public String getDianyingPhoto() {
						return dianyingPhoto;
					}
					/**
					* 设置： 视频海报
					*/
					public void setDianyingPhoto(String dianyingPhoto) {
						this.dianyingPhoto = dianyingPhoto;
					}
					/**
					* 获取： 视频
					*/
					public String getDianyingVideo() {
						return dianyingVideo;
					}
					/**
					* 设置： 视频
					*/
					public void setDianyingVideo(String dianyingVideo) {
						this.dianyingVideo = dianyingVideo;
					}
					/**
					* 获取： 票数
					*/
					public Integer getVoting() {
						return voting;
					}
					/**
					* 设置： 票数
					*/
					public void setVoting(Integer voting) {
						this.voting = voting;
					}
					/**
					* 获取： 视频类型名称
					*/
					public Integer getLeixTypes() {
						return leixTypes;
					}
					/**
					* 设置： 视频类型名称
					*/
					public void setLeixTypes(Integer leixTypes) {
						this.leixTypes = leixTypes;
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
					/**
					* 获取： 项目具体内容
					*/
					public String getDianyingContent() {
						return dianyingContent;
					}
					/**
					* 设置： 项目具体内容
					*/
					public void setDianyingContent(String dianyingContent) {
						this.dianyingContent = dianyingContent;
					}







}
