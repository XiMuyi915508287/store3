/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.ui.common;

import com.store.util.DateUtil;
import java.awt.Component;
import java.awt.Dimension;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author chenjingjun
 */
public class DateTableCellRenderer extends DefaultTableCellRenderer{
        @Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                Date date = (Date)value;
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                setText(DateUtil.formatYMDHMSChinese(date));
		return this;
	}
}
