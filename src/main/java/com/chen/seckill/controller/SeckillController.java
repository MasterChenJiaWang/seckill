/**
 * 
 */
package com.chen.seckill.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
 *<p>标题: SeckillController </p>
 *<p>描述： </p>
 *<p>company:</p>
 * @作者  陈加望
 * @时间  2017年3月15日 下午4:24:36
 *@版本 
 */
@Controller
@RequestMapping("/seckill")
public class SeckillController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ISeckillService seckillService;
	
	@RequestMapping(name="/list",method=RequestMethod.GET)
	//@ResponseBody
	public String list(Model model){
		List<Seckill> list = seckillService.getSeckillList();
		model.addAttribute("list", list);
		return "list";
	}
	
	@RequestMapping(value="/{seckillId}/detail",method=RequestMethod.GET)
	//@ResponseBody
	public String detail(@PathVariable("seckillId") Long seckillId,Model model){
		if(seckillId==null){
			return "redirect:/seckill/list";
		}
		Seckill seckill= seckillService.getById(seckillId);
		if(seckill==null){
			return "forward:/seckill/list";
		}
		model.addAttribute("seckill", seckill);
		return "detail";
	}
	
	
	@RequestMapping(name="/{seckillId}/exposer",
			method=RequestMethod.POST,
			produces={"application/json; charset=utf-8"})
	@ResponseBody
	public  SeckillResult<Exposer> exposer(Long seckillId){
		SeckillResult<Exposer> seckillResult;
		
		try {
			Exposer  exposer = seckillService.exportSeckillUrl(seckillId);
			 seckillResult = new SeckillResult<>(true, exposer);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			seckillResult=new SeckillResult<>(false, e.getMessage());
		}
		return seckillResult;
	}
	
	
	@RequestMapping(value="/{seckillId}/{md5}/execution",
			method=RequestMethod.POST,
			produces={"application/json; charset=utf-8"})
	@ResponseBody
	public SeckillResult<SeckillExecution>  execute(@PathVariable("seckillId")Long seckillId,
			@PathVariable("md5")String md5,@CookieValue(value="killPhone",required=false)Long phone){
		
		SeckillResult<SeckillExecution> seckillResult;
		if(phone==null){
			return new SeckillResult<SeckillExecution>(false,"未注册");
		}
		try {
			SeckillExecution seckillExecution = seckillService.executeSeckill(seckillId, phone, md5);
			
			return new SeckillResult<SeckillExecution>(true, seckillExecution);
		} catch (RepeatKillException e) {
			SeckillExecution execution = new SeckillExecution(seckillId, SeckillStateEnum.REPEAT_KILL);
			return new SeckillResult<SeckillExecution>(false, execution);
		} catch (SeckillCloseException e) {
			SeckillExecution execution = new SeckillExecution(seckillId, SeckillStateEnum.END);
			return new SeckillResult<SeckillExecution>(false, execution);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			SeckillExecution execution = new SeckillExecution(seckillId, SeckillStateEnum.INNER_ERROR);
			return new SeckillResult<SeckillExecution>(false, execution);
		}
		
	}
	
	
	
	@RequestMapping(value="/time/now",method=RequestMethod.GET)
	public SeckillResult<Long> time(){
		
		Date nowTime = new Date();
		return new SeckillResult<Long>(true, nowTime.getTime());
	}
	
	
	
}
