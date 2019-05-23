package com.example.master.myapplication;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.formatter.ColumnChartValueFormatter;
import lecho.lib.hellocharts.formatter.SimpleColumnChartValueFormatter;
import lecho.lib.hellocharts.model.SubcolumnValue;

/**
 * @author MyApplication
 * @date 2019/5/23 14:44
 */
public class Column {
    private boolean hasLabels = false;
    private boolean hasLabelsOnlyForSelected = false;
    private ColumnChartValueFormatter formatter = new SimpleColumnChartValueFormatter();
    // TODO: consider Collections.emptyList()
    private List<SubcolumnValue> values = new ArrayList<SubcolumnValue>();

    public Column() {

    }

    public Column(List<SubcolumnValue> values) {
        setValues(values);
    }

    public Column(Column column) {
        this.hasLabels = column.hasLabels;
        this.hasLabelsOnlyForSelected = column.hasLabelsOnlyForSelected;
        this.formatter = column.formatter;

        for (SubcolumnValue columnValue : column.values) {
            this.values.add(new SubcolumnValue(columnValue));
        }
    }

    public void update(float scale) {
        for (SubcolumnValue value : values) {
            value.update(scale);
        }

    }

    public void finish() {
        for (SubcolumnValue value : values) {
            value.finish();
        }
    }

    public List<SubcolumnValue> getValues() {
        return values;
    }

    public Column setValues(List<SubcolumnValue> values) {
        if (null == values) {
            this.values = new ArrayList<SubcolumnValue>();
        } else {
            this.values = values;
        }
        return this;
    }

    public boolean hasLabels() {
        return hasLabels;
    }

    public Column setHasLabels(boolean hasLabels) {
        this.hasLabels = hasLabels;
        if (hasLabels) {
            this.hasLabelsOnlyForSelected = false;
        }
        return this;
    }

    /**
     * @see #setHasLabelsOnlyForSelected(boolean)
     */
    public boolean hasLabelsOnlyForSelected() {
        return hasLabelsOnlyForSelected;
    }

    /**
     * Set true if you want to show value labels only for selected value, works best when chart has
     * isValueSelectionEnabled set to true {@link lecho.lib.hellocharts.view.Chart#setValueSelectionEnabled(boolean)}.
     */
    public Column setHasLabelsOnlyForSelected(boolean hasLabelsOnlyForSelected) {
        this.hasLabelsOnlyForSelected = hasLabelsOnlyForSelected;
        if (hasLabelsOnlyForSelected) {
            this.hasLabels = false;
        }
        return this;
    }

    public ColumnChartValueFormatter getFormatter() {
        return formatter;
    }

    public Column setFormatter(ColumnChartValueFormatter formatter) {
        if (null != formatter) {
            this.formatter = formatter;
        }
        return this;
    }
}