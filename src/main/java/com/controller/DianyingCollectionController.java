package com.controller;


import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.StringUtil;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;

import com.entity.DianyingCollectionEntity;

import com.service.DianyingCollectionService;
import com.entity.view.DianyingCollectionView;
import com.service.DianyingService;
import com.entity.DianyingEntity;
import com.utils.PageUtils;
import com.utils.R;

/**
 * 电影收藏
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/dianyingCollection")
public class DianyingCollectionController {
    private static final Logger logger = LoggerFactory.getLogger(DianyingCollectionController.class);

    @Autowired
    private DianyingCollectionService dianyingCollectionService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;



    //级联表service
    @Autowired
    private DianyingService dianyingService;


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(StringUtils.isNotBlank(role) && role.equals("用户")){
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        }
        if(StringUtil.isEmpty(role)){
            return R.error(511,"权限为空");
        }
        params.put("orderBy","id");
        PageUtils page = dianyingCollectionService.queryPage(params);

        //字典表数据转换
        List<DianyingCollectionView> list =(List<DianyingCollectionView>)page.getList();
        for(DianyingCollectionView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        DianyingCollectionEntity dianyingCollection = dianyingCollectionService.selectById(id);
        if(dianyingCollection !=null){
            //entity转view
            DianyingCollectionView view = new DianyingCollectionView();
            BeanUtils.copyProperties( dianyingCollection , view );//把实体数据重构到view中

            //级联表
            DianyingEntity dianying = dianyingService.selectById(dianyingCollection.getDianyingId());
            if(dianying != null){
                BeanUtils.copyProperties( dianying , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                view.setDianyingId(dianying.getId());
            }
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody DianyingCollectionEntity dianyingCollection, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,dianyingCollection:{}",this.getClass().getName(),dianyingCollection.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(StringUtil.isEmpty(role)){
            return R.error(511,"权限为空");
        }
        Wrapper<DianyingCollectionEntity> queryWrapper = new EntityWrapper<DianyingCollectionEntity>()
            .eq("dianying_id", dianyingCollection.getDianyingId())
            .eq("yonghu_id", dianyingCollection.getYonghuId())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        DianyingCollectionEntity dianyingCollectionEntity = dianyingCollectionService.selectOne(queryWrapper);
        if(dianyingCollectionEntity==null){
            dianyingCollection.setInsertTime(new Date());
            dianyingCollection.setCreateTime(new Date());
            dianyingCollectionService.insert(dianyingCollection);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody DianyingCollectionEntity dianyingCollection, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,dianyingCollection:{}",this.getClass().getName(),dianyingCollection.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(StringUtil.isEmpty(role)){
            return R.error(511,"权限为空");
        }
        //根据字段查询是否有相同数据
        Wrapper<DianyingCollectionEntity> queryWrapper = new EntityWrapper<DianyingCollectionEntity>()
            .notIn("id",dianyingCollection.getId())
            .andNew()
            .eq("dianying_id", dianyingCollection.getDianyingId())
            .eq("yonghu_id", dianyingCollection.getYonghuId())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        DianyingCollectionEntity dianyingCollectionEntity = dianyingCollectionService.selectOne(queryWrapper);
        if(dianyingCollectionEntity==null){
            //  String role = String.valueOf(request.getSession().getAttribute("role"));
            //  if("".equals(role)){
            //      dianyingCollection.set
            //  }
            dianyingCollectionService.updateById(dianyingCollection);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        dianyingCollectionService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }



    /**
    * 前端列表
    */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(StringUtils.isNotBlank(role) && role.equals("用户")){
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        }
        if(StringUtil.isEmpty(role)){
            return R.error(511,"权限为空");
        }
        // 没有指定排序字段就默认id倒序
        if(StringUtil.isEmpty(String.valueOf(params.get("orderBy")))){
            params.put("orderBy","id");
        }
        PageUtils page = dianyingCollectionService.queryPage(params);

        //字典表数据转换
        List<DianyingCollectionView> list =(List<DianyingCollectionView>)page.getList();
        for(DianyingCollectionView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c);
        }
        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        DianyingCollectionEntity dianyingCollection = dianyingCollectionService.selectById(id);
            if(dianyingCollection !=null){
                //entity转view
                DianyingCollectionView view = new DianyingCollectionView();
                BeanUtils.copyProperties( dianyingCollection , view );//把实体数据重构到view中

                //级联表
                    DianyingEntity dianying = dianyingService.selectById(dianyingCollection.getDianyingId());
                if(dianying != null){
                    BeanUtils.copyProperties( dianying , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setDianyingId(dianying.getId());
                }
                //修改对应字典表字段
                dictionaryService.dictionaryConvert(view);
                return R.ok().put("data", view);
            }else {
                return R.error(511,"查不到数据");
            }
    }


    /**
    * 前端保存
    */
    @RequestMapping("/add")
    public R add(@RequestBody DianyingCollectionEntity dianyingCollection, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,dianyingCollection:{}",this.getClass().getName(),dianyingCollection.toString());
        Wrapper<DianyingCollectionEntity> queryWrapper = new EntityWrapper<DianyingCollectionEntity>()
            .eq("dianying_id", dianyingCollection.getDianyingId())
            .eq("yonghu_id", dianyingCollection.getYonghuId())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        DianyingCollectionEntity dianyingCollectionEntity = dianyingCollectionService.selectOne(queryWrapper);
        if(dianyingCollectionEntity==null){
            dianyingCollection.setInsertTime(new Date());
            dianyingCollection.setCreateTime(new Date());
        //  String role = String.valueOf(request.getSession().getAttribute("role"));
        //  if("".equals(role)){
        //      dianyingCollection.set
        //  }
        dianyingCollectionService.insert(dianyingCollection);
            return R.ok();
        }else {
            return R.error(511,"您已经收藏过了");
        }
    }





}

