package com.alex.common.utils;


import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.io.UnsupportedEncodingException;
import java.lang.StringBuffer;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * 分页实体类
 * @author ice.xrt 2011-03-16
 *
 */
public class Pages { 
	private int pageNo;//当前页数 
	private int pageSize;//每页条数
	private int allCount;//总条数
	private int allPage;//总页数   
	private int recordStart;//每页开始  
	private int recordEnd;//每页结束  
	private boolean hasPrePage;//是否有上一页  
	private boolean hasNextPage;//是否有下一页   
	private int showCount;//显示页数   
	private String url;    
	private List<Object> pageList; 
	//private HashMap<String,String> searchTerms;
	private HashMap<Object,Object> searchTerms;
	
	
	
	
	 
	/**
     * 创建pages对象 
     * @author ice.xrt 2010-09-02
     */
	public Pages() {  
		pageNo = 1;  
		pageSize = 10;  
		allPage = 1;  
		recordStart = 0;  
		recordEnd = 0;  
		hasPrePage = false;  
		hasNextPage = false;  
		try {  
			excecute();  
		} catch (Exception ex) {  
			ex.printStackTrace();
		}  
	}  
	    	   
	/**
	* 创建pages对象
	* @author ice.xrt 2010-09-02
	* @param pageNo 当前页数
	* @param allCount 总数量
	* 默认每页25条
	*/
	public Pages(int pageNo, int allCount) {  
		this.pageNo = pageNo;  
		this.pageSize = 25;  
	    this.allCount = allCount;  
	    this.allPage = 1;  
	    this.recordStart = 0;  
	    this.recordEnd = 0;  
	    hasPrePage = false;  
	    hasNextPage = false;  
	    try {  
	    	excecute();  
	    } catch (Exception ex) {  
	    	ex.printStackTrace();
	    }  
	}  
	    	   
	 /**
     * 创建pages对象
     * @author ice.xrt 2010-09-02
     * @param pageNo 当前页数
     * @param pageSize 每页条数
     * @param allCount 总条数
     */
  public Pages(int pageNo, int pageSize, int allCount) {  
         this.pageNo = pageNo;  
         this.pageSize = pageSize;  
         this.allCount = allCount;  
         this.allPage = 1;  
         this.recordStart = 0;  
         this.recordEnd = 0;  
         hasPrePage = false;  
         hasNextPage = false;  
         try {  
             excecute();  
         } catch (Exception ex) {
        	 ex.printStackTrace();
         }  
     }  
    
  public void excecute(int pageNo, int pageSize, int allCount) {  
      this.pageNo = pageNo;  
      this.pageSize = pageSize;  
      this.allCount = allCount;  
      this.allPage = 1;  
      this.recordStart = 0;  
      this.recordEnd = 0;  
      hasPrePage = false;  
      hasNextPage = false;  
      try {  
          excecute();  
      } catch (Exception ex) {
     	 ex.printStackTrace();
      }  
  }  
 
     /**
      * 计算分页结果
      *@author ice.xrt 2010-09-02
      *
      */
     public void excecute() {  
         if (pageNo <= 0) {  
             pageNo = 1;  
         }  
         recordStart = (pageNo - 1) * pageSize ;  
         recordEnd = Math.min(recordStart + pageSize, allCount);  
         if (allCount % pageSize == 0) {  
             allPage = allCount / pageSize;  
         } else {  
             allPage = allCount / pageSize + 1;  
         }  
         if (pageNo > 1) {  
             hasPrePage = true;  
         }  
         if (pageNo < allPage) {  
             hasNextPage = true;  
         }  
         if (showCount <= 0) {  
             showCount = 9;  
         }  
     }
	    	     
