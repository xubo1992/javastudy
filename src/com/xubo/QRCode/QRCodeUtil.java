package com.xubo.QRCode;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.swetake.util.Qrcode;

import jp.sourceforge.qrcode.QRCodeDecoder;
import jp.sourceforge.qrcode.data.QRCodeImage;

public class QRCodeUtil {

	// 加密: 文字信息 -> 二维码
	/*
	 * 参数说明：
	 * 		content  -> 文字内容
	 * 		impPath  -> 保存的位置
	 * 		impType  -> 文件类型
	 * 		size	 -> 图片的尺寸
	 */
	public void encodeQRCode(String content,String imgPath,String impType,int size) {
		File file = new File(imgPath);
		// 内存中的图片
		BufferedImage image = qRcodeCommon(content,impType,size);
		try {
			ImageIO.write(image,impType, file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/*
	 * 产生一个二维码的BufferedImage
	 */
	public BufferedImage qRcodeCommon(String content,String impType,int size) {
		BufferedImage image = null;
		// 字符串 -->boolean[][]
		Qrcode qrcode = new Qrcode();
		// 设置二维码的容错率 7% L->M->Q->H 30% 容错率越高可存储的信息越少，但二维码清晰度要求越小
		qrcode.setQrcodeErrorCorrect('M'); 
		// 可存放的信息类型，N：数字，A：数字+A-Z	B:所有
		qrcode.setQrcodeEncodeMode('B');
		// 尺寸：取值范围：1-40
		qrcode.setQrcodeVersion(size);
		// 将content 变为了一个二维字节数组
		boolean[][] bs = qrcode.calQrcode(content.getBytes());
		int imgSize = 67 + 12 * (size - 1);
		
		image = new BufferedImage(imgSize, imgSize, BufferedImage.TYPE_INT_RGB);
		// 创建画板
		Graphics2D gs = image.createGraphics();
		
		gs.setBackground(Color.WHITE);// 设置背景色为白色
		gs.clearRect(0, 0, imgSize, imgSize);// 初始化面板大小	x、y轴，面板的宽与高 
		gs.setColor(Color.BLACK);// 设置面板上的画笔颜色
		int pixoff = 2;
		for(int i = 0; i < bs.length; i++) {
			for( int j = 0; j < bs.length; j++) {
				if(bs[i][j]) {
					gs.fillRect(i*3+pixoff, j*3+pixoff, 3, 3);
				}
			}
		}
		Image logo = null;
		try {
			logo = ImageIO.read(new File("src/com/xubo/QRCode/123.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		gs.drawImage(logo, imgSize/5*2, imgSize/5*2, imgSize/5*1, imgSize/5*1,null);
		gs.dispose();// 释放空间
		image.flush();
		return image;
	}

	// 解密
	public String decodeQRCode(String imgPath) {
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File(imgPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		QRCodeDecoder decoder = new QRCodeDecoder();
		QRCodeImageImpl codeImageImpl = new QRCodeImageImpl(image);
		byte[] decode = decoder.decode(codeImageImpl);
		String str = new String(decode);
		return str;
	}
	
	public class QRCodeImageImpl implements QRCodeImage{
		private BufferedImage image;
		public QRCodeImageImpl(BufferedImage image) {
			this.image = image;
		}
		@Override
		public int getHeight() {
			return image.getHeight();
		}

		@Override
		public int getPixel(int x, int y) {
			return image.getRGB(x, y);
		}

		@Override
		public int getWidth() {
			return image.getWidth();
		}
		
	}
}
