
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream.GetField;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


public class Algorithm {

	static private int[][] arrayImage;

	public int getLuminancia(int r, int g, int b) {// Recebe os indices
													// RGB retorna o
													// indice de
													// luminancia

		int limiar = (int) (r * 0.2126 + g * 0.7152 + b * 0.0722);

		return limiar;
	}

	public BufferedImage getEscalaCinza(BufferedImage im) {// Recebe uma
															// imagem e
															// retorna a
															// imagem
															// binaria
		// Array com a largura e altura da imagem (Imagem binaria com media)
		BufferedImage img = im;
		arrayImage = null;
		arrayImage = new int[img.getHeight()][img.getHeight()];
		// Variaveis para o calcula da media
		int l = 0, m = 0;
		double k = 0.0;
		// Media das
		int n = 0;
		// Cor
		Color cor;

		for (int i = 0; i < img.getWidth(); i++) {
			for (int j = 0; j < img.getHeight(); j++) {
				cor = new Color(img.getRGB(i, j));
				k = getLuminancia(cor.getRed(), cor.getGreen(), cor.getBlue());
				l = (int) k;
				img.setRGB(i, j, new Color(l, l, l).getRGB());
				arrayImage[i][j] = l;
			}
		}

		return img;
	}

	public double getMedia(BufferedImage img) { // Media dos valores dos
												// pixels
		// Array com a largura e altura da imagem (Imagem binaria com media)

		// valores usados no calculo
		int l = 0, m = 0, n = 0;

		for (int i = 0; i < img.getWidth(); i++) {
			for (int j = 0; j < img.getHeight(); j++) {
				l += arrayImage[i][j];
				m++;
			}
		}
		n = l / m;
		return n;
	}

	public BufferedImage getImagemBinaria(BufferedImage im) {
		BufferedImage img = im;
		// Array com a largura e altura da imagem (Imagem binaria com media)
		int[][] arrayImage = new int[img.getHeight()][img.getHeight()];
		// Imagem em escala de cinza
		img = getEscalaCinza(img);
		// cor
		Color cor;
		double media = getMedia(img);
		System.out.println(media);
		for (int i = 0; i < img.getWidth(); i++) {
			for (int j = 0; j < img.getHeight(); j++) {
				cor = new Color(img.getRGB(i, j));
				if (cor.getRed() < media) {
					img.setRGB(i, j, new Color(0, 0, 0).getRGB());
				} else {
					img.setRGB(i, j, new Color(255, 255, 255).getRGB());
				}

				// img.setRGB(i, j, new Color(255, 255, 255).getRGB());
			}
		}
		arrayImage = null;
		return img;
	}
	
	
	public BufferedImage getImagemBinariaAlgoritmo(BufferedImage img) {
		// Array com a largura e altura da imagem (Imagem binaria com media)
		//int[][] arrayImage = new int[img.getHeight()][img.getHeight()];
		// Imagem em escala de cinza
		//img = getEscalaCinza(img);
		// cor
		Color cor;
		//double media = getMedia(img);
		//System.out.println(media);
		double media = 0;
		int red;
		for (int i = 0; i < img.getWidth(); i++) {
			for (int j = 0; j < img.getHeight(); j++) {
				cor = new Color(img.getRGB(i, j));
				//System.out.println(cor.getRed());
				red = cor.getRed();
				media += red; //Como o algoritmo ja coloca a escala de cinza, esse algoritmo pega somente a cor vermelha, ja que as outras cores são as mesmas por padrao
				
				
			}
		}
		media /= img.getWidth()*img.getHeight();
		cor = null;
		for (int i = 0; i < img.getWidth(); i++) {
			for (int j = 0; j < img.getHeight(); j++) {
				cor = new Color(img.getRGB(i, j));
				if (cor.getRed() < media) {
					img.setRGB(i, j, new Color(0, 0, 0).getRGB());
				} else {
					img.setRGB(i, j, new Color(255, 255, 255).getRGB());
				}

				// img.setRGB(i, j, new Color(255, 255, 255).getRGB());
			}
		}
		//arrayImage = null;
		return img;
	}
	
	
	
