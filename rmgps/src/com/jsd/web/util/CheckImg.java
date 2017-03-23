package com.jsd.web.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CheckImg extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
	 * methods.
	 * 
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 */
	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("image/jpeg");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		HttpSession session = request.getSession();
		// 锟斤拷锟节达拷锟叫达拷锟斤拷图锟斤拷
		int width = 60, height = 20;
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);

		// 锟斤拷取图锟斤拷锟斤拷锟斤拷锟斤拷
		Graphics g = image.getGraphics();

		// 锟斤拷锟斤拷锟斤拷锟斤拷
		Random random = new Random();

		// 锟借定锟斤拷锟斤拷色
		g.setColor(getRandColor(200, 250));
		g.fillRect(0, 0, width, height);

		// 锟借定锟斤拷锟斤拷
		g.setFont(new Font("Times New Roman", Font.PLAIN, 18));

		// 锟斤拷锟竭匡拷
		// g.setColor(new Color());
		// g.drawRect(0,0,width-1,height-1);

		// 锟斤拷锟斤拷锟斤拷155锟斤拷锟斤拷锟斤拷撸锟绞雇碱拷锟斤拷械锟斤拷锟街わ拷氩伙拷妆锟斤拷锟斤拷锟斤拷锟斤拷探锟解到
		g.setColor(getRandColor(160, 200));
		for (int i = 0; i < 155; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			g.drawLine(x, y, x + xl, y + yl);
		}

		// 取锟斤拷锟斤拷锟斤拷锟斤拷锟街わ拷锟?4位锟斤拷锟斤拷)
		String sRand = "";
		for (int i = 0; i < 4; i++) {
			String rand = String.valueOf(random.nextInt(10));
			sRand += rand;
			// 锟斤拷锟斤拷证锟斤拷锟斤拷示锟斤拷图锟斤拷锟斤拷
			g.setColor(new Color(20 + random.nextInt(110), 20 + random
					.nextInt(110), 20 + random.nextInt(110)));// 锟斤拷锟矫猴拷锟斤拷锟?锟斤拷锟斤拷色锟斤拷同锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷为锟斤拷锟斤拷太锟接斤拷锟斤拷锟斤拷只锟斤拷直锟斤拷锟斤拷锟?
			g.drawString(rand, 13 * i + 6, 16);
		}

		// 锟斤拷锟斤拷证锟斤拷锟斤拷锟絊ESSION
		session.setAttribute("checkImg", sRand);
		// 图锟斤拷锟斤拷效
		g.dispose();
		ServletOutputStream responseOutputStream = response.getOutputStream();
		// 锟斤拷锟酵碱拷锟揭筹拷锟?
		ImageIO.write(image, "png", responseOutputStream);

		// 锟斤拷锟铰关憋拷锟斤拷锟斤拷锟斤拷
		responseOutputStream.flush();
		responseOutputStream.close();
	}

	Color getRandColor(int fc, int bc) {// 锟斤拷围锟斤拷锟斤拷锟斤拷锟斤拷色
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

	// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on
	// the + sign on the left to edit the code.">
	/**
	 * Handles the HTTP <code>GET</code> method.
	 * 
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Handles the HTTP <code>POST</code> method.
	 * 
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Returns a short description of the servlet.
	 */
	public String getServletInfo() {
		return "Short description";
	}
	// </editor-fold>
}
