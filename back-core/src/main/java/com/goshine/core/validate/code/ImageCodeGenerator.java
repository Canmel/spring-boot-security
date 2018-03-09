/**
 * 
 */
package com.goshine.core.validate.code;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import com.goshine.core.properties.SecurityProperties;

/**
 * @author baily
 *
 */
public class ImageCodeGenerator implements ValidateCodeGenerator {
	
	@Autowired
	private SecurityProperties securityProperties;
	
	// 设置备选验证码:包括"a-z"和数字"0-9"
	private static final String BASE_CODE = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	// 创建一个随机数生成器类
	private Random random = new Random();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.goshine.core.validate.code.ValidateCodeGenerator#createImageCode(org.
	 * springframework.web.context.request.ServletWebRequest)
	 */
	@Override
	public ImageCode createImageCode(ServletWebRequest request) {
		int width = ServletRequestUtils.getIntParameter(request.getRequest(), "width",
				securityProperties.getCode().getImage().getWidth());

		int height = ServletRequestUtils.getIntParameter(request.getRequest(), "height",
				securityProperties.getCode().getImage().getHeight());
		BufferedImage buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = buffImg.createGraphics();
		// 设定图像背景色(因为是做背景，所以偏淡)
		g.setColor(getRandColor(200, 250));
		g.fillRect(0, 0, width, height);
		// 创建字体，字体的大小应该根据图片的高度来定。
		Font font = new Font("Times New Roman", Font.HANGING_BASELINE, 28);
		// 设置字体。
		g.setFont(font);
		// 画边框。
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, width - 1, height - 1);
		// 随机产生155条干扰线，使图象中的认证码不易被其它程序探测到。
		g.setColor(getRandColor(160, 200));

		for (int i = 0; i < 155; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			g.drawLine(x, y, x + xl, y + yl);
		}

		StringBuffer randomCode = getRandomCode(g);
		// 图象生效
		g.dispose();
		return new ImageCode(buffImg, randomCode.toString(), securityProperties.getCode().getImage().getExpireIn());
	}
	
	/**
	 * 随机生成验证码
	 * 
	 * @param g
	 * @return
	 */
	public StringBuffer getRandomCode(Graphics2D g) {
		int length = securityProperties.getCode().getImage().getLength();
		// randomCode用于保存随机产生的验证码，以便用户登录后进行验证。
		StringBuffer randomCode = new StringBuffer();
		int size = BASE_CODE.length();
		for (int i = 0; i < length; i++) {
			// 得到随机产生的验证码数字。
			int start = random.nextInt(size);
			String strRand = BASE_CODE.substring(start, start + 1);
			// 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
			g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
			g.drawString(strRand, 15 * i + 6, 24);
			// 将产生的四个随机数组合在一起。
			randomCode.append(strRand);
		}
		return randomCode;
	}
	
	/**
	 * 随机生成颜色
	 * @param fc
	 * @param bc
	 * @return
	 */
	public Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255) {
			fc = 255;
		}
		if (bc > 255) {
			bc = 255;
		}
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

	public SecurityProperties getSecurityProperties() {
		return securityProperties;
	}

	public void setSecurityProperties(SecurityProperties securityProperties) {
		this.securityProperties = securityProperties;
	}
}