	public BufferedImage getDesfoqueGaussiano(BufferedImage im) {
		BufferedImage img = im;
		
		Color cores[] = new Color[9 + 1];

		cores[0] = new Color(0);

		cores[1] = new Color(0);
		cores[2] = new Color(0);
		cores[3] = new Color(0);

		cores[4] = new Color(0);
		cores[5] = new Color(0);
		cores[6] = new Color(0);

		cores[7] = new Color(0);
		cores[8] = new Color(0);
		cores[9] = new Color(0);

		int miR = 0, miG = 0, miB = 0, temp = 0, S = 0;
		double r = 0, g = 0, b = 0;
		int ri = 0, gi = 0, bi = 0;
		double varR = 0, varG = 0, varB = 0; 
		
		
		for (int i = 0; i < img.getWidth(); i ++) {
			for (int j = 0; j < img.getHeight(); j ++) {
				Color cor = new Color(img.getRGB(i, j));
				/*
				 * r = (int) cor.getRed(); g = (int) cor.getGreen(); b = (int)
				 * cor.getBlue();
				 */
				if ((i > 1 && j > 1) && (i < img.getWidth() - 1)
						&& (j < img.getHeight() - 1)) {

					// Red
					//k - abscissas
					//k1 - ordenadas
					//miR - Media do vermelho
					for (int k = -1; k <= 1; k++) {
						for (int k2 = 1; k2 >= -1 ; k2--) {
							for (int k3 = 1; k3 < 10;k3++){
								cores[k3] = new Color(img.getRGB(k + i, k2 + j));
								miR = cores[k3].getRed();
							}
						}
					}
					
					miR /= 9;

					// Green
					//k - abscissas
					//k1 - ordenadas
					//miG - Media do verde

					for (int k = -1; k <= 1; k++) {
						for (int k2 = 1; k2 >= -1 ; k2--) {
							for (int k3 = 1; k3 < 10;k3++){
								cores[k3] = new Color(img.getRGB(k + i, k2 + j));
								miG = cores[k3].getGreen();
							}
						}
					}

					miG /= 9;

					// Blue
					//k - abscissas
					//k1 - ordenadas
					//miB - Media do azul
					for (int k = -1; k <= 1; k++) {
						for (int k2 = 1; k2 >= -1 ; k2--) {
							for (int k3 = 1; k3 < 10;k3++){
								cores[k3] = new Color(img.getRGB(k + i, k2 + j));
								miB = cores[k3].getBlue();
							}
						}
					}

					miB /= 9;

					//Calculos gaussiano
					//Red
					//var - variancia e desvio padrao
					for (int k = 1; k < 10; k++) {
						varR += cores[k].getRed() - miR;
					}	
					varR /= 8;
					varR = Math.sqrt(varR);
					
					//Green
					for (int k = 1; k < 10; k++) {
						varG += cores[k].getGreen() - miG;
					}	
					varG /= 8;
					varG = Math.sqrt(varG);
					
					//Blue
					for (int k = 1; k < 10; k++) {
						varB += cores[k].getBlue() - miB;
					}	
					varB /= 8;
					varB = Math.sqrt(varB);
					
					// Setando a imagem
					Color c;
					for (int k = -1; k <= 1; k++) {
						for (int k2 = 1; k2 >= -1 ; k2--) {
							c = new Color(img.getRGB(k + i, k2 + j));

							//Red
							r = ((c.getRed() - miR)/varR);
							ri = (int) r;
							
							//Green
							g = ((c.getGreen() - miG)/varG);
							gi = (int) g;
							
							//Blue
							b = ((c.getBlue() - miB)/varB);
							bi = (int) b;
							/*System.out.println("r - " + r + " ri - " + ri + " miR - " + miR + "\n" + 
											"b - " + r + " bi " + bi + " miB - " + miB + "\n" + 
											"g - " + r + " gi " + gi + " miG - " + miG
									);*/
							img.setRGB(k + i, k2 + j, new Color(ri, gi, bi).getRGB());
						}
					}

				}

				// img.setRGB(i, j, new Color(r, g, b).getRGB());
			}
		}
		arrayImage = null;
		return img;
	}

	
	