     /**
      * 打印分页条hitml
      * @author ice.xrt 2010-09-02
      * @return String
      */
     public String getHtml(){
    	 StringBuffer sb=new StringBuffer();
    	 StringBuffer tUrl=new StringBuffer();
    	 if(searchTerms!=null){
//	    	 for(Map.Entry<String, String> termss:searchTerms.entrySet()){
//	    		 tUrl.append("&"+termss.getKey()+"="+termss.getValue());
//	    	 }
//    		 for(Map.Entry<Object, Object> termss:searchTerms.entrySet()){
//	    		 tUrl.append("&"+termss.getKey()+"="+termss.getValue());
//	    	 }
    		 for(Map.Entry<Object, Object> termss:searchTerms.entrySet()){
    			 System.err.println("termss.getValue()=="+termss.getValue());
    			 tUrl.append("&"+termss.getKey()+"="+BrowserContext.encodeURls(termss.getValue().toString()));
    			 
	    	 }
    	 }
    	 sb.append("<div align=\"center\">");
    	 sb.append(" <a href=\""+this.url+"?pages.pageNo=1"+tUrl+"\">首页</a>  ");
    	 if(this.isHasPrePage()){
    		int prePage=this.getPageNo()-1;
    		sb.append(" <a href=\""+this.url+"?pages.pageNo="+prePage+tUrl+"\">上一页</a> ");
    	 }else{
    		 sb.append(" 上一页  ");
    	 }
    	 if(this.isHasNextPage()){
    		 int nextPage = this.getPageNo()+1;
    		 sb.append("  <a href=\""+this.url+"?pages.pageNo="+nextPage+tUrl+"\">下一页</a>  ");
    	 }else{
    		 sb.append("  下一页  ");
    	 }
    	 sb.append("  <a href=\" "+this.url+"?pages.pageNo="+this.getAllPage()+tUrl+" \">尾页</a>  ");
    	 sb.append(" 总共"+this.getAllCount()+"条  ");
    	 sb.append(" 共"+this.getAllPage()+"页");
    	 sb.append(" 当前第"+this.getPageNo()+"页");
    	 sb.append("</div>");
    	 return sb.toString();
     }
     
    public static void main(String[] args) {
//    	StringBuilder sb=new StringBuilder();
//    	sb.append("撒旦法");
//    	System.err.println(sb);
    	System.err.println(new Pages().getHtml());
	}
	    	     
	/**
     * 获取总条数
     * @return int
     */
	public int getAllCount() {
		return allCount;
	}
	
	/**
	 * 设置总条数
	 * @param allCount
	 */
	public void setAllCount(int allCount) {
		this.allCount = allCount;
	}

	/**
	 * 获取总页数
	 * @return int
	 */
	public int getAllPage() {
		return allPage;
	}
	
	/**
	 * 设置总页数
	 * @param allPage
	 */
	public void setAllPage(int allPage) {
		this.allPage = allPage;
	}
	
	/**
	 * 是否有下一页
	 * @return boolean
	 */
	public boolean isHasNextPage() {
		return hasNextPage;
	}
	
	/**
	 * 设置是否有下一页
	 * @param hasNextPage
	 */
	public void setHasNextPage(boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}
	
	/**
	 * 是否有上一页
	 * @return
	 */
	public boolean isHasPrePage() {
		return hasPrePage;
	}
	
	/**
	 * 设置是否有上一页
	 * @return
	 */
	public void setHasPrePage(boolean hasPrePage) {
		this.hasPrePage = hasPrePage;
	}
	
	/**
	 * 获取分页结果
	 * @return
	 */
	public List<Object> getPageList() {
		return pageList;
	}
	
	/**
	 * 设置分页结果
	 * @param pageList
	 */
	public void setPageList(List<Object> pageList) {
		this.pageList = pageList;
	}
	
	/**
	 * 获取当前页
	 * @return
	 */
	public int getPageNo() {
		if(pageNo==0){
			pageNo=1;
		}
		return pageNo;
	}
	
	/**
	 * 设置当前页
	 * @param pageNo
	 */
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	
	/**
	 * 每页数据条数
	 * @return
	 */
	public int getPageSize() {
		return pageSize;
	}
	
	/**
	 * 设置每页数据条数
	 * @param pageSize
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	/**
	 * 每页结束
	 * @return
	 */
	public int getRecordEnd() {
		return recordEnd;
	}
	
	/**
	 * 设置每页结束
	 * @param recordEnd
	 */
	public void setRecordEnd(int recordEnd) {
		this.recordEnd = recordEnd;
	}
	
	/**
	 * 获取每页开始
	 * @return int
	 */
	public int getRecordStart() {
		return recordStart;
	}
	
	/**
	 * 设置每页开始
	 * @param recordStart
	 */
	public void setRecordStart(int recordStart) {
		this.recordStart = recordStart;
	}
	
	/**
	 * 显示条数
	 * @return int
	 */
	public int getShowCount() {
		return showCount;
	}
	
	/**
	 * 设置显示条数
	 * @param showCount
	 */
	public void setShowCount(int showCount) {
		this.showCount = showCount;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	 public  HashMap<Object,Object> getSearchTerms() {
			return searchTerms;
	}
	//    public  HashMap<String,String> getSearchTerms() {
//			return searchTerms;
//	}

//	public void setSearchTerms(HashMap<String,String> searchTerms) {
//			this.searchTerms = searchTerms;
//	}
    
    public void setSearchTerms(HashMap<Object,Object> searchTerms) {
		this.searchTerms = searchTerms;
    }
}
