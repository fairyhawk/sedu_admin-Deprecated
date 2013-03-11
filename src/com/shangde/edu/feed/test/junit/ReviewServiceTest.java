package com.shangde.edu.feed.test.junit;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.shangde.common.vo.PageResult;
import com.shangde.edu.cus.domain.Customer;
import com.shangde.edu.feed.condition.QueryReviewCondition;
import com.shangde.edu.feed.domain.Review;
import com.shangde.edu.feed.service.IReview;

/**
 * 留言模块测试
 * 
 * @author Libg
 * 
 */
public class ReviewServiceTest extends BaseTest {

	/**
	 * 添加
	 */
	public void testAddReview() {

		fail();

		IReview reviewService = (IReview) springContext
				.getBean("reviewService");

		Review review = new Review();
		review.setSubjectId(1);
		review.setCourseId(1);
		review.setContent("content-内容");

		Customer customer = new Customer();
		customer.setCusId(347);
		review.setCustomer(customer);

		Customer targetCustomer = new Customer();
		targetCustomer.setCusId(347);
		review.setTargetCustomer(targetCustomer);

		review.setTargetReviewId(1);

		review.setCounts(0);
		review.setDetail(null);
		review.setStatus(1);

		Date now = new Date();
		review.setPubDate(now);
		review.setModified(now);

		reviewService.addReview(review);
	}

	/**
	 * 修改
	 */
	public void testUpdateReview() {
		fail();

		IReview reviewService = (IReview) springContext
				.getBean("reviewService");

		Review review = new Review();
		review.setId(1);
		review.setSubjectId(1);
		review.setCourseId(1);
		review.setContent("content-内容---");

		Customer customer = new Customer();
		customer.setCusId(347);
		review.setCustomer(customer);

		Customer targetCustomer = new Customer();
		targetCustomer.setCusId(347);
		review.setTargetCustomer(targetCustomer);

		Review targetReview = new Review();
		targetReview.setId(1);
		review.setTargetReview(targetReview);

		review.setCounts(0);
		review.setDetail(null);
		review.setStatus(1);

		Date now = new Date();
		review.setPubDate(now);
		review.setModified(now);

		reviewService.updateReview(review);

	}

	/**
	 * 根据id查询对象
	 */
	public void testGetReviewById() {
		fail();

		IReview reviewService = (IReview) springContext
				.getBean("reviewService");

		Review review = reviewService.getReviewById(1);

		System.out.println("==============================================");
		System.out.println("==============================================");
		System.out.println("==============================================");
		System.out.println(review.toString());
		System.out.println("============================================");
	}

	/**
	 * 根据id删除对象
	 */
	public void testDelReviewById() {
		fail();

		IReview reviewService = (IReview) springContext
				.getBean("reviewService");

		reviewService.delReviewById(1);

	}

	/**
	 * 修改字段counts值[递增/递减]
	 * 
	 * @param map
	 *            key=incremental表示增量(1)/id表示记录id
	 * @return
	 */
	public void testUpdateReviewCounts() {
		fail();

		IReview reviewService = (IReview) springContext
				.getBean("reviewService");

		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("id", 1);
		map.put("incremental", 1);

		reviewService.updateReviewCounts(map);

	}

	/**
	 * 分页查询数据
	 */
	public void testGetReviewList() {
		fail();

		IReview reviewService = (IReview) springContext
				.getBean("reviewService");

		QueryReviewCondition queryReviewCondition = new QueryReviewCondition();
		queryReviewCondition.setCurrentPage(1);
		queryReviewCondition.setPageSize(100);
		PageResult pr = reviewService.getReviewList(queryReviewCondition);

		List<Review> reviewList = pr.getPageResult();

		System.out.println("-----------------------------------------------");
		System.out.println("-----------------------------------------------");
		System.out.println("-----------------------------------------------");
		System.out.println("总页数->" + pr.getTotalPage());
		System.out.println("总记录数->" + pr.getTotalRecord());

		for (Review r : reviewList) {
			System.out.println("-------------------------");
			System.out.println(r.getId());
		}

	}

	public void testGetReviewListBack() {

		IReview reviewService = (IReview) springContext
				.getBean("reviewService");
		QueryReviewCondition queryReviewCondition = new QueryReviewCondition();
		queryReviewCondition.setCurrentPage(1);
		queryReviewCondition.setPageSize(100);

		queryReviewCondition.setStartTime("2011-10-18 15:19:50");
		queryReviewCondition.setEndTime("2011-10-18 15:20:15");

		PageResult pr = reviewService.getReviewListBack(queryReviewCondition);

		List<Review> reviewList = pr.getPageResult();

		System.out
				.println("----------------------------------------------kkk-");
		System.out.println("-----------------------------------------------");
		System.out.println("-----------------------------------------------");
		System.out.println("总页数->" + pr.getTotalPage());
		System.out.println("总记录数->" + pr.getTotalRecord());

		for (Review r : reviewList) {
			System.out.println("-------------------------");
			System.out.println(r.getId());
		}
	}

}