	public BufferedImage getDesfoque(BufferedImage im) {
		BufferedImage img = im;
		
		Color cores[] = new Color[9 + 1];

		cores[0] = new Color(0);

		cores[1] = new Color(0);
		cores[2] = new Color(0);
		cores[3] = new Color(0);

		cores[4] = new Color(0);
		cores[5] = new Color(0);
		cores[6] = new Color(0);

		cores[7] = new Color(0);
		cores[8] = new Color(0);
		cores[9] = new Color(0);

		int r = 0, g = 0, b = 0, temp = 0;

		for (int i = 0; i < img.getWidth(); i ++) {
			for (int j = 0; j < img.getHeight(); j ++) {
				Color cor = new Color(img.getRGB(i, j));
				/*
				 * r = (int) cor.getRed(); g = (int) cor.getGreen(); b = (int)
				 * cor.getBlue();
				 */
				if ((i > 1 && j > 1) && (i < img.getWidth() - 1)
						&& (j < img.getHeight() - 1)) {

					// Red
					cores[1] = new Color(img.getRGB(i - 1, j + 1));
					cores[2] = new Color(img.getRGB(i, j + 1));
					cores[3] = new Color(img.getRGB(i + 1, j + 1));

					cores[4] = new Color(img.getRGB(i - 1, j));
					cores[5] = new Color(img.getRGB(i, j));
					cores[6] = new Color(img.getRGB(i + 1, j));

					cores[7] = new Color(img.getRGB(i - 1, j - 1));
					cores[8] = new Color(img.getRGB(i, j - 1));
					cores[9] = new Color(img.getRGB(i + 1, j - 1));

					r = cores[1].getRed() + cores[2].getRed()
							+ cores[3].getRed() + cores[4].getRed()
							+ cores[5].getRed() + cores[6].getRed()
							+ cores[7].getRed() + cores[8].getRed()
							+ cores[9].getRed();
					r /= 9;

					// Green

					cores[1] = new Color(img.getRGB(i - 1, j + 1));
					cores[2] = new Color(img.getRGB(i, j + 1));
					cores[3] = new Color(img.getRGB(i + 1, j + 1));

					cores[4] = new Color(img.getRGB(i - 1, j));
					cores[5] = new Color(img.getRGB(i, j));
					cores[6] = new Color(img.getRGB(i + 1, j));

					cores[7] = new Color(img.getRGB(i - 1, j - 1));
					cores[8] = new Color(img.getRGB(i, j - 1));
					cores[9] = new Color(img.getRGB(i + 1, j - 1));

					g = cores[1].getGreen() + cores[2].getGreen()
							+ cores[3].getGreen() + cores[4].getGreen()
							+ cores[5].getGreen() + cores[6].getGreen()
							+ cores[7].getGreen() + cores[8].getGreen()
							+ cores[9].getGreen();
					g /= 9;

					// Blue

					cores[1] = new Color(img.getRGB(i - 1, j + 1));
					cores[2] = new Color(img.getRGB(i, j + 1));
					cores[3] = new Color(img.getRGB(i + 1, j + 1));

					cores[4] = new Color(img.getRGB(i - 1, j));
					cores[5] = new Color(img.getRGB(i, j));
					cores[6] = new Color(img.getRGB(i + 1, j));

					cores[7] = new Color(img.getRGB(i - 1, j - 1));
					cores[8] = new Color(img.getRGB(i, j - 1));
					cores[9] = new Color(img.getRGB(i + 1, j - 1));

					b = cores[1].getBlue() + cores[2].getBlue()
							+ cores[3].getBlue() + cores[4].getBlue()
							+ cores[5].getBlue() + cores[6].getBlue()
							+ cores[7].getBlue() + cores[8].getBlue()
							+ cores[9].getBlue();
					b /= 9;

					// Setando a imagem
					img.setRGB(i - 1, j + 1, new Color(r, g, b).getRGB());
					img.setRGB(i, j + 1, new Color(r, g, b).getRGB());
					img.setRGB(i + 1, j + 1, new Color(r, g, b).getRGB());

					img.setRGB(i - 1, j, new Color(r, g, b).getRGB());
					img.setRGB(i, j, new Color(r, g, b).getRGB());
					img.setRGB(i + 1, j, new Color(r, g, b).getRGB());

					img.setRGB(i - 1, j - 1, new Color(r, g, b).getRGB());
					img.setRGB(i, j - 1, new Color(r, g, b).getRGB());
					img.setRGB(i + 1, j - 1, new Color(r, g, b).getRGB());

				}

				// img.setRGB(i, j, new Color(r, g, b).getRGB());
			}
		}
		arrayImage = null;
		return img;
	}

	
	public BufferedImage getNegativo(BufferedImage img){
		
		Color cores[] = new Color[9 + 1];

		cores[0] = new Color(0);

		cores[1] = new Color(0);
		cores[2] = new Color(0);
		cores[3] = new Color(0);

		cores[4] = new Color(0);
		cores[5] = new Color(0);
		cores[6] = new Color(0);

		cores[7] = new Color(0);
		cores[8] = new Color(0);
		cores[9] = new Color(0);

		
		for (int i = 0; i < img.getWidth(); i ++) {
			for (int j = 0; j < img.getHeight(); j ++) {
				Color cor = new Color(img.getRGB(i, j));
				
				img.setRGB(i, j, new Color(255 - cor.getRed(), 255 - cor.getGreen(), 255 - cor.getBlue()).getRGB());
			
			}
		}
		
		return img;
	}


