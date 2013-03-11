package com.shangde.common.util;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.shangde.common.action.CommonAction;
import com.shangde.edu.finance.condition.QueryContractCondition;
import com.shangde.edu.finance.domain.Contract;
import com.shangde.edu.finance.service.IContract;

/**
 * 查询所有来源于亿起发的订单数据
 * 
 * @author 刘世豪
 * 
 */
public class QueryAdvertOrder extends CommonAction{
	/**
	 * @param src
	 *            广告主为亿起发指定的标识
	 * @param cid
	 *            广告主在亿起发推广的标识
	 * @param orderTime
	 *            订单日期
	 */
	private String src ;
	private Integer cid ;
	private Date orderTime ;
	private String startDate;
	private String endDate;
	/** log对象 */
    private Log logger = LogFactory.getLog(getClass());
    
	private IContract contractService;
	private QueryContractCondition queryContractCondition ;
	/**
	 * 以流的方式输出订单列表，亿起发用
	 * @throws Exception
	 */
	public String queryOrder() throws Exception{
		if(null!=src||!"".equals(src)||null!=cid||null!=orderTime){
			this.getQueryContractCondition().setSrc(src) ;
			this.getQueryContractCondition().setCid(cid) ;
			SimpleDateFormat dateFm = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat dateFm2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dateTime = dateFm.format(orderTime);
			this.getQueryContractCondition().setCreateTime(dateTime) ;
			List<Contract> contList = contractService.queryOrderByList(queryContractCondition) ;
			HttpServletRequest request = ServletActionContext.getRequest() ;
			HttpServletResponse response = ServletActionContext.getResponse() ;
			if(null==contList||contList.size()==0){
				response.setContentType("text/html;charset=utf-8") ;
				response.getWriter().print("Did not meet the conditions of the data") ;
			}else{
				String fileName = src+"-highso"+dateTime+".txt" ;
				//response.reset();
				//response.setContentType("text/plain;charset=UTF-8");
				response.setHeader("Pragma","No-cache");   
		        response.setHeader("Cache-Control","no-cache");
		        response.setDateHeader("Expires", 0);
				response.setContentType("application/x-download;charset=UTF-8");
				//response.setHeader("Content-Disposition",fileName);
				response.setHeader("Content-Disposition", "attachment; filename="+fileName);
				for(Contract ct : contList){
					response.getOutputStream().write((ct.getWi()+"||").getBytes());
					response.getOutputStream().write((dateFm2.format(ct.getCreateTime())+"||").getBytes());
					response.getOutputStream().write((ct.getContractId()+"||").getBytes());
					response.getOutputStream().write((ct.getContractCutSumMoney().toString()+"\n").getBytes());
				}
				response.getOutputStream().flush();
				response.getOutputStream().close(); 
			}
		}
		return null ;
	}
	/**
	 * 订单列表显示在jsp中，亿起发用
	 * @return
	 * @throws IOException 
	 */
	public String queryOrderPage() throws IOException{
		if(null!=src||!"".equals(src)||null!=cid||null!=orderTime){
			this.getQueryContractCondition().setSrc(src) ;
			this.getQueryContractCondition().setCid(cid) ;
			SimpleDateFormat dateFm = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat dateFm2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dateTime = dateFm.format(orderTime);
			this.getQueryContractCondition().setCreateTime(dateTime) ;
			List<Contract> contList = contractService.queryOrderByList(queryContractCondition) ;
			//ActionContext.getContext().put("orderList", contList) ;
			HttpServletResponse response = ServletActionContext.getResponse() ;
			response.setCharacterEncoding("utf-8") ;
			response.setContentType("text/plain;charset=utf-8");
			if(null!=contList&&contList.size()!=0){
				for(Contract ct : contList){
					response.getWriter().write(ct.getWi()+"||"+dateFm2.format(ct.getCreateTime())+"||"+ct.getContractId()+"||"+ct.getContractSumMoney()+"\n") ;
				}
			}
		}
		return null ;
	}
	/**
     * 以流的方式输出订单列表,中视网盟用
     * @throws Exception
     */
    public String queryzswmOrder() {
        String splitstr1="\t";//各个字段之间的间隔符号
        String splitstr2="\n";//换行
        HttpServletResponse response = ServletActionContext.getResponse() ;
        response.setCharacterEncoding("utf-8") ;
        response.setContentType("text/plain;charset=utf-8");
       try{
        if(null!=startDate&& endDate !=null){
            this.getQueryContractCondition().setWebFrom("zswm") ;
            SimpleDateFormat dateFm = new SimpleDateFormat("yyyyMMdd");
            SimpleDateFormat dateFm2 = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat dateFm3 = new SimpleDateFormat("HHmmss");
            this.getQueryContractCondition().setStartTime(dateFm2.format(dateFm.parse(startDate))+" 00:00:00") ;
            this.getQueryContractCondition().setEndTime(dateFm2.format(dateFm.parse(endDate))+" 23:59:59");
            
            List<Contract> contList = contractService.getcontractListForCPS(queryContractCondition) ;
            if(null==contList||contList.size()==0){
                response.getWriter().print("") ;
            }else{
                for(Contract ct : contList){
                  //hhmiss  实绩产生的时间 (例: 21点9分8秒时 210908, 零点10分8秒时 001008来表示)
                    response.getWriter().write((dateFm3.format(ct.getCreateTime())+splitstr1));
                    //a_id    记录的 ZSWMINFO值，下订单时将该值存到数据库中
                    if(ct.getSrc()==null){
                        response.getWriter().write((""+splitstr1));
                    }else{
                        response.getWriter().write((ct.getSrc()+splitstr1));
                    }
                    //m_id    广告主ID (中视网盟分配) 
                    response.getWriter().write(("highso"+splitstr1));
                    //mbr_id  下订单的用户 ID，没有可填固定值 
                    response.getWriter().write((ct.getCusId()+splitstr1));
                    //o_cd    区别购买业绩的 唯一值 (订单号)
                    response.getWriter().write((ct.getContractId()+splitstr1));
                    //p_cd    区别订单商品的值（商品编号），没有可填固定值 
                    response.getWriter().write(("highsocourse"+splitstr1));
                    //price   一个商品的价格 (商品单价)
                    response.getWriter().write((ct.getContractCutSumMoney()+splitstr1));
                    //it_cnt  购买的商品数量 (购买了几个同样物品)
                    response.getWriter().write(("1"+splitstr1));
                    //c_cd    购买的商品分类 (按分类返现比率不同时必须填写用以确定支付标准) ，没有可填固定值
                    response.getWriter().write(("0"+splitstr1));
                    //o_stat  结算状态 100 : 未结算（用户刚下的订单） 200 : 核对完（用户付款且没有退货，交易成功） 300 : 取消（用户，未付款或退货，交易失败）
                    if(ct.getStatus()==0){
                        response.getWriter().write(("100"+splitstr2));
                    }else if(ct.getStatus()==1){
                        response.getWriter().write(("200"+splitstr2));
                    }else{
                        response.getWriter().write(("300"+splitstr2));
                    }
                    
                }
               
            }
        }
       }catch (Exception e){
           logger.error("返回ZSWM订单数据错误",e);
       }
        return null ;
    }
    
	public IContract getContractService() {
		return contractService;
	}
	
	public void setContractService(IContract contractService) {
		this.contractService = contractService;
	}

	public QueryContractCondition getQueryContractCondition() {
		if (queryContractCondition == null) {
			queryContractCondition = new QueryContractCondition();
		}

		return queryContractCondition;
	}

	public void setQueryContractCondition(
			QueryContractCondition queryContractCondition) {
		this.queryContractCondition = queryContractCondition;
	}

	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}
    public String getStartDate() {
        return startDate;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    public String getEndDate() {
        return endDate;
    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    
    public static void main(String[] args) {
        
        SimpleDateFormat dateFm = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat dateFm2 = new SimpleDateFormat("yyyy-MM-dd");
        
        String dateTime=null;
        try {
             dateTime = dateFm2.format(dateFm.parse("20110601"));
           System.out.println(dateTime+" 00:00:00") ;
        } catch (Exception e) {
            e.printStackTrace();
        }
       
    }
    
}
