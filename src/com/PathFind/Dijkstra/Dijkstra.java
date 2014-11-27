package com.PathFind.Dijkstra;

import java.util.Vector;

public class Dijkstra {
	int n = 0; // ������ ����

	final static int m = 30000; // ���� ���� ��... ���� ū���� ����
	int data[][]; // ��ü ���� ����Ÿ

	boolean visit[]; // �湮�� Ȯ��
	int dis[]; // ������ ������ �Ÿ�
	int prev[]; // ������ ���� ���� ����

	int s, e; // �������� ���� ����
	int stack[]; // ���������� ���������� ���� ����

	Vector<Integer> stackV;

	public void init(int dataI[][]) // ���ͽ�Ʈ��(Dijkstra) �˰���/���� ���� ���� �ִܰŸ�
	{
		data = dataI;
		n = data.length;

		dis = new int[n];
		visit = new boolean[n];
		prev = new int[n];
		stack = new int[n];
		stackV = new Vector<Integer>();
	}

	public int theLeastDistance() {
		return dis[e - 1];
	}

	public void start(int start, int end) {
		System.out
				.println("==========================================================");
		System.out.println("Dijkstra start");
		System.out.println("startPoint: " + start);
		System.out.println("endPoint: " + end);
		System.out
				.println("===========================================================");
		s = start;
		e = end;

		int k = 0;
		int min = 0;

		for (int i = 0; i < n; i++) { /* �ʱ�ȭ */
			dis[i] = m;
			prev[i] = 0;
			visit[i] = false;
		}

		dis[s - 1] = 0; /* �������� �Ÿ��� 0 */

		for (int i = 0; i < n; i++) {
			min = m;
			for (int j = 0; j < n; j++) { /* ������ ����ŭ �ݺ� */
				if (visit[j] == false && dis[j] < min) { /* Ȯ������ �ʰ� �Ÿ��� ª�� ������ ã�� */
					k = j;
					min = dis[j];
				}
			}
			visit[k] = true; /* �ش� ���� Ȯ�� üũ */

			if (min == m)
				break; /* ����� ���� ������ ���� */

			/****
			 * I -> J ���� I -> K -> J�� �Ÿ��� �� ������ ����
			 ****/
			for (int j = 0; j < n; j++) {
				if (dis[k] + data[k][j] < dis[j]) {
					dis[j] = dis[k] + data[k][j]; /* �ִܰŸ� ���� */
					prev[j] = k; /* J�� ���� ���ؼ��� K�� ���ľ� �� */
				}
			}
		}
		nowLeastDistance(); //�ֿܼ��� �ִܰŸ� ���
		inverseFind(); // �ֿܼ��� �ִ� ��� ���
	}

	/**** �ִ� �Ÿ� ��� ****/
	public void nowLeastDistance() {
		System.out.printf("�ִܰŸ�:  %10d  �ð�   ", dis[e - 1]);
	}

	/**** �ִ� ��θ� ���� ****/
	public void inverseFind() {
		int tmp = 0;
		int top = -1;
		tmp = e - 1;
		while (true) {
			stack[++top] = tmp + 1;
			if (tmp == s - 1)
				break; /* �������� �̸������� ���� */
			tmp = prev[tmp];
		}

		/* ������ ��� ��� */
		stackV.removeAllElements();
		for (int i = top; i > -1; i--) {
			 System.out.printf("%d", stack[i]);
			stackV.add(stack[i]);
			if (i != 0)System.out.printf(" -> ");
		}
		System.out.printf("\n");
	}
	
	public Vector<Integer> getStack()
	{
		return stackV;
	}
	
}