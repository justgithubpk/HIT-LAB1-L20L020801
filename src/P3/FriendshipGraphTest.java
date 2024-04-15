package P3;

import static org.junit.Assert.*;

import org.junit.Test;

public class FriendshipGraphTest {
	FriendshipGraph graph=new FriendshipGraph();
	/*
	 * Testing strategy
	 * �������󣺴����ĸ����󼴿�
	 */
	@Test
	public void addVertextest() {
		Person yu = new Person("Yu");
		Person tao = new Person("Tao");
		Person cai = new Person("Cai");
		Person ji = new Person("Ji");
		graph.addVertex(yu);
		graph.addVertex(tao);
		graph.addVertex(cai);
		graph.addVertex(ji);
		assertTrue(graph.allpeople.contains(yu));
		assertTrue(graph.allpeople.contains(tao));
		assertTrue(graph.allpeople.contains(cai));
		assertTrue(graph.allpeople.contains(ji));
	}
	/*
	 * Testing strategy
	 * ��������ͼ���ѣ�ֻ��Ҫ��ȡ����㶼�������Ѽ���
	 */
	@Test
	public void addEdgetest() {
		Person wang = new Person("Wang");
		Person ning = new Person("Ning");
		Person li = new Person("Li");
		Person hai = new Person("Hai");
		graph.addVertex(wang);
		graph.addVertex(ning);
		graph.addVertex(li);
		graph.addVertex(hai);
		graph.addEdge(wang, ning);
		graph.addEdge(ning, wang);
		graph.addEdge(ning, li);
		graph.addEdge(li, ning);
		assertTrue(wang.gethisfriend().contains(ning));
		assertTrue(ning.gethisfriend().contains(wang));
		assertTrue(ning.gethisfriend().contains(li));
		assertTrue(li.gethisfriend().contains(ning));
	}
	
	/*
	 * Testing strategy
	 * ����������֮��ľ��뻮�֣�����Ϊ0������Ϊ-1������Ϊ����1
	 * �����Ƿ�ͬһ���˻��֣�ͬһ����֮����룬������֮�����
	 * ����ÿ��ȡֵ���£�
	 */
	@Test
	public void getDistancetest() {
		Person wo = new Person("Wo");
		Person shi = new Person("Shi");
		Person xue = new Person("Xue");
		Person sheng = new Person("Sheng");
		graph.addVertex(wo);
		graph.addVertex(shi);
		graph.addVertex(xue);
		graph.addVertex(sheng);
		graph.addEdge(wo, shi);
		graph.addEdge(shi, wo);
		graph.addEdge(shi, xue);
		graph.addEdge(xue, shi);
		assertEquals(1, graph.getDistance(wo, shi));
		assertEquals(2, graph.getDistance(wo, xue));
		assertEquals(0, graph.getDistance(sheng, sheng));
		assertEquals(-1, graph.getDistance(wo, sheng));
	}
}
