package org.dmr;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

import org.dmr.domain.LruCache;
import org.dmr.domain.impl.LruCacheImpl;
import org.dmr.domain.impl.UnityKnowledgeString;

/**
 * Created by davidmartinezros on 22/04/2017.
 * 
 * email: davidnezan@gmail.com
 * 
 * web: http://davidmartinezros.com
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LruCacheApplicationTests {

	private LruCache lru;

	@Before
	public void setUp(){
		lru = LruCacheImpl.getInstance(null);
		lru.setLruSize(3);
	}

	@Test
	public void lru_add_values_with_first_item_removed(){
		UnityKnowledgeString unity1= new UnityKnowledgeString("aaa", null);
		lru.put("aaa", unity1);
		UnityKnowledgeString unity2= new UnityKnowledgeString("bbb", null);
		lru.put("bbb", unity2);
		UnityKnowledgeString unity3= new UnityKnowledgeString("ccc", null);
		lru.put("ccc", unity3);
		UnityKnowledgeString unity4= new UnityKnowledgeString("ddd", null);
		lru.put("ddd", unity4);

		assertEquals(lru.toString(), "bbb ccc ddd ");
	}

	@Test
	public void lru_add_values_and_use_first() throws Exception {
		UnityKnowledgeString unity1= new UnityKnowledgeString("aaa", null);
		lru.put("aaa", unity1);
		UnityKnowledgeString unity2= new UnityKnowledgeString("bbb", null);
		lru.put("bbb", unity2);
		UnityKnowledgeString unity3= new UnityKnowledgeString("ccc", null);
		lru.put("ccc", unity3);
		lru.get("aaa");
		UnityKnowledgeString unity4= new UnityKnowledgeString("ddd", null);
		lru.put("ddd", unity4);

		assertEquals(lru.toString(), "ccc aaa ddd ");
	}

	@Test
	public void lru_add_values_and_use_second() throws Exception {
		UnityKnowledgeString unity1= new UnityKnowledgeString("aaa", null);
		lru.put("aaa", unity1);
		UnityKnowledgeString unity2= new UnityKnowledgeString("bbb", null);
		lru.put("bbb", unity2);
		UnityKnowledgeString unity3= new UnityKnowledgeString("ccc", null);
		lru.put("ccc", unity3);
		lru.get("bbb");
		UnityKnowledgeString unity4= new UnityKnowledgeString("ddd", null);
		lru.put("ddd", unity4);

		assertEquals(lru.toString(), "ccc bbb ddd ");
	}

	@Test(expected = NullPointerException.class)
	public void lru_add_values_and_get_first() throws Exception {
		UnityKnowledgeString unity1= new UnityKnowledgeString("aaa", null);
		lru.put("aaa", unity1);
		UnityKnowledgeString unity2= new UnityKnowledgeString("bbb", null);
		lru.put("bbb", unity2);
		UnityKnowledgeString unity3= new UnityKnowledgeString("ccc", null);
		lru.put("ccc", unity3);
		UnityKnowledgeString unity4= new UnityKnowledgeString("ddd", null);
		lru.put("ddd", unity4);
		lru.get("aaa");
	}

}
