package com.kitri.util;

public class PageNavigation {
	private String root;
	private int pageNo;
	private int newArticleCount;
	private int totalArticleCount; // �ѰԽù���
	private int totalPageCount; // totalArticleCount�� ���� ���� �� ����
								// TAC�� 237�̶�� TPC�� 12
								// TAC/20 +1
	private boolean nowFirst; // ���� ���� �����������... �� �� ���� ���ٸ�
								// true�� ������ ������
	private boolean nowEnd;
	private String navigator; // ������ �ѷ��ֱ� 11~ 20 �̷���

	public String getRoot() {
		return root;
	}

	public void setRoot(String root) {
		this.root = root;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getNewArticleCount() {
		return newArticleCount;
	}

	public void setNewArticleCount(int newArticleCount) {
		this.newArticleCount = newArticleCount;
	}

	public int getTotalArticleCount() {
		return totalArticleCount;
	}

	public void setTotalArticleCount(int totalArticleCount) {
		this.totalArticleCount = totalArticleCount;
	}

	public int getTotalPageCount() {
		return totalPageCount;
	}

	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}

	public boolean isNowFirst() {
		return nowFirst;
	}

	public void setNowFirst(boolean nowFirst) {
		this.nowFirst = nowFirst;
	}

	public boolean isNowEnd() {
		return nowEnd;
	}

	public void setNowEnd(boolean nowEnd) {
		this.nowEnd = nowEnd;
	}

	public String getNavigator() {
		return navigator;
	}

	public void makeNavigator() {
		StringBuffer tmpNavigator = new StringBuffer();
		
		int pageSize= KitriConstance.NAVIGATOR_SIZE;// TODO ���߿� �����̶� ���� ��
		
		int preEnd= (pageNo-1)/ pageSize* pageSize;

		tmpNavigator.append("<table cellpadding='0' cellspacing='0' border='0'>\n");
		tmpNavigator.append(" <tr>\n");
		if (this.isNowFirst()) { // ù ���������
			tmpNavigator.append("  <td><font color='#999999'>\n<a href='javascript:totalArticle()'>"); // �ֽ��� 1������
			//but �˻������ ���� �ʾƵ� �Ǵϱ� listArticle�� �ƴ� totalArticle, �׸��� �Ű������� ������ �ʿ� ���� ������ 1�����
			tmpNavigator.append("   <img src='" + root
					+ "/img/board/icon_prev02.gif' width='7' height='11' border='0' align='absmiddle' hspace='3'>�ֽŸ��</a>\n");
			tmpNavigator.append("   <img src='" + root
					+ "/img/board/icon_prev01_dim.gif' width='3' height='11' border='0' align='absmiddle' hspace='3'>\n");
			tmpNavigator.append("   ����</font>\n"); // a�±װ� ����
		} else {
			tmpNavigator.append("  <td>\n<a href='javascript:totalArticle(1)'>"); // �ֽ��� 1������
			tmpNavigator.append("   <img src='" + root
					+ "/img/board/icon_prev02.gif' width='7' height='11' border='0' align='absmiddle' hspace='3'>�ֽŸ�� </a>\n");
			tmpNavigator.append("   <a href='javascript:listArticle("+ preEnd+ ")'>"); // ������ a�±�
							//(pg-1)ps* ps �ϸ� ������, 39�� 30, 17�̸� 10
			tmpNavigator.append("   <img src='" + root
					+ "/img/board/icon_prev01_dim.gif' width='3' height='11' border='0' align='absmiddle' hspace='3'>\n");
			tmpNavigator.append("   ����</a>"); // ������
		}
		tmpNavigator.append("  \n</td>\n");
		tmpNavigator.append("  <td style='padding: 0 5 0 5'>\n");
		tmpNavigator.append("   <table cellpadding='0' cellspacing='0' border='0'>\n");
		tmpNavigator.append("    <tr>\n");
		tmpNavigator.append("     <td width='1' nowrap><img src='" + root + "/img/board/n_tab.gif' width='1'");
		tmpNavigator.append(" height='11' border='0' align='absmiddle'><br>");
		tmpNavigator.append("     </td>\n");

		int startPage = preEnd+ 1;
		int endPage = preEnd+ pageSize;
		if(endPage > totalPageCount)
			endPage= totalPageCount;

		for (int i = startPage; i <= endPage; i++) {
			if (pageNo == i) {
				tmpNavigator.append("     <td style='padding:0 7 0 7;' nowrap><font class='text_acc_02'><b>" + i
						+ "</b></font></td>\n");
				tmpNavigator.append("     <td width='1' nowrap><img src='" + root + "/img/board/n_tab.gif' width='1'");
				tmpNavigator.append(" height='11' border='0' align='absmiddle'><br>\n");
			} else {
				tmpNavigator.append("     <td style='padding:0 7 0 7;' nowrap><a href='javascript:listArticle(" + i + ")'>"
						+ i + "</td>\n");
				tmpNavigator.append("     <td width='1' nowrap><img src='" + root + "/img/board/n_tab.gif' width='1'");
				tmpNavigator.append(" height='11' border='0' align='absmiddle'><br>\n");
			}
		}
		tmpNavigator.append("     </td>\n");
		tmpNavigator.append("    </tr>\n");
		tmpNavigator.append("   </table>\n");
		tmpNavigator.append("  </td>\n");
		tmpNavigator.append("  <td>\n");

		if (this.isNowEnd()) {
			tmpNavigator.append("   <font color='#999999'>����<img"); //a�±� x
			tmpNavigator.append("   src='" + root + "/img/board/icon_next01_dim.gif' width='3' height='11'");
			tmpNavigator.append(" border='0' align='absmiddle' hspace='3'> \n");
			tmpNavigator.append("   �����<img src='" + root + "/img/board/icon_next02_dim.gif' width='7' height='11'");
			tmpNavigator.append(" border='0' align='absmiddle' hspace='3'></font>\n");
		} else {
			tmpNavigator.append("   <a href='javascript:listArticle("+ (preEnd+ pageSize+ 1)+")'>����<img"); //a�±� o
			tmpNavigator.append(" src='" + root + "/img/board/icon_next01_dim.gif' width='3' height='11'");
			tmpNavigator.append(" border='0' align='absmiddle' hspace='3'></a>\n");
			tmpNavigator.append("   <a href='javascript:listArticle(" + totalPageCount + ")'>�����<img src='" + root
					+ "/img/board/icon_next02_dim.gif' width='7' height='11'");
			tmpNavigator.append(" border='0' align='absmiddle' hspace='3'>\n");
		}

		tmpNavigator.append("  </td>\n");
		tmpNavigator.append(" </tr>\n");
		tmpNavigator.append("</table>\n");

		this.navigator = tmpNavigator.toString();
	}
}