	public BufferedImage getBordaSobel(BufferedImage img) {
		
		
		Color cores[] = new Color[9 + 1];

		cores[0] = new Color(0);

		cores[1] = new Color(0);
		cores[2] = new Color(0);
		cores[3] = new Color(0);

		cores[4] = new Color(0);
		cores[5] = new Color(0);
		cores[6] = new Color(0);

		cores[7] = new Color(0);
		cores[8] = new Color(0);
		cores[9] = new Color(0);

		int miR = 0, miG = 0, miB = 0, temp = 0, S = 0;
		double r = 0, g = 0, b = 0;
		//X
		double rX = 0, gX = 0, bX = 0;
		//Y
		double rY = 0, gY = 0, bY = 0;
		int ri = 0, gi = 0, bi = 0, p = 1;
		double varR = 0, varG = 0, varB = 0; 
		int lum = 0;

		int[] filtroX = new int[9+1];
		filtroX[1] = 1;
		filtroX[2] = 2;
		filtroX[3] = 1;
		
		filtroX[4] = 0;
		filtroX[5] = 0;
		filtroX[6] = 0;
		
		filtroX[7] = -1;
		filtroX[8] = -2;
		filtroX[9] = -1;
		
		int[] filtroY = new int[9+1];
		filtroY[1] = -1;
		filtroY[2] = 0;
		filtroY[3] = 1;
		
		filtroY[4] = -2;
		filtroY[5] = 0;
		filtroY[6] = 2;
		
		filtroY[7] = -1;
		filtroY[8] = 0;
		filtroY[9] = 1;
		
		for (int i = 0; i < img.getWidth(); i ++) {
			for (int j = 0; j < img.getHeight(); j ++) {
				Color cor = new Color(img.getRGB(i, j));
				/*
				 * r = (int) cor.getRed(); g = (int) cor.getGreen(); b = (int)
				 * cor.getBlue();
				 */
				if ((i > 1 && j > 1) && (i < img.getWidth() - 1)
						&& (j < img.getHeight() - 1)) {

					// Red
					//k - abscissas
					//k1 - ordenadas
					//miR - Media do vermelho

					for (int k = -1; k <= 1; k++) {
						for (int k2 = 1; k2 >= -1 ; k2--) {
							//filtro X
							cores[p] = new Color(img.getRGB(k + i, k2 + j));
							rX += cores[p].getRed() * filtroX[p];
							
							//filtro Y
							cores[p] = new Color(img.getRGB(k + i, k2 + j));
							rY += cores[p].getRed() * filtroY[p];
							
							//System.out.println(cores[p].getRed());
							p++;
						}
					}
					p = 1;
					
					
					r = Math.pow(2, rX) + Math.pow(2, rY);
					g = Math.pow(2, gX) + Math.pow(2, gY);
					b = Math.pow(2, bX) + Math.pow(2, bY);
					// Setando a imagem
					//Binarização
					ri = (int) r;
					
					if(ri < 0){
						img.setRGB(i, j, new Color(0, 0, 0).getRGB());
					}else if( ri > 255){
						img.setRGB(i, j, new Color(255, 255, 255).getRGB());
					}else {
						img.setRGB(i, j, new Color(ri, ri, ri).getRGB());
					}
					
					r = 0;
					g = 0;
					b = 0;
				}

				// img.setRGB(i, j, new Color(r, g, b).getRGB());
			}
		}
		arrayImage = null;
		return img;
	}

	
	public BufferedImage getAlgoritmoTeste(BufferedImage img){
		BufferedImage imgRed = new BufferedImage(img.getWidth(), img.getHeight(), 5);
		BufferedImage imgGreen = new BufferedImage(img.getWidth(), img.getHeight(), 5);
		BufferedImage imgBlue = new BufferedImage(img.getWidth(), img.getHeight(), 5);
		

		for (int i = 0; i < img.getWidth(); i ++) {//Percorre a imagem
			for (int j = 0; j < img.getHeight(); j ++) {

				Color color = new Color(img.getRGB(i, j));//pega a cor do pixel

				//Seta o img com a escala de cinza vermelha/verde/azul da imagem
				imgRed.setRGB(i, j, new Color(color.getRed(), color.getRed(), color.getRed()).getRGB());
				imgGreen.setRGB(i, j, new Color(color.getGreen(), color.getGreen(), color.getGreen()).getRGB());
				imgBlue.setRGB(i, j, new Color(color.getBlue(), color.getBlue(), color.getBlue()).getRGB());
			}
		}

		Color colorRed;
		Color colorGreen;
		Color colorBlue;
		
		getImagemBinariaAlgoritmo(imgRed);
		getImagemBinariaAlgoritmo(imgGreen);
		getImagemBinariaAlgoritmo(imgBlue);
		
		for (int i = 0; i < img.getWidth(); i ++) {//Percorre a imagem
			for (int j = 0; j < img.getHeight(); j ++) {
				colorRed = new Color(imgRed.getRGB(i, j));
				colorGreen = new Color(imgGreen.getRGB(i, j));
				colorBlue = new Color(imgBlue.getRGB(i, j));
				//As cores de vermelho verde e azul das imagens são as mesmas
				if(
						( (colorRed.getRed() == 0) && (colorGreen.getGreen() == 0) && (colorBlue.getBlue() == 0) )
						||
						( (colorRed.getRed() == 255) && (colorGreen.getGreen() == 0) && (colorBlue.getBlue() == 0) )
						||
						( (colorRed.getRed() == 0) && (colorGreen.getGreen() == 255) && (colorBlue.getBlue() == 0) )
						||
						( (colorRed.getRed() == 0) && (colorGreen.getGreen() == 0) && (colorBlue.getBlue() == 255) )
						||
						( (colorRed.getRed() == 255) && (colorGreen.getGreen() == 0) && (colorBlue.getBlue() == 255) )
												
						){
					img.setRGB(i, j, new Color(0, 0, 0).getRGB());
				}else if(
						
						( (colorRed.getRed() == 255) && (colorGreen.getGreen() == 255) && (colorBlue.getBlue() == 255) )
						||
						( (colorRed.getRed() == 255) && (colorGreen.getGreen() == 255) && (colorBlue.getBlue() == 0) )
						||
						( (colorRed.getRed() == 0) && (colorGreen.getGreen() == 255) && (colorBlue.getBlue() == 255) )
						
						){
					img.setRGB(i, j, new Color(255, 255, 255).getRGB());
					
				
				}
			}
		}

		
		
		
		return img;
	}
	
	
	public BufferedImage getReconhecimentoDeImagens(BufferedImage img){
		
		getEscalaCinza(img);
		
		getDesfoque(img);
		
		getImagemBinaria(img);
		
		
		
		return img;
	}
	
	
	public static void main(String[] args) throws IOException {
		// Escala de cinza e imagem binaria
		BufferedImage img = ImageIO.read(new File("images/lena.jpg"));
		ImageIcon imageIcon;
		Algorithm g = new Algorithm();
		
		
		
		

		/*imageIcon = new ImageIcon(img);

		JOptionPane.showMessageDialog(null, new JLabel(null, imageIcon,
				JLabel.LEFT));
		*/
		int[] v = new int[9+1];
		v[1] = -1;
		v[2] = -2;
		v[3] = -1;
		
		v[4] = 0;
		v[5] = 0;
		v[6] = 0;
		
		v[7] = 1;
		v[8] = 2;
		v[9] = 1;
		int nor = 1;
		//g.getEscalaCinza(img);
		imageIcon = new ImageIcon(g.getAlgoritmoTeste(img));

		JOptionPane.showMessageDialog(null, new JLabel(null, imageIcon,
				JLabel.LEFT));
		
		
		System.exit(0);
	}

}