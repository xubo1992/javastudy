package com.xubo.QRCode;

public class Test {

	public static void main(String[] args) {
		/*
		 * ���ɶ�ά���·��
		 * ��ά���еĴ洢��Ϣ(������Ϣ����վ��Ϣ��)
		 */
		String imgPath = "src/com/xubo/QRCode/��ά��.png";
		String content = "helloworld���";
		
		/*
		 * ���ɶ�ά�룺
		 * 	1�����ܣ������ݷ����ά��
		 * 	2�����ܣ�����ά�����Ϣ��������
		 */
		// ����
		QRCodeUtil codeUtil = new QRCodeUtil();
		codeUtil.encodeQRCode(content, imgPath, "png", 17);
		// ����
		String decodeQRCode = codeUtil.decodeQRCode("src/com/xubo/QRCode/��ά��.png");
		System.out.println(decodeQRCode);
	}
}
