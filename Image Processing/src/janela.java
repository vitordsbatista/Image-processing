import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.statistics.HistogramType;

//Classe para a exibição dos algoritmos
public class janela extends JFrame implements ItemListener{
	
	private JComboBox algoritmos = new JComboBox();
	private JLabel image = new JLabel();	
	private	JScrollPane scrollPane;
	private BufferedImage bimg;    //armazena a imagem 
	public janela() throws IOException{
		//Configurações da janela
		super("agoritmos");
		JPanel pane = new JPanel((LayoutManager) new FlowLayout(FlowLayout.CENTER, 0, 0) );
		setContentPane(pane);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());  
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(null); 
        
        
        
        // -- Conf do Combo box
		algoritmos.addItem("-- Escolha um algoritmo --");
		algoritmos.addItem("Escala de cinza");
		algoritmos.addItem("Binaria");
		algoritmos.addItem("Desfoque de caixa");
		algoritmos.addItem("Negativo");
		algoritmos.addItem("Binarização com media");
		algoritmos.addItem("Desfoque Gaussiano");
		algoritmos.addItem("Borda de Sobel");
		algoritmos.setBounds(10, 10, 230, 20);
		algoritmos.addItemListener(this);
		
		//Conf da imagem
		bimg = ImageIO.read(new File("images/lena.jpg"));
        image.setBounds(10, 10, 600, 600);
		image.setIcon(new ImageIcon(bimg));  
		 
		//Conf do scrollPane
		scrollPane = new JScrollPane();
		scrollPane.getViewport().add( image );
		scrollPane.setBounds(10, 40, 500, 500);
		
		//configurações dos Hitogramas
		
		//Vermelho
		//ChartPanel CPRed = new ChartPanel(hist(bimg, "Red", 0));
		//CPRed.setBounds(650, 10, 600, 600);
		
		//Verde
		//ChartPanel CPGreen = new ChartPanel(hist(bimg, "Green", 1));
		//CPGreen.setBounds(650, 10, 100, 100);
		/*
		//Azul
		ChartPanel CPBlue = new ChartPanel(hist(bimg, "Blue", 2));
		CPBlue.setBounds(650, 110, 100, 100);
		
		//Alpha
		ChartPanel CPAlpha = new ChartPanel(hist(bimg, "Alpha", 3));
		CPAlpha.setBounds(650, 220, 100, 100);
		*/
		
		//Adicionando ao painel
		//add(CPRed);
		
		//add(CPGreen);
		//add(CPBlue);
		//add(CPAlpa);
		
		add(algoritmos);
		add(scrollPane);
	}
	
	//Histograma da imagem
	public JFreeChart hist(BufferedImage imagem, String plotTitle, int colorNumber){
		
		//chama a classe histograma com os valores na matriz
		Histograma h = new Histograma();
		//Cria um dataset (JFreeChart)
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		//Nome da serie
		String serie = "Número de pixels";
		
		/**
		 * Verifica de qual cor será realizado o histograma
		 * 0 - Vermelho
		 * 1 - Verde
		 * 2 - Azul
		 * 3 - Alpha
		 */
		
		if(colorNumber == 0){
			for (int i = 0; i < 256; i++){
	            dataset.addValue(h.histograma(imagem)[0][i], serie, "" + i);
	        }
		}else if(colorNumber == 1){
			for (int i = 0; i < 256; i++){
	            dataset.addValue(h.histograma(imagem)[1][i], serie, "" + i);
	        }
		}else if(colorNumber == 2){
			for (int i = 0; i < 256; i++){
	            dataset.addValue(h.histograma(imagem)[2][i], serie, "" + i);
	        }
		}else if(colorNumber == 3){
			for (int i = 0; i < 256; i++){
	            dataset.addValue(h.histograma(imagem)[3][i], serie, "" + i);
	        }
		}
		
		//Outras configurações do grafico (ver documentação JFreeChart)
        String xaxis = null;
        String yaxis = null; 
        PlotOrientation orientation = PlotOrientation.VERTICAL; 
        boolean show = false; 
        boolean toolTips = false;
        boolean urls = false; 
        
        //Adiciona ao chart
        JFreeChart chart = ChartFactory.createBarChart(plotTitle, xaxis, yaxis, 
                 dataset, orientation, show, toolTips, urls);
		
		return chart;
	}
	//Metodo Principal
	public static void main(String[] args) throws IOException  {
		janela j = new janela();

	}
	//Eventos do JComboBox (algoritmos)
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		//chamada do metodo com os algoritmos
		algorithm alg = new algorithm();
		
		//Configurações da exibição da imagem e escolha dos algoritmos (funciona mas mode melhorar).
		BufferedImage img = bimg;
		//Escolha dos algoritmos
		if (e.getSource() == algoritmos){
			if(algoritmos.getSelectedItem() == "Escala de cinza"){
				//image.setIcon(new ImageIcon());
				alg.getEscalaCinza(bimg);
			}else if(algoritmos.getSelectedItem() == "Binaria"){
				alg.getImagemBinaria(bimg);
			}else if(algoritmos.getSelectedItem() == "Desfoque de caixa"){
				alg.getDesfoque(img);
			}else if(algoritmos.getSelectedItem() == "Negativo"){
				
			}else if(algoritmos.getSelectedItem() == "Binarização com media"){
				alg.getBinarizaçaoPorMedia(bimg);
			}else if(algoritmos.getSelectedItem() == "Desfoque Gaussiano"){

			}else if(algoritmos.getSelectedItem() == "Borda de Sobel"){

			}
		} 
	}
}