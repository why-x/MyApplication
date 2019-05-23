package com.example.master.myapplication;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.listener.LineChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.util.ChartUtils;

/**
 * 简单折线线条的绘制
 */
public class SimpleLineActivity extends Activity {
    private LineChartView chart2;        //显示线条的自定义View
    private LineChartData data2;          // 折线图封装的数据类
    private int numberOfLines = 2;         //线条的数量
    private int maxNumberOfLines = 4;     //最大的线条数据
    private int numberOfPoints = 12;     //点的数量

    float[][] randomNumbersTab = new float[maxNumberOfLines][numberOfPoints];         //二维数组，线的数量和点的数量

    private boolean hasAxes = true;       //是否有轴，x和y轴
    private boolean hasAxesNames = true;   //是否有轴的名字
    private boolean hasLines = true;       //是否有线（点和点连接的线）
    private boolean hasPoints = true;       //是否有点（每个值的点）
    private ValueShape shape = ValueShape.CIRCLE;    //点显示的形式，圆形，正方向，菱形
    private boolean isFilled = false;                //是否是填充
    private boolean hasLabels = false;               //每个点是否有名字
    private boolean isCubic = false;                 //是否是立方的，线条是直线还是弧线
    private boolean hasLabelForSelected = false;       //每个点是否可以选择（点击效果）
    private boolean pointsHaveDifferentColor;           //线条的颜色变换
    private boolean hasGradientToTransparent = false;      //是否有梯度的透明


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_line);
        isCubic = getIntent().getBooleanExtra("isCubic", false);
        String title = getIntent().getStringExtra("title");
        TextView tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("" + title);
        initView2();
        initData2();
        initEvent2();
    }


    private void initView2() {
        chart2 = (LineChartView) findViewById(R.id.chart);
    }

    private void initData2() {
        // Generate some random values.
        generateValues();   //设置四条线的值数据
        generateData();    //设置数据

        // Disable viewport recalculations, see toggleCubic() method for more info.
        chart2.setViewportCalculationEnabled(false);

        chart2.setZoomType(ZoomType.HORIZONTAL);//设置线条可以水平方向收缩
        resetViewport();   //设置折线图的显示大小
    }

    private void initEvent2() {
        chart2.setOnValueTouchListener(new ValueTouchListener());

    }

    /**
     * 图像显示大小
     */
    private void resetViewport() {
        // Reset viewport height range to (0,100)
        final Viewport v = new Viewport(chart2.getMaximumViewport());
        v.bottom = 0;
        v.top = 100;
        v.left = 0;
        v.right = numberOfPoints - 1;
        chart2.setMaximumViewport(v);
        chart2.setCurrentViewport(v);
    }

    /**
     * 设置四条线条的数据
     */
    private void generateValues() {
        for (int i = 0; i < maxNumberOfLines; ++i) {
            for (int j = 0; j < numberOfPoints; ++j) {
                randomNumbersTab[i][j] = (float) Math.random() * 100f;
            }
        }
    }

    /**
     * 配置数据
     */
    private void generateData() {

        List<Line> lines = new ArrayList<Line>();
        for (int i = 0; i < numberOfLines; ++i) {

            List<PointValue> values = new ArrayList<PointValue>();
            for (int j = 0; j < numberOfPoints; ++j) {
                values.add(new PointValue(j, randomNumbersTab[i][j]));
            }


            Line line = new Line(values);
            line.setColor(ChartUtils.COLORS[i]);
            line.setShape(shape);// 点的形状
            line.setCubic(isCubic);// 直线 弧线
            line.setFilled(isFilled);// 是否填充
            line.setHasLabels(hasLabels);// 每个点是否有名字
            line.setHasLabelsOnlyForSelected(hasLabelForSelected);// 是否有点击效果
            line.setHasLines(hasLines);// 是否有线点与点之间
            line.setHasPoints(hasPoints);// 是否有点 每个值
            // line.setHasGradientToTransparent(hasGradientToTransparent);// 是否有梯度的透明
            if (pointsHaveDifferentColor) {
                line.setPointColor(ChartUtils.COLORS[(i + 1) % ChartUtils.COLORS.length]);
            }
            lines.add(line);
        }

        data2 = new LineChartData(lines);

        if (hasAxes) {
            Axis axisX = new Axis();
            Axis axisY = new Axis().setHasLines(true);
            if (hasAxesNames) {
                axisX.setTextColor(Color.BLACK);
                axisY.setTextColor(Color.BLACK);
                axisX.setName("Axis X");
                axisY.setName("Axis Y");
            }
            data2.setAxisXBottom(axisX);
            data2.setAxisYLeft(axisY);
        } else {
            data2.setAxisXBottom(null);
            data2.setAxisYLeft(null);
        }

        data2.setBaseValue(Float.NEGATIVE_INFINITY);
        chart2.setLineChartData(data2);

    }

    /**
     * 触摸监听类
     */
    private class ValueTouchListener implements LineChartOnValueSelectListener {

        @Override
        public void onValueSelected(int lineIndex, int pointIndex, PointValue value) {
            Toast.makeText(SimpleLineActivity.this, "Selected: " + value, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onValueDeselected() {


        }

    }

}