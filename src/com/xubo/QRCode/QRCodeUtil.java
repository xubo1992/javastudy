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

	// ����: ������Ϣ -> ��ά��
	/*
	 * ����˵����
	 * 		content  -> ��������
	 * 		impPath  -> �����λ��
	 * 		impType  -> �ļ�����
	 * 		size	 -> ͼƬ�ĳߴ�
	 */
	public void encodeQRCode(String content,String imgPath,String impType,int size) {
		File file = new File(imgPath);
		// �ڴ��е�ͼƬ
		BufferedImage image = qRcodeCommon(content,impType,size);
		try {
			ImageIO.write(image,impType, file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/*
	 * ����һ����ά���BufferedImage
	 */
	public BufferedImage qRcodeCommon(String content,String impType,int size) {
		BufferedImage image = null;
		// �ַ��� -->boolean[][]
		Qrcode qrcode = new Qrcode();
		// ���ö�ά����ݴ��� 7% L->M->Q->H 30% �ݴ���Խ�߿ɴ洢����ϢԽ�٣�����ά��������Ҫ��ԽС
		qrcode.setQrcodeErrorCorrect('M'); 
		// �ɴ�ŵ���Ϣ���ͣ�N�����֣�A������+A-Z	B:����
		qrcode.setQrcodeEncodeMode('B');
		// �ߴ磺ȡֵ��Χ��1-40
		qrcode.setQrcodeVersion(size);
		// ��content ��Ϊ��һ����ά�ֽ�����
		boolean[][] bs = qrcode.calQrcode(content.getBytes());
		int imgSize = 67 + 12 * (size - 1);
		
		image = new BufferedImage(imgSize, imgSize, BufferedImage.TYPE_INT_RGB);
		// ��������
		Graphics2D gs = image.createGraphics();
		
		gs.setBackground(Color.WHITE);// ���ñ���ɫΪ��ɫ
		gs.clearRect(0, 0, imgSize, imgSize);// ��ʼ������С	x��y�ᣬ���Ŀ���� 
		gs.setColor(Color.BLACK);// ��������ϵĻ�����ɫ
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
		gs.dispose();// �ͷſռ�
		image.flush();
		return image;
	}

	// ����
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
