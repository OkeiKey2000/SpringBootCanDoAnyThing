package org.com.ori.key;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Stopwatch;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.StampedLock;

/**
 * ${description}
 *
 * @author: LengShui on ${YEAR}-${MONTH}-${DAY} ${HOUR}:${MINUTE}
 **/
public class TestMain {
	public static volatile JSONArray BATCH_DATA;
	// 属于悲观锁的一种 比较适用于 读多写少的情况
	private static final ReadWriteLock RWLOCK = new ReentrantReadWriteLock();
	private static final Lock RLOCK = RWLOCK.readLock();
	private static final Lock WLOCK = RWLOCK.writeLock();

	private static final StampedLock stampLock = new StampedLock();


	static {
		BATCH_DATA = new JSONArray();
		res = new AtomicInteger();
	}

	private static Integer SPLIT_SIZE = 1;

	private static AtomicInteger res;

	private static final ThreadPoolExecutor CMS_ARTICLE_BASE_INFO_PUSH_POOL = new ThreadPoolExecutor(20, 20, 5, TimeUnit.SECONDS,
			new ArrayBlockingQueue<>(1500), new ThreadPoolExecutor.CallerRunsPolicy());

	public static void main(String[] args) {
		Stopwatch stopwatch = Stopwatch.createStarted();
		for (int i = 0; i < 10000010; i++) {
			// 经过测试 只有在读多写少的场景下才可以使用 读写锁来进行优化同时建议使用StampedLock 来帮助进行优化 如果不是该场景 还不如使用Synchronized进行优化
			CMS_ARTICLE_BASE_INFO_PUSH_POOL.execute(() -> pushMessageWithRWLock(new JSONObject()));// 1728
//			CMS_ARTICLE_BASE_INFO_PUSH_POOL.execute(() -> pushMessageWithSynchronized(new JSONObject()));// 1220
//			CMS_ARTICLE_BASE_INFO_PUSH_POOL.execute(() -> pushMessageWithStampRWLock(new JSONObject()));// 1431
		}
		System.out.println(res.addAndGet(BATCH_DATA.size()));
		System.out.println(stopwatch.elapsed(TimeUnit.MILLISECONDS));

	}

	private static void pushMessageWithSynchronized(JSONObject messages) {
		try {
			synchronized (BATCH_DATA) {
				BATCH_DATA.add(messages);
				if (BATCH_DATA.size() >= SPLIT_SIZE) {
//					System.out.println(BATCH_DATA.size());
					res.addAndGet(BATCH_DATA.size());
					BATCH_DATA = BATCH_DATA.fluentClear();
				}
			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private static void pushMessageWithRWLock(JSONObject message) {
		WLOCK.lock();
		try {
			BATCH_DATA.add(message);
			if (BATCH_DATA.size() >= SPLIT_SIZE) {
				res.addAndGet(BATCH_DATA.size());
				BATCH_DATA.fluentClear();
			}
		}finally {
			WLOCK.unlock();
		}
	}
	private static void pushMessageWithStampRWLock(JSONObject message) {
		long l = stampLock.writeLock();
		try {
			BATCH_DATA.add(message);
			if (BATCH_DATA.size() >= SPLIT_SIZE) {
				res.addAndGet(BATCH_DATA.size());
				BATCH_DATA.fluentClear();
			}
		}finally {
			stampLock.unlock(l);
		}
	}
}