package com.eudemon.taurus.app.action;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eudemon.taurus.app.entity.Result;
import com.eudemon.taurus.app.json.JasonUtils;
import com.eudemon.taurus.app.util.RequestUtils;

@Controller
@RequestMapping(value = "verifycode")
public class VerifycodeAction {

	@RequestMapping(value = "show")
	public void show(HttpServletRequest request, HttpServletResponse response) {
		int width = RequestUtils.getParameterAsInt(request, "width", 60);
		int height = RequestUtils.getParameterAsInt(request, "height", 20);

		// 设置页面不缓存
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		// 在内存中创建图象
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		// 获取图形上下文
		Graphics g = image.getGraphics();
		// 生成随机类
		Random random = new Random();
		// 设定背景色
		g.setColor(getRandColor(220, 250));
		g.fillRect(0, 0, width, height);
		// 设定字体
		g.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		// 画边框
		// g.drawRect(0,0,width-1,height-1);
		g.draw3DRect(0, 0, width - 1, height - 1, true);
		// 随机产生155条干扰线，使图象中的认证码不易被其它程序探测到
		g.setColor(getRandColor(160, 200));
		for (int i = 0; i < 155; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			g.drawLine(x, y, x + xl, y + yl);
		}
		// 取随机产生的认证码(6位数字)
		String sRand = "";
		String s = "012345678901234567890123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ012345678901234567890123456789abcdefghijklmnopqrstuvwxyz";
		for (int i = 0; i < 4; i++) {
			char rand = s.charAt(random.nextInt(s.length()));
			sRand += rand;
			// 将认证码显示到图象中
			g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
			// 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
			g.drawString(String.valueOf(rand), 13 * i + 6, 16);
		}
		g.drawOval(0, 12, 60, 11);
		// 将认证码存入SESSION
		request.getSession().setAttribute("validationCode", sRand);
		// 图象生效
		g.dispose();
		ServletOutputStream output;
		try {
			output = response.getOutputStream();
			// 输出图象到页面
			ImageIO.write(image, "JPEG", output);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 生成随机颜色
	 */
	private Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

	@RequestMapping(value = "check")
	public void check(HttpServletRequest request, HttpServletResponse response) {
		String cb = request.getParameter("callback");
		Result rs = new Result();
		
		try {
			String checkcode = request.getParameter("checkcode");
			String validationCode = (String) request.getSession().getAttribute("validationCode");
			
			if (checkcode == null || checkcode.length() == 0) {
				rs.setCode("500");
				rs.setMessage("验证码不能为空");
				JasonUtils.writeJasonP(response, rs, cb);
				return;
			} else {
				if (!checkcode.equals(validationCode)) {
					rs.setCode("500");
					rs.setMessage("验证码输入错误");
					JasonUtils.writeJasonP(response, rs, cb);
					return;
				}
			}
		} catch (Exception e) {
			rs.setCode("500");
			rs.setMessage("验证码检查失败, " + e.getMessage());
			JasonUtils.writeJasonP(response, rs, cb);
			return;
		}
		
		JasonUtils.writeJasonP(response, rs, cb);
	}
}