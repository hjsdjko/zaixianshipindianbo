package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * 视频
 *
 * @author 
 * @email
 */
@TableName("dianying")
public class DianyingEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public DianyingEntity() {

	}

	public DianyingEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @TableField(value = "id")

    private Integer id;


    /**
     * 视频名称
     */
    @TableField(value = "dianying_name")

    private String dianyingName;


    /**
     * 视频主演
     */
    @TableField(value = "dianying_zhuyan")

    private String dianyingZhuyan;


    /**
     * 视频海报
     */
    @TableField(value = "dianying_photo")

    private String dianyingPhoto;


    /**
     * 视频
     */
    @TableField(value = "dianying_video")

    private String dianyingVideo;


    /**
     * 票数
     */
    @TableField(value = "voting")

    private Integer voting;


    /**
     * 视频类型名称
     */
    @TableField(value = "leix_types")

    private Integer leixTypes;


    /**
     * 项目具体内容
     */
    @TableField(value = "dianying_content")

    private String dianyingContent;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "create_time",fill = FieldFill.INSERT)

    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：视频名称
	 */
    public String getDianyingName() {
        return dianyingName;
    }


    /**
	 * 获取：视频名称
	 */

    public void setDianyingName(String dianyingName) {
        this.dianyingName = dianyingName;
    }
    /**
	 * 设置：视频主演
	 */
    public String getDianyingZhuyan() {
        return dianyingZhuyan;
    }


    /**
	 * 获取：视频主演
	 */

    public void setDianyingZhuyan(String dianyingZhuyan) {
        this.dianyingZhuyan = dianyingZhuyan;
    }
    /**
	 * 设置：视频海报
	 */
    public String getDianyingPhoto() {
        return dianyingPhoto;
    }


    /**
	 * 获取：视频海报
	 */

    public void setDianyingPhoto(String dianyingPhoto) {
        this.dianyingPhoto = dianyingPhoto;
    }
    /**
	 * 设置：视频
	 */
    public String getDianyingVideo() {
        return dianyingVideo;
    }


    /**
	 * 获取：视频
	 */

    public void setDianyingVideo(String dianyingVideo) {
        this.dianyingVideo = dianyingVideo;
    }
    /**
	 * 设置：票数
	 */
    public Integer getVoting() {
        return voting;
    }


    /**
	 * 获取：票数
	 */

    public void setVoting(Integer voting) {
        this.voting = voting;
    }
    /**
	 * 设置：视频类型名称
	 */
    public Integer getLeixTypes() {
        return leixTypes;
    }


    /**
	 * 获取：视频类型名称
	 */

    public void setLeixTypes(Integer leixTypes) {
        this.leixTypes = leixTypes;
    }
    /**
	 * 设置：项目具体内容
	 */
    public String getDianyingContent() {
        return dianyingContent;
    }


    /**
	 * 获取：项目具体内容
	 */

    public void setDianyingContent(String dianyingContent) {
        this.dianyingContent = dianyingContent;
    }
    /**
	 * 设置：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Dianying{" +
            "id=" + id +
            ", dianyingName=" + dianyingName +
            ", dianyingZhuyan=" + dianyingZhuyan +
            ", dianyingPhoto=" + dianyingPhoto +
            ", dianyingVideo=" + dianyingVideo +
            ", voting=" + voting +
            ", leixTypes=" + leixTypes +
            ", dianyingContent=" + dianyingContent +
            ", createTime=" + createTime +
        "}";
    }
}
