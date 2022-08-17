package org.com.ori.key;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ${description}
 *
 * @author: LengShui on ${YEAR}-${MONTH}-${DAY} ${HOUR}:${MINUTE}
 **/
public class TestMain {

	public static volatile JSONArray BATCH_DATA;
	private static final ReadWriteLock RWLOCK = new ReentrantReadWriteLock();
	private static final Lock RLOCK = RWLOCK.readLock();
	private static final Lock WLOCK = RWLOCK.writeLock();

	static {
		BATCH_DATA = new JSONArray();
		res = new AtomicInteger();
	}

	private static Integer SPLIT_SIZE = 100;

	private static AtomicInteger res;

	private static final ThreadPoolExecutor CMS_ARTICLE_BASE_INFO_PUSH_POOL = new ThreadPoolExecutor(20, 20, 5, TimeUnit.SECONDS,
			new ArrayBlockingQueue<>(1500), new ThreadPoolExecutor.CallerRunsPolicy());

	public static void main(String[] args) {

		for (int i = 0; i < 1000010; i++) {
			CMS_ARTICLE_BASE_INFO_PUSH_POOL.execute(() -> pushMessage(new JSONObject()));
		}
		System.out.println(res.addAndGet(BATCH_DATA.size()));

	}

	private static void pushMessage(JSONObject messages) {
		try {
			synchronized (BATCH_DATA) {
				BATCH_DATA.add(messages);
				if (BATCH_DATA.size() >= SPLIT_SIZE) {
					System.out.println(BATCH_DATA.size());
					res.addAndGet(BATCH_DATA.size());
					BATCH_DATA = BATCH_DATA.fluentClear();
				}
			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}
}