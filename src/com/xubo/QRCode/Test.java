package com.xubo.QRCode;

public class Test {

	public static void main(String[] args) {
		/*
		 * 生成二维码的路径
		 * 二维码中的存储信息(文字信息，网站信息等)
		 */
		String imgPath = "src/com/xubo/QRCode/二维码.png";
		String content = "helloworld你好";
		
		/*
		 * 生成二维码：
		 * 	1、加密：将内容放入二维码
		 * 	2、解密：将二维码的信息解析出来
		 */
		// 加密
		QRCodeUtil codeUtil = new QRCodeUtil();
		codeUtil.encodeQRCode(content, imgPath, "png", 17);
		// 解密
		String decodeQRCode = codeUtil.decodeQRCode("src/com/xubo/QRCode/二维码.png");
		System.out.println(decodeQRCode);
	}
}
