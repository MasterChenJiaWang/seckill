/**
 * 
 */
package com.chen.seckill.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chen.seckill.dto.Exposer;
import com.chen.seckill.dto.SeckillExecution;
import com.chen.seckill.dto.SeckillResult;
import com.chen.seckill.enums.SeckillStateEnum;
import com.chen.seckill.exception.RepeatKillException;
import com.chen.seckill.exception.SeckillCloseException;
import com.chen.seckill.pojo.Seckill;
import com.chen.seckill.service.ISeckillService;


/**
 *<p>����: SeckillController </p>
 *<p>������ </p>
 *<p>company:</p>
 * @����  �¼���
 * @ʱ��  2017��3��15�� ����4:24:36
 *@�汾 
 */
@Controller
@RequestMapping("/seckill")
public class SeckillController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	 @Autowired
	    private ISeckillService seckillService;

	    @RequestMapping(value = "/list",method = RequestMethod.GET)
	    public String list(Model model)
	    {
	        //list.jsp+mode=ModelAndView
	        //获取列表页
	        List<Seckill> list=seckillService.getSeckillList();
	        model.addAttribute("list",list);
	        return "list";
	    }

	    @RequestMapping(value = "/{seckillId}/detail",method = RequestMethod.GET)
	    public String detail(@PathVariable("seckillId") Long seckillId, Model model)
	    {
	        if (seckillId == null)
	        {
	            return "redirect:/seckill/list";
	        }

	        Seckill seckill=seckillService.getById(seckillId);
	        if (seckill==null)
	        {
	            return "forward:/seckill/list";
	        }

	        model.addAttribute("seckill",seckill);

	        return "detail";
	    }

	    //ajax ,json暴露秒杀接口的方法
	    @RequestMapping(value = "/{seckillId}/exposer",
	                    method = RequestMethod.POST,
	                    produces = {"application/json;charset=UTF-8"})
	    @ResponseBody
	    public SeckillResult<Exposer> exposer(@PathVariable("seckillId")Long seckillId)
	    {
	        SeckillResult<Exposer> result;
	        try{
	            Exposer exposer=seckillService.exportSeckillUrl(seckillId);
	            result=new SeckillResult<Exposer>(true,exposer);
	        }catch (Exception e)
	        {
	            e.printStackTrace();
	            result=new SeckillResult<Exposer>(false,e.getMessage());
	        }

	        return result;
	    }

	    @RequestMapping(value = "/{seckillId}/{md5}/execution",
	            method = RequestMethod.POST,
	            produces = {"application/json;charset=UTF-8"})
	    @ResponseBody
	    public SeckillResult<SeckillExecution> execute(@PathVariable("seckillId") Long seckillId,
	                                                   @PathVariable("md5") String md5,
	                                                   @CookieValue(value = "killPhone",required = false) Long phone)
	    {
	        if (phone==null)
	        {
	            return new SeckillResult<SeckillExecution>(false,"未注册");
	        }
	        SeckillResult<SeckillExecution> result;

	        try {
	            SeckillExecution execution = seckillService.executeSeckill(seckillId, phone, md5);
	            return new SeckillResult<SeckillExecution>(true, execution);
	        }catch (RepeatKillException e1)
	        {
	            SeckillExecution execution=new SeckillExecution(seckillId, SeckillStateEnum.REPEAT_KILL);
	            return new SeckillResult<SeckillExecution>(true,execution);
	        }catch (SeckillCloseException e2)
	        {
	            SeckillExecution execution=new SeckillExecution(seckillId, SeckillStateEnum.END);
	            return new SeckillResult<SeckillExecution>(true,execution);
	        }
	        catch (Exception e)
	        {
	            SeckillExecution execution=new SeckillExecution(seckillId, SeckillStateEnum.INNER_ERROR);
	            return new SeckillResult<SeckillExecution>(true,execution);
	        }

	    }

	    //获取系统时间
	    @RequestMapping(value = "/time/now",method = RequestMethod.GET)
	    @ResponseBody
	    public SeckillResult<Long> time()
	    {
	        Date now=new Date();
	        return new SeckillResult<Long>(true,now.getTime());
	    }
	}
