package com.gaomin.crm.model;

import java.awt.Font;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

@Component("jFree")
public class JFree extends AbstractView{

	@SuppressWarnings("unchecked")
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		List<Map<String, Object>> mapList = (List<Map<String, Object>>) model
				.get("mapList");
		Map<String, Object> params = new HashMap<String, Object>();
		for (Map<String, Object> map : mapList) {
			String name = (String)map.get("name");
			Object orderMoney = ((BigDecimal)map.get("orderMoney")).intValue();
			params.put(name,orderMoney);
		}
		
		JFreeChart chart = createChart(createDataset(params));
		ChartUtilities.writeChartAsJPEG(response.getOutputStream(), chart, 500, 270);
		
	}

	private static PieDataset createDataset(Map<String, Object> map) {
		DefaultPieDataset localDefaultPieDataset = new DefaultPieDataset();
		
		for(Map.Entry<String, Object> entry: map.entrySet()){
			localDefaultPieDataset.setValue(entry.getKey(), (int)entry.getValue());
		}
		
		return localDefaultPieDataset;
	}

	private static JFreeChart createChart(PieDataset paramPieDataset) {
		JFreeChart localJFreeChart = ChartFactory.createPieChart3D(
				"客户贡献分析", paramPieDataset, true, true, false);
		PiePlot3D localPiePlot3D = (PiePlot3D) localJFreeChart.getPlot();
		localPiePlot3D.setDarkerSides(true);
		localPiePlot3D.setStartAngle(290.0D);
		localPiePlot3D.setDirection(Rotation.CLOCKWISE);
		localPiePlot3D.setForegroundAlpha(0.5F);
		localPiePlot3D.setNoDataMessage("No data to display");
		
		Font font = new Font("宋体", Font.PLAIN, 15);
		TextTitle txtTitle = null;
		txtTitle = localJFreeChart.getTitle();
		txtTitle.setFont(font);
		PiePlot pieplot = (PiePlot) localJFreeChart.getPlot();
		pieplot.setLabelFont(font);
		
		localJFreeChart.getLegend().setItemFont(font);
		
		return localJFreeChart;
	}
	

}
